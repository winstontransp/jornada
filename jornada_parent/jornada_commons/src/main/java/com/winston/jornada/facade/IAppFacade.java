package com.winston.jornada.facade;

import java.util.List;

import com.powerlogic.jcompany.commons.PlcBaseContextVO;
import com.powerlogic.jcompany.commons.PlcException;
import com.powerlogic.jcompany.commons.facade.IPlcFacade;
import com.winston.jornada.entity.seguranca.SegUrl;

public interface IAppFacade extends IPlcFacade {

	public List<SegUrl> recuperaUrlsAcessoUsuario(PlcBaseContextVO context,	String login) throws PlcException;
	
}
