package com.julio.energiaInteligente.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.julio.energiaInteligente.domain.ProgramacaoEstado;
import com.julio.energiaInteligente.domain.ProgramacaoExcedente;
import com.julio.energiaInteligente.domain.ProgramacaoMudanca;

@Configuration
public class JacksonConfig {
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(ProgramacaoEstado.class);
				objectMapper.registerSubtypes(ProgramacaoExcedente.class);
				objectMapper.registerSubtypes(ProgramacaoMudanca.class);
				super.configure(objectMapper);
			}
		};
		return builder;
	}
}