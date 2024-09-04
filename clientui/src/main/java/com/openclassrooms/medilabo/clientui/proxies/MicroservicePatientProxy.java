package com.openclassrooms.medilabo.clientui.proxies;

import com.openclassrooms.medilabo.clientui.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "patient", url = "${api.gateway.url}/patient-service")
public interface MicroservicePatientProxy {
    @GetMapping(value = "/patient")
    ResponseEntity<List<PatientBean>> getAllPatients();

    @GetMapping( value = "/patient/{id}")
    ResponseEntity<PatientBean> getPatientById(@PathVariable("id") int id);

    @PostMapping("/patient/add")
    ResponseEntity<PatientBean> addPatient(@RequestBody PatientBean patientDTO);

    @RequestMapping(value = "/patient/{id}/update", method = RequestMethod.PUT)
    ResponseEntity<PatientBean> updatePatient(@RequestBody PatientBean patientDTO, @PathVariable("id") int id);

}
