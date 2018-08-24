package com.julio.energiaInteligente.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.julio.energiaInteligente.domain.Dispositivos;


@Repository
public interface DispositivosRepository extends JpaRepository<Dispositivos, Integer> {
	
	Optional<Dispositivos> findByIdDispositivo(String idDispositivo);
	
}
