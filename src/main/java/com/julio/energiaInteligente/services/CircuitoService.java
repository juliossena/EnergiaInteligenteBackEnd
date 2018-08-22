package com.julio.energiaInteligente.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julio.energiaInteligente.domain.Circuito;
import com.julio.energiaInteligente.dto.CircuitoDTO;
import com.julio.energiaInteligente.repositories.CircuitoRepository;
import com.julio.energiaInteligente.security.UserSS;
import com.julio.energiaInteligente.services.exceptions.ObjectNotFoundException;

@Service
public class CircuitoService {

	@Autowired
	private CircuitoRepository repo;
	
	public Circuito find(Integer id) {
		Optional<Circuito> obj = repo.findById(id);
	 
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Circuito.class.getName()));
	}
	
	public List<Circuito> findAll() {
		UserSS user = UserService.authenticated();
		
		List<Circuito> obj = repo.findByUsuario_id(user.getId());
	 
		return obj;
	}
	
	public Circuito fromDTO(CircuitoDTO objDto) {
		return new Circuito(objDto.getId(), null, null, objDto.getNome(), objDto.getLigado(), null);
	}
	
	public Circuito update(Circuito obj) {
		Circuito newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(Circuito newObj, Circuito obj) {
		newObj.setNome(obj.getNome());
		newObj.setLigado(obj.getLigado());
	}
	
}
