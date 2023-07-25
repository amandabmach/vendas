package com.project.vendas.rest.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfoPedidosDTO {
	
	private Integer codigo;
	private String cpf;
	private String nomeCliente;
	private Double total;
	private String dataPedido;
	private String status;
	private List<InfoItemPedidosDTO> items;
	
}
