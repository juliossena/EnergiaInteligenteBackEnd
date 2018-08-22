package com.julio.energiaInteligente.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.julio.energiaInteligente.domain.Programacao;


@Repository
public interface ProgramacaoRepository extends JpaRepository<Programacao, Integer> {
	
	List<Programacao> findByCircuito_id(Integer circuito_id);

}
