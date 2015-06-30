package com.winston.jornada.model.jornadadet;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.powerlogic.jcompany.commons.PlcBaseContextVO;
import com.powerlogic.jcompany.commons.PlcException;
import com.powerlogic.jcompany.model.PlcBaseParentRepository;
import com.winston.jornada.entity.Critica;
import com.winston.jornada.entity.CriticaParam;
import com.winston.jornada.entity.Jornada;
import com.winston.jornada.entity.JornadaCritica;
import com.winston.jornada.entity.JornadaEvento;
import com.winston.jornada.entity.Operacao;
import com.winston.jornada.entity.Turno;
import com.winston.jornada.persistence.jpa.jornadadet.JornadaDAO;

@ApplicationScoped
public class JornadaCriticaRepository extends PlcBaseParentRepository {

	private final String horarioMinimoInicioJornada = "horario_minimo_inicio_jornada";
	private final String horarioMaximoFimJornada = "horario_maximo_fim_jornada";
	private final String intervaloMinimoRefeicao = "intervalo_minimo_refeicao";
	private final String intervaloMinimoInterjornada = "intervalo_minimo_interjornada";
	private final String duracaoMaximaJornada = "duracao_maxima_jornada";
	
	@Inject
	private JornadaDAO dao;

	private Logger log = Logger.getLogger(JornadaCriticaRepository.class.getCanonicalName());
	
	public void antesAtualizar (PlcBaseContextVO context, Jornada jornada) throws PlcException {
		List<JornadaCritica> criticas = null;
		
		if (jornada != null) {
			removerCriticas(context, jornada);
			criticas = criarCriticas(context, jornada);
			gravarCriticas(context, jornada, criticas);
		}
	}

	private List<JornadaCritica> criarCriticas(PlcBaseContextVO context, Jornada jornada) throws PlcException {
		List<JornadaEvento> eventos = jornada.getEventos();
		List<JornadaCritica> criticasJornada = new ArrayList<JornadaCritica>();
	
		@SuppressWarnings("unchecked")
		List<CriticaParam> parametros = (List<CriticaParam>)dao.findAll(context, CriticaParam.class, "id");
		
		//TODO: Tratar o caso de não ter sido configurado o registro de parametros de criticas da jornada
		CriticaParam param = parametros.get(0);
		
		DateTimeFormatter timeFmt = DateTimeFormat.forPattern("HH:mm:ss");

		@SuppressWarnings("unchecked")
		List<Critica> criticas = (List<Critica>)dao.findAll(context, Critica.class, "id");
		
		// Recupera a operacao da Jornada
		Operacao operacao = jornada.getOperacao();
//		Operacao operacao = (Operacao) dao.findById(context, Operacao.class, jornada.getOperacao().getId());
		
		LocalTime paramInicio= LocalTime.parse(param.getHorarioMinimoInicioJornada(), timeFmt);
		LocalTime paramFim = LocalTime.parse(param.getHorarioMaximoFimJornada(), timeFmt);
		
		JornadaCritica criticaJornada = null;
		
		for (JornadaEvento evento : eventos) {
			
			if (evento != null && evento.getTipo() != null) {
				DateTime inicio = new DateTime(evento.getInicio());
				DateTime fim = new DateTime(evento.getFim());

				LocalTime horaInicio = new LocalTime(inicio);
				LocalTime horaFim = new LocalTime(fim);
				
				String duracao = evento.getDuracao();
				
				switch (evento.getTipo()) {
				case J: // Evento do tipo Jornada
					criticarJornada(jornada, operacao, criticasJornada, param, timeFmt, criticas, paramInicio, paramFim, horaInicio, horaFim, duracao);
					break;
				case R: // Evento do tipo refeição
					criticarRefeicao(jornada, criticasJornada, param, criticas, duracao);
					break;
				case C: // Evento do tipo Carregamento
					break;
				case D: // Evento do tipo Descarregamento
					break;
				default:
					break;
				}
			}
		}
		
		return criticasJornada;
	}

