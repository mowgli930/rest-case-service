package se.plushogskolan.restcaseservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import se.plushogskolan.casemanagement.config.InfrastructureConfig;

@SpringBootApplication
@Import(InfrastructureConfig.class)
@ComponentScan(basePackages = {"se.plushogskolan.casemanagement", "se.plushogskolan.restcaseservice"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
