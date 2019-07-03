package com.project.nms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication(scanBasePackages={"com.project.nms.repository", "com.project.nms.controller","com.project.nms.service","com.project.nms.config"}
)
@EnableJpaAuditing
public class NmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NmsApplication.class, args);
	}

}
