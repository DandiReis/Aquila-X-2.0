package br.com.mackenzie.aquilax.model;

import jakarta.persistence.*;

@Entity
@Table(name = "drone")
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_drone")
    private Long idDrone;

    private String modelo;

    @Column(name = "num_identificacao")
    private int numIdentificacao;

    private int status; // 1 = Pronto, 0 = Manutenção

    @Column(name = "id_frota")
    private int idFrota;

    // Construtor padrão (Obrigatório)
    public Drone() {}

    // Getters e Setters (Para o Spring conseguir ler os dados)
    public Long getIdDrone() { return idDrone; }
    public void setIdDrone(Long idDrone) { this.idDrone = idDrone; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public int getNumIdentificacao() { return numIdentificacao; }
    public void setNumIdentificacao(int numIdentificacao) { this.numIdentificacao = numIdentificacao; }
}