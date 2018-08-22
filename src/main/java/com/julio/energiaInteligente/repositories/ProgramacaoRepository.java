package com.julio.energiaInteligente.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.julio.energiaInteligente.domain.Programacao;


@Repository
public interface ProgramacaoRepository extends JpaRepository<Programacao, Integer> {

}
