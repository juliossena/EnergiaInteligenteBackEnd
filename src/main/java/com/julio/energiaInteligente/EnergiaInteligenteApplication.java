package com.julio.energiaInteligente;

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


	public static void main(String[] args) {
		SpringApplication.run(EnergiaInteligenteApplication.class, args);
		
		int qtdPaginas = 71;
		String pesquisa = "uno";
		for (int i = 1 ; i <= qtdPaginas ; i++) {
			String msg = consultarOLX(pesquisa, i);
			ArrayList<String> links = listaLinks(msg);
			
			for (String link : links) {
				imprimirInformacoes(consultarCarroOLX(link));
			}
		}
	}

	@Override
	public void run(String... args) throws Exception {

		

	}
	
	public static void imprimirInformacoes(String msgCarro) {
		System.out.print(informacao("\"name\" :", msgCarro) + "\t");
		System.out.print(informacao(",\"brand\":", msgCarro) + "\t");
		System.out.print(informacao(",\"model\":", msgCarro) + "\t");
		System.out.print(informacao(",\"modelDate\":", msgCarro) + "\t");
		System.out.print(informacao(",\"numberOfDoors\":", msgCarro) + "\t");
		System.out.print(informacao(",\"vehicleTransmission\":", msgCarro) + "\t");
		System.out.print(informacao(",\"fuelType\":", msgCarro) + "\t");
		System.out.print(informacao(",\"mileageFromOdometer\":", msgCarro) + "\t");
		System.out.print(informacao(",\"bodyType\":", msgCarro) + "\t");
		System.out.println(informacao("\"price\" :", msgCarro));
	}
	
	public static String informacao(String procura, String msgCarro) {
		String split[] = msgCarro.split(procura);
		if (split.length > 1) {
			String split2[] = split[1].split("\"");
			if (split2.length > 1) {
				return split2[1];
			}
		}
		
		return null;
	}
	
	public static ArrayList<String> listaLinks(String msg) {
		ArrayList<String> retornos = new ArrayList<String>();
		String split[] = msg.split("<li class=\"item\" data-list_id");
		for (int i = 1 ; i < split.length ; i++) {
			String split2[] = split[i].split(" href=\"");
			if (split2.length > 1) {
				String split3[] = split2[1].split("\" title=\"");
				if (split3.length > 1) {
					String split4[] = split3[0].split("[?]");
					retornos.add(split4[0]);
				}
			}
		}
		return retornos;
	}
	
	public static String consultarOLX(String nome, int pagina) {
		String url = "https://mg.olx.com.br/belo-horizonte-e-regiao/autos-e-pecas/carros-vans-e-utilitarios?o=" + pagina + "&q=" + nome;

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set(CONTENT_TYPE, APPLICATION_JSON);
			RestTemplate restTemplate = new RestTemplate();
			return restTemplate
					.exchange(url, HttpMethod.GET, new HttpEntity<Object>(headers), String.class)
					.getBody();

		} catch (ResourceAccessException e) {
		}
		return "";
	}
	
	public static String consultarCarroOLX(String url) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set(CONTENT_TYPE, APPLICATION_JSON);
			RestTemplate restTemplate = new RestTemplate();
			return restTemplate
					.exchange(url, HttpMethod.GET, new HttpEntity<Object>(headers), String.class)
					.getBody();

		} catch (ResourceAccessException e) {
		}
		return "";
	}
}
