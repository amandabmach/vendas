package com.project.vendas.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.vendas.dto.ItemPedidoDTO;
import com.project.vendas.dto.PedidoDTO;
import com.project.vendas.entities.Cliente;
import com.project.vendas.entities.ItemPedido;
import com.project.vendas.entities.Pedido;
import com.project.vendas.entities.Produto;
import com.project.vendas.exception.VendasException;
import com.project.vendas.repositories.ClienteRepository;
import com.project.vendas.repositories.ItemPedidoRepository;
import com.project.vendas.repositories.PedidoRepository;
import com.project.vendas.repositories.ProdutoRepository;
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
}
