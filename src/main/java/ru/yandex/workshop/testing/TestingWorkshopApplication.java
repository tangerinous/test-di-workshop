package ru.yandex.workshop.testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class TestingWorkshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestingWorkshopApplication.class, args);
	}

}
