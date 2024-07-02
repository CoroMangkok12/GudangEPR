package com.epr.eprdemofe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EprdemofeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EprdemofeApplication.class, args);

		System.out.println("\nEPR FE is running...\n");
	}

	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
