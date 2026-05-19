package br.com.mackenzie.aquilax.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "logs_missao")
public class LogMissao {

    @Id
    private String id;
    private Long idMissao;
    private String mensagem;
    private LocalDateTime timestamp;

    public LogMissao() {}

    public LogMissao(Long idMissao, String mensagem) {
        this.idMissao = idMissao;
        this.mensagem = mensagem;
        this.timestamp = LocalDateTime.now();
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Long getIdMissao() { return idMissao; }
    public void setIdMissao(Long idMissao) { this.idMissao = idMissao; }
    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}