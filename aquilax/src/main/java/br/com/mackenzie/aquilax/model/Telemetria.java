package br.com.mackenzie.aquilax.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Document(collection = "telemetria")
public class Telemetria {
    @Id
    private String id;
    private Integer idDrone;
    private Double bateria;
    private String coordenadas;
    private Double altitude;
    private LocalDateTime timestamp;

    public Telemetria() {}

    public Telemetria(Integer idDrone, Double bateria, String coordenadas, Double altitude) {
        this.idDrone = idDrone;
        this.bateria = bateria;
        this.coordenadas = coordenadas;
        this.altitude = altitude;
        this.timestamp = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    }

    // Getters e Setters (importante!)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Integer getIdDrone() { return idDrone; }
    public void setIdDrone(Integer idDrone) { this.idDrone = idDrone; }
    public Double getBateria() { return bateria; }
    public void setBateria(Double bateria) { this.bateria = bateria; }
    public String getCoordenadas() { return coordenadas; }
    public void setCoordenadas(String coordenadas) { this.coordenadas = coordenadas; }
    public Double getAltitude() { return altitude; }
    public void setAltitude(Double altitude) { this.altitude = altitude; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}