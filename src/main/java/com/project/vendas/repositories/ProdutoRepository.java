package com.project.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.vendas.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
