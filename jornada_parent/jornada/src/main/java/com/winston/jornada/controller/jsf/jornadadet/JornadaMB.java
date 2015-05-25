package com.winston.jornada.controller.jsf.jornadadet;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.powerlogic.jcompany.commons.PlcBaseContextVO;
import com.powerlogic.jcompany.commons.PlcConstants;
import com.powerlogic.jcompany.commons.annotation.PlcUriIoC;
import com.powerlogic.jcompany.commons.config.qualifiers.QPlcDefault;
import com.powerlogic.jcompany.commons.config.stereotypes.SPlcMB;
import com.powerlogic.jcompany.config.aggregation.PlcConfigAggregation;
import com.powerlogic.jcompany.config.collaboration.FormPattern;
import com.powerlogic.jcompany.config.collaboration.PlcConfigForm;
import com.powerlogic.jcompany.config.collaboration.PlcConfigFormLayout;
import com.powerlogic.jcompany.controller.jsf.action.util.PlcConversationControl;
import com.powerlogic.jcompany.controller.jsf.annotations.PlcHandleException;
import com.powerlogic.jcompany.controller.jsf.util.PlcCreateContextUtil;
import com.powerlogic.jcompany.controller.util.PlcIocControllerFacadeUtil;
import com.winston.jornada.controller.jsf.AppMB;
import com.winston.jornada.entity.Jornada;
import com.winston.jornada.entity.JornadaEvento;
import com.winston.jornada.facade.IAppFacade;

@PlcConfigAggregation(
	entity = com.winston.jornada.entity.Jornada.class, 
	details = { @com.powerlogic.jcompany.config.aggregation.PlcConfigDetail(
					clazz = com.winston.jornada.entity.ReturnMessage.class, 
					collectionName = "returnMessage", numNew = 4, onDemand = false), 
			@com.powerlogic.jcompany.config.aggregation.PlcConfigDetail(
					clazz = com.winston.jornada.entity.JornadaEvento.class, 
					collectionName = "eventos", numNew = 0, onDemand = false)			
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

//	@Inject @QPlcDefault 
//	protected PlcCreateContextUtil contextMontaUtil;
//
//	@Inject @Named(PlcConstants.PlcJsfConstantes.PLC_CONTROLE_CONVERSACAO)
//	protected PlcConversationControl plcControleConversacao;	
//
//	@Inject @QPlcDefault 
//	protected PlcIocControllerFacadeUtil iocControleFacadeUtil;

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

//	/**
//	 * Lista de Eventos da Jornada - injetado pela CDI
//	 */
//	@Produces
//	@Named("eventos")
//	public List<JornadaEvento> obterEventosJornada() {
//		
//		if (this.entityPlc != null) {
//
//			PlcBaseContextVO context = contextMontaUtil.createContextParam(plcControleConversacao);
//
//			Jornada jornadaArg = (Jornada)this.entityPlc;
//			JornadaEvento eventoArg = new JornadaEvento();
//			
//			eventoArg.setJornada(jornadaArg);
//			
//			return (List<JornadaEvento>)iocControleFacadeUtil.getFacade(IAppFacade.class).findList(context, eventoArg, "", 1, 15);
//		}
//		
//		return new ArrayList<JornadaEvento>();
//	}
	
}
