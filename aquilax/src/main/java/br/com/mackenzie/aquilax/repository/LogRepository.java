package br.com.mackenzie.aquilax.repository;

import br.com.mackenzie.aquilax.model.LogMissao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<LogMissao, String> {
    // O Spring detecta que esse é pro MongoDB por causa do "MongoRepository"
}
