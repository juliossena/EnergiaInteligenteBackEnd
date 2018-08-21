package com.julio.energiaInteligente.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.julio.energiaInteligente.domain.Circuito;


@Repository
public interface CircuitoRepository extends JpaRepository<Circuito, Integer> {

}
