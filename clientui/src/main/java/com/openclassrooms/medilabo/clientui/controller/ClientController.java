package com.openclassrooms.medilabo.clientui.controller;

import com.openclassrooms.medilabo.clientui.beans.PatientBean;
import com.openclassrooms.medilabo.clientui.proxies.MicroservicePatientProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
