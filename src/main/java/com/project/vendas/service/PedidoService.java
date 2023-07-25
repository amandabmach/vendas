package com.project.vendas.service;

import java.util.Optional;

import com.project.vendas.domain.entities.Pedido;
import com.project.vendas.domain.enums.StatusPedido;
import com.project.vendas.rest.dto.PedidoDTO;

public interface PedidoService {
	
	Pedido salvar(PedidoDTO dto);
	Optional<Pedido> obterPedidoCompleto(Integer id);
	void atualizaStatus(Integer id, StatusPedido statusPedido);
	
}
