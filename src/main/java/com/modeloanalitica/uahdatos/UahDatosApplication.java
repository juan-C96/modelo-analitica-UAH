package com.modeloanalitica.uahdatos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@SpringBootApplication
public class UahDatosApplication {

	public static void main(String[] args) {
		SpringApplication.run(UahDatosApplication.class, args);
	}
}
