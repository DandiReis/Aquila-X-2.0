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

	@Bean
	public CommandLineRunner demo(MissaoService service) {
		return args -> {
			System.out.println("\n--- INICIANDO TESTE DE SISTEMA MILITAR ---");
			service.iniciarMissaoMilitar("Vigilância de Perímetro Mackenzie", 1, 1);
			System.out.println("--- TESTE FINALIZADO COM SUCESSO ---\n");
		};
	}
}