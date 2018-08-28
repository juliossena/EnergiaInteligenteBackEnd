package com.julio.energiaInteligente.services;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.julio.energiaInteligente.domain.Circuito;
import com.julio.energiaInteligente.domain.Dispositivos;
import com.julio.energiaInteligente.domain.Medicao;
import com.julio.energiaInteligente.domain.Programacao;
import com.julio.energiaInteligente.domain.ProgramacaoExcedente;
import com.julio.energiaInteligente.domain.ProgramacaoMudanca;
import com.julio.energiaInteligente.domain.ProgramacaoMudancaRepetir;
import com.julio.energiaInteligente.domain.enums.TipoEstado;
import com.julio.energiaInteligente.domain.enums.TipoExcedente;
import com.julio.energiaInteligente.repositories.ProgramacaoRepetirRepository;
import com.julio.energiaInteligente.repositories.ProgramacaoRepository;
import com.julio.energiaInteligente.request.FirebaseRequest;
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
	
	private Programacao updateUltimaRequisicao(Programacao obj) {
		Programacao newObj = find(obj.getId());
		newObj.setUltimaRequisicao(new Date());
		newObj = repo.save(newObj);
		return newObj;
	}

	private void updateData(Programacao newObj, Programacao obj) {
		newObj.setNome(obj.getNome());
	}

	public Circuito verificaAlert(Medicao medicao) {
		Circuito circuito = medicao.getCircuito();
		List<Programacao> obj = repo.findByCircuito_id(medicao.getCircuito().getId());
		for (Programacao programacao : obj) {
			// Verifica se a programacao esta ativa
			if (programacao.isLigado()) {
				// Verifica se foi feito a solicitacao recentemente
				if (programacao.getUltimaRequisicao() == null || 
						programacao.getUltimaRequisicao().getTime()
						+ medicao.getCircuito().getConfiguracaoCircuito().getEsperaRepeticao() > new Date().getTime()) {

					if (programacao instanceof ProgramacaoExcedente) {
						ProgramacaoExcedente programacaoExcedente = (ProgramacaoExcedente) programacao;
						if (programacaoExcedente.getTipoExcedente() == TipoExcedente.MAIOR) {
							if (medicao.getPotencia() >= programacaoExcedente.getPotencia()) {
								enviarMensagemFirebase("ATENÇÃO!", "Foi acionado um alerta por excesso de consumo!",
										medicao.getCircuito().getUsuario().getDispositivos());
							}
						} else if (programacaoExcedente.getTipoExcedente() == TipoExcedente.MENOR) {
							if (medicao.getPotencia() <= programacaoExcedente.getPotencia()) {
								enviarMensagemFirebase("ATENÇÃO!", "Foi acionado um alerta por falta de consumo!",
										medicao.getCircuito().getUsuario().getDispositivos());
							}
						}
					}

					if (programacao instanceof ProgramacaoMudanca) {
						ProgramacaoMudanca programacaoMudanca = (ProgramacaoMudanca) programacao;
						if (programacaoMudanca.getRepetir()) {

							GregorianCalendar gc = new GregorianCalendar();
				            gc.setTime(new Date());
				            int diaDaSemana = gc.get(GregorianCalendar.DAY_OF_WEEK);
						} else {
							// Verifica se passou o horario expecificado no BD
							if (new Date().getTime() > programacaoMudanca.getHorario().getTime()) {
								if (new Date().getTime() < (programacaoMudanca.getHorario().getTime()
										+ (medicao.getCircuito().getConfiguracaoCircuito().getTempoAtualizacao()
												* 3000))) {
									programacao = updateUltimaRequisicao(programacao);
									if (programacaoMudanca.getTipoEstado() == TipoEstado.LIGADO) {
										circuito = circuitoService.ligarDesligarCircuito(medicao.getCircuito(), true);
									} else if (programacaoMudanca.getTipoEstado() == TipoEstado.DESLIGADO) {
										circuito = circuitoService.ligarDesligarCircuito(medicao.getCircuito(), false);
									}
								}
							}
						}

					}

				}

			}
		}
		return circuito;
	}

	private void enviarMensagemFirebase(String titulo, String mensagem, List<Dispositivos> dispositivos) {
		try {
			FirebaseRequest.enviarMensagens(titulo, mensagem, dispositivos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
