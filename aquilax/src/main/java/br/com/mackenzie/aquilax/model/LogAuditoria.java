package br.com.mackenzie.aquilax.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "logs_auditoria") // Mapeia direto para a coleção logs_auditoria do seu print do Compass!
public class LogAuditoria {

    @Id
    private String id;
    private String registroOperador;
    private String acao;
    private LocalDateTime timestamp;

    public LogAuditoria() {
        this.timestamp = LocalDateTime.now();
    }

    public LogAuditoria(String registroOperador, String acao) {
        this.registroOperador = registroOperador;
        this.acao = acao;
        this.timestamp = LocalDateTime.now();
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getRegistroOperador() { return registroOperador; }
    public void setRegistroOperador(String registroOperador) { this.registroOperador = registroOperador; }

    public String getAcao() { return acao; }
    public void setAcao(String acao) { this.acao = acao; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}