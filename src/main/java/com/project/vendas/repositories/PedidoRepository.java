package com.project.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.vendas.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
