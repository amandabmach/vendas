package com.project.vendas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.vendas.entities.Cliente;
import com.project.vendas.repositories.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> findAll(){
		List<Cliente> list = clienteRepository.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Integer id){
		Optional<Cliente> obj = clienteRepository.findById(id);
		if(obj.isPresent()) {
			return ResponseEntity.ok().body(obj.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Cliente> insert(@RequestBody Cliente cliente){
		Cliente novoCliente = clienteRepository.save(cliente);
		return ResponseEntity.ok(novoCliente);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		Optional <Cliente> cliente = clienteRepository.findById(id);
		if(cliente.isPresent()) {
			clienteRepository.delete(cliente.get());
			return ResponseEntity.noContent().build();	
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Cliente cliente){
		return clienteRepository.findById(id)
				.map(clienteExistente -> {
					 cliente.setId(clienteExistente.getId()); 
					 clienteRepository.save(cliente); 
					 return ResponseEntity.noContent().build();
				}).orElseGet( () -> ResponseEntity.notFound().build());
	}
	
	@GetMapping("/filtro")
	public ResponseEntity <List<Cliente>> findByParameter(Cliente filtro){
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Example<Cliente> example = Example.of(filtro, matcher);
		List<Cliente> lista = clienteRepository.findAll(example);
		return ResponseEntity.ok(lista);
	}
}
