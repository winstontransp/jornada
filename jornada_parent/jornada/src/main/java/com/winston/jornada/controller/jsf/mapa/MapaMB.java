package com.winston.jornada.controller.jsf.mapa;

import java.util.Calendar;
import java.util.Date;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

import com.powerlogic.jcompany.commons.PlcException;
import com.powerlogic.jcompany.commons.annotation.PlcUriIoC;
import com.powerlogic.jcompany.commons.config.stereotypes.SPlcMB;
import com.powerlogic.jcompany.config.aggregation.PlcConfigAggregation;
import com.powerlogic.jcompany.config.collaboration.FormPattern;
import com.powerlogic.jcompany.config.collaboration.PlcConfigForm;
import com.powerlogic.jcompany.config.collaboration.PlcConfigFormLayout;
import com.powerlogic.jcompany.controller.jsf.annotations.PlcHandleException;
import com.winston.jornada.controller.jsf.AppMB;
import com.winston.jornada.entity.mapa.Mapa;

@PlcConfigAggregation(entity = com.winston.jornada.entity.mapa.Mapa.class)

@PlcConfigForm(
	formPattern = FormPattern.Con, formLayout = @PlcConfigFormLayout(dirBase = "/WEB-INF/fcls/mapa")
)

/**
 * Classe de Controle gerada pelo assistente
 */
@SPlcMB
@PlcUriIoC("mapa")
@PlcHandleException
public class MapaMB extends AppMB {
	private static final long serialVersionUID = 1L;

	/**
	 * Entidade da ação injetado pela CDI
	 */
	@Produces
	@Named("mapa")
	public Mapa createEntityPlc() {
		
		if (this.entityPlc == null) {
			this.entityPlc = new Mapa();
			this.newEntity();
			
			Calendar calendar = Calendar.getInstance();
			
			Mapa mapa = (Mapa)this.entityPlc;
			
			Date dataAtual = new Date();
			calendar.setTime(dataAtual);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			mapa.setDataInicio(calendar.getTime());
		
			calendar.setTime(mapa.getDataInicio());
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			mapa.setDataFim(calendar.getTime());
		}
		
		return (Mapa) this.entityPlc;
	}
	
}
