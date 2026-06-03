package br.com.mackenzie.aquilax.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "telemetria")
public class Telemetria {

    @Id
    private String id;
    private Integer idDrone;
    private Double bateriaInicio;
    private Double bateriaFim;
    private Double coordenadaInicio;
    private Double coordenadaFim;
    private String statusMissao; // Missão Iniciada / Negada
    private String statusVoo;    // Frota em voo
    private String statusFinal;   // Missão Finalizada / Interrompida
    private LocalDateTime timestamp;

    public Telemetria() {
        this.timestamp = LocalDateTime.now();
    }

    // Construtor completo para gerar o relatório único de telemetria
    public Telemetria(Integer idDrone, Double bateriaInicio, Double bateriaFim, 
                      Double coordenadaInicio, Double coordenadaFim, 
                      String statusMissao, String statusVoo, String statusFinal) {
        this.idDrone = idDrone;
        this.bateriaInicio = bateriaInicio;
        this.bateriaFim = bateriaFim;
        this.coordenadaInicio = coordenadaInicio;
        this.coordenadaFim = coordenadaFim;
        this.statusMissao = statusMissao;
        this.statusVoo = statusVoo;
        this.statusFinal = statusFinal;
        this.timestamp = LocalDateTime.now();
    }

    // Getters e Setters para todos os campos...
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Integer getIdDrone() { return idDrone; }
    public void setIdDrone(Integer idDrone) { this.idDrone = idDrone; }
    public Double getBateriaInicio() { return bateriaInicio; }
    public void setBateriaInicio(Double bateriaInicio) { this.bateriaInicio = bateriaInicio; }
    public Double getBateriaFim() { return bateriaFim; }
    public void setBateriaFim(Double bateriaFim) { this.bateriaFim = bateriaFim; }
    public Double getCoordenadaInicio() { return coordenadaInicio; }
    public void setCoordenadaInicio(Double coordenadaInicio) { this.coordenadaInicio = coordenadaInicio; }
    public Double getCoordenadaFim() { return coordenadaFim; }
    public void setCoordenadaFim(Double coordenadaFim) { this.coordenadaFim = coordenadaFim; }
    public String getStatusMissao() { return statusMissao; }
    public void setStatusMissao(String statusMissao) { this.statusMissao = statusMissao; }
    public String getStatusVoo() { return statusVoo; }
    public void setStatusVoo(String statusVoo) { this.statusVoo = statusVoo; }
    public String getStatusFinal() { return statusFinal; }
    public void setStatusFinal(String statusFinal) { this.statusFinal = statusFinal; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}