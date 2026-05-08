package br.com.mackenzie.aquilax.service;

import br.com.mackenzie.aquilax.model.LogMissao;
import br.com.mackenzie.aquilax.model.Missao;
import br.com.mackenzie.aquilax.repository.LogRepository;
import br.com.mackenzie.aquilax.repository.MissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MissaoService {

    @Autowired
    private MissaoRepository missaoRepository;

    @Autowired
    private LogRepository logRepository;

    @Transactional
    public void iniciarMissaoMilitar(String objetivo, int idOperador, int idDrone) {
        
        // 1. Criar a Missão usando o construtor que criamos na Model
        Missao missao = new Missao(objetivo, idOperador, idDrone);
        
        // 2. Salvar no MySQL (Dados Estruturados)
        missaoRepository.save(missao);
        System.out.println("✅ [MySQL] Missão persistida com ID: " + missao.getIdMissao());

        // 3. Criar o Log rico para o MongoDB Atlas (Auditoria)
        LogMissao log = new LogMissao(
            "MISSION_START", 
            idOperador, 
            idDrone, 
            objetivo, 
            "SISTEMAS_OPERACIONAIS_OK"
        );
        
        // 4. Salvar no MongoDB
        logRepository.save(log);
        System.out.println("☁️ [MongoDB] Log de auditoria enviado para a nuvem.");
    }
}