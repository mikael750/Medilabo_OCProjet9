package com.openclassrooms.medilabo.risks.services;

import com.openclassrooms.medilabo.risks.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "patient", url = "http://localhost:8081")
public interface PatientServiceProxy {

    @GetMapping(value = "/patient")
    ResponseEntity<List<PatientBean>> getAllPatients();

    @GetMapping( value = "/patient/{id}")
    ResponseEntity<PatientBean> getPatientById(@PathVariable("id") int id);

    @PostMapping("/patient/add")
    ResponseEntity<PatientBean> addPatient(@RequestBody PatientBean patientDTO);

    @PostMapping("/patient/{id}/update")
    ResponseEntity<PatientBean> updatePatient(@RequestBody PatientBean patientDTO, @PathVariable("id") int id);

}
