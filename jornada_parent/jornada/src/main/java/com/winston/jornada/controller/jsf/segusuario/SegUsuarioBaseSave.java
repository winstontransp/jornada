package com.winston.jornada.controller.jsf.segusuario;

import javax.inject.Inject;

import com.powerlogic.jcompany.commons.PlcBaseContextVO;
import com.powerlogic.jcompany.commons.config.qualifiers.QPlcSpecific;
import com.powerlogic.jcompany.config.collaboration.FormPattern;
import com.powerlogic.jcompany.controller.jsf.PlcBaseSaveMB;

@QPlcSpecific(name="segusuario")
public class SegUsuarioBaseSave extends PlcBaseSaveMB {

	private static final long serialVersionUID = 229403406159405533L;
	
	@Inject
	SenhaUtil senhaUtil;

	@Override
	protected boolean saveBefore(FormPattern pattern, Object entityPlc, PlcBaseContextVO context) {
		senhaUtil.validarSenha(entityPlc);
		
		return super.saveBefore(pattern, entityPlc, context);
	}
	
}
