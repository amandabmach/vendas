package com.project.vendas.rest.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer cliente;
	private Double total;
	private List<ItemPedidoDTO> items;
	
}
