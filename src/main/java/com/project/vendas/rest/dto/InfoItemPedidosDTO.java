package com.project.vendas.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfoItemPedidosDTO {
	
	private String descricaoProduto;
	private Double precoUnitario;
	private Integer quantidade;
	
}
