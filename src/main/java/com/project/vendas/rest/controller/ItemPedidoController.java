package com.project.vendas.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.project.vendas.domain.entities.ItemPedido;
import com.project.vendas.domain.repositories.ItemPedidoRepository;

@RestController
@RequestMapping("/itens")
public class ItemPedidoController {
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@GetMapping
	public List<ItemPedido> findAll(){
		return itemPedidoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ItemPedido findById(@PathVariable Integer id){
		return itemPedidoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException( HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}
	
}
