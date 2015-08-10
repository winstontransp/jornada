package com.winston.jornada.facade;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import com.powerlogic.jcompany.commons.PlcBaseContextVO;
import com.powerlogic.jcompany.commons.PlcException;
import com.powerlogic.jcompany.commons.config.qualifiers.QPlcDefault;
import com.powerlogic.jcompany.commons.config.stereotypes.SPlcFacade;
import com.powerlogic.jcompany.facade.PlcFacadeImpl;
import com.winston.jornada.entity.mapa.Mapa;
import com.winston.jornada.entity.seguranca.SegUrl;
import com.winston.jornada.model.mapa.MapaRepository;
import com.winston.jornada.model.seguranca.SegUsuarioRepository;

@QPlcDefault
@SPlcFacade
public class AppFacadeImpl extends PlcFacadeImpl implements IAppFacade {

	@Inject
	SegUsuarioRepository segUsuarioRepository;

	@Inject
	MapaRepository mapaRepository;
	
	@Override
	public List<SegUrl> recuperaUrlsAcessoUsuario(PlcBaseContextVO context,	String login) throws PlcException {
		return segUsuarioRepository.recuperaUrlsAcessoUsuario(context, login);
	}

	@Override
	public Long recuperarContagemMapa(PlcBaseContextVO context, Mapa mapaArg) throws PlcException {
		return mapaRepository.recuperarContagemMapa(context, mapaArg);
	}
	
	@Override
	public Collection recuperarMapa(PlcBaseContextVO context, Mapa mapaArg, int inicio, int total) throws PlcException {
		return mapaRepository.recuperarMapa(context, mapaArg, inicio, total);
	}
	
}
