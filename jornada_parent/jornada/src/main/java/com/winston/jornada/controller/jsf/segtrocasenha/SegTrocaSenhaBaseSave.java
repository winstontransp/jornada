package com.winston.jornada.controller.jsf.segtrocasenha;

import javax.inject.Inject;

import com.powerlogic.jcompany.commons.PlcBaseContextVO;
import com.powerlogic.jcompany.commons.config.qualifiers.QPlcSpecific;
import com.powerlogic.jcompany.config.collaboration.FormPattern;
import com.powerlogic.jcompany.controller.jsf.PlcBaseSaveMB;
import com.winston.jornada.controller.jsf.segusuario.SenhaUtil;

@QPlcSpecific(name="segtrocasenha")
public class SegTrocaSenhaBaseSave extends PlcBaseSaveMB {

	private static final long serialVersionUID = 6054855513137241567L;

	@Inject
	SenhaUtil senhaUtil;

	@Override
	protected boolean saveBefore(FormPattern pattern, Object entityPlc, PlcBaseContextVO context) {
		senhaUtil.validarSenha(entityPlc);
		
		return super.saveBefore(pattern, entityPlc, context);
	}
	
}
