package com.winston.jornada.controller.jsf.importacao;

import java.util.Map;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

import com.powerlogic.jcompany.commons.PlcConstants;
import com.powerlogic.jcompany.commons.annotation.PlcUriIoC;
import com.powerlogic.jcompany.commons.config.stereotypes.SPlcMB;
import com.powerlogic.jcompany.config.aggregation.PlcConfigAggregation;
import com.powerlogic.jcompany.config.collaboration.FormPattern;
import com.powerlogic.jcompany.config.collaboration.PlcConfigForm;
import com.powerlogic.jcompany.config.collaboration.PlcConfigFormLayout;
import com.powerlogic.jcompany.controller.jsf.annotations.PlcHandleException;
import com.winston.jornada.controller.jsf.AppMB;
import com.winston.jornada.entity.importacao.ExcecaoImportCtrlJornada;
import com.winston.jornada.entity.importacao.ExcecaoImportCtrlJornadaDetalhe;

@PlcConfigAggregation(entity = ExcecaoImportCtrlJornada.class, 
	details = { @com.powerlogic.jcompany.config.aggregation.PlcConfigDetail(
		clazz = ExcecaoImportCtrlJornadaDetalhe.class, 
		collectionName = "detalheExcecao", numNew = 4, onDemand = false, 
		navigation = @com.powerlogic.jcompany.config.aggregation.PlcConfigPagedDetail(numberByPage = 20))
})

@PlcConfigForm(formPattern = FormPattern.Mdt, 
	formLayout = @PlcConfigFormLayout(dirBase = "/WEB-INF/fcls/importacao")
)

/**
 * Classe de Controle gerada pelo assistente
 */
@SPlcMB
@PlcUriIoC("excecaoimportctrljornada")
@PlcHandleException
public class ExcecaoImportCtrlJornadaMB extends AppMB {

	private static final long serialVersionUID = 1L;

	/**
	 * Entidade da ação injetado pela CDI
	 */
	@Produces
	@Named("excecao")
	public ExcecaoImportCtrlJornada createEntityPlc() {

		if (this.entityPlc == null) {
			this.entityPlc = new ExcecaoImportCtrlJornada();
			this.newEntity();
		}
		
		return (ExcecaoImportCtrlJornada) this.entityPlc;
	}

	@Override
	public void handleButtonsAccordingFormPattern() {
		super.handleButtonsAccordingFormPattern();

		Map<String, Object> requestMap = contextUtil.getRequestMap();
		requestMap.put(PlcConstants.ACAO.EXIBE_BT_INCLUIR, PlcConstants.NAO_EXIBIR);
	}

	
}
