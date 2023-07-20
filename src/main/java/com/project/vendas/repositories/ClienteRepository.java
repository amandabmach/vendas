package com.project.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.vendas.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
