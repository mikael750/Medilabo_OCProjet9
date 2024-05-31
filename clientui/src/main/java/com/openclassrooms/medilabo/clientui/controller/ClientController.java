package com.openclassrooms.medilabo.clientui.controller;

import com.openclassrooms.medilabo.clientui.beans.PatientBean;
import com.openclassrooms.medilabo.clientui.proxies.MicroservicePatientProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClientController {

    private final MicroservicePatientProxy patientProxy;

    public ClientController(MicroservicePatientProxy patientProxy) {
        this.patientProxy = patientProxy;
    }

    @RequestMapping("/")
    public String accueil(Model model){
        ResponseEntity<List<PatientBean>> responseEntity = patientProxy.getAllPatients();
        List<PatientBean> patientsList = responseEntity.getBody();
        model.addAttribute("patients", patientsList);
        return "Accueil";
    }

    /**
     * @param model
     * @return
     */
    @GetMapping("/patient")
    public String getAllPatients(Model model){

        ResponseEntity<List<PatientBean>> responseEntity = patientProxy.getAllPatients();
        List<PatientBean> patientsList = responseEntity.getBody();

        model.addAttribute("patientsList", patientsList);

        return "patients";

    }

    @GetMapping("/patient/{id}")
    public String getPatientById(Model model, @PathVariable int id){

        ResponseEntity<PatientBean> responseEntity = patientProxy.getPatientById(id);
        PatientBean patientFound = responseEntity.getBody();

        model.addAttribute("patientFound", patientFound);

        return "patient";

    }

    @GetMapping("/patient/add")
    public String addPatientForm(Model model) {

        model.addAttribute("patientDTO", new PatientBean());

        return "addPatient";
    }

    @PostMapping("/patient/add")
    public String addPatient(@ModelAttribute PatientBean patientDTO, Model model) {

        ResponseEntity<PatientBean> responseEntity = patientProxy.addPatient(patientDTO);
        PatientBean patientAdded = responseEntity.getBody();

        return "redirect:/patient";
    }
}
