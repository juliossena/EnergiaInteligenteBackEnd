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
import com.julio.energiaInteligente.domain.Medicao;
import com.julio.energiaInteligente.services.CircuitoService;
import com.julio.energiaInteligente.services.MedicaoService;


@RestController
@RequestMapping(value = "/medicoes")
public class MedicaoResource {

	@Autowired
	private MedicaoService service;
	
	@Autowired
	private CircuitoService circuitoService;
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Medicao> find(@PathVariable Integer id) {
		Medicao obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value = "last/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Medicao>> findLast(@PathVariable Integer id) {
		List<Medicao> list = service.search(id);
		return ResponseEntity.ok().body(list);

	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Circuito> insert(@Valid @RequestBody Medicao obj) throws InterruptedException {
		obj = service.insert(obj);
		
		Circuito circuito = circuitoService.aguarda(obj);
		
		return ResponseEntity.ok().body(circuito);
	}
}
