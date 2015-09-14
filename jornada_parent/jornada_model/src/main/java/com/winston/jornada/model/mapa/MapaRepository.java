package com.winston.jornada.model.mapa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.powerlogic.jcompany.commons.PlcBaseContextVO;
import com.powerlogic.jcompany.commons.PlcException;
import com.powerlogic.jcompany.commons.annotation.PlcAggregationIoC;
import com.powerlogic.jcompany.commons.config.stereotypes.SPlcRepository;
import com.powerlogic.jcompany.model.PlcBaseRepository;
import com.winston.jornada.entity.Jornada;
import com.winston.jornada.entity.Motorista;
import com.winston.jornada.entity.MotoristaAfastamento;
import com.winston.jornada.entity.mapa.DiaMapa;
import com.winston.jornada.entity.mapa.Mapa;
import com.winston.jornada.entity.mapa.StatusDiaMapa;
import com.winston.jornada.persistence.jpa.jornadadet.JornadaDAO;

/**
 * Classe de Modelo gerada pelo assistente
 */
 
@SPlcRepository 
@PlcAggregationIoC(clazz=Mapa.class)
public class MapaRepository extends PlcBaseRepository {

	@Inject
	JornadaDAO jornadaDAO;

	public Long recuperarContagemMapa(PlcBaseContextVO context, Mapa arg) throws PlcException {
		Calendar calendar = Calendar.getInstance();
		validarDatas(arg, calendar);
		
		List<Jornada> jornadas = jornadaDAO.findList(context, arg.getDataInicio(), arg.getDataFim(), arg.getMotorista(), arg.getTurno(), arg.getOperacao());

		long contaMapas = 0L;
		int i = 0;
		
		// Enquanto houver jornadas 
		while (jornadas.size() > 0 && i < jornadas.size()) {
			Jornada jornada = jornadas.get(i);
			Motorista motorista = jornada.getMotorista();
			Motorista motoristaAnt = motorista;
			
			// Enquanto for o mesmo motorista
			while (i < jornadas.size() && motorista.getId().compareTo(motoristaAnt.getId()) == 0 ) {
				i++;				
				
				if (i < jornadas.size()) {
					jornada = jornadas.get(i);
					motorista = jornada.getMotorista();
				}
			}
			
			contaMapas++;
		}
		
		return contaMapas;
	}
	
	public Collection recuperarMapa(PlcBaseContextVO context, Mapa arg, int inicio, int total) throws PlcException {
		Calendar calendar = Calendar.getInstance();
		validarDatas(arg, calendar);
		
		List<Jornada> jornadas = jornadaDAO.findList(context, arg.getDataInicio(), arg.getDataFim(), arg.getMotorista(), arg.getTurno(), arg.getOperacao());
		List<Mapa> mapas = new ArrayList<Mapa>();
		Mapa mapa = null;
		
		//Carrega as férias dos motoristas que possuem férias na faixa do período informado
		List<MotoristaAfastamento> listaAfastamentos = null;
		listaAfastamentos = (List<MotoristaAfastamento>)jornadaDAO.findByFields(context, MotoristaAfastamento.class, "querySelByFaixa", new String[]{"inicio", "termino"}, new Object[]{arg.getDataInicio(), arg.getDataFim()});
		
		// Adiciona 2 minutos na hora de termino do afatamento para possiblitar a comparação com Date.before() e Date.after() 
		for (MotoristaAfastamento afastamento : listaAfastamentos) {
			
			if (afastamento != null) {
				calendar.setTime(afastamento.getTermino());
				calendar.add(Calendar.MINUTE, 2);
				afastamento.setTermino(calendar.getTime());
			}
		}
		
		int indice = 0;
		int contaMotorista = 0;
		
		int i = 0;
		
		// Enquanto houver jornadas 
		while (jornadas.size() > 0 && i < jornadas.size() && contaMotorista <= total) {
			Jornada jornada = jornadas.get(i);
			Motorista motorista = jornada.getMotorista();
			Motorista motoristaAnt = motorista;
			
			if (indice >= inicio && contaMotorista <= total) {
				mapa = new Mapa();
				mapa.setMotorista(motorista);
				mapa.setDataInicio(arg.getDataInicio());
				mapa.setDataFim(arg.getDataFim());
				mapa.setOperacao(jornada.getOperacao());
				mapa.setTurno(jornada.getOperacao().getTurno());
			}
			
			int ini = 1;
			int j = 0;
			
			int contaDiaTrabalhado = 0;
			
			// Enquanto for o mesmo motorista
			while (i < jornadas.size() && motorista.getId().compareTo(motoristaAnt.getId()) == 0 && contaMotorista <= total) {
				
				if (indice >= inicio) { 
					calendar.setTime(jornada.getData());
					calendar.add(Calendar.MINUTE, 1);
					int dia = calendar.get(Calendar.DAY_OF_MONTH);
	
					for (j=ini; j<=dia; j++) {
						calendar.set(Calendar.DAY_OF_MONTH, j);

						DiaMapa diaMapa = mapa.getDiaMapa(j);
						diaMapa.setData(calendar.getTime());
						diaMapa.setJornadaId(jornada.getId());
						diaMapa.setStatusJornada(jornada.getStatus());
						diaMapa.setMotoristaId(motorista.getId());
						
						if (dia == j) {
							contaDiaTrabalhado++;
							
							if (contaDiaTrabalhado < 7) {
								diaMapa.setStatusDia(StatusDiaMapa.T);
							} else {
								diaMapa.setStatusDia(StatusDiaMapa.E);
							}
							
						} else {
							contaDiaTrabalhado = 0;

							// Se esta com algum afastamento no dia informado
							if (verificarAfastamentoMotoristaNoDia(motorista, diaMapa.getData(), listaAfastamentos)) {
								diaMapa.setStatusDia(obterMotivoAtastamentoDia(motorista, diaMapa.getData(), listaAfastamentos));
							} else {
								diaMapa.setStatusDia(StatusDiaMapa.D);
							}
						}
					}
				}
				
				ini = j;
				i++;				
				
				if (i < jornadas.size()) {
					jornada = jornadas.get(i);
					motorista = jornada.getMotorista();
				}
			}
			
			if (indice >= inicio && contaMotorista <= total) {
				completaMapaAfastamentoMotorista(mapa, listaAfastamentos);
				
				mapas.add(mapa);
				contaMotorista++;
			}
			
			indice++;
		}
		
		return mapas;
	}

	
	private void completaMapaAfastamentoMotorista(Mapa mapa, List<MotoristaAfastamento> motoristaAfastamento) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(mapa.getDataFim());
		cal.add(Calendar.HOUR_OF_DAY, 1);
		Date mapaFim = cal.getTime();
		
