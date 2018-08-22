package com.julio.energiaInteligente.resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.julio.energiaInteligente.domain.Circuito;
import com.julio.energiaInteligente.response.HistoricoResponse;
import com.julio.energiaInteligente.services.CircuitoService;
import com.julio.energiaInteligente.services.MedicaoService;

@RestController
@RequestMapping(value = "/historico")
public class HistoricoResource {

	@Autowired
	private CircuitoService circuitoService;
	
	@Autowired
	private MedicaoService medicaoService;
	
	@RequestMapping(value = "inicio={dataInicial}&final={dataFinal}", method = RequestMethod.GET)
	public ResponseEntity<List<HistoricoResponse>> find(@PathVariable Date dataInicial, @PathVariable Date dataFinal) {
		List<HistoricoResponse> retorno = new ArrayList<>();
		
		List<Circuito> obj = circuitoService.findAll();
		
		for(Circuito circuito: obj) {
			HistoricoResponse historicoResponse = new HistoricoResponse();
			historicoResponse.setConsumoPico(medicaoService.findPico(dataInicial, dataFinal, circuito.getId()));
			
			retorno.add(historicoResponse);
		}
		
		return ResponseEntity.ok().body(retorno);
	}

}
