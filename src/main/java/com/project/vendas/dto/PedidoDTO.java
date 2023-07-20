package com.project.vendas.dto;

import java.io.Serializable;
import java.util.List;

public class PedidoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer cliente;
	private Double total;
	
	private List<ItemPedidoDTO> items;
	
	public PedidoDTO() {
		
	}

	public PedidoDTO(Integer cliente, Double total, List<ItemPedidoDTO> items) {
		this.cliente = cliente;
		this.total = total;
		this.items = items;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public List<ItemPedidoDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemPedidoDTO> items) {
		this.items = items;
	}
}
