package com.project.vendas.rest.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer produto;
	private Integer quantidade;
	
}
