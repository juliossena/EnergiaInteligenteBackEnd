package com.julio.energiaInteligente.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julio.energiaInteligente.domain.Circuito;
import com.julio.energiaInteligente.domain.Dispositivos;
import com.julio.energiaInteligente.domain.Usuario;
import com.julio.energiaInteligente.repositories.DispositivosRepository;
import com.julio.energiaInteligente.repositories.UsuarioRepository;
import com.julio.energiaInteligente.services.exceptions.ObjectNotFoundException;

@Service
public class DispositivosService {

	@Autowired
	private DispositivosRepository repo;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
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

	public Dispositivos insert(Dispositivos obj, String emailUsuario) {
		obj.setId(null);
		obj.setUsuario(usuarioRepository.findByEmail(emailUsuario));
		obj.setUltimoAcesso(new Date());
		obj = repo.save(obj);
		return obj;
	}
	
	public void disableDispositivo(String idDispositivo) {
		Optional<Dispositivos> newObj = repo.findByIdDispositivo(idDispositivo);
		newObj.get().setAtivo(false);
		repo.save(newObj.get());
	}

	public Dispositivos update(Dispositivos obj, String emailUsuario) {
		Dispositivos newObj = find(obj.getId());
		updateData(newObj, usuarioRepository.findByEmail(emailUsuario));
		return repo.save(newObj);
	}

	private void updateData(Dispositivos newObj, Usuario usuario) {
		newObj.setAtivo(true);
		newObj.setUltimoAcesso(new Date());
		newObj.setUsuario(usuario);
	}

}
