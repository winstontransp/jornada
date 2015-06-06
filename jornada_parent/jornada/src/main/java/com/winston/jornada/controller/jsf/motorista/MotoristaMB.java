package com.winston.jornada.controller.jsf.motorista;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

import com.winston.jornada.entity.Motorista;
import com.winston.jornada.controller.jsf.AppMB;

import com.powerlogic.jcompany.commons.annotation.PlcUriIoC;
import com.powerlogic.jcompany.commons.config.stereotypes.SPlcMB;
import com.powerlogic.jcompany.controller.jsf.annotations.PlcHandleException;
import com.powerlogic.jcompany.config.collaboration.FormPattern;

import com.powerlogic.jcompany.config.collaboration.PlcConfigFormLayout;
import com.powerlogic.jcompany.config.collaboration.PlcConfigForm;
import com.powerlogic.jcompany.config.collaboration.PlcConfigForm.ExclusionMode;

import com.powerlogic.jcompany.config.aggregation.PlcConfigAggregation;

@PlcConfigAggregation(entity = com.winston.jornada.entity.Motorista.class, 
	details = { @com.powerlogic.jcompany.config.aggregation.PlcConfigDetail(
		clazz = com.winston.jornada.entity.MotoristaFerias.class, 
			collectionName = "motoristaFerias", numNew = 4, onDemand = false, exclusionMode = ExclusionMode.LOGICAL)
})

@PlcConfigForm(formPattern = FormPattern.Mdt, 
	formLayout = @PlcConfigFormLayout(dirBase = "/WEB-INF/fcls/motorista"), exclusionMode = ExclusionMode.LOGICAL
)

/**
 * Classe de Controle gerada pelo assistente
 */
@SPlcMB
@PlcUriIoC("motorista")
@PlcHandleException
public class MotoristaMB extends AppMB {

	private static final long serialVersionUID = 1L;

	/**
	 * Entidade da ação injetado pela CDI
	 */
	@Produces
	@Named("motorista")
	public Motorista createEntityPlc() {
		if (this.entityPlc == null) {
			this.entityPlc = new Motorista();
			this.newEntity();
		}
		return (Motorista) this.entityPlc;
	}

}
