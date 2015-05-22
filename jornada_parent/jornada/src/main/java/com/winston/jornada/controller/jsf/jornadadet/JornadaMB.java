package com.winston.jornada.controller.jsf.jornadadet;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

import com.powerlogic.jcompany.commons.annotation.PlcUriIoC;
import com.powerlogic.jcompany.commons.config.stereotypes.SPlcMB;
import com.powerlogic.jcompany.config.aggregation.PlcConfigAggregation;
import com.powerlogic.jcompany.config.collaboration.FormPattern;
import com.powerlogic.jcompany.config.collaboration.PlcConfigForm;
import com.powerlogic.jcompany.config.collaboration.PlcConfigFormLayout;
import com.powerlogic.jcompany.controller.jsf.annotations.PlcHandleException;
import com.winston.jornada.controller.jsf.AppMB;
import com.winston.jornada.entity.Jornada;

@PlcConfigAggregation(
	entity = com.winston.jornada.entity.Jornada.class, 
	details = { @com.powerlogic.jcompany.config.aggregation.PlcConfigDetail(
			clazz = com.winston.jornada.entity.ReturnMessage.class, 
			collectionName = "returnMessage", numNew = 4, onDemand = false)
})

@PlcConfigForm(
	formPattern = FormPattern.Mad, formLayout = @PlcConfigFormLayout(dirBase = "/WEB-INF/fcls/jornadadet")
)

/**
 * Classe de Controle gerada pelo assistente
 */
@SPlcMB
@PlcUriIoC("jornadadet")
@PlcHandleException
public class JornadaMB extends AppMB {

	private static final long serialVersionUID = 1L;


	/**
	 * Entidade da ação injetado pela CDI
	 */
	@Produces
	@Named("jornadadet")
	public Jornada createEntityPlc() {
		if (this.entityPlc == null) {
			this.entityPlc = new Jornada();
			this.newEntity();
		}
		return (Jornada) this.entityPlc;
	}

}
