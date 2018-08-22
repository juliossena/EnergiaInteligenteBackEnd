package com.julio.energiaInteligente.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.julio.energiaInteligente.domain.ConfiguracaoCircuito;
import com.julio.energiaInteligente.services.ConfiguracaoCircuitoService;

@RestController
@RequestMapping(value = "/configuracaoCircuito")
public class ConfiguracaoCircuitoResource {

	@Autowired
	private ConfiguracaoCircuitoService service;

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<ConfiguracaoCircuito> update(@Valid @RequestBody ConfiguracaoCircuito obj) {
		obj = service.update(obj);

		return ResponseEntity.ok().body(obj);
	}
}
