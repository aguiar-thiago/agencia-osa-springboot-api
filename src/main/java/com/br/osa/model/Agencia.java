package com.br.osa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter @Setter
public class Agencia implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_AGENCIA")
	private int id;
	
	@Column(name = "NOME_AGENCIA")
	private String nomeAgencia;
	
	@Column(name = "POSICAO_X")
	private double posicaoX;
	
	@Column(name = "POSICAO_Y")
	private double posicaoY;
	
}
