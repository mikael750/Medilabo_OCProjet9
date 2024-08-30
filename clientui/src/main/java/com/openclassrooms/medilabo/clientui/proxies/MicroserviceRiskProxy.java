package com.openclassrooms.medilabo.clientui.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "risks", url = "http://report-risk:8084/")
public interface MicroserviceRiskProxy {

    @GetMapping(value = "/riskReport/{id}")
    String getReportRisk(@PathVariable("id") int id);
}
