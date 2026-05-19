package br.com.mackenzie.aquilax.controller;

import br.com.mackenzie.aquilax.model.Operador;
import br.com.mackenzie.aquilax.model.Telemetria;
import br.com.mackenzie.aquilax.repository.LogRepository;
import br.com.mackenzie.aquilax.service.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AutenticacaoController {

    @Autowired
    private AutenticacaoService authService;

    // Injetamos o repositório do MongoDB aqui para salvar o log de acesso
    @Autowired
    private LogRepository logRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciais) {
        try {
            String registro = credenciais.get("registroOperador");
            String senha = credenciais.get("senha");
            
            Operador operador = authService.validarAcesso(registro, senha);
            return ResponseEntity.ok(operador);
            
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    // NOVO ENDPOINT: Grava a auditoria de login diretamente no MongoDB Atlas
    @PostMapping("/log-acesso")
    public ResponseEntity<?> salvarLogAcesso(@RequestBody Map<String, String> dadosLog) {
        try {
            String registro = dadosLog.get("registroOperador");
            String mensagem = "Operador " + registro + " autenticado com sucesso via segundo fator (MFA).";
            
            // Instancia a Telemetria adaptada para Log de Auditoria
            // Passamos idDrone como 0 (pois é um evento de sistema), a mensagem no campo de texto e 0.0 na bateria/velocidade
            Telemetria logAcesso = new Telemetria(0, 100.0, mensagem, 0.0);
            
            // Salva fisicamente no cluster do MongoDB Atlas
            logRepository.save(logAcesso);
            
            System.out.println("🛡️ [MONGO] Auditoria de segurança gravada para o operador: " + registro);
            return ResponseEntity.ok().body("{\"status\": \"Log gravado no Atlas!\"}");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao salvar log: " + e.getMessage());
        }
    }
}