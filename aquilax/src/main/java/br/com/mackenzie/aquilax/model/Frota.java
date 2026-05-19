package br.com.mackenzie.aquilax.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Frota") // Garante o mapeamento com a tabela existente no MySQL
public class Frota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_frota")
    private Long idFrota;

    @Column(name = "nome_esquadrao")
    private String nomeEsquadrao;

    @Column(name = "setor_atuacao")
    private String setorAtuacao;

    // Getters e Setters
    public Long getIdFrota() { return idFrota; }
    public void setIdFrota(Long idFrota) { this.idFrota = idFrota; }

    public String getNomeEsquadrao() { return nomeEsquadrao; }
    public void setNomeEsquadrao(String nomeEsquadrao) { this.nomeEsquadrao = nomeEsquadrao; }

    public String getSetorAtuacao() { return setorAtuacao; }
    public void setSetorAtuacao(String setorAtuacao) { this.setorAtuacao = setorAtuacao; }
}