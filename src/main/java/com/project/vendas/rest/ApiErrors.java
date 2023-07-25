package com.project.vendas.rest;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ApiErrors {
	
	@Getter
	private List<String> errors;
	
	public ApiErrors(String msgErro) {
		this.errors = Arrays.asList(msgErro);
	}

}
