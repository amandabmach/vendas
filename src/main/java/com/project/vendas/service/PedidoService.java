package com.project.vendas.service;

import com.project.vendas.dto.PedidoDTO;
import com.project.vendas.entities.Pedido;

public interface PedidoService {
	
	Pedido salvar(PedidoDTO dto);

}
