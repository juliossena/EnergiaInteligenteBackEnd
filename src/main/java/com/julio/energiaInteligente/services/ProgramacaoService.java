package com.julio.energiaInteligente.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.julio.energiaInteligente.domain.Programacao;
import com.julio.energiaInteligente.domain.ProgramacaoMudanca;
import com.julio.energiaInteligente.domain.ProgramacaoMudancaRepetir;
import com.julio.energiaInteligente.repositories.ProgramacaoRepetirRepository;
import com.julio.energiaInteligente.repositories.ProgramacaoRepository;
import com.julio.energiaInteligente.services.exceptions.DataIntegrityException;
import com.julio.energiaInteligente.services.exceptions.ObjectNotFoundException;

@Service
public class ProgramacaoService {

	@Autowired
	private ProgramacaoRepository repo;
	
	@Autowired
	private ProgramacaoRepetirRepository programacaoRepetirRepository;
	
	@Autowired
	private CircuitoService circuitoService;
	
	public Programacao find(Integer id) {
		Optional<Programacao> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Programacao.class.getName()));
	}
	
	public List<Programacao> findAllByCircuito(Integer idCircuito) {

		List<Programacao> obj = repo.findByCircuito_id(idCircuito);

		return obj;
	}
	
	public Programacao insert(Programacao obj) {
		obj.setId(null);
		
		obj.setCircuito(circuitoService.find(obj.getCircuito().getId()));

		obj = repo.save(obj);
		return obj;
	}
	
	public ProgramacaoMudanca insertMudanca(ProgramacaoMudanca obj) {
		obj.setId(null);
		
		obj.setCircuito(circuitoService.find(obj.getCircuito().getId()));
		obj = repo.save(obj);
		
		for (ProgramacaoMudancaRepetir programacaoMudancaRepetir : obj.getRepeticoes()) {
			programacaoMudancaRepetir.setProgramacaoMudanca(obj);
			programacaoRepetirRepository.save(programacaoMudancaRepetir);
		}
		
		return obj;
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir");
		}
	}
	
	public Programacao update(Programacao obj) {
		Programacao newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(Programacao newObj, Programacao obj) {
		newObj.setNome(obj.getNome());
	}
}
