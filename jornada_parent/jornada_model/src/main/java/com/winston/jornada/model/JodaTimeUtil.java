package com.winston.jornada.model;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Duration;

public class JodaTimeUtil {
	
	public String calcularDuracao(Date inicio, Date fim) {
		DateTime dataInicial = new DateTime(inicio);
		DateTime dataFinal = new DateTime(fim);
		
		return calcularDuracao(dataInicial, dataFinal); 
	}

	public String calcularDuracao(DateTime dataInicial, DateTime dataFinal) {
		String duracao = null;
		
		Duration duration = new Duration(dataInicial, dataFinal);
		
		duracao = String.format("%02d", duration.getStandardHours()) + ":" + 
			String.format("%02d", duration.getStandardMinutes() % 60) + ":" + 
			String.format("%02d", duration.getStandardSeconds() % 60);
		
		return duracao; 
	}
	
}
