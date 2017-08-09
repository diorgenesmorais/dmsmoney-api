package com.dms.dmsmoneyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.dms.dmsmoneyapi.config.property.DmsmoneyApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(DmsmoneyApiProperty.class)
public class DmsmoneyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DmsmoneyApiApplication.class, args);
	}
}
