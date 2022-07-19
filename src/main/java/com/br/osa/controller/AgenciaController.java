package com.br.osa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.osa.model.dto.AgenciaDistanciaDto;
import com.br.osa.model.dto.AgenciaDto;
import com.br.osa.service.AgenciaService;

@RestController
@RequestMapping(value = "/desafio")
public class AgenciaController {
	
	@Autowired
	private AgenciaService agenciaService;
	
	@PostMapping(value = "/cadastro")
	public ResponseEntity<AgenciaDto> save(@RequestBody AgenciaDto agenciaDto) {
		return ResponseEntity.ok().body(agenciaService.save(agenciaDto));
	}
		
	@GetMapping(value = "/buscarAgencia")
	public ResponseEntity<AgenciaDto> findAgencia(@RequestParam double posicaoX, @RequestParam double posicaoY) {
		return ResponseEntity.ok().body(agenciaService.findAgencia(posicaoX, posicaoY));
	}
	
	@GetMapping(value = "/distancia")
	public ResponseEntity<List<AgenciaDistanciaDto>> findAgenciasProximas(@RequestParam double posicaoX, @RequestParam double posicaoY) {
		return ResponseEntity.ok().body(agenciaService.getAgenciaProxima(posicaoX, posicaoY));
	}
	
	@PutMapping(value = "/atualizarNomeAgencia")
	public ResponseEntity<AgenciaDto> updateNomeAgencia(@RequestBody AgenciaDto agenciaDto) {
		return ResponseEntity.ok().body(agenciaService.updateNomeAgencia(agenciaDto));
	}
	
	@DeleteMapping(value = "/deletarAgencia")
	public ResponseEntity<String> deleteAgencia(@RequestParam double posicaoX, @RequestParam double posicaoY) {
		agenciaService.deleteAgencia(posicaoX, posicaoY);
		return ResponseEntity.ok().body("Agencia deletada com sucesso");
	}

}
