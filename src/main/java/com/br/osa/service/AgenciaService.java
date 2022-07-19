package com.br.osa.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.osa.mapper.AgenciaMapper;
import com.br.osa.model.Agencia;
import com.br.osa.model.dto.AgenciaDistanciaDto;
import com.br.osa.model.dto.AgenciaDto;
import com.br.osa.repository.AgenciaRepository;
import com.br.osa.service.exceptions.ObjectExistInBDException;
import com.br.osa.service.exceptions.ObjectNotFounException;

@Service
public class AgenciaService {
	
	@Autowired
	private AgenciaRepository agenciaRepository;

	@Autowired
	private AgenciaMapper agenciaMapper;
	
	
	public AgenciaDto save(AgenciaDto agenciaDto) {
		if (existeAgencia(agenciaDto.getPosicaoX(), agenciaDto.getPosicaoY()))
			throw new ObjectExistInBDException("Agencia já existe na base de dados!");

		return agenciaMapper.convertAgenciaToDto(agenciaRepository.save(agenciaMapper.convertDtoToAgencia(agenciaDto)));
	}
	
	public AgenciaDto findAgencia(double posicaoX, double posicaoY ) {		
		return agenciaMapper.convertAgenciaToDto(agenciaRepository.findAgencia(posicaoX, posicaoY)
					.orElseThrow(() -> new ObjectNotFounException("Agencia não encontrada.")));
	}
	
	public AgenciaDto updateNomeAgencia(AgenciaDto agenciaDto) {
		AgenciaDto agencia = findAgencia(agenciaDto.getPosicaoX(), agenciaDto.getPosicaoY());
		agencia.setNomeAgencia(agenciaDto.getNomeAgencia());
		return agenciaMapper.convertAgenciaToDto(agenciaRepository.save(agenciaMapper.convertDtoToAgencia(agencia)));
	}
	
	public void deleteAgencia(double posicaoX, double posicaoY) {
		agenciaRepository.delete(agenciaMapper.convertDtoToAgencia(findAgencia(posicaoX, posicaoY)));
	}
		
	public List<AgenciaDistanciaDto> getAgenciaProxima(double posicaoX, double posicaoY) {
		List<AgenciaDistanciaDto> listAgenciaDistancia = new ArrayList<>();

		agenciaRepository.findAll().forEach(agencia -> {
			double distancia = calcularDistancia(posicaoX, posicaoY, agencia);
			listAgenciaDistancia.add(new AgenciaDistanciaDto(agencia.getNomeAgencia(), distancia));
        });
		listAgenciaDistancia.sort(Comparator.comparing(AgenciaDistanciaDto::getDistanciaAgencia));
		return listAgenciaDistancia;
	}

	private double calcularDistancia(double userPosicaoX, double userPosicaoY, Agencia agencia) {
		double distancia = Math.sqrt(Math.pow(userPosicaoX - agencia.getPosicaoX(), 2) + Math.pow(userPosicaoY - agencia.getPosicaoY(), 2));
		DecimalFormat saida = new DecimalFormat("##.##");
		return Double.parseDouble(saida.format(distancia).replace(",", "."));
	}
	
	private boolean existeAgencia(double posicaoX, double posicaoY ) {		
		return agenciaRepository.findAgencia(posicaoX, posicaoY).isPresent();
	}
	
	
}
