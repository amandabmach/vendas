package com.project.vendas.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.vendas.domain.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
	
	@Query("select p from Pedido p left join fetch p.itens where p.id = :id")
	Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
}
