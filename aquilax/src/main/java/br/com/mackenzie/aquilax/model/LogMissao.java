package br.com.mackenzie.aquilax.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "logs_operacionais")
public class LogMissao {
    @Id
    private String id;
    private String tipoEvento; 
    private int idOperador;
    private int idDrone;
    private String objetivo;
    private String statusSistema;
    private LocalDateTime timestamp = LocalDateTime.now();

    // Construtor completo para o log militar
    public LogMissao(String tipoEvento, int idOperador, int idDrone, String objetivo, String statusSistema) {
        this.tipoEvento = tipoEvento;
        this.idOperador = idOperador;
        this.idDrone = idDrone;
        this.objetivo = objetivo;
        this.statusSistema = statusSistema;
    }

    // Getters...
}