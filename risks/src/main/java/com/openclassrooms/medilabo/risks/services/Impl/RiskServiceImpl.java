package com.openclassrooms.medilabo.risks.services.Impl;

import com.openclassrooms.medilabo.risks.beans.NoteBean;
import com.openclassrooms.medilabo.risks.beans.PatientBean;
import com.openclassrooms.medilabo.risks.services.NoteServiceProxy;
import com.openclassrooms.medilabo.risks.services.PatientServiceProxy;
import com.openclassrooms.medilabo.risks.services.RiskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class RiskServiceImpl implements RiskService {

    @Autowired
    private final PatientServiceProxy patientServiceProxy;

    @Autowired
    private final NoteServiceProxy noteServiceProxy;

    @Autowired
    public RiskServiceImpl(PatientServiceProxy patientServiceProxy, NoteServiceProxy noteServiceProxy) {
        this.patientServiceProxy = patientServiceProxy;
        this.noteServiceProxy = noteServiceProxy;
    }

    @Override
    public String evaluateRiskForPatient(int patientId) {
        ResponseEntity<PatientBean> patient = patientServiceProxy.getPatientById(patientId);
        ResponseEntity<List<NoteBean>> notes = noteServiceProxy.getNote(patientId);

        String ageRisk = new AgeBasedRiskEvaluationStrategy().evaluateRisk(Objects.requireNonNull(patient.getBody()), notes.getBody());
        String symptomRisk = new SymptomBasedRiskEvaluationStrategy().evaluateRisk(patient.getBody(), Objects.requireNonNull(notes.getBody()));

        return riskReport(ageRisk,symptomRisk, patient.getBody().getGenre());
    }

    /**
     * Figures out the Risk of diabetes of the patient
     *
     * @param ageRisk ageRisk
     * @param symptomRisk symptomRisk
     * @return risk report
     */
    private String riskReport(String ageRisk, String symptomRisk, String gender) {

        if ((ageRisk == "Over 30" && Integer.parseInt(symptomRisk) >= 8)
        || (gender == "F" && ageRisk == "Under 30" && Integer.parseInt(symptomRisk) >= 7)
        || (gender == "M" && ageRisk == "Under 30" && Integer.parseInt(symptomRisk) >= 5)) {
            return "Early onset";
        } else if((ageRisk == "Over 30" && Integer.parseInt(symptomRisk) >= 6)
        || (gender == "F" && ageRisk == "Under 30" && Integer.parseInt(symptomRisk) >= 4)
        || (gender == "M" && ageRisk == "Under 30" && Integer.parseInt(symptomRisk) >= 3)){
            return "In Danger";
        } else if (ageRisk == "Over 30" && Integer.parseInt(symptomRisk) >= 2 && Integer.parseInt(symptomRisk) <= 5){
            return "Borderline";
        }

        return "None";
    }

    /**
     * Strategy Pattern
     */
    private interface RiskEvaluationStrategy {
        String evaluateRisk(PatientBean patient, List<NoteBean> notes);
    }

    private static class AgeBasedRiskEvaluationStrategy implements RiskEvaluationStrategy {
        @Override
        public String evaluateRisk(PatientBean patient, List<NoteBean> notes) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateOfBirth = LocalDate.parse(patient.dateNaissance, formatter);
            LocalDate currentDate = LocalDate.now();
            int age = Period.between(dateOfBirth, currentDate).getYears();
            if (age >= 30) {
                return "Over 30";
            }
            return "Under 30";
        }
    }

    private static final List<String> DIABETES_SYMPTOMS = Arrays.asList(
            "Hémoglobine A1C", "Microalbumine", "Taille", "Poids",
            "Fumeur, Fumeuse", "Anormal", "Cholestérol", "Vertiges",
            "Rechute", "Réaction", "Anticorps"
    );

    private static class SymptomBasedRiskEvaluationStrategy implements RiskEvaluationStrategy {
        @Override
        public String evaluateRisk(PatientBean patient, List<NoteBean> notes) {

            int symptomCount = 0;
            for (NoteBean note : notes) {
                String content = note.getNote();
                for (String symptom : DIABETES_SYMPTOMS) {
                    if (content.contains(symptom)) {
                        symptomCount++;
                    }
                }
            }

            return String.valueOf(symptomCount);
        }
    }
}
