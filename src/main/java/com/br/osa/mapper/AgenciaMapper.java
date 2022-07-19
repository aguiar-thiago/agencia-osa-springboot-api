package com.br.osa.mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.br.osa.model.Agencia;
import com.br.osa.model.dto.AgenciaDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AgenciaMapper {

	private final ModelMapper mapper;
	
	public AgenciaDto convertAgenciaToDto(Agencia agencia) {
		return mapper.map(agencia, AgenciaDto.class);
	}
	
	public AgenciaDto convertOptionalToDto(Optional<Agencia> agencia) {
		return mapper.map(agencia, AgenciaDto.class);
	}
	
	public Agencia convertDtoToAgencia(AgenciaDto agenciaDto) {
		return mapper.map(agenciaDto, Agencia.class);
	}
	
	public List<AgenciaDto> convertListDto(List<Agencia> listAgencia) {
		return listAgencia.stream().map(this:: convertAgenciaToDto).collect(Collectors.toList());
	}
	
}
