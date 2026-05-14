package br.com.mackenzie.aquilax.repository;

import br.com.mackenzie.aquilax.model.Operador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface OperadorRepository extends JpaRepository<Operador, Long> {
    // O Spring vai criar a query mágica: SELECT * FROM operador WHERE registro_operador = ?
    Optional<Operador> findByRegistroOperador(String registroOperador);
}