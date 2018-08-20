package com.julio.energiaInteligente.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.julio.energiaInteligente.domain.Usuario;
import com.julio.energiaInteligente.repositories.UsuarioRepository;

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public void instantiateTestDatabase() throws ParseException {
		Usuario user = new Usuario(null, "julio", "juliosouzasena@gmail.com", pe.encode("123"), "321", "101.265.196-76");
		
		
		usuarioRepository.saveAll(Arrays.asList(user));
	}
}
