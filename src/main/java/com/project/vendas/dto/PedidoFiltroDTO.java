package com.project.vendas.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class PedidoFiltroDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private LocalDate dataPedido;
	private Double total;
	
	public PedidoFiltroDTO(LocalDate dataPedido, Double total) {
		super();
		this.dataPedido = dataPedido;
		this.total = total;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
}
