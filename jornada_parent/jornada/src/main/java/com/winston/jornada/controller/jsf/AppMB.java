/* Jaguar-jCompany Developer Suite. Powerlogic 2010-2014. Please read licensing information or contact Powerlogic 
 * for more information or contribute with this project: suporte@powerlogic.com.br - www.powerlogic.com.br        */
package com.winston.jornada.controller.jsf;

import org.apache.log4j.Logger;

import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import javax.inject.Named;

import com.powerlogic.jcompany.commons.PlcBaseContextVO;
import com.powerlogic.jcompany.commons.PlcConstants;
import com.powerlogic.jcompany.commons.config.qualifiers.QPlcDefault;
import com.powerlogic.jcompany.commons.config.stereotypes.SPlcMB;
import com.powerlogic.jcompany.controller.jsf.PlcBaseMB;
import com.powerlogic.jcompany.controller.jsf.action.util.PlcConversationControl;
import com.powerlogic.jcompany.controller.jsf.annotations.PlcHandleException;
import com.powerlogic.jcompany.controller.jsf.util.PlcCreateContextUtil;

@Specializes
@PlcHandleException
@SPlcMB
public class AppMB extends PlcBaseMB {

	private static final long serialVersionUID = 1L;
	
	private PlcBaseContextVO context;

	@Inject @Named(PlcConstants.PlcJsfConstantes.PLC_CONTROLE_CONVERSACAO)
	protected PlcConversationControl plcControleConversacao;	

	@Inject @QPlcDefault 
	protected PlcCreateContextUtil contextMontaUtil;	

	@Inject
	protected transient Logger log;

//	protected PlcBaseContextVO getContext() {
//		PlcBaseContextVO context = (PlcBaseContextVO) contextUtil.getRequest()
//				.getAttribute(PlcConstants.CONTEXT);
//		return context;
//	}

	public PlcBaseContextVO getContext() {
		
		if (this.context == null) {
			this.context = (PlcBaseContextVO) contextMontaUtil.createContextParam(plcControleConversacao);
		}

		return context;
	}
}
