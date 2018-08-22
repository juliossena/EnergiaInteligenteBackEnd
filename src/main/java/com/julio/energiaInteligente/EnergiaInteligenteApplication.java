package com.julio.energiaInteligente;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.julio.energiaInteligente.repositories.MedicaoRepository;


@SpringBootApplication
public class EnergiaInteligenteApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EnergiaInteligenteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		

	}
}
