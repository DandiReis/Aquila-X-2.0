package br.com.mackenzie.aquilax.repository;

import br.com.mackenzie.aquilax.model.LogAuditoria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogAuditoriaRepository extends MongoRepository<LogAuditoria, String> {
}