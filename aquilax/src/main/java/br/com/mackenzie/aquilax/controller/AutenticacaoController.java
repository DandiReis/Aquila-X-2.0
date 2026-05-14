package br.com.mackenzie.aquilax.controller;

import br.com.mackenzie.aquilax.model.Operador;
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciais) {
        try {
            String registro = credenciais.get("registroOperador");
            String senha = credenciais.get("senha");
            
            Operador operador = authService.validarAcesso(registro, senha);
            
            // Se chegou aqui, as credenciais são válidas (Acessar Sistema)
            return ResponseEntity.ok(operador);
            
        } catch (RuntimeException e) {
            // Se deu erro, emite o alerta conforme o diagrama
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}