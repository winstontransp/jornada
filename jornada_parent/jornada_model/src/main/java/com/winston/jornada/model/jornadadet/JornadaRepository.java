package com.winston.jornada.model.jornadadet;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.powerlogic.jcompany.commons.PlcBaseContextVO;
import com.powerlogic.jcompany.commons.annotation.PlcAggregationIoC;
import com.powerlogic.jcompany.commons.config.stereotypes.SPlcRepository;
import com.powerlogic.jcompany.model.bindingtype.PlcUpdateBefore;
import com.winston.jornada.entity.Jornada;
import com.winston.jornada.entity.JornadaEvento;
import com.winston.jornada.entity.Macro;
import com.winston.jornada.entity.Motorista;
import com.winston.jornada.entity.ReturnMessage;
import com.winston.jornada.entity.TipoEvento;
import com.winston.jornada.model.AppBaseRepository;
import com.winston.jornada.persistence.jpa.jornadadet.JornadaDAO;
import com.winston.jornada.persistence.jpa.motorista.MotoristaDAO;

/**
 * Classe de Modelo gerada pelo assistente
 */

@SPlcRepository
@PlcAggregationIoC(clazz = Jornada.class)
public class JornadaRepository extends AppBaseRepository {

	@Inject
	private JornadaDAO dao;
	
	@Inject
	private MotoristaDAO motDao;

	
	public void antesAtualizar (@Observes @PlcUpdateBefore PlcBaseContextVO context) {
		List<JornadaEvento> eventos = null;

		Jornada jornada = (Jornada) context.getEntityForExtension();

		if (jornada != null) {
			removerEventos(context, jornada);
			eventos = criarEventos(context, jornada);
			gravarEventos(context, jornada, eventos);
		}
	}

	private List<JornadaEvento> criarEventos(PlcBaseContextVO context, Jornada jornada) {
		List<ReturnMessage> rms = jornada.getReturnMessage();
		List<JornadaEvento> eventos = new ArrayList<JornadaEvento>();
	
		JornadaEvento evento = null;
		
		for (ReturnMessage returnMessage : rms) {
			Macro macro = returnMessage.getMacro();
			
			if (macro != null && macro.getCodigo() != null) {

				switch (macro.getCodigo()) {
				case 1: // Início de Jornada
					tratarMotorista(context, jornada, returnMessage);
					
					jornada.setData(returnMessage.getPositionTime());
					
					evento = new JornadaEvento();
					evento.setTipo(TipoEvento.J);
					evento.setInicio(returnMessage.getPositionTime());
					evento.setJornada(jornada);
					eventos.add(evento);
					break;
					
				case 2: // Intervalo para refeição
					evento = new JornadaEvento();
					evento.setTipo(TipoEvento.R);
					evento.setInicio(returnMessage.getPositionTime());
					evento.setJornada(jornada);
					eventos.add(evento);
					break;

				case 3: // Reinicio de Direção
					evento = recuperaEventoPorTipo(eventos, TipoEvento.R);
					
					if (evento != null) {
						evento.setFim(returnMessage.getPositionTime());
						evento.setDuracao(calcularDuracao(evento.getInicio(), evento.getFim()));
					}
					
					break;
					
				case 4: // Fim de Jornada
					evento = recuperaEventoPorTipo(eventos, TipoEvento.J);
					evento.setFim(returnMessage.getPositionTime());
					evento.setDuracao(calcularDuracao(evento.getInicio(), evento.getFim()));
					break;
					
				case 5: // Início de Carregamento
					evento = new JornadaEvento();
					evento.setTipo(TipoEvento.C);
					evento.setInicio(returnMessage.getPositionTime());
					evento.setJornada(jornada);
					eventos.add(evento);
					break;
					
				case 6: // Fim de Carregamento
					evento = recuperaEventoPorTipo(eventos, TipoEvento.C);
					evento.setFim(returnMessage.getPositionTime());
					evento.setDuracao(calcularDuracao(evento.getInicio(), evento.getFim()));
					break;
					
				case 7: // Início de Descarregamento
					evento = new JornadaEvento();
					evento.setTipo(TipoEvento.D);
					evento.setInicio(returnMessage.getPositionTime());
					evento.setJornada(jornada);
					eventos.add(evento);
					break;
					
				case 8: // Fim de Descarregamento
					evento = recuperaEventoPorTipo(eventos, TipoEvento.D);
					evento.setFim(returnMessage.getPositionTime());
					evento.setDuracao(calcularDuracao(evento.getInicio(), evento.getFim()));
					break;
					
				default:
					break;
				}
			}
		}
		
		return eventos;
	}

	private JornadaEvento recuperaEventoPorTipo(List<JornadaEvento> eventos, TipoEvento tipo) {
		JornadaEvento returnEvent = null;
		
		// Deixa o 'for' ir até o final para obter sempre o último evento do tipo indicado
		for (JornadaEvento evento : eventos) {
			
			if (evento.getTipo().equals(tipo)) {
				returnEvent = evento;
			}
		}
		
		return returnEvent;
	}

	private void tratarMotorista(PlcBaseContextVO context, Jornada jornada, ReturnMessage returnMessage)
			throws NumberFormatException {
		String textoMatricula = returnMessage.getMacroText();
		
		if (textoMatricula != null) {
			Motorista motorista = jornada.getMotorista();
			Long matriculaOriginal = motorista.getMatricula();
			Long matriculaInformada = new Long(textoMatricula.substring(1));
			
			// Verifica se alterou a a matricula
			if (matriculaOriginal.compareTo(matriculaInformada) != 0) {
				
				// Caso tenha alterado deve-se recuperar o novo motorista
				motorista = motDao.findMotoristaPorMatricula(context, matriculaInformada);
				
				if (motorista != null) { 
					jornada.setMotorista(motorista);
				}
			}
		} else {
			jornada.setMotorista(null);
		}
	}

	private void removerEventos(PlcBaseContextVO context, Jornada jornada) {
		List<JornadaEvento> eventos = jornada.getEventos();

		for (JornadaEvento jornadaEvento : eventos) {
			 dao.delete(context, jornadaEvento);
		}

		eventos.clear();
	}
	
	private void gravarEventos(PlcBaseContextVO context, Jornada jornada, List<JornadaEvento> eventos) {

		for (JornadaEvento jornadaEvento : eventos) {
			dao.insert(context, jornadaEvento);
		}

		jornada.setEventos(eventos);
	}

	
}
