package br.com.mackenzie.aquilax;

import br.com.mackenzie.aquilax.service.MissaoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AquilaxApplication {

	public static void main(String[] args) {
		SpringApplication.run(AquilaxApplication.class, args);
	}

}