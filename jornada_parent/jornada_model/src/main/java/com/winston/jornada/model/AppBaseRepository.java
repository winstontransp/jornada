package com.winston.jornada.model;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import com.powerlogic.jcompany.model.PlcBaseRepository;

public class AppBaseRepository extends PlcBaseRepository {

	protected String calcularDuracao(Date inicio, Date fim) {
		String duracao = null;
		
		DateTime dataInicial = new DateTime(inicio);
		DateTime dataFinal = new DateTime(fim);
		
		Duration duration = new Duration(dataInicial, dataFinal);
		
		duracao = String.format("%02d", duration.getStandardHours()) + ":" + 
			String.format("%02d", duration.getStandardMinutes() % 60) + ":" + 
			String.format("%02d", duration.getStandardSeconds() % 60);
		
		return duracao; 
	}
	
}
