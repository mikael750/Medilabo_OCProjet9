package com.openclassrooms.medilabo.clientui.proxies;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "patient", url = "localhost:8081")
public interface MicroservicePatientProxy {



}
