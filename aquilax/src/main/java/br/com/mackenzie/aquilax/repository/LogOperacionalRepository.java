package br.com.mackenzie.aquilax.repository;

import br.com.mackenzie.aquilax.model.LogOperacional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogOperacionalRepository extends MongoRepository<LogOperacional, String> {
}