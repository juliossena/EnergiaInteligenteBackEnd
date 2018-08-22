package com.julio.energiaInteligente.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julio.energiaInteligente.domain.Programacao;
import com.julio.energiaInteligente.repositories.ProgramacaoRepository;

@Service
public class ProgramacaoService {

	@Autowired
	private ProgramacaoRepository repo;
	
	@Autowired
	private CircuitoService circuitoService;
	
	public List<Programacao> findAllByCircuito(Integer idCircuito) {

		List<Programacao> obj = repo.findByCircuito_id(idCircuito);

		return obj;
	}
	
	public Programacao insert(Programacao obj) {
		obj.setId(null);

		
		obj.setCircuito(circuitoService.find(obj.getCircuito().getId()));

		obj = repo.save(obj);
		return obj;
	}
}
