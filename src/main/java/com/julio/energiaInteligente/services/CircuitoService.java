package com.julio.energiaInteligente.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julio.energiaInteligente.domain.Circuito;
import com.julio.energiaInteligente.repositories.CircuitoRepository;
import com.julio.energiaInteligente.services.exceptions.ObjectNotFoundException;

@Service
public class CircuitoService {

	@Autowired
	private CircuitoRepository repo;
	
	public Circuito find(Integer id) {
		Optional<Circuito> obj = repo.findById(id);
	 
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Circuito.class.getName()));
	}
}
