package com.openclassrooms.medilabo.clientui.proxies;

import com.openclassrooms.medilabo.clientui.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "patient", url = "http://localhost:8080/")
public interface MicroservicePatientProxy {
    @GetMapping(value = "/patient")
    ResponseEntity<List<PatientBean>> getAllPatients();

    @GetMapping( value = "/patient/{id}")
    ResponseEntity<PatientBean> getPatientById(@PathVariable("id") int id);

}
