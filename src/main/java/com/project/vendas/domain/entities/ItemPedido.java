package com.project.vendas.domain.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedido implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "pedido_id")
    private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name = "produto_id")
    private Produto produto;
	
	@Column(name = "quantidade")
    private Integer quantidade;
    
}