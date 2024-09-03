package com.openclassrooms.medilabo.clientui.controller;

import com.openclassrooms.medilabo.clientui.beans.NoteBean;
import com.openclassrooms.medilabo.clientui.beans.PatientBean;
import com.openclassrooms.medilabo.clientui.proxies.MicroservicePatientProxy;
import com.openclassrooms.medilabo.clientui.proxies.MicroserviceRiskProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClientController {

    private final MicroservicePatientProxy patientProxy;

    private final MicroserviceRiskProxy riskProxy;

    @Autowired
    private NoteController noteController;

    public ClientController(MicroservicePatientProxy patientProxy, MicroserviceRiskProxy riskProxy) {
        this.patientProxy = patientProxy;
        this.riskProxy = riskProxy;
    }

    /**
     * Retrieves the list of all patients and adds it to the model for display.
     *
     * @param model
     * @return list of patients
     */
    @GetMapping("/patient")
    public String getAllPatients(Model model){

        ResponseEntity<List<PatientBean>> responseEntity = patientProxy.getAllPatients();
        List<PatientBean> patientsList = responseEntity.getBody();

        model.addAttribute("patientsList", patientsList);

        return "patients";

    }

    /**
     *  Retrieves a patient's details by their ID, along with their notes and risk report,
     *  and adds them to the model for display.
     *
     * @param model model
     * @param id The ID of the patient
     * @return patient's details
     */
    @GetMapping("/patient/{id}")
    public String getPatientById(Model model, @PathVariable int id){

        ResponseEntity<PatientBean> responseEntity = patientProxy.getPatientById(id);
        PatientBean patientFound = responseEntity.getBody();
        List<NoteBean> notes = noteController.getNotes(model,id);
        String riskReport = riskProxy.getReportRisk(id);

        model.addAttribute("notes", notes);
        model.addAttribute("patientFound", patientFound);
        model.addAttribute("riskReport", riskReport);

        return "patient";

    }

    /**
     * Displays the form for adding a new patient.
     *
     * @param model model
     * @return view for adding a new patient
     */
    @GetMapping("/patient/add")
    public String addPatientForm(Model model) {

        model.addAttribute("patientDTO", new PatientBean());

        return "addPatient";
    }

    /**
     *  Handles the submission of the form for adding a new patient
     *
     * @param patientDTO PatientBean
     * @param model model
     * @return redirection to the patient list
     */
    @PostMapping("/patient/add")
    public String addPatient(@ModelAttribute PatientBean patientDTO, Model model) {

        patientProxy.addPatient(patientDTO);

        return "redirect:/patient";
    }

    /**
     * Displays the form for updating an existing patient's details.
     *
     * @param model model
     * @param id The ID of the patient
     * @return view that displays the form for updating the patient's details
     */
    @GetMapping("/patient/{id}/update")
    public String updateForm(Model model, @PathVariable int id) {
        ResponseEntity<PatientBean> responseEntity = patientProxy.getPatientById(id);
        PatientBean patientFound = responseEntity.getBody();

        model.addAttribute("patientFound", patientFound);

        return "updatePatient";
    }

    /**
     * Handles the submission of the form to update an existing patient's details
     *
     * @param patientDTO PatientBean
     * @param id The ID of the patient
     * @return redirection to the patient list
     */
    @RequestMapping(value = "/patient/{id}/update", method = RequestMethod.PUT)
    public String updatePatient(@ModelAttribute PatientBean patientDTO, @PathVariable("id") int id){

        patientProxy.updatePatient(patientDTO, id);

        return "redirect:/patient";
    }

}
