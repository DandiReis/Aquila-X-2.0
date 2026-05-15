package br.com.mackenzie.aquilax.service;
import br.com.mackenzie.aquilax.model.Drone;
import br.com.mackenzie.aquilax.model.Missao;
import br.com.mackenzie.aquilax.model.Telemetria;
import br.com.mackenzie.aquilax.repository.DroneRepository;
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

    @Autowired
    private DroneRepository droneRepository;

    @Transactional
    public void iniciarMissaoMilitar(String objetivo, int idOperador, int idDrone) {
        
        validarMissao(objetivo, idDrone);
        Missao missao = new Missao(objetivo, idOperador, idDrone);
        missaoRepository.save(missao);
        System.out.println("✅ [MySQL] Missão persistida com ID: " + missao.getIdMissao());
        Telemetria logInicial = new Telemetria(idDrone, 100.0, "PONTO_PARTIDA", 0.0);
        logRepository.save(logInicial);
        System.out.println("☁️ [MongoDB] Log tático sincronizado com a nuvem.");
        simularVoo(idDrone);
    }

    public void simularVoo(Integer idDrone) {
        System.out.println("🛰️ [SISTEMA] Iniciando recepção de telemetria...");
    
        for (int i = 1; i <= 5; i++) {
            Telemetria t = new Telemetria(idDrone, 100.0 - (i*2), "coord-" + i, 50.0 + i);
            logRepository.save(t); 
        
            System.out.println("📍 Telemetria #" + i + " salva no MongoDB Atlas!");
        }
    }


    public void validarMissao(String objetivo, int idDrone) {
    // 1. Regra: Objetivo não pode ser curto demais
        if (objetivo == null || objetivo.length() < 5) {
            throw new RuntimeException("ALERTA: Objetivo muito vago! Solicitar correção dos dados.");
        }

    // 2. Regra: Verificar Prontidão do Drone
        Drone drone = droneRepository.findById((long) idDrone)
            .orElseThrow(() -> new RuntimeException("ALERTA: Drone não localizado na frota!"));

         if (drone.getStatus() != 1) {
            throw new RuntimeException("ALERTA: Drone em manutenção! Selecione outro equipamento.");
        }
    
        System.out.println("✅ Parâmetros validados. Prontidão confirmada.");
    }
}
   