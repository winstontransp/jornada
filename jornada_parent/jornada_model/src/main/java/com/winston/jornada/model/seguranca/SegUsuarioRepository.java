package com.winston.jornada.model.seguranca;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.powerlogic.jcompany.commons.PlcBaseContextVO;
import com.powerlogic.jcompany.commons.PlcException;
import com.powerlogic.jcompany.commons.annotation.PlcAggregationIoC;
import com.powerlogic.jcompany.commons.config.stereotypes.SPlcRepository;
import com.winston.jornada.entity.seguranca.SegPerfilUrl;
import com.winston.jornada.entity.seguranca.SegUrl;
import com.winston.jornada.entity.seguranca.SegUsuario;
import com.winston.jornada.entity.seguranca.SegUsuarioPerfil;
import com.winston.jornada.model.AppBaseRepository;
import com.winston.jornada.persistence.jpa.segperfilurl.SegPerfilUrlDAO;
import com.winston.jornada.persistence.jpa.segusuario.SegUsuarioDAO;
import com.winston.jornada.persistence.jpa.segusuarioperfil.SegUsuarioPerfilDAO;

@SPlcRepository
@PlcAggregationIoC(clazz=SegUsuario.class)
public class SegUsuarioRepository extends AppBaseRepository {

	@Inject
	private SegUsuarioDAO  usuarioDAO;

	@Inject
	private SegUsuarioPerfilDAO  usuarioPerfilDAO;

	@Inject
	private SegPerfilUrlDAO  perfilUrlDAO;

	
	public List<SegUrl> recuperaUrlsAcessoUsuario(PlcBaseContextVO context,	String login) throws PlcException {
		List<SegPerfilUrl> listaPerfilUrl;
		List<SegUrl> listaUrls = new ArrayList<SegUrl>();
		
		SegUsuario usuario = usuarioDAO.obterUsuarioPorLogin(context, login);
		
		List<SegUsuarioPerfil> perfilUsuarioList = usuarioPerfilDAO.obterPerfilUsuarioPorUsuario(context, usuario);
		
		for (SegUsuarioPerfil perfilUsuario : perfilUsuarioList) {
			listaPerfilUrl = perfilUrlDAO.obterPerfilUrlPorPerfil(context, perfilUsuario.getSegPerfil());
			
			for (SegPerfilUrl segPerfilUrl : listaPerfilUrl) {
				listaUrls.add(segPerfilUrl.getUrl());
			}
		}
		
		return listaUrls;
	}
	
}
