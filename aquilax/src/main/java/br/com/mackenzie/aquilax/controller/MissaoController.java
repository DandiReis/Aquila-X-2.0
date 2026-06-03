package br.com.mackenzie.aquilax.controller;

import br.com.mackenzie.aquilax.model.Missao;
import br.com.mackenzie.aquilax.model.Drone;
import br.com.mackenzie.aquilax.repository.DroneRepository;
import br.com.mackenzie.aquilax.repository.MissaoRepository;
import br.com.mackenzie.aquilax.service.MissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/missoes")
@CrossOrigin(origins = "*") // <-- ADICIONE ESTA LINHA EXATAMENTE AQUI!
public class MissaoController {

    @Autowired
    private MissaoRepository missaoRepository;

    @Autowired
    private MissaoService missaoService;

    @Autowired
    private DroneRepository droneRepository; // Organizado junto com os outros @Autowired

    @PostMapping("/iniciar")
    public String criarMissao(@RequestBody Missao request) {
        // Chama o serviço que salva no MySQL e no MongoDB Atlas
        missaoService.iniciarMissaoMilitar(
            request.getObjetivo(), 
            request.getIdOperador(), 
            request.getIdDrone()
        );
        return "🚀 [SISTEMA] Comando de decolagem enviado com sucesso para os bancos!";
    }

    @GetMapping("/listar")
    public List<Missao> listarTodas() {
        return missaoRepository.findAll();
    }

    @GetMapping("/frota-disponivel")
    public List<Drone> listarFrotaPronta() {
        return droneRepository.findByStatus(1);
    }

    @PostMapping("/evasao")
    public ResponseEntity<String> dispararEvasao() {
        missaoService.acionarEvasaoTatica();
        System.out.println("🚨 [API] Endpoint de evasão acionado pelo operador!");
        return ResponseEntity.ok("Protocolo de evasão iniciado com sucesso.");
    }
}