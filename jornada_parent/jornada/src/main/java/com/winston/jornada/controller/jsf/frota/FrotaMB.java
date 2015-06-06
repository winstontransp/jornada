package com.winston.jornada.controller.jsf.frota;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

import com.powerlogic.jcompany.commons.annotation.PlcUriIoC;
import com.powerlogic.jcompany.commons.config.stereotypes.SPlcMB;
import com.powerlogic.jcompany.config.aggregation.PlcConfigAggregation;
import com.powerlogic.jcompany.config.collaboration.FormPattern;
import com.powerlogic.jcompany.config.collaboration.PlcConfigForm;
import com.powerlogic.jcompany.config.collaboration.PlcConfigFormLayout;
import com.powerlogic.jcompany.config.collaboration.PlcConfigPagination;
import com.powerlogic.jcompany.config.collaboration.PlcConfigSelection;
import com.powerlogic.jcompany.config.collaboration.PlcConfigTabular;
import com.powerlogic.jcompany.controller.jsf.PlcEntityList;
import com.powerlogic.jcompany.controller.jsf.annotations.PlcHandleException;
import com.winston.jornada.controller.jsf.AppMB;

@PlcConfigAggregation(entity = com.winston.jornada.entity.Frota.class)
@PlcConfigForm(
	formPattern = FormPattern.Tab, 
	tabular = @PlcConfigTabular(numNew = 4), 
	formLayout = @PlcConfigFormLayout(dirBase = "/WEB-INF/fcls/frota"),
	selection = @PlcConfigSelection(apiQuerySel="queryMan", 
	pagination=@PlcConfigPagination(numberByPage=50))
)

/**
 * Classe de Controle gerada pelo assistente
 */
@SPlcMB
@PlcUriIoC("frota")
@PlcHandleException
public class FrotaMB extends AppMB {

	private static final long serialVersionUID = 1L;

	/**
	 * Entidade da ação injetado pela CDI
	 */
	@Produces
	@Named("frotaLista")
	public PlcEntityList createEntityListPlc() {

		if (this.entityListPlc == null) {
			this.entityListPlc = new PlcEntityList();
			this.newObjectList();
		}
		
		return this.entityListPlc;
	}

}
