package br.com.mackenzie.aquilax.repository;

import br.com.mackenzie.aquilax.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DroneRepository extends JpaRepository<Drone, Long> {
    // Busca automática: SELECT * FROM drone WHERE status = 1
    List<Drone> findByStatus(int status);
}