package br.com.mackenzie.aquilax.controller;

import br.com.mackenzie.aquilax.model.Operador;
import br.com.mackenzie.aquilax.model.LogAuditoria;
import br.com.mackenzie.aquilax.repository.LogAuditoriaRepository;
import br.com.mackenzie.aquilax.service.AutenticacaoService; // <-- Importa o Service que vamos criar
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AutenticacaoController {

    @Autowired
    private AutenticacaoService authService; // <-- Injeta o Service real aqui!

    @Autowired
    private LogAuditoriaRepository logAuditoriaRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciais) {
        try {
            String registro = credenciais.get("registroOperador");
            String senha = credenciais.get("senha");
            
            // 1. O service valida no MySQL
            Operador operador = authService.validarAcesso(registro, senha);
            
            // 2. Com o login validado, grava no MongoDB Atlas
            LogAuditoria logAcesso = new LogAuditoria(registro, "Autenticação bem-sucedida via segundo fator (MFA).");
            logAuditoriaRepository.save(logAcesso);
            
            System.out.println("🛡️ [MONGO] Auditoria de login registrada para o operador: " + registro);
            
            return ResponseEntity.ok(operador);
            
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}