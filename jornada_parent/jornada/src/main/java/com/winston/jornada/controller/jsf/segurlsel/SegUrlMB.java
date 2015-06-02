package com.winston.jornada.controller.jsf.segurlsel;

import javax.enterprise.inject.Produces;
import javax.inject.Named;


import com.winston.jornada.entity.seguranca.SegUrl;
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
		entity = com.winston.jornada.entity.seguranca.SegUrl.class
	)


@PlcConfigForm (
	
	formPattern=FormPattern.Sel,
	formLayout = @PlcConfigFormLayout(dirBase="/WEB-INF/fcls/segurl")
	
	
)


/**
 * Classe de Controle gerada pelo assistente
 */
 
@SPlcMB
@PlcUriIoC("segurlsel")
@PlcHandleException
public class SegUrlMB extends AppMB  {

	private static final long serialVersionUID = 1L;
	
	
     		
	/**
	* Entidade da ação injetado pela CDI
	*/
	@Produces  @Named("segurlsel")
	public SegUrl createEntityPlc() {
        if (this.entityPlc==null) {
              this.entityPlc = new SegUrl();
              this.newEntity();
        }
        return (SegUrl)this.entityPlc;     	
	}
		
}
