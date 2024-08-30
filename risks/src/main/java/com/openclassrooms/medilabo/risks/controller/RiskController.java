package com.openclassrooms.medilabo.risks.controller;

import com.openclassrooms.medilabo.risks.services.RiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/riskReport")
public class RiskController {

    @Autowired
    private RiskService service;

    /**
     *  Retrieves the risk report for a specific patient
     *
     * @param id ID of the patient
     * @return A string containing the risk report for the specified patient
     */
    @GetMapping("{id}")
    public String getReportRisk(@PathVariable("id") int id) {
        return service.evaluateRiskForPatient(id);
    }

}
