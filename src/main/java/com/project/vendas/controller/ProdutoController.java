package com.project.vendas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.vendas.entities.Produto;
import com.project.vendas.repositories.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> findAll(){
		List<Produto> list = produtoRepository.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Integer id){
		Optional<Produto> obj = produtoRepository.findById(id);
		if(obj.isPresent()) {
			return ResponseEntity.ok().body(obj.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Produto> insert(@RequestBody Produto produto){
		Produto novoProduto = produtoRepository.save(produto);
		return ResponseEntity.ok(novoProduto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		Optional <Produto> produto = produtoRepository.findById(id);
		if(produto.isPresent()) {
			produtoRepository.delete(produto.get());
			return ResponseEntity.noContent().build();	
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Produto produto){
		return produtoRepository.findById(id)
				.map(produtoExistente -> {
					 produto.setId(produtoExistente.getId()); 
					 produtoRepository.save(produto); 
					 return ResponseEntity.noContent().build();
				}).orElseGet( () -> ResponseEntity.notFound().build());
	}
}
