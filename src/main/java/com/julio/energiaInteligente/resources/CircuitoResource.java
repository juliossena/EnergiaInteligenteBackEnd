package com.julio.energiaInteligente.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.julio.energiaInteligente.domain.Circuito;
import com.julio.energiaInteligente.dto.CircuitoDTO;
import com.julio.energiaInteligente.services.CircuitoService;


@RestController
@RequestMapping(value = "/circuito")
public class CircuitoResource {

	@Autowired
	private CircuitoService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Circuito>> find() {
		List<Circuito> obj = service.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<CircuitoDTO> update(@Valid @RequestBody CircuitoDTO objDto) {
		Circuito obj = service.fromDTO(objDto);
		obj = service.update(obj);
		return ResponseEntity.ok().body(objDto);
	}
}
