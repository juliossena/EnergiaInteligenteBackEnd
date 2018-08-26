package com.julio.energiaInteligente.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.julio.energiaInteligente.domain.ProgramacaoMudancaRepetir;


@Repository
public interface ProgramacaoRepetirRepository extends JpaRepository<ProgramacaoMudancaRepetir, Integer> {
	

}
