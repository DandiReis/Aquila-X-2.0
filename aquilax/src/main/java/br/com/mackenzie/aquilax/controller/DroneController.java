package br.com.mackenzie.aquilax.controller;

import br.com.mackenzie.aquilax.model.Drone;
import br.com.mackenzie.aquilax.repository.DroneRepository; // Ajuste conforme seu pacote
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drones")
@CrossOrigin(origins = "*") // Garante que o navegador não barre a requisição por CORS
public class DroneController {

    @Autowired
    private DroneRepository droneRepository;

    @GetMapping
    public List<Drone> listarTodos() {
        return droneRepository.findAll();
    }
}