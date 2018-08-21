package com.julio.energiaInteligente.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julio.energiaInteligente.domain.Medicao;
import com.julio.energiaInteligente.repositories.MedicaoRepository;
import com.julio.energiaInteligente.services.exceptions.AuthorizationException;
import com.julio.energiaInteligente.services.exceptions.ObjectNotFoundException;

@Service
public class MedicaoService {

	@Autowired
	private MedicaoRepository repo;
	
	@Autowired
	private CircuitoService circuitoService;
	
	public Medicao find(Integer id) {
		Optional<Medicao> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Medicao.class.getName()));
	}
	
	public Medicao insert(Medicao obj) {
		obj.setId(null);
		obj.setHorario(new Date());
		
		String tokenEnviada = obj.getCircuito().getToken();
		obj.setCircuito(circuitoService.find(obj.getCircuito().getId()));
		
		if (tokenEnviada.equals(obj.getCircuito().getToken())) {
	        throw new AuthorizationException("Acesso negado");
	    }
		
		obj = repo.save(obj);
		return obj;
	}
}
