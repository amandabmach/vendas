package com.project.vendas.rest.dto;

import java.io.Serializable;
import java.util.List;

import com.project.vendas.validation.NotEmptyList;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "{campo.codigo-cliente.obrigatorio}")
	private Integer cliente;
	
	@NotNull(message = "{campo.total-pedido.obrigatorio}")
	private Double total;
	
	@NotEmptyList(message = "{campo.itens-pedido.obrigatorio}")
	private List<ItemPedidoDTO> items;
	
}
