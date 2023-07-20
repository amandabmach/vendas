package com.project.vendas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.vendas.dto.PedidoDTO;
import com.project.vendas.entities.Pedido;
import com.project.vendas.repositories.PedidoRepository;
import com.project.vendas.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService service;
	
	@Autowired PedidoRepository repository;
	
	@PostMapping
	public Integer save(@RequestBody PedidoDTO dto) {
		Pedido pedido = service.salvar(dto);
		return pedido.getId();
	}
	
	@GetMapping
	public ResponseEntity<List<Pedido>> findAll(){
		List<Pedido> list = repository.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/filtro")
	public ResponseEntity<List<Pedido>> findByFilter(Pedido filtro){
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Example<Pedido> example = Example.of(filtro, matcher);
		List<Pedido> lista = repository.findAll(example);
		return ResponseEntity.ok(lista);
		
	}
}
