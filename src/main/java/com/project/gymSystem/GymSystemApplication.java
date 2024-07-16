package com.project.gymSystem;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "GymSystem",
				description = "API responsavel pela gestão de uma academia",
				version = "1"
		)
)
public class GymSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymSystemApplication.class, args);
	}

}
