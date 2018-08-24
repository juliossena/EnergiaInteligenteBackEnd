package com.julio.energiaInteligente.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.julio.energiaInteligente.domain.Dispositivos;
import com.julio.energiaInteligente.domain.Usuario;
import com.julio.energiaInteligente.dto.CredenciaisDTO;
import com.julio.energiaInteligente.response.LoginResponse;
import com.julio.energiaInteligente.services.DispositivosService;
import com.julio.energiaInteligente.services.UsuarioService;
import com.julio.energiaInteligente.services.exceptions.ObjectNotFoundException;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	private JWTUtil jwtUtil;
	
	@Autowired
	private DispositivosService dispositivosService;
	
	@Autowired
	private UsuarioService usuarioService;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {
			// Converte as credenciais que vem no Body para o tipo CredenciaisDTO
			CredenciaisDTO creds = new ObjectMapper().readValue(req.getInputStream(), CredenciaisDTO.class);
			

			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getEmail(),
					creds.getSenha(), new ArrayList<>());

			Authentication auth = authenticationManager.authenticate(authToken);
			
			if (auth.isAuthenticated()) {
				try {
					Dispositivos dispositivos = dispositivosService.findIdCelular(creds.getIdCelular());
					dispositivosService.update(dispositivos);
				} catch (ObjectNotFoundException e) {
					Usuario user = usuarioService.findByEmail(creds.getEmail());
					Dispositivos dispositivos = new Dispositivos(null, creds.getIdCelular(), true, null, user);
					dispositivosService.insert(dispositivos, user);
				}
			}
			return auth;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		
		String email = ((UserSS) auth.getPrincipal()).getUsername();
		String token = jwtUtil.generateToken(email);
		LoginResponse login = new LoginResponse("Bearer " + token);
		String json = new Gson().toJson(login);
		res.addHeader("Authorization", "Bearer " + token);
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(json);
	}

}
