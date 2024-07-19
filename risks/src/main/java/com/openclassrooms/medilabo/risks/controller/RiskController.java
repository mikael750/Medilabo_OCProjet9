package com.openclassrooms.medilabo.risks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/riskReport")
public class RiskController {

    @Autowired
    // service;

    @GetMapping("/riskReport/{id}")
    public String getReportRisk(@PathVariable("id") int id) {

        return null;
    }

}
