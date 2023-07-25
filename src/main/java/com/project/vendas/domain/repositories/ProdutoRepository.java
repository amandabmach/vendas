package com.project.vendas.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.vendas.domain.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
