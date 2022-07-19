package com.br.osa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.br.osa.model.Agencia;

public interface AgenciaRepository extends JpaRepository<Agencia, Long> {

	@Query("from Agencia where posicaoX = :posicaoX and posicaoY = :posicaoY")
    public Optional<Agencia> findAgencia(@Param("posicaoX") double posicaoX, @Param("posicaoY") double posicaoY);
	
}
