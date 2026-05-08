package br.com.mackenzie.aquilax.model; // 1. O pacote deve ser a primeira linha

import jakarta.persistence.*; // 2. Isso resolve todos os erros de @Entity, @Table, @Id, etc.
import java.time.LocalDateTime; // 3. Isso resolve o erro do LocalDateTime

@Entity
@Table(name = "missao")
public class Missao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_missao")
    private Long idMissao;

    @Column(name = "objetivo", nullable = false) // Garante que não seja null
    private String objetivo;

    @Column(name = "id_operador")
    private int idOperador;

    @Column(name = "id_drone")
    private int idDrone;

    @Column(name = "data_inicio")
    private LocalDateTime dataInicio = LocalDateTime.now();

    // 1. CONSTRUTOR VAZIO (OBRIGATÓRIO para o Spring ler do banco)
    public Missao() {}

    // 2. CONSTRUTOR COMPLETO (Para você usar no Service)
    public Missao(String objetivo, int idOperador, int idDrone) {
        this.objetivo = objetivo;
        this.idOperador = idOperador;
        this.idDrone = idDrone;
        this.dataInicio = LocalDateTime.now();
    }

    // 3. GETTERS E SETTERS (O Spring usa o GET para mostrar no navegador!)
    public Long getIdMissao() { return idMissao; }
    public void setIdMissao(Long idMissao) { this.idMissao = idMissao; }

    public String getObjetivo() { return objetivo; }
    public void setObjetivo(String objetivo) { this.objetivo = objetivo; }

    public int getIdOperador() { return idOperador; }
    public void setIdOperador(int idOperador) { this.idOperador = idOperador; }

    public int getIdDrone() { return idDrone; }
    public void setIdDrone(int idDrone) { this.idDrone = idDrone; }

    public LocalDateTime getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDateTime dataInicio) { this.dataInicio = dataInicio; }
}