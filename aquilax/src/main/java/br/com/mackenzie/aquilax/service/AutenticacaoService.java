package br.com.mackenzie.aquilax.service;

import br.com.mackenzie.aquilax.model.Operador;
import br.com.mackenzie.aquilax.repository.OperadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // Etiqueta para o Spring reconhecer como um Serviço
public class AutenticacaoService {

    @Autowired
    private OperadorRepository operadorRepository;
    
    public Operador validarAcesso(String registro, String senha) {
        // Vai ao MySQL buscar o operador pelo registro e checa a senha
        return operadorRepository.findByRegistroOperador(registro)
                .filter(op -> op.getSenha().equals(senha))
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas! Alerta de Erro emitido.")); 
    }
}