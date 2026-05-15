package br.com.mackenzie.aquilax.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import br.com.mackenzie.aquilax.model.Telemetria;

public interface LogRepository extends MongoRepository<Telemetria, String> {
}