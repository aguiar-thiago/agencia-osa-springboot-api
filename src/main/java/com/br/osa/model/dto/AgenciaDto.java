package com.br.osa.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class AgenciaDto {

	@JsonIgnore
	private int id;
	private String nomeAgencia;
	private double posicaoX;
	private double posicaoY;
	
	public AgenciaDto(String nomeAgencia, double posicaoX, double posicaoY) {
		this.nomeAgencia = nomeAgencia;
		this.posicaoX = posicaoX;
		this.posicaoY = posicaoY;
	}
		
}
