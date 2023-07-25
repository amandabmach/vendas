package com.project.vendas.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.project.vendas.domain.entities.Produto;
import com.project.vendas.domain.repositories.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public List<Produto> findAll(){
		return produtoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Produto findById(@PathVariable Integer id){
		return produtoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException( HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto insert(@RequestBody Produto produto){
		return produtoRepository.save(produto);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id){
		produtoRepository.findById(id)
		.map(prod -> {
			produtoRepository.delete(prod);
			return prod;
			}).orElseThrow(() -> new ResponseStatusException( HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Integer id, @RequestBody Produto produto){
		produtoRepository.findById(id)
			.map(produtoExistente -> {
				 produto.setId(produtoExistente.getId()); 
				 produtoRepository.save(produto); 
				 return produto;
			}).orElseThrow(() -> new ResponseStatusException( HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}
}
