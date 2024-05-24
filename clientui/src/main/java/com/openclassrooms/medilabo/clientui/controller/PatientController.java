package com.openclassrooms.medilabo.clientui.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Controller;

@Controller
@FeignClient(name = "patient", url = "http://localhost:8080/patient")
public class PatientController {
}
