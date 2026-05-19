package br.com.mackenzie.aquilax.service;
import br.com.mackenzie.aquilax.model.Drone;
import br.com.mackenzie.aquilax.model.Missao;
import br.com.mackenzie.aquilax.model.Telemetria;
import br.com.mackenzie.aquilax.repository.DroneRepository;
import br.com.mackenzie.aquilax.repository.LogRepository;
import br.com.mackenzie.aquilax.repository.MissaoRepository;

import java.util.concurrent.CompletableFuture;

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

        // EXIGÊNCIA 1: Log de decolagem inicial no MongoDB Atlas usando sua classe Telemetria
        Telemetria logInicial = new Telemetria(idDrone, 100.0, "Missão Iniciada.", 0.0);
        logRepository.save(logInicial);
        System.out.println("☁️ [MongoDB] Inicialização tática gravada.");

        // Dispara a simulação de voo assíncrona para não travar a requisição do front
        CompletableFuture.runAsync(() -> {
            simularVoo(idDrone);
        });
    }

    public void simularVoo(Integer idDrone) {
        System.out.println("🛰️ [SISTEMA] Iniciando ciclo de monitoramento ativo...");
        
        double bateria = 100.0;
        boolean problemaDetectado = false;

        // O voo vai acontecer em 5 etapas táticas com pausas de tempo reais
        for (int i = 1; i <= 5; i++) {
            try {
                Thread.sleep(2000); // Aguarda 2 segundos entre cada checagem de telemetria
                
                bateria -= (Math.random() * 10) + 5; // Consumo dinâmico de carga
                String mensagemLog = "Voo em curso - Quadrante " + i;

                // EXIGÊNCIA 2: Checagem de integridade (Simula pane ou bateria baixa na etapa 3)
                if (i == 3 && (bateria < 75 || Math.random() > 0.4)) {
                    problemaDetectado = true;
                    mensagemLog = "Frota com probelma retornando a base.";
                    
                    // Salva o log de erro imediatamente no Atlas e força a quebra do loop de avanço
                    Telemetria logErro = new Telemetria(idDrone, bateria, mensagemLog, 0.0);
                    logRepository.save(logErro);
                    System.out.println("⚠️ [ALERTA] Rota de emergência acionada!");
                    
                    Thread.sleep(2500); // Tempo do drone voar de volta sob pane
                    break; 
                }

                // Salva a telemetria padrão se o voo estiver normal
                Telemetria t = new Telemetria(idDrone, bateria, mensagemLog, 50.0 + i);
                logRepository.save(t); 
                System.out.println("📍 Telemetria #" + i + " sincronizada no Atlas.");

            } catch (InterruptedException e) {
                System.out.println("❌ Erro crítico no clock de simulação.");
            }
        }

        // EXIGÊNCIA 3: Finalização segura do ciclo e retorno ao hangar da Barra Funda
        try {
            Thread.sleep(1500);
            Telemetria logFinal = new Telemetria(idDrone, bateria, "missão encerrada", 0.0);
            logRepository.save(logFinal);
            System.out.println("🏁 [SISTEMA] Ciclo de voo finalizado com sucesso.");
        } catch (InterruptedException e) {
            e.printStackTrace();
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
   