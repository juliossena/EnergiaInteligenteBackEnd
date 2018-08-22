package com.julio.energiaInteligente.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julio.energiaInteligente.repositories.ProgramacaoRepository;

@Service
public class ProgramacaoService {

	@Autowired
	private ProgramacaoRepository repo;
	
	
}