		for (MotoristaAfastamento afastamentoMotorista : motoristaAfastamento) {
			// Se é o motorista em questão
			if (afastamentoMotorista.getMotorista().getId().compareTo(mapa.getMotorista().getId()) == 0) {
				cal.setTime(afastamentoMotorista.getTermino());
				cal.add(Calendar.HOUR_OF_DAY, 1);
				Date fimAfastamento = cal.getTime();
				
				Date data = afastamentoMotorista.getInicio();
				
				cal.setTime(data);
				int i = cal.get(Calendar.DAY_OF_MONTH);
				
				// Percorre todo o período de afastamento (até o fim do período do mapa, no máximo)
				while (data.before(fimAfastamento) && data.after(mapa.getDataInicio()) && data.before(mapaFim)) {
					DiaMapa diaMapa = mapa.getDiaMapa(i);
					
					// So registra afastamento se não houver outro evento no dia
					if (StatusDiaMapa.I.equals(diaMapa.getStatusDia())) {
						diaMapa.setStatusDia(StatusDiaMapa.I);
						diaMapa.setData(data);
					}

					i++;
					cal.set(Calendar.DAY_OF_MONTH, i);
					data = cal.getTime();
				}
				
				break;
			}
		}
	}

	private StatusDiaMapa obterMotivoAtastamentoDia(Motorista motorista, Date diaMapa, List<MotoristaAfastamento> listaAfastamentos) {
		StatusDiaMapa motivoAfastamento = StatusDiaMapa.I; 
		
		for (MotoristaAfastamento afastamento : listaAfastamentos) {
			
			// Se é o motorista em questão
			if (afastamento != null && afastamento.getMotorista().getId().compareTo(motorista.getId()) == 0) {
				
				if (diaMapa.after(afastamento.getInicio()) && diaMapa.before(afastamento.getTermino())) {
					motivoAfastamento = StatusDiaMapa.valueOf(afastamento.getMotivo().name());
					break;
				}
			}
		}
		
		return motivoAfastamento;
	}

	
	private boolean verificarAfastamentoMotoristaNoDia(Motorista motorista, Date diaMapa, List<MotoristaAfastamento> listaAfastamentos) {
		boolean ret = false;
		
		for (MotoristaAfastamento afastamentoMotorista : listaAfastamentos) {
			
			// Se é o motorista em questão
			if (afastamentoMotorista.getMotorista().getId().compareTo(motorista.getId()) == 0) {
				
				if (diaMapa.after(afastamentoMotorista.getInicio()) && diaMapa.before(afastamentoMotorista.getTermino())) {
					ret = true;
					break;
				}
			}
		}
		
		return ret;
	}

	private void validarDatas(Mapa arg, Calendar calendar) throws PlcException {
		if (arg.getDataInicio() == null) {
			Date dataAtual = new Date();
			calendar.setTime(dataAtual);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			arg.setDataInicio(calendar.getTime());
		}
		
		if (arg.getDataFim() == null) {
			calendar.setTime(arg.getDataInicio());
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			arg.setDataFim(calendar.getTime());
		}
		
		calendar.setTime(arg.getDataInicio());
		
		int diaIni = calendar.get(Calendar.DAY_OF_MONTH);
		int mesIni = calendar.get(Calendar.MONTH);
		int anoIni = calendar.get(Calendar.YEAR);

		calendar.setTime(arg.getDataFim());

		int diaFim = calendar.get(Calendar.DAY_OF_MONTH);
		int mesFim = calendar.get(Calendar.MONTH);
		int anoFim = calendar.get(Calendar.YEAR);
		
		if (mesIni != mesFim || anoIni != anoFim) {
			throw new PlcException("A pesquisa deve ser realizada dentro do mesmo mês!");
		}

		if (diaIni > diaFim) {
			throw new PlcException("A data de início não pode ser maior que a data fim!");
		}
		
	}

}
