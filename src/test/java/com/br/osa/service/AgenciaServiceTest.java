package com.br.osa.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.br.osa.config.ApplicationConfigTest;
import com.br.osa.mapper.AgenciaMapper;
import com.br.osa.model.Agencia;
import com.br.osa.model.dto.AgenciaDto;
import com.br.osa.repository.AgenciaRepository;

class AgenciaServiceTest extends ApplicationConfigTest {
	
	private static final int ID              = 1;
	private static final String NOME_AGENCIA = "TESTE UNITARIO";
	private static final int POSICAO_X       = 1;
	private static final int POSICAO_Y       = 1;

	@InjectMocks
	private AgenciaService agenciaService;
	
	@Mock
	private AgenciaRepository agenciaRepository;
	
	@Mock
	private AgenciaMapper agenciaMapper;
	
	private AgenciaDto agenciaDto;
	private Optional<Agencia> agenciaOptional;
	
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		agenciaDto = new AgenciaDto(NOME_AGENCIA , POSICAO_X, POSICAO_Y);
		agenciaOptional = Optional.of(new Agencia(ID, NOME_AGENCIA, POSICAO_X, POSICAO_Y));
	}
	
	@Test
	@DisplayName("DEVE BUSCAR A GENCIA COM SUCESSO")
	void finAgenciaSucesso() {
		when(agenciaRepository.findAgencia(anyDouble(), anyDouble())).thenReturn(agenciaOptional);
		when(agenciaMapper.convertAgenciaToDto(any())).thenReturn(agenciaDto);
		
		AgenciaDto response = agenciaService.findAgencia(POSICAO_X, POSICAO_Y); 
		
		assertNotNull(response);
		assertEquals(AgenciaDto.class, response.getClass());
		assertEquals(NOME_AGENCIA, response.getNomeAgencia());
		assertEquals(POSICAO_X, response.getPosicaoX());
		assertEquals(POSICAO_Y, response.getPosicaoY());
	}
	
	void deleteAgenciaSucesso() {	
	}
	
	void deveSalvarVenda() {
	}
	
	void updateNomeAgencia() {	
	}
	
	void getAgenciaProxima() {
	}

}
