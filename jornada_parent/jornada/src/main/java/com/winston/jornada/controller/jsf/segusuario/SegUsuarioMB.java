package com.winston.jornada.controller.jsf.segusuario;

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
import com.winston.jornada.entity.seguranca.SegUsuario;

@PlcConfigAggregation(
	entity = com.winston.jornada.entity.seguranca.SegUsuario.class, 
	details = { @com.powerlogic.jcompany.config.aggregation.PlcConfigDetail(
		clazz = com.winston.jornada.entity.seguranca.SegUsuarioPerfil.class,
		collectionName = "segUsuarioPerfil", numNew = 4, onDemand = false)
	}
)

@PlcConfigForm (
	formPattern=FormPattern.Mdt,
	formLayout = @PlcConfigFormLayout(dirBase="/WEB-INF/fcls/segusuario")
)

/**
 * Classe de Controle gerada pelo assistente
 */
 
@SPlcMB
@PlcUriIoC("segusuario")
@PlcHandleException
public class SegUsuarioMB extends AppMB  {

	private static final long serialVersionUID = 1L;
     		
	/**
	* Entidade da ação injetado pela CDI
	*/
	@Produces  @Named("segusuario")
	public SegUsuario createEntityPlc() {

		if (this.entityPlc==null) {
              this.entityPlc = new SegUsuario();
              this.newEntity();
        }
		
        return (SegUsuario)this.entityPlc;     	
	}
	
}
