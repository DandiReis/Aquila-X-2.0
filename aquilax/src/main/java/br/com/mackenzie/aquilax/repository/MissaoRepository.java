package br.com.mackenzie.aquilax.repository;

import br.com.mackenzie.aquilax.model.Missao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissaoRepository extends JpaRepository<Missao, Long> {
    // Pronto! O Spring já criou pra você:
    // .save()    -> Salva no MySQL
    // .findAll() -> Busca todas as missões
    // .delete()  -> Deleta
    // .findById()-> Busca por ID
}
