package com.julio.energiaInteligente.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.julio.energiaInteligente.domain.Usuario;
import com.julio.energiaInteligente.repositories.UsuarioRepository;
import com.julio.energiaInteligente.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	/*@Autowired
	private EmailService emailService;*/

	@Autowired
	private BCryptPasswordEncoder pe;

	private Random rand = new Random();

	public void sendNewPassword(String email) {
		/*Usuario usuario = usuarioRepository.findByEmail(email);
		if (usuario == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}

		String newPass = newPassword();
		usuario.setSenha(pe.encode(newPass));

		usuarioRepository.save(usuario);
		emailService.sendNewPasswordEmail(usuario, newPass);*/

	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { // Gera um digito
			return (char) (rand.nextInt(10) + 48);
		} else if (opt == 1) { // Gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		} else { // Gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}
}
