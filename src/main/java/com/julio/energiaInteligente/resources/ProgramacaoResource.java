package com.julio.energiaInteligente.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.julio.energiaInteligente.services.ProgramacaoService;


@RestController
@RequestMapping(value = "/programacao")
public class ProgramacaoResource {

	@Autowired
	private ProgramacaoService service;
	
}
