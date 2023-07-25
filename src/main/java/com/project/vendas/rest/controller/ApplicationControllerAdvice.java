package com.project.vendas.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.vendas.exception.PedidoNaoEncontradoException;
import com.project.vendas.exception.VendasException;
import com.project.vendas.rest.ApiErrors;

@RestControllerAdvice
public class ApplicationControllerAdvice {
	
	@ExceptionHandler(VendasException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleVendasException(VendasException ex) {
		String mensagemErro = ex.getMessage();
		return new ApiErrors(mensagemErro);
	}
	
	@ExceptionHandler(PedidoNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors handlePedidoNotFoundException(PedidoNaoEncontradoException ex) {
		return new ApiErrors(ex.getMessage());
	}
	
}
