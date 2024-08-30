package com.openclassrooms.medilabo.risks.services;

import com.openclassrooms.medilabo.risks.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


//@FeignClient(name = "patient", url = "http://localhost:8081")
@FeignClient(name = "patient", url = "http://patient-service:8081/")
public interface PatientServiceProxy {

    @GetMapping(value = "/patient")
    PatientBean getAllPatients();

    @GetMapping( value = "/patient/{id}")
    PatientBean getPatientById(@PathVariable("id") int id);

    @PostMapping("/patient/add")
    PatientBean addPatient(@RequestBody PatientBean patientDTO);

    @PostMapping("/patient/{id}/update")
    PatientBean updatePatient(@RequestBody PatientBean patientDTO, @PathVariable("id") int id);

}
