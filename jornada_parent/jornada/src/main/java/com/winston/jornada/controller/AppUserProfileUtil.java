/* Jaguar-jCompany Developer Suite. Powerlogic 2010-2014. Please read licensing information or contact Powerlogic 
 * for more information or contribute with this project: suporte@powerlogic.com.br - www.powerlogic.com.br        */ 
package com.winston.jornada.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.powerlogic.jcompany.commons.PlcBaseContextVO;
import com.powerlogic.jcompany.commons.PlcBaseUserProfileVO;
import com.powerlogic.jcompany.commons.config.qualifiers.QPlcDefault;
import com.powerlogic.jcompany.commons.config.stereotypes.SPlcUtil;
import com.powerlogic.jcompany.controller.jsf.util.PlcCreateContextUtil;
import com.powerlogic.jcompany.controller.util.PlcBaseUserProfileUtil;
import com.winston.jornada.commons.AppUserProfileVO;
import com.winston.jornada.entity.seguranca.SegUrl;
import com.winston.jornada.facade.IAppFacade;

/**
 * jornada . Implementar aqui lógicas de perfil do usuário da aplicação (user profiling)
 */
@SPlcUtil
@QPlcDefault
public class AppUserProfileUtil extends PlcBaseUserProfileUtil {
	@Inject
	@QPlcDefault
	private IAppFacade facade;

	@Inject
	@QPlcDefault
	protected PlcCreateContextUtil contextMontaUtil;

	/**
	 * jCompany 6.0: Lógicas de Inicialização de Perfil de Usuario Recebe a
	 * requisição, o perfil do usuário preenchido no ancestral e a interface
	 * para chamada da persistência.
	 * 
	 * Preencher o objeto de Perfil com informações específicas,
	 * especializando-o se necessário.
	 */
	@Override
	public PlcBaseUserProfileVO registrySpecificProfile(
			HttpServletRequest request, HttpServletResponse response,
			PlcBaseUserProfileVO plcPerfilVO) throws Exception {

		// Importante transformar o valor de String para Object
		Map<String, Object> m = new HashMap<String, Object>();

		AppUserProfileVO appUsuarioPerfilVO = (AppUserProfileVO) plcPerfilVO;

		PlcBaseContextVO context = contextMontaUtil.createContextParamMinimum();

		// Deve estar ao final da montagem do Map
		appUsuarioPerfilVO.getPlcVerticalSecurity().putAll(m);

		List<SegUrl> urls = facade.recuperaUrlsAcessoUsuario(context, plcPerfilVO.getLogin());

		if (appUsuarioPerfilVO.getResources() == null) {
			appUsuarioPerfilVO.setResources(new HashMap<String, Object>());
		}
		
		adicionaUrlPadroes(appUsuarioPerfilVO);
		
		if (urls != null) {

			for (SegUrl url : urls) {
				appUsuarioPerfilVO.getResources().put(url.getUrl(), url);
			}
		}

		// Retorna objeto modificado
		return plcPerfilVO;
	}
	
	
	private void adicionaUrlPadroes(AppUserProfileVO appUsuarioPerfilVO){
		
		SegUrl url = new SegUrl();
		
		appUsuarioPerfilVO.getResources().put("soa/service/security", url);
		appUsuarioPerfilVO.getResources().put("f/javax.faces.resource/jsf.js", url);
		
	}
}