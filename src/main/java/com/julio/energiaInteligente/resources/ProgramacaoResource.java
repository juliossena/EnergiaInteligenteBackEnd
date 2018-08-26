package com.julio.energiaInteligente.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.julio.energiaInteligente.domain.Circuito;
import com.julio.energiaInteligente.domain.Programacao;
import com.julio.energiaInteligente.domain.ProgramacaoMudanca;
import com.julio.energiaInteligente.services.ProgramacaoService;

@RestController
@RequestMapping(value = "/programacao")
public class ProgramacaoResource {

	@Autowired
	private ProgramacaoService service;

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Programacao>> find(@PathVariable Integer id) {
		List<Programacao> obj = service.findAllByCircuito(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value = "{idCircuito}", method = RequestMethod.POST)
	public ResponseEntity<Programacao> insert(@Valid @RequestBody Programacao obj, @PathVariable Integer idCircuito) {
		Circuito circuito = new Circuito();
		circuito.setId(idCircuito);
		obj.setCircuito(circuito);
		
		obj = service.insert(obj);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value = "2/{idCircuito}", method = RequestMethod.POST)
	public ResponseEntity<ProgramacaoMudanca> insertMudanca(@Valid @RequestBody ProgramacaoMudanca obj, @PathVariable Integer idCircuito) {
		Circuito circuito = new Circuito();
		circuito.setId(idCircuito);
		obj.setCircuito(circuito);
		
		obj = service.insertMudanca(obj);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Programacao> update(@Valid @RequestBody Programacao obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
