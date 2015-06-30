package com.winston.jornada.controller.jsf.segurl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;

import com.powerlogic.jcompany.commons.PlcBaseContextVO;
import com.powerlogic.jcompany.commons.PlcConstants;
import com.powerlogic.jcompany.commons.PlcException;
import com.powerlogic.jcompany.commons.annotation.PlcUriIoC;
import com.powerlogic.jcompany.commons.config.qualifiers.QPlcDefault;
import com.powerlogic.jcompany.commons.config.qualifiers.QPlcDefaultLiteral;
import com.powerlogic.jcompany.commons.config.stereotypes.SPlcMB;
import com.powerlogic.jcompany.commons.util.cdi.PlcCDIUtil;
import com.powerlogic.jcompany.config.aggregation.PlcConfigAggregation;
import com.powerlogic.jcompany.config.collaboration.FormPattern;
import com.powerlogic.jcompany.config.collaboration.PlcConfigForm;
import com.powerlogic.jcompany.config.collaboration.PlcConfigFormLayout;
import com.powerlogic.jcompany.config.collaboration.PlcConfigPagination;
import com.powerlogic.jcompany.config.collaboration.PlcConfigSelection;
import com.powerlogic.jcompany.config.collaboration.PlcConfigTabular;
import com.powerlogic.jcompany.controller.appinfo.PlcAppInfoUtil;
import com.powerlogic.jcompany.controller.jsf.PlcEntityList;
import com.powerlogic.jcompany.controller.jsf.annotations.PlcHandleException;
import com.powerlogic.jcompany.controller.util.PlcIocControllerFacadeUtil;
import com.powerlogic.jcompany.domain.type.PlcYesNo;
import com.powerlogic.jcompany.domain.validation.PlcMessage.Cor;
import com.winston.jornada.commons.AppConstants;
import com.winston.jornada.controller.jsf.AppMB;
import com.winston.jornada.entity.seguranca.SegUrl;

@PlcConfigAggregation(entity = com.winston.jornada.entity.seguranca.SegUrl.class)

@PlcConfigForm(formPattern = FormPattern.Tab, 
	tabular = @PlcConfigTabular(numNew = 4), 
	formLayout = @PlcConfigFormLayout(dirBase = "/WEB-INF/fcls/segurl"),
	selection = @PlcConfigSelection(apiQuerySel="queryMan", 
	pagination=@PlcConfigPagination(numberByPage=50))
)

/**
 * Classe de Controle gerada pelo assistente
 */
@SPlcMB
@PlcUriIoC("segurl")
@PlcHandleException
public class SegUrlMB extends AppMB {

	private static final long serialVersionUID = 1L;

	@Inject @QPlcDefault 
	protected PlcIocControllerFacadeUtil iocControleFacadeUtil;
	
	/**
	 * Entidade da ação injetado pela CDI
	 */
	@Produces
	@Named("segurlLista")
	public PlcEntityList createEntityListPlc() {

		if (this.entityListPlc == null) {
			this.entityListPlc = new PlcEntityList();
			this.newObjectList();
		}
		
		return this.entityListPlc;
	}

	@Override
	public void handleButtonsAccordingFormPattern() {
		super.handleButtonsAccordingFormPattern();
		
		Map<String, Object> requestMap = contextUtil.getRequestMap();
		requestMap.put(PlcConstants.ACAO.EXIBE_BT_INCLUIR, PlcConstants.NAO_EXIBIR);
		
		contextUtil.getRequest().setAttribute(AppConstants.ACAO.EXIBE_BT_RECUPERAR_URLS, PlcConstants.EXIBIR);
	}
	
	public void recuperarUrlsAplicacao(){

		try {
			PlcAppInfoUtil appInfoUtil =  PlcCDIUtil.getInstance().getInstanceByType(PlcAppInfoUtil.class, QPlcDefaultLiteral.INSTANCE);
			appInfoUtil.config(contextUtil.getApplicationContext());
		    sincronizarXMLAutoCadastro(getContext(), appInfoUtil.getAppInfoXml());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Popula o form com informacoes da aplicacao e recursos fornecidos no xml
	 * recuperado.
	 * 
	 * @param plcMapping
	 * @param form
	 * @param request
	 * @param response
	 * @param app
	 * @param xml
	 */
	@SuppressWarnings("unchecked")
	private void sincronizarXMLAutoCadastro(PlcBaseContextVO context,  String xml) throws PlcException {
		
		List<SegUrl> listaRecurso = (List<SegUrl>) iocControleFacadeUtil.getFacade().findSimpleList(getContext(), SegUrl.class, "");
		List<SegUrl> listaNovosRecurso = new ArrayList<SegUrl>(); 

		StringBuffer bf = new StringBuffer(StringUtils.substringBetween(xml, "<recursos>", "</recursos>"));
		String linha = getLinha(bf);

		while (linha != null) {
			
			if (linha.indexOf("<recurso") != -1) {
				String recurso[] = getRecurso(linha);
				boolean achou = false;

				for (SegUrl rec : listaRecurso) {
					
					if (recurso[0].equals(rec.getUrl())) {
						achou = true;
						break;
					}
				}

				if (!achou) {
					SegUrl url = new SegUrl();
					url.setUrl(recurso[0]);
					url.setCasoUso(recurso[1]);
					url.setBloqueado(PlcYesNo.N);
					listaNovosRecurso.add(url);
				}
			}
			
			linha = getLinha(bf);
		}
		
		if (!listaNovosRecurso.isEmpty()) {			
			this.entityListPlc.getItensPlc().clear();
			
			for (SegUrl rec : listaNovosRecurso) {
				this.entityListPlc.getItensPlc().add(rec);
			}
			
			msgUtil.msg("Novos recursos encontrados. Favor clicar no gravar para confirmar a inclusão dos novos recursos", Cor.msgAzulPlc.toString());
			
		} else {
			msgUtil.msg("Nenhum recurso novo encontrado", Cor.msgAmareloPlc.toString());
		}

	}
	
	/**
	 * Retorna uma linha do StringBuffer. Procura o delimitador LF (\n) ou CR (\r). 
	 * A substring encontrada eh removida do buffer.
	 * 
	 * @param bf
	 * @return
	 */
	private String getLinha(StringBuffer bf) {
		String linha = null;
		int lf = bf.indexOf("\n");
		
		if (lf == -1) {
			lf = bf.indexOf("\r");
		}
		
		if (lf != -1) {
			linha = bf.substring(0, lf).trim();
			bf.delete(0, lf + 1);
		}

		return linha;
	}

	
	/**
	 * Recupera o nome do recurso e descricao.
	 * 
	 * @param xml
	 * @return [0] - nome; [1] - descricao (pode ser null)
	 */
	private String[] getRecurso(String xml) {

		String nome = StringUtils.substringBetween(xml, "nome=\"", "\"") /*+ ".do"*/;
		String desc = null;
		
		if (xml.indexOf("descricao=") != -1) {
			desc = StringUtils.substringBetween(xml, "descricao=\"", "\"");
		}

		return new String[] { nome, desc };
	}
	
}
