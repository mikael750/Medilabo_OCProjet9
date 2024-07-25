package com.openclassrooms.medilabo.risks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.openclassrooms.medilabo")
public class RisksApplication {

	public static void main(String[] args) {
		SpringApplication.run(RisksApplication.class, args);
	}

}
