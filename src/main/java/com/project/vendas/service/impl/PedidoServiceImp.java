package com.project.vendas.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.vendas.domain.entities.Cliente;
import com.project.vendas.domain.entities.ItemPedido;
import com.project.vendas.domain.entities.Pedido;
import com.project.vendas.domain.entities.Produto;
import com.project.vendas.domain.enums.StatusPedido;
import com.project.vendas.domain.repositories.ClienteRepository;
import com.project.vendas.domain.repositories.ItemPedidoRepository;
import com.project.vendas.domain.repositories.PedidoRepository;
import com.project.vendas.domain.repositories.ProdutoRepository;
import com.project.vendas.exception.PedidoNaoEncontradoException;
import com.project.vendas.exception.VendasException;
import com.project.vendas.rest.dto.ItemPedidoDTO;
import com.project.vendas.rest.dto.PedidoDTO;
import com.project.vendas.service.PedidoService;

import jakarta.transaction.Transactional;

@Service
public class PedidoServiceImp implements PedidoService{
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	ItemPedidoRepository itemRepository;
	

	@Override
	@Transactional
	public Pedido salvar(PedidoDTO dto) {
		
		Integer idCliente = dto.getCliente();
		Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(()-> new VendasException("Código de cliente inválido"));
		
		Pedido pedido = new Pedido();
		pedido.setTotal(dto.getTotal());
		pedido.setDataPedido(LocalDate.now());
		pedido.setCliente(cliente);
		pedido.setStatus(StatusPedido.REALIZADO);
		
		List<ItemPedido> itemPedido = converterItem(pedido, dto.getItems());
		pedidoRepository.save(pedido);
		itemRepository.saveAll(itemPedido);
		pedido.setItens(itemPedido);
		
		return pedido;
	}
	
	private List<ItemPedido> converterItem(Pedido pedido, List<ItemPedidoDTO> items) {
		if(items.isEmpty()) {
			throw new VendasException("Não é possivel realizar um pedido sem items!");
		}
		return items.stream().map(dto -> {
			Integer idProduto = dto.getProduto();
			Produto produto = produtoRepository.findById(idProduto).orElseThrow(()-> new VendasException("Código de produto inválido: " + idProduto));
	
			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setQuantidade(dto.getQuantidade());
			itemPedido.setPedido(pedido);
			itemPedido.setProduto(produto);
			return itemPedido;
		}).collect(Collectors.toList());
	}

	@Override
	public Optional<Pedido> obterPedidoCompleto(Integer id) {
		return pedidoRepository.findByIdFetchItens(id);
	}

	@Override
	@Transactional
	public void atualizaStatus(Integer id, StatusPedido statusPedido) {
		pedidoRepository.findById(id).map(pedido -> {
			pedido.setStatus(statusPedido);
			return pedidoRepository.save(pedido);
		}).orElseThrow(() -> new PedidoNaoEncontradoException());
	}
}
