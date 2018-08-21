package com.julio.energiaInteligente.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.julio.energiaInteligente.domain.ConfiguracaoCircuito;


@Repository
public interface ConfiguracaoCircuitoRepository extends JpaRepository<ConfiguracaoCircuito, Integer> {

}
