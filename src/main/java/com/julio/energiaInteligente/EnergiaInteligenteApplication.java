package com.julio.energiaInteligente;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EnergiaInteligenteApplication implements CommandLineRunner {
	
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String APPLICATION_JSON = "application/json";


	public static void main(String[] args) throws IOException {
		SpringApplication.run(EnergiaInteligenteApplication.class, args);
	    
	}

	@Override
	public void run(String... args) throws Exception {

	}
	
}
