package com.project.vendas.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.project.vendas.domain.entities.Cliente;
import com.project.vendas.domain.entities.Produto;
import com.project.vendas.domain.repositories.ClienteRepository;
import com.project.vendas.domain.repositories.ProdutoRepository;
import com.project.vendas.rest.dto.ItemPedidoDTO;
import com.project.vendas.rest.dto.PedidoDTO;
import com.project.vendas.service.PedidoService;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private PedidoService pedidoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		List<ItemPedidoDTO> list = new ArrayList<>();
		
		Cliente c1 = new Cliente(null, "Amanda", "101.546.654.96");
		Cliente c2 = new Cliente(null, "Isadora", "254.546.984.96");
		Cliente c3 = new Cliente(null, "Ana Caroline", "654.478.655.14");
		
		
		clienteRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Produto p1 = new Produto(null, "TV Smart 39'", 2899.00);
		Produto p2 = new Produto(null, "Smartphone Samsung Galaxy S10", 3199.00);
		Produto p3 = new Produto(null, "Notebook Dell Inspiron 5000 i5", 4239.00);
		
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		ItemPedidoDTO item1 = new ItemPedidoDTO(p1.getId(), 2);
		ItemPedidoDTO item2 = new ItemPedidoDTO(p2.getId(), 3);
		ItemPedidoDTO item3 = new ItemPedidoDTO(p3.getId(), 6);

		list.add(item1);
		list.add(item2);
		list.add(item3);
				
		PedidoDTO pd = new PedidoDTO(c1.getId(), 40.829, list);
		
		pedidoRepository.salvar(pd);
		
	}
}
