package com.winston.jornada.facade;

import java.io.IOException;
import java.util.Map;

import javax.enterprise.inject.Specializes;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.powerlogic.jcompany.commons.PlcBaseUserProfileDTO;
import com.powerlogic.jcompany.commons.PlcBaseUserProfileVO;
import com.powerlogic.jcompany.commons.PlcConstants;
import com.powerlogic.jcompany.commons.integration.IPlcJSecurityApi;
import com.powerlogic.jcompany.commons.integration.impl.PlcJSecurityApiImpl;
import com.winston.jornada.entity.seguranca.SegUrl;

@Specializes
public class AppSegurancaImpl extends PlcJSecurityApiImpl implements IPlcJSecurityApi {
	
	@Override
	public void efetivaSeguranca(ServletRequest request, ServletResponse response) {
		super.efetivaSeguranca(request, response);
		
//		PlcBaseUserProfileVO perfil = (PlcBaseUserProfileVO) ((HttpServletRequest) request).getSession().getAttribute(PlcConstants.USER_INFO_KEY);
//		
//		if (perfil != null && !possuiDireitoAcesso(perfil, recuperaNomeRecurso((HttpServletRequest) request))) {
//			try {
//				((HttpServletResponse)response).sendError(HttpServletResponse.SC_FORBIDDEN);
//				response.flushBuffer();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		} else {
//			super.efetivaSeguranca(request, response);
//		}
	}
	
	
	/**
	 * Verifica se o usuario tem direito de acesso ao recurso e devolve os
	 * eventos permitidos e modos de visualizacao de campos. Caso o recurso nao
	 * seja encontrado assume-se que o usuario nao tem acesso.
	 * 
	 * @param perfil
	 * @param recurso
	 * @return
	 */
	private Boolean possuiDireitoAcesso(PlcBaseUserProfileVO perfil, String recurso) {

		if (perfil == null) {
			return Boolean.FALSE;
		}

		if (!perfil.isProfileLoaded() || perfil.isAccessDenied()) {
			return Boolean.FALSE;
		}

		Map<String, Object> rec = perfil.getResources();

		if (rec != null) {			
			SegUrl url =  (SegUrl) rec.get(recurso);
			
			if (url != null){
				return Boolean.TRUE;
			}
		}

		if (recurso == null || recurso.trim().isEmpty() || recurso.contains("f/rfRes/")){
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}
	
	
	/**
	 * Remove sessionid da uri, se existir.
	 * 
	 * @param uri
	 * @return
	 */
	private String removeSessionId(String uri) {
		int ini = uri.indexOf(";jsessionid");
		
		if (ini != -1) {
			int fim = uri.indexOf("?");

			if (fim == -1) {
				uri = uri.substring(0, ini);
			} else {
				uri = uri.substring(0, ini) + uri.substring(fim);
			}
		}
		
		return uri;
	}
	
	/**
	 * Recupera o nome do recurso, removendo a string "jsessionid".
	 * 
	 * @param request
	 * @return
	 */
	public String recuperaNomeRecurso(HttpServletRequest request) {
		String uri = removeSessionId(request.getRequestURI());
		String ctx = request.getContextPath();
		uri = uri.substring(ctx.length() +1);
		
		return uri;
	}
	
	@Override
	public PlcBaseUserProfileDTO carregaProfileJSecurity(
			HttpServletRequest request, HttpServletResponse response,
			PlcBaseUserProfileDTO usuario) {
		
		return usuario;//return super.carregaProfileJSecurity(request, response, usuario);
	}

}
