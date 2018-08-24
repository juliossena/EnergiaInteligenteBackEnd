package com.julio.energiaInteligente.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julio.energiaInteligente.domain.Circuito;
import com.julio.energiaInteligente.domain.Dispositivos;
import com.julio.energiaInteligente.domain.Usuario;
import com.julio.energiaInteligente.repositories.DispositivosRepository;
import com.julio.energiaInteligente.services.exceptions.ObjectNotFoundException;

@Service
public class DispositivosService {

	@Autowired
	private DispositivosRepository repo;
	
	public Dispositivos find(Integer id) {
		Optional<Dispositivos> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Circuito.class.getName()));
	}
	
	public Dispositivos findIdCelular(String idDispositivo) {
		Optional<Dispositivos> obj = repo.findByIdDispositivo(idDispositivo);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! " + Dispositivos.class.getName()));
	}

	public Dispositivos insert(Dispositivos obj, Usuario usuario) {
		obj.setId(null);
		obj.setUltimoAcesso(new Date());
		obj = repo.save(obj);
		return obj;
	}

	public Dispositivos update(Dispositivos obj) {
		Dispositivos newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(Dispositivos newObj, Dispositivos obj) {
		newObj.setAtivo(true);
		newObj.setUltimoAcesso(new Date());
		newObj.setUsuario(obj.getUsuario());
	}

}
