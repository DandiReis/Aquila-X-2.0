package br.com.mackenzie.aquilax.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "drone")
public class Drone {
    @ManyToOne
    @JoinColumn(name = "id_frota")
    private Frota frota;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_drone")
    private Integer idDrone;

    private String modelo;

    @Column(name = "num_identificacao")
    private int numIdentificacao;
    private int status;

    // Getters e Setters (Para o Spring conseguir ler os dados)
    public Integer getIdDrone() { return idDrone; }
    public void setIdDrone(Integer idDrone) { this.idDrone = idDrone; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public int getNumIdentificacao() { return numIdentificacao; }
    public void setNumIdentificacao(int numIdentificacao) { this.numIdentificacao = numIdentificacao; }

    public Frota getFrota() { return frota; }
    public void setFrota(Frota frota) { this.frota = frota; }
}