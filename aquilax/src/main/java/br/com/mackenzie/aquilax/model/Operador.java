package br.com.mackenzie.aquilax.model;

import jakarta.persistence.*;

@Entity
@Table(name = "operador")
public class Operador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_operador")
    private Long id;

    private String nome;

    @Column(name = "registro_operador")
    private String registroOperador;

    private String senha;

    @Column(name = "nivel_de_access")
    private String nivelAcesso;

    // Construtores, Getters e Setters...
    public String getSenha() { return senha; }
    public String getNome() { return nome; }
}