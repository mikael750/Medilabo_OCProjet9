package com.openclassrooms.medilabo.clientui.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "risks", url = "${api.gateway.url}/report-risk")
public interface MicroserviceRiskProxy {

    @GetMapping(value = "/riskReport/{id}")
    String getReportRisk(@PathVariable("id") int id);
}
