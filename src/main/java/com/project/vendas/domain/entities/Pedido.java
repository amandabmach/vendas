package com.project.vendas.domain.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.vendas.domain.enums.StatusPedido;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
    private Cliente cliente;
	
	@Column(name = "data_pedido")
    private LocalDate dataPedido;
	
	@Column(name = "total")
    private Double total;
    
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusPedido status;
    
    @JsonIgnore
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;
    
    public Pedido(Integer id, Cliente cliente, LocalDate dataPedido, Double total) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.dataPedido = dataPedido;
		this.total = total;
		
	}
}