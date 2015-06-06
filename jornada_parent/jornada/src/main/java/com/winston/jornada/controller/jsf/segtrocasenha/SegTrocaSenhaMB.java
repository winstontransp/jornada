package com.winston.jornada.controller.jsf.segtrocasenha;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

import com.powerlogic.jcompany.commons.annotation.PlcUriIoC;
import com.powerlogic.jcompany.commons.config.stereotypes.SPlcMB;
import com.powerlogic.jcompany.config.aggregation.PlcConfigAggregation;
import com.powerlogic.jcompany.config.collaboration.FormPattern;
import com.powerlogic.jcompany.config.collaboration.PlcConfigForm;
import com.powerlogic.jcompany.config.collaboration.PlcConfigFormLayout;
import com.powerlogic.jcompany.config.collaboration.PlcConfigSelection;
import com.powerlogic.jcompany.controller.jsf.annotations.PlcHandleException;
import com.winston.jornada.controller.jsf.AppMB;
import com.winston.jornada.entity.seguranca.SegUsuario;

@PlcConfigAggregation(
	entity = com.winston.jornada.entity.seguranca.SegUsuario.class
)

@PlcConfigForm (
	selection = @PlcConfigSelection(apiQuerySel = "querySel2"),
	formPattern=FormPattern.Usu,
	formLayout = @PlcConfigFormLayout(dirBase="/WEB-INF/fcls/segtrocasenha")
)

/**
 * Classe de Controle gerada pelo assistente
 */
 
@SPlcMB
@PlcUriIoC("segtrocasenha")
@PlcHandleException
public class SegTrocaSenhaMB extends AppMB {

	private static final long serialVersionUID = 1L;
	
	/**
	* Entidade da ação injetado pela CDI
	*/
	@Produces  @Named("segtrocasenha")
	public SegUsuario createEntityPlc() {

		if (this.entityPlc==null) {
              this.entityPlc = new SegUsuario();
              this.newEntity();
        }
		
        return (SegUsuario)this.entityPlc;     	
	}
		
}
