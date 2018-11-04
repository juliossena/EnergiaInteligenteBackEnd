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
import com.julio.energiaInteligente.response.CircuitoResponse;
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
	public ResponseEntity<List<CircuitoResponse>> find(@PathVariable Long dataInicial, @PathVariable Long dataFinal) {
		List<CircuitoResponse> retorno = new ArrayList<>();
		
		List<Circuito> obj = circuitoService.findAll();
		
		for(Circuito circuito: obj) {
			HistoricoResponse historicoResponse = new HistoricoResponse();
			try {
				historicoResponse.setConsumoPico(medicaoService.findPico(new Date(dataInicial), new Date(dataFinal), circuito.getId()));
				historicoResponse.setMediaConsumo(medicaoService.findAVG(new Date(dataInicial), new Date(dataFinal), circuito.getId()));
				historicoResponse.setHorarioPico(medicaoService.findHorarioPico(new Date(dataInicial), new Date(dataFinal), circuito.getId()));
				historicoResponse.setConsumoTotal(medicaoService.findConsumoTotal(new Date(dataInicial), new Date(dataFinal), circuito.getId()));
				historicoResponse.setConsumoReais(historicoResponse.getConsumoTotal() * circuito.getConfiguracaoCircuito().getCustoPorW());
			}catch (Exception e) {
				historicoResponse.setConsumoPico(0);
				historicoResponse.setMediaConsumo(0);
				historicoResponse.setHorarioPico(null);
				historicoResponse.setConsumoTotal(0);
				historicoResponse.setConsumoReais(0);
			}
			
			
			circuito.setMedicoes(null);
			CircuitoResponse circuitoResponse = new CircuitoResponse(circuito);
			circuitoResponse.setHistoricoResponse(historicoResponse);
			retorno.add(circuitoResponse);
		}
		
		return ResponseEntity.ok().body(retorno);
	}

}
