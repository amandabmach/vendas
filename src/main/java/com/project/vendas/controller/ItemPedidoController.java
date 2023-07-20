package com.project.vendas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.vendas.entities.ItemPedido;
import com.project.vendas.repositories.ItemPedidoRepository;

@RestController
@RequestMapping("/itens")
public class ItemPedidoController {
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	@GetMapping
	public ResponseEntity<List<ItemPedido>> findAll(){
		List<ItemPedido> list = itemPedidoRepository.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ItemPedido> findById(@PathVariable Integer id){
		Optional<ItemPedido> obj = itemPedidoRepository.findById(id);
		if(obj.isPresent()) {
			return ResponseEntity.ok().body(obj.get());
		}
		return ResponseEntity.notFound().build();
	}
}
