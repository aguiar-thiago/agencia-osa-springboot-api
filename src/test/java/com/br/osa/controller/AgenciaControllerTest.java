package com.br.osa.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.br.osa.config.ApplicationConfigTest;
import com.br.osa.model.dto.AgenciaDistanciaDto;
import com.br.osa.model.dto.AgenciaDto;
import com.br.osa.service.AgenciaService;


class AgenciaControllerTest extends ApplicationConfigTest {
	
	private static final String NOME_AGENCIA   = "TESTE UNITARIO";
	private static final int POSICAO_X         = 1;
	private static final int POSICAO_Y         = 1;
	private static final int AGENCIA_DISTANCIA = 1;
	
	@InjectMocks
	private AgenciaController controller;
	
	@Mock
	private AgenciaService agenciaService;
	
	private AgenciaDto agenciaDto;
	private AgenciaDistanciaDto agenciaDistanciaDto;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		agenciaDto = new AgenciaDto(NOME_AGENCIA , POSICAO_X, POSICAO_Y);
		agenciaDistanciaDto = new AgenciaDistanciaDto(NOME_AGENCIA, AGENCIA_DISTANCIA);
	}
	
	@Test()
	@DisplayName("DEVE SALVAR UMA NOVA AGENCIA COM SUCESSO NO BD")
	void salveAgenciaSucesso() {
		when(agenciaService.save(any())).thenReturn(agenciaDto);
		
		ResponseEntity<AgenciaDto> response = controller.save(agenciaDto);
		
		validarResponseAndBody(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(AgenciaDto.class, response.getBody().getClass());
		validarDadosAgenciaDto(response);
	}
	
	@Test()
	@DisplayName("DEVE BUSCAR UMA AGENCIA COM SUCESSO")
	void buscarAgenciaSucesso() {
		when(agenciaService.findAgencia(anyDouble() , anyDouble())).thenReturn(agenciaDto);
		
		ResponseEntity<AgenciaDto> response = controller.findAgencia(POSICAO_X, POSICAO_Y);
		
		validarResponseAndBody(response);
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(AgenciaDto.class, response.getBody().getClass());
		validarDadosAgenciaDto(response);
	}
	
	@Test()
	@DisplayName("DEVE BUSCAR UMA LISTA DE AGENCIA ORDENADA DA MAIS PROXIMA PARA MAIS DISTANTE")
	void findAgenciasProximasSucesso() {
		when(agenciaService.getAgenciaProxima(anyDouble(), anyDouble())).thenReturn(List.of(agenciaDistanciaDto));
		
		ResponseEntity<List<AgenciaDistanciaDto>> response  = controller.findAgenciasProximas(POSICAO_X, POSICAO_Y);
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(AgenciaDistanciaDto.class, response.getBody().get(0).getClass());
		assertEquals(AGENCIA_DISTANCIA, response.getBody().get(0).getDistanciaAgencia());
		assertEquals(NOME_AGENCIA, response.getBody().get(0).getNomeAgencia());
	}
	
	@Test()
	@DisplayName("DEVE ATUALIZAR O NOME DA AGENCIA")
	void updateNomeAgenciaSucesso() {
		when(agenciaService.updateNomeAgencia(any())).thenReturn(agenciaDto);
		
		ResponseEntity<AgenciaDto> response = controller.updateNomeAgencia(agenciaDto);
		
		validarResponseAndBody(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(AgenciaDto.class, response.getBody().getClass());
		validarDadosAgenciaDto(response);
	}
	
	@Test()
	@DisplayName("DEVE DELETAR A AGENCIA DA BASE DE DADOS")
	void deleteAgencia() {
		doNothing().when(agenciaService).deleteAgencia(anyDouble(), anyDouble());
		
		ResponseEntity<String> response = controller.deleteAgencia(POSICAO_X, POSICAO_Y);
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		verify(agenciaService, Mockito.times(1)).deleteAgencia(POSICAO_X, POSICAO_Y);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	private void validarDadosAgenciaDto(ResponseEntity<AgenciaDto> response) {
		assertEquals(NOME_AGENCIA, response.getBody().getNomeAgencia());
		assertEquals(POSICAO_X, response.getBody().getPosicaoX());
		assertEquals(POSICAO_Y, response.getBody().getPosicaoY());
	}
	
	private void validarResponseAndBody(ResponseEntity<AgenciaDto> response) {
		assertNotNull(response);
		assertNotNull(response.getBody());
	}

}
