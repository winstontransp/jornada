package com.winston.jornada.controller.jsf.criticaparam;

import javax.enterprise.inject.Produces;
import javax.inject.Named;


import com.winston.jornada.entity.CriticaParam;
import com.winston.jornada.controller.jsf.AppMB;

import com.powerlogic.jcompany.commons.annotation.PlcUriIoC;
import com.powerlogic.jcompany.commons.config.stereotypes.SPlcMB;
import com.powerlogic.jcompany.controller.jsf.annotations.PlcHandleException;
import com.powerlogic.jcompany.config.collaboration.FormPattern;

import com.powerlogic.jcompany.config.collaboration.PlcConfigFormLayout;
import com.powerlogic.jcompany.config.collaboration.PlcConfigForm;
import com.powerlogic.jcompany.config.collaboration.PlcConfigForm.ExclusionMode;




import com.powerlogic.jcompany.config.aggregation.PlcConfigAggregation;


@PlcConfigAggregation(
		entity= com.winston.jornada.entity.CriticaParam.class
	)


@PlcConfigForm (
	
	formPattern=FormPattern.Apl,
	formLayout = @PlcConfigFormLayout(dirBase="/WEB-INF/fcls/criticaparam")
	
	
)


/**
 * Classe de Controle gerada pelo assistente
 */
 
@SPlcMB
@PlcUriIoC("criticaparam")
@PlcHandleException
public class CriticaParamMB extends AppMB  {

	private static final long serialVersionUID = 1L;
	
	
     		
	/**
	* Entidade da ação injetado pela CDI
	*/
	@Produces  @Named("criticaparam")
	public CriticaParam createEntityPlc() {
        if (this.entityPlc==null) {
              this.entityPlc = new CriticaParam();
              this.newEntity();
        }
        return (CriticaParam)this.entityPlc;     	
	}
		
}
