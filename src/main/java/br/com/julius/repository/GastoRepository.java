package br.com.julius.repository;

import br.com.julius.model.Categoria;
import br.com.julius.model.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * O Spring Data gera o SQL a partir do NOME do método.
 * save, findById, existsById e deleteById já vêm prontos do JpaRepository.
 */
public interface GastoRepository extends JpaRepository<Gasto, Long> {

    // RF02 + RF05: lista / pesquisa pela descrição (busca vazia traz tudo)
    List<Gasto> findByDescricaoContainingIgnoreCaseOrderByDataDesc(String descricao);

    // RF05 + RF06: pesquisa + filtro por categoria
    List<Gasto> findByDescricaoContainingIgnoreCaseAndCategoriaOrderByDataDesc(String descricao, Categoria categoria);
}
