package com.julio.energiaInteligente.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.julio.energiaInteligente.domain.Circuito;
import com.julio.energiaInteligente.domain.ConfiguracaoCircuito;
import com.julio.energiaInteligente.domain.Usuario;
import com.julio.energiaInteligente.repositories.CircuitoRepository;
import com.julio.energiaInteligente.repositories.ConfiguracaoCircuitoRepository;
import com.julio.energiaInteligente.repositories.UsuarioRepository;

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CircuitoRepository circuitoRepository;
	
	@Autowired
	private ConfiguracaoCircuitoRepository configuracaoCircuitoRepository;

	public void instantiateTestDatabase() throws ParseException {
		Usuario user = new Usuario(null, "julio", "juliosouzasefna@gmail.com", pe.encode("123"), "321", "101.265.196-76");
		ConfiguracaoCircuito confCir = new ConfiguracaoCircuito(null, "123", "123", 15, 5);
		
		Circuito circuito = new Circuito(null, user, pe.encode("haithaibiriou"), "circuito 1", true, confCir);
		
		configuracaoCircuitoRepository.saveAll(Arrays.asList(confCir));
		usuarioRepository.saveAll(Arrays.asList(user));
		circuitoRepository.saveAll(Arrays.asList(circuito));
		
	}
}
