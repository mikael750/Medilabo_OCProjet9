package com.openclassrooms.medilabo.risks.services;

import com.openclassrooms.medilabo.risks.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patient", url = "http://gateway:8080/")
public interface PatientServiceProxy {

    @GetMapping( value = "/patient-service/patient/{id}")
    PatientBean getPatientById(@PathVariable("id") int id);

}
