package br.com.mackenzie.aquilax.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "operador")
public class Operador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_operador")
    private Integer id;

    private String nome;

    @Column(name = "registro_operador")
    private String registroOperador;

    private String senha;

    @Column(name = "nivel_de_access")
    private String nivelAcesso;

    public String getSenha() { return senha; }
    public String getNome() { return nome; }
    public Integer getId() { return id; }
}