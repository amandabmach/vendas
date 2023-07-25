package com.project.vendas.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.project.vendas.domain.entities.Cliente;
import com.project.vendas.domain.repositories.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping
	public List<Cliente> findAll(){
		List<Cliente> list = clienteRepository.findAll();
		return list;
	}
	
	@GetMapping("/{id}")
	public Cliente findById(@PathVariable Integer id){
		return clienteRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException( HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente insert(@RequestBody Cliente cliente){
		return clienteRepository.save(cliente);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
	    clienteRepository.findById(id)
	        .map(cliente -> {
	            clienteRepository.delete(cliente);
	            return cliente;
	        })
	        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Integer id, @RequestBody Cliente cliente){
		clienteRepository.findById(id)
				.map(clienteExistente -> {
					 cliente.setId(clienteExistente.getId()); 
					 clienteRepository.save(cliente); 
					 return cliente;
				}).orElseThrow(() -> new ResponseStatusException( HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}
	
	@GetMapping("/filtro")
	public List<Cliente> findByParameter(Cliente filtro){
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Example<Cliente> example = Example.of(filtro, matcher);
		return clienteRepository.findAll(example);
	}
}
