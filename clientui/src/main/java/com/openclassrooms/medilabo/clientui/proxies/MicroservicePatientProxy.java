package com.openclassrooms.medilabo.clientui.proxies;

import com.openclassrooms.medilabo.clientui.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "patient", url = "http://gateway:8080/")
public interface MicroservicePatientProxy {
    @GetMapping(value = "/patient-service/patient")
    ResponseEntity<List<PatientBean>> getAllPatients();

    @GetMapping( value = "/patient-service/patient/{id}")
    ResponseEntity<PatientBean> getPatientById(@PathVariable("id") int id);

    @PostMapping("/patient-service/patient/add")
    ResponseEntity<PatientBean> addPatient(@RequestBody PatientBean patientDTO);

    @PostMapping("/patient-service/patient/{id}/update")
    ResponseEntity<PatientBean> updatePatient(@RequestBody PatientBean patientDTO, @PathVariable("id") int id);

}
