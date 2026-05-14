package br.com.mackenzie.aquilax.controller;

import br.com.mackenzie.aquilax.model.Drone;
import br.com.mackenzie.aquilax.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/frota") // O caminho base agora é /api/frota
public class FrotaController {

    @Autowired
    private DroneRepository droneRepository;

    @GetMapping("/disponiveis")
    public List<Drone> listarDronesProntos() {
        // No teu banco, status 1 = Pronto para Missão
        return droneRepository.findByStatus(1);
    }
}