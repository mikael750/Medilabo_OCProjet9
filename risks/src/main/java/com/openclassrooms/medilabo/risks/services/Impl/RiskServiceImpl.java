package com.openclassrooms.medilabo.risks.services.Impl;

import com.openclassrooms.medilabo.risks.beans.NoteBean;
import com.openclassrooms.medilabo.risks.beans.PatientBean;
import com.openclassrooms.medilabo.risks.services.NoteServiceProxy;
import com.openclassrooms.medilabo.risks.services.PatientServiceProxy;
import com.openclassrooms.medilabo.risks.services.RiskService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
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

    private static final Logger logger = LoggerFactory.getLogger(RiskServiceImpl.class);

    @Autowired
    public RiskServiceImpl(PatientServiceProxy patientServiceProxy, NoteServiceProxy noteServiceProxy) {
        this.patientServiceProxy = patientServiceProxy;
        this.noteServiceProxy = noteServiceProxy;
    }

    @Override
    public String evaluateRiskForPatient(int patientId) {
        var patient = patientServiceProxy.getPatientById(patientId);
        var notes = noteServiceProxy.getNote(patientId);

        var ageRisk = new AgeBasedRiskEvaluationStrategy().evaluateRisk(Objects.requireNonNull(patient), notes);
        var symptomRisk = new SymptomBasedRiskEvaluationStrategy().evaluateRisk(patient, Objects.requireNonNull(notes));

        return riskReport(ageRisk,symptomRisk, patient.getGenre());
    }

    /**
     * Figures out the Risk of diabetes of the patient
     *
     * @param ageRisk ageRisk
     * @param symptomRisk symptomRisk
     * @return risk report
     */
    private String riskReport(String ageRisk, String symptomRisk, String gender) {

        if ((Objects.equals(ageRisk, "Over 30") && Integer.parseInt(symptomRisk) >= 8)
        || (Objects.equals(gender, "F") && Objects.equals(ageRisk, "Under 30") && Integer.parseInt(symptomRisk) >= 7)
        || (Objects.equals(gender, "M") && Objects.equals(ageRisk, "Under 30") && Integer.parseInt(symptomRisk) >= 5)) {
            return "Early onset";
        } else if((Objects.equals(ageRisk, "Over 30") && Integer.parseInt(symptomRisk) >= 6)
        || (Objects.equals(gender, "F") && Objects.equals(ageRisk, "Under 30") && Integer.parseInt(symptomRisk) >= 4)
        || (Objects.equals(gender, "M") && Objects.equals(ageRisk, "Under 30") && Integer.parseInt(symptomRisk) >= 3)){
            return "In Danger";
        } else if (Objects.equals(ageRisk, "Over 30") && Integer.parseInt(symptomRisk) >= 2 && Integer.parseInt(symptomRisk) <= 5){
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
            "hemoglobine a1c", "microalbumine", "taille", "poids",
            "fumeur", "fumeuse", "anormal", "cholesterol", "vertiges",
            "rechute", "reaction", "anticorps"
    );

    private static class SymptomBasedRiskEvaluationStrategy implements RiskEvaluationStrategy {
        @Override
        public String evaluateRisk(PatientBean patient, List<NoteBean> notes) {
            int symptomCount = 0;
            List<String> normalizedSymptoms = DIABETES_SYMPTOMS.stream()
                    .map(this::normalizeText).toList();

            logger.info("Normalized Symptoms List: {}", normalizedSymptoms);

            for (NoteBean note : notes) {
                String content = normalizeText(note.getNote());
                for (String symptom : normalizedSymptoms) {
                    if (content.contains(symptom)) {
                        logger.info("Symptom Detected: '{}' in Note: '{}'", symptom, content);
                        symptomCount++;
                    }
                }
            }

            logger.info("Total Symptoms Detected: {}", symptomCount);
            return String.valueOf(symptomCount);
        }

        /**
         * Delete the accents and converts them in minuscules
         *
         * @param text
         * @return
         */
        private String normalizeText(String text) {
            String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
            normalized = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
            return normalized;
        }

    }
}
