package com.project.vendas.dto;

import java.io.Serializable;

public class ItemPedidoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer produto;
	private Integer quantidade;
	
	public ItemPedidoDTO() {
		
	}
	public ItemPedidoDTO(Integer produto, Integer quantidade) {
		super();
		this.produto = produto;
		this.quantidade = quantidade;
	}
	
	public Integer getProduto() {
		return produto;
	}
	
	public void setProduto(Integer produto) {
		this.produto = produto;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public void setQuatidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