	/**
	 * Verifica se o motorista respeitou o tempo mínimo da refeição.
	 * @param jornada
	 * @param criticasJornada
	 * @param param
	 * @param criticas
	 * @param duracao
	 */
	private void criticarRefeicao(Jornada jornada,	List<JornadaCritica> criticasJornada, CriticaParam param, List<Critica> criticas, String duracao) {
		JornadaCritica criticaJornada;
		
		if (duracao.compareTo(param.getHorarioMaximoFimJornada()) < 0) {
			Critica critica = obterCritica(criticas, intervaloMinimoRefeicao); 
			
			criticaJornada = new JornadaCritica();
			criticaJornada.setMensagem("Duração da Refeição: " + duracao + ". Duração mínima permitida: " +  param.getIntervaloMinimoRefeicao() + " hora(s)");
			criticaJornada.setJornada(jornada);
			criticaJornada.setCritica(critica);
			criticasJornada.add(criticaJornada);
		}
	}

	/**
	 * Verifica se o motorista respeito o horário mínimo de início de jornada, o horário máximo de fim da jornada e o tempo de duração máxima da jornada.
	 * A crítica de início e fim de jornada só é realizada quando  a operação é do tipo Normal. 
	 * @param jornada
	 * @param criticasJornada
	 * @param param
	 * @param timeFmt
	 * @param criticas
	 * @param paramInicio
	 * @param paramFim
	 * @param horaInicio
	 * @param horaFim
	 * @param duracao
	 */
	private void criticarJornada(Jornada jornada, Operacao operacao, List<JornadaCritica> criticasJornada, CriticaParam param, DateTimeFormatter timeFmt, List<Critica> criticas,
			LocalTime paramInicio, LocalTime paramFim, LocalTime horaInicio, LocalTime horaFim, String duracao) {
		JornadaCritica criticaJornada;
		
		if (Turno.N.equals(operacao.getTurno()) && horaInicio.compareTo(paramInicio) < 0) {
			Critica critica = obterCritica(criticas, horarioMinimoInicioJornada); 
			
			criticaJornada = new JornadaCritica();
			criticaJornada.setMensagem("Início de Jornada: " + horaInicio.toString(timeFmt) + ". Horário mínimo permitido: " +  paramInicio.toString(timeFmt) + " hora(s)");
			criticaJornada.setJornada(jornada);
			criticaJornada.setCritica(critica);
			criticasJornada.add(criticaJornada);
		}

		if (Turno.N.equals(operacao.getTurno()) && horaFim.compareTo(paramFim) > 0) {
			Critica critica = obterCritica(criticas, horarioMaximoFimJornada); 
			
			criticaJornada = new JornadaCritica();
			criticaJornada.setMensagem("Fim da jornada: " + horaFim.toString(timeFmt) + ". Horário máximo permitido: " +  paramFim.toString(timeFmt) + " hora(s)");
			criticaJornada.setJornada(jornada);
			criticaJornada.setCritica(critica);
			criticasJornada.add(criticaJornada);
		}

		if (duracao.compareTo(param.getDuracaoMaximaJornada()) > 0) {
			Critica critica = obterCritica(criticas, duracaoMaximaJornada); 
			
			criticaJornada = new JornadaCritica();
			criticaJornada.setMensagem("Duração da Jornada: " + duracao + ". Duração máxima permitido: " +  param.getDuracaoMaximaJornada()  + " hora(s)");
			criticaJornada.setJornada(jornada);
			criticaJornada.setCritica(critica);
			criticasJornada.add(criticaJornada);
		}
	}

	private Critica obterCritica(List<Critica> criticas, String nomeParametro) {
		Critica criticaLocalizada = null;
		
		for (Critica critica : criticas) {
			
			if (nomeParametro.equals(critica.getCampoCriticaParam())) {
				criticaLocalizada = critica;
				break;
			}
		}
		
		return criticaLocalizada;
	}

	private void removerCriticas(PlcBaseContextVO context, Jornada jornada) {
		List<JornadaCritica> criticasJornada = jornada.getCriticas();

		for (JornadaCritica JornadaCritica : criticasJornada) {
			 dao.delete(context, JornadaCritica);
		}

		criticasJornada.clear();
	}
	
	private void gravarCriticas(PlcBaseContextVO context, Jornada jornada, List<JornadaCritica> criticasJornada) {

		for (JornadaCritica jornadaCritica : criticasJornada) {
			dao.insert(context, jornadaCritica);
		}

		jornada.setCriticas(criticasJornada);
	}

}
