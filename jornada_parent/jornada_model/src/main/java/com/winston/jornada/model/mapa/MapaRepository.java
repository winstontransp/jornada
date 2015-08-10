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
			
			// Enquanto for o mesmo motorista
			while (i < jornadas.size() && motorista.getId().compareTo(motoristaAnt.getId()) == 0 && contaMotorista <= total) {
				
				if (indice >= inicio) { 
					calendar.setTime(jornada.getData());
					int dia = calendar.get(Calendar.DAY_OF_MONTH);
	
					for (j=ini; j<=dia; j++) {
	
						if (dia == j) {
							//trabalhou
							mapa.setStatusDia(j, StatusDiaMapa.T);
						} else {
							// se esta de férias
								//ferias
							// senão 
								// descanso
							mapa.setStatusDia(j, StatusDiaMapa.D);
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
				mapas.add(mapa);
				contaMotorista++;
			}
			
			indice++;
		}
		
		return mapas;
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
