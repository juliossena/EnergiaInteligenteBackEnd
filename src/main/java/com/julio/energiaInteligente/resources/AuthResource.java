package com.julio.energiaInteligente.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.julio.energiaInteligente.dto.EmailDTO;
import com.julio.energiaInteligente.security.JWTUtil;
import com.julio.energiaInteligente.security.UserSS;
import com.julio.energiaInteligente.services.AuthService;
import com.julio.energiaInteligente.services.DispositivosService;
import com.julio.energiaInteligente.services.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

	@Autowired
	private JWTUtil jWTUtil;
	
	@Autowired
	private AuthService service;
	
	@Autowired
	private DispositivosService dispositivosService;
	
	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jWTUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDTO) {
		service.sendNewPassword(objDTO.getEmail());
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/logoff/{idDispositivo}", method = RequestMethod.GET)
	public ResponseEntity<Void> logoff(@PathVariable String idDispositivo) {
		dispositivosService.disableDispositivo(idDispositivo);
		return ResponseEntity.noContent().build();
	}
}
