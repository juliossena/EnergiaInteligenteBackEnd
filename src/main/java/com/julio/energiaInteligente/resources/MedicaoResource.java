package com.julio.energiaInteligente.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.julio.energiaInteligente.domain.Medicao;
import com.julio.energiaInteligente.services.MedicaoService;


@RestController
@RequestMapping(value = "/medicoes")
public class MedicaoResource {

	@Autowired
	private MedicaoService service;
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Medicao> find(@PathVariable Integer id) {
		Medicao obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Medicao obj) throws InterruptedException {
		obj = service.insert(obj);
		
		Thread.sleep(obj.getCircuito().getConfiguracaoCircuito().getTempoAtualizacao() * 1000);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
