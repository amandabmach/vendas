package com.project.vendas.rest.controller;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.project.vendas.domain.entities.ItemPedido;
import com.project.vendas.domain.entities.Pedido;
import com.project.vendas.domain.enums.StatusPedido;
import com.project.vendas.domain.repositories.PedidoRepository;
import com.project.vendas.rest.dto.AtualizacaoStatusPedidoDTO;
import com.project.vendas.rest.dto.InfoItemPedidosDTO;
import com.project.vendas.rest.dto.InfoPedidosDTO;
import com.project.vendas.rest.dto.PedidoDTO;
import com.project.vendas.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService service;
	
	@Autowired PedidoRepository repository;
	
	@GetMapping
	public List<Pedido> findAll(){
		return repository.findAll();
	}
	
	@GetMapping("/filtro")
	public List<Pedido>findByFilter(Pedido filtro){
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Example<Pedido> example = Example.of(filtro, matcher);
		return repository.findAll(example);		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Integer save(@RequestBody PedidoDTO dto) {
		Pedido pedido = service.salvar(dto);
		return pedido.getId();
	}
	
	@GetMapping("/{id}")
	public InfoPedidosDTO getById(@PathVariable Integer id){
		return service
			.obterPedidoCompleto(id)
			.map(p -> converter(p))
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));	
		
	}
	
	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateStatus(@PathVariable Integer id, @RequestBody AtualizacaoStatusPedidoDTO dto) {
		String novoStatus = dto.getNovoStatus();
		service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
	}
	
	private InfoPedidosDTO converter(Pedido pedido) {
		return InfoPedidosDTO
			.builder()
			.codigo(pedido.getId())
			.dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
			.cpf(pedido.getCliente().getCpf())
			.nomeCliente(pedido.getCliente().getNome())
			.total(pedido.getTotal())
			.status(pedido.getStatus().name())
			.items(converterItens(pedido.getItens()))
			.build();
	}
	
	private List<InfoItemPedidosDTO> converterItens(List<ItemPedido> itens){
		if(CollectionUtils.isEmpty(itens)) {
			return Collections.emptyList();
		}
		
		return itens.stream().map(
			item -> InfoItemPedidosDTO
				.builder().descricaoProduto(item.getProduto().getDescricao())
				.precoUnitario(item.getProduto().getPreco())
				.quantidade(item.getQuantidade())
				.build()
		).collect(Collectors.toList());
	}
}
