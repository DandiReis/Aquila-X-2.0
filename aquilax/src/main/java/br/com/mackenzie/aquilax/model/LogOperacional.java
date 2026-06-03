package br.com.mackenzie.aquilax.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "logs_operacionais")
public class LogOperacional {

    @Id
    private String id;
    private Long idMissao;
    private Integer idOperador; // <-- ADICIONADO
    private Integer idDrone;    // <-- ADICIONADO
    private String evento;
    private String detalhes;
    private LocalDateTime timestamp;

    public LogOperacional() {
        this.timestamp = LocalDateTime.now();
    }

    // Atualiza o construtor para receber os novos dados
    public LogOperacional(Long idMissao, Integer idOperador, Integer idDrone, String evento, String detalhes) {
        this.idMissao = idMissao;
        this.idOperador = idOperador;
        this.idDrone = idDrone;
        this.evento = evento;
        this.detalhes = detalhes;
        this.timestamp = LocalDateTime.now();
    }

    // Getters e Setters para os novos campos
    public Integer getIdOperador() { return idOperador; }
    public void setIdOperador(Integer idOperador) { this.idOperador = idOperador; }

    public Integer getIdDrone() { return idDrone; }
    public void setIdDrone(Integer idDrone) { this.idDrone = idDrone; }

    // (Mantém os outros getters e setters que já tinhas aqui...)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Long getIdMissao() { return idMissao; }
    public void setIdMissao(Long idMissao) { this.idMissao = idMissao; }
    public String getEvento() { return evento; }
    public void setEvento(String evento) { this.evento = evento; }
    public String getDetalhes() { return detalhes; }
    public void setDetalhes(String detalhes) { this.detalhes = detalhes; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}