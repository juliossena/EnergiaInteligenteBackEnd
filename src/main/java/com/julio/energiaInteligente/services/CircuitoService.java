package com.julio.energiaInteligente.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julio.energiaInteligente.domain.Circuito;
import com.julio.energiaInteligente.domain.Medicao;
import com.julio.energiaInteligente.dto.CircuitoDTO;
import com.julio.energiaInteligente.repositories.CircuitoRepository;
import com.julio.energiaInteligente.security.UserSS;
import com.julio.energiaInteligente.services.exceptions.ObjectNotFoundException;

@Service
public class CircuitoService {

	@Autowired
	private CircuitoRepository repo;

	@Autowired
	private MedicaoService medicaoService;
	
	@Autowired
	private UsuarioService usuarioService;

	private static Map<Integer, Boolean> medicaoProcessamento;
	private Circuito circuito;

	public Circuito find(Integer id) {
		Optional<Circuito> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Circuito.class.getName()));
	}

	public Circuito insert(Circuito obj, Integer idUsuario) {
		obj.setId(null);

		obj.setUsuario(usuarioService.find(idUsuario));
		obj = repo.save(obj);
		return obj;
	}

	public List<Circuito> findAll() {
		UserSS user = UserService.authenticated();

		List<Circuito> obj = repo.findByUsuario_id(user.getId());

		return obj;
	}

	public List<Circuito> findAllMedicoes() {
		List<Circuito> circuitos = findAll();

		for (Circuito circuito : circuitos) {
			circuito.setMedicoes(medicaoService.search(circuito.getId()));
			
			Long tempoDelay = new Date().getTime() - (circuito.getConfiguracaoCircuito().getTempoAtualizacao() * 3000);
			if(circuito.getMedicoes() != null && circuito.getMedicoes().size() > 0) {
				if(circuito.getMedicoes().get(0).getHorario().getTime() < tempoDelay) {
					circuito.getMedicoes().get(0).setPotencia((float) 0);
				}
			}
			
		}

		return circuitos;
	}

	public Circuito fromDTO(CircuitoDTO objDto) {
		return new Circuito(objDto.getId(), null, null, objDto.getNome(), objDto.getLigado(), null);
	}

	public Circuito update(Circuito obj) {
		Circuito newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void interrompeEspera(Circuito circuito) {
		this.circuito = circuito;
		if (medicaoProcessamento != null) {
			medicaoProcessamento.put(circuito.getId(), true);
		}
	}

	public void iniciarProcessamentoMedicao(Medicao medicao) {
		if (medicaoProcessamento == null) {
			medicaoProcessamento = new HashMap<Integer, Boolean>();
		}

		medicaoProcessamento.put(medicao.getCircuito().getId(), false);
	}

	public Circuito aguarda(Medicao medicao, Long tempInicial) {
		this.circuito = medicao.getCircuito();
		iniciarProcessamentoMedicao(medicao);

		while (System.currentTimeMillis()
				- tempInicial <= (medicao.getCircuito().getConfiguracaoCircuito().getTempoAtualizacao() * 1000)) {

			Boolean b = medicaoProcessamento.get(medicao.getCircuito().getId());

			if (b != null && b.booleanValue()) {
				break;
			}
		}

		medicaoProcessamento.remove(medicao.getId());

		return this.circuito;

	}

	private void updateData(Circuito newObj, Circuito obj) {
		newObj.setNome(obj.getNome());
		newObj.setLigado(obj.getLigado());
	}
	
	public Circuito ligarDesligarCircuito(Circuito obj, boolean ligado) {
		obj.setLigado(ligado);
		return update(obj);
	}

}
