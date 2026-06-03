package br.com.mackenzie.aquilax.service;

import br.com.mackenzie.aquilax.model.Drone;
import br.com.mackenzie.aquilax.model.Missao;
import br.com.mackenzie.aquilax.model.Telemetria;
import br.com.mackenzie.aquilax.model.LogOperacional;
import br.com.mackenzie.aquilax.repository.DroneRepository;
import br.com.mackenzie.aquilax.repository.LogRepository;
import br.com.mackenzie.aquilax.repository.MissaoRepository;
import br.com.mackenzie.aquilax.repository.LogOperacionalRepository;

import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MissaoService {

    @Autowired
    private MissaoRepository missaoRepository;

    @Autowired
    private LogRepository logRepository; // Repositório da Telemetria

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private LogOperacionalRepository logOperacionalRepository;

    // Removemos o @Autowired errado e usamos volatile para garantir a leitura correta entre threads assíncronas
    private volatile boolean evasaoAcionada = false;

    public void acionarEvasaoTatica() {
         this.evasaoAcionada = true;
    }

    @Transactional
    public void iniciarMissaoMilitar(String objetivo, int idOperador, int idDrone) {
        try {
            // Sempre reseta a flag para garantir que a nova missão comece normal
            this.evasaoAcionada = false;

            // Se falhar a validação (Drone em manutenção ou objetivo curto), cai no catch e nega a missão
            validarMissao(objetivo, idDrone);
            
            Missao missao = new Missao(objetivo, idOperador, idDrone);
            missaoRepository.save(missao);
            System.out.println("✅ [MySQL] Missão persistida com ID: " + missao.getIdMissao());

            // 1. Log Operacional ATUALIZADO: incluindo operador e frota
            LogOperacional logOp = new LogOperacional(
                (long) missao.getIdMissao(), 
                idOperador, 
                idDrone, 
                "MISSAO_INICIADA", 
                "Missão autorizada. Objetivo: " + objetivo
            );
            logOperacionalRepository.save(logOp);
            System.out.println("🚀 [MongoDB] Evento gravado em logs_operacionais!");

            // 2. Dispara a simulação de voo que gerará o LOG ÚNICO de telemetria no final
            CompletableFuture.runAsync(() -> {
                simularVooUnicoLog(idDrone, (long) missao.getIdMissao(), idOperador);
            });

        } catch (RuntimeException e) {
            // CASO A MISSÃO SEJA NEGADA pelas regras da validação:
            System.err.println("❌ Missão Negada: " + e.getMessage());
            
            // Grava o Log Operacional de negação
            LogOperacional logNegado = new LogOperacional(0L, idOperador, idDrone, "MISSAO_NEGADA", e.getMessage());
            logOperacionalRepository.save(logNegado);
            
            // Grava uma Telemetria única de "Missão Negada" sem dados de voo
            Telemetria telemetriaNegada = new Telemetria(idDrone, 100.0, 100.0, 0.0, 0.0, "Missão Negada", "Não decolou", "Missão Interrompida");
            logRepository.save(telemetriaNegada);
            
            throw e; // Repassa o erro para o Controller avisar o front
        }
    }

    public void simularVooUnicoLog(Integer idDrone, Long idMissao, Integer idOperador) {
        System.out.println("🛰️ [SISTEMA] Iniciando ciclo de simulação para log único...");
        
        // Dados de Início
        double bateriaInicio = 100.0;
        double coordenadaInicio = 50.0; 
        
        // Variáveis de controle durante o voo
        double bateriaAtual = bateriaInicio;
        double coordenadaAtual = coordenadaInicio;
        String statusFinal = "Missão Finalizada"; // Padrão de sucesso
        String statusVoo = "Frota em voo";

        // Loop tático (apenas simula o tempo no console, sem salvar no banco a cada volta)
        for (int i = 1; i <= 5; i++) {
            try {
                Thread.sleep(1500); // Aguarda o tempo de voo fictício
                
                // NOVO: Verifica se o operador acionou o botão de Evasão no Front-End
                if (this.evasaoAcionada) {
                    statusFinal = "Missão Interrompida - Evasão Acionada";
                    statusVoo = "Retornando à Base";
                    System.out.println("🚨 [EVASÃO] Protocolo de segurança ativado! Interrompendo rota.");
                    
                    // Salva IMEDIATAMENTE no log operacional que a evasão foi disparada por segurança
                    LogOperacional logEvasao = new LogOperacional(
                        idMissao, 
                        idOperador, 
                        idDrone, 
                        "EVASAO_ACIONADA", 
                        "Protocolo de evasão acionado pelo operador. Retorno forçado ao hangar da Barra Funda."
                    );
                    logOperacionalRepository.save(logEvasao);
                    break; // Corta o loop na hora!
                }

                bateriaAtual -= (Math.random() * 8) + 4;
                coordenadaAtual += 1.0;
                System.out.println("🛸 Drone voando... Quadrante " + i + " | Bateria: " + String.format("%.1f", bateriaAtual) + "%");

                // Simulação nativa de Pane/Interrupção na etapa 3 caso o botão não seja clicado
                if (i == 3 && (bateriaAtual < 75 || Math.random() > 0.7)) {
                    statusFinal = "Missão Interrompida";
                    statusVoo = "Pane de Sistema";
                    System.out.println("⚠️ [ALERTA] Falha técnica detectada automaticamente!");
                    break; 
                }
            } catch (InterruptedException e) {
                System.out.println("❌ Erro no clock de simulação.");
            }
        }

        // SALVAMENTO DO LOG ÚNICO DE TELEMETRIA (Com os status consolidados)
        Telemetria relatorioVoo = new Telemetria(
            idDrone,
            bateriaInicio,
            bateriaAtual,       // Bateria final pós-voo
            coordenadaInicio,
            coordenadaAtual,    // Última coordenada alcançada
            "Missão Iniciada",  // Adendo 1
            statusVoo,          // Adendo 2: "Frota em voo" ou "Retornando à Base" ou "Pane"
            statusFinal         // Adendo 3: "Missão Finalizada" ou "Missão Interrompida - Evasão Acionada"
        );

        logRepository.save(relatorioVoo);
        System.out.println("🏁 [MongoDB Atlas] REGISTRO ÚNICO de telemetria sincronizado com sucesso!");
    }

    public void validarMissao(String objetivo, int idDrone) {
        if (objetivo == null || objetivo.length() < 5) {
            throw new RuntimeException("ALERTA: Objetivo muito vago!");
        }

        Drone drone = droneRepository.findById((long) idDrone)
            .orElseThrow(() -> new RuntimeException("ALERTA: Drone não localizado na frota!"));

        if (drone.getStatus() != 1) {
            throw new RuntimeException("ALERTA: Drone em manutenção!");
        }
        System.out.println("✅ Parâmetros validados. Prontidão confirmada.");
    }
}