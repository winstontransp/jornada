package com.winston.jornada.controller.jsf.segurl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import com.powerlogic.jcompany.commons.PlcBaseContextVO;
import com.powerlogic.jcompany.commons.PlcBeanMessages;
import com.powerlogic.jcompany.commons.PlcConstants;
import com.powerlogic.jcompany.commons.PlcConstants.PlcJsfConstantes;
import com.powerlogic.jcompany.commons.PlcException;
import com.powerlogic.jcompany.commons.config.qualifiers.QPlcDefault;
import com.powerlogic.jcompany.commons.config.qualifiers.QPlcSpecific;
import com.powerlogic.jcompany.config.collaboration.FormPattern;
import com.powerlogic.jcompany.config.collaboration.PlcConfigSelection;
import com.powerlogic.jcompany.controller.jsf.PlcBaseSearchMB;
import com.powerlogic.jcompany.controller.jsf.PlcEntityList;
import com.powerlogic.jcompany.controller.util.PlcBeanPopulateUtil;
import com.powerlogic.jcompany.domain.validation.PlcMessage;

@QPlcSpecific(name="segurl")
public class SegurUrlSearchMB extends PlcBaseSearchMB {
	
	@Inject @QPlcDefault
	private PlcBeanPopulateUtil beanPopulateUtil;
	
	/**
	 * Realiza pesquisas para consulta ou selecao.
	 */
	@Override
	public String search(PlcEntityList entityListPlc)  {

		this.entityListPlc = entityListPlc;

		if (contextUtil.getRequest().getAttribute(PlcConstants.ACAO.EVENTO)==null)
			contextUtil.getRequest().setAttribute(PlcConstants.ACAO.EVENTO, PlcConstants.ACAO.EVT_PESQUISAR);

		entityListPlc.setItensPlc(new ArrayList<Object>());

		PlcBaseContextVO context = contextMontaUtil.createContextParam(plcControleConversacao);

		String url = urlUtil.resolveCollaborationNameFromUrl(contextUtil.getRequest());
		FormPattern pattern 			= configUtil.getConfigAggregation(url).pattern().formPattern();
		Collection listaEntity = new ArrayList<Object>();

		if (pattern != null && pattern==FormPattern.Tab){
			// Incluido para acertar problema de informação modo quando pesquisa em Tabular
			String evento = (String)contextUtil.getRequest().getAttribute("evento");
			if (PlcConstants.ACAO.EVT_PESQUISAR.equals(evento))
				contextUtil.getRequest().setAttribute(PlcConstants.MODOS.MODO, PlcConstants.MODOS.MODO_CONSULTA);
		}

		int numPorPagina = -1;
		PlcConfigSelection selecao = configUtil.getConfigCollaboration(urlUtil.resolveCollaborationNameFromUrl(contextUtil.getRequest())).selection();
		if (selecao!=null)
			numPorPagina = selecao.pagination().numberByPage();

		if (searchBefore(selecao,context,entityListPlc)) {
			
			try {
				if (entityPlc == null
						|| (entityPlc != null 
							&& !entityPlc.getClass().isAssignableFrom(configUtil.getConfigAggregation(url).entity()))) {
					entityPlc = configUtil.getConfigAggregation(url).entity().newInstance();
					Map<String, Object> argumentos = getSearchParameters();
					beanPopulateUtil.transferMapToBean(argumentos, entityPlc);
				}	
// ############ ESSAS LINHAS FORAM COMENTADAS PARA QUE A TABULAR POSSA TER PAGINAÇÃO	
//				if (FormPattern.Tab.equals(pattern)) {
//					String propriedadeOrdenacao =configUtil.getConfigCollaboration(urlUtil.resolveCollaborationNameFromUrl(contextUtil.getRequest())).tabular().orderProp();
//					listaEntity = iocControleFacadeUtil.getFacade(url).findSimpleList(context, configUtil.getConfigAggregation(url).entity(), propriedadeOrdenacao);
//				} else if (FormPattern.Ctb.equals(pattern)) {
//						String propriedadeOrdenacao = configUtil.getConfigCollaboration(urlUtil.resolveCollaborationNameFromUrl(contextUtil.getRequest())).tabular().orderProp();
//						plcControleConversacao.setOrdenacaoPlc(propriedadeOrdenacao);
//						listaEntity = iocControleFacadeUtil.getFacade().findList(context, entityPlc, plcControleConversacao.getOrdenacaoPlc(), 0, 0);
//				} else 
				
				if (numPorPagina == -1) {
					listaEntity = iocControleFacadeUtil.getFacade().findList(context, entityPlc, plcControleConversacao.getOrdenacaoPlc(), 0, 0);
				} else {
					// Pesquisa com Navegador
					if (searchWithNavigatorBefore(selecao,context,entityListPlc)){
						listaEntity = searchWithNavigator(entityListPlc, entityPlc);
						listaEntity = searchWithNavigatorAfter(listaEntity);
					}
				}
				
			} catch(PlcException plcE){
				throw plcE;			
			} catch (Exception e) {
				throw new PlcException("PlcBaseSearchMB", "search", e, log, "");
			}
	
			String formatoPesquisa = contextUtil.getRequestParameter("formato");
			if (StringUtils.isNotBlank(formatoPesquisa) && formatoPesquisa.equals("RSS")){
				createRssResponse(listaEntity);
				return searchAfter(selecao,context,entityListPlc);
			}
	
			if (listaEntity == null)
				listaEntity = new ArrayList<Object>();
	
			if (listaEntity.isEmpty()) {
				// Se não existir nenhum, mantém registros em branco para entrada de dados
				msgUtil.msg(PlcBeanMessages.JCOMPANY_WARNING_SEL_NOT_FOUND, new Object[]{}, PlcMessage.Cor.msgVermelhoPlc.toString());
	
			} else {
				
				//Recupera combo dinamico para cada item da coleção
				if (FormPattern.Tab.equals(pattern)|| FormPattern.Ctb.equals(pattern)) {
					retrieveNestedComboTabular(listaEntity);
				}

				entityListPlc.setItensPlc(((List)listaEntity));
				searchCheckExport();
			}
	
			contextUtil.getRequestMap().put(PlcJsfConstantes.PLC_LIMPA_CAIXA_EXCLUSAO, Boolean.TRUE);
		}

		return searchAfter(selecao,context,entityListPlc);
	}
	

}
