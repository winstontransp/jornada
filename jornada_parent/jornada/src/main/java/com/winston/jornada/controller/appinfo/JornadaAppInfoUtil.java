package com.winston.jornada.controller.appinfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Specializes;

import org.apache.commons.lang.StringUtils;

import com.powerlogic.jcompany.config.collaboration.FormPattern;
import com.powerlogic.jcompany.config.collaboration.PlcConfigCollaborationPOJO;
import com.powerlogic.jcompany.controller.appinfo.PlcAppInfoUtil;
import com.powerlogic.jcompany.controller.appinfo.PlcAppMBInfo;
import com.powerlogic.jcompany.controller.util.PlcConfigUtil;

@Specializes
public class JornadaAppInfoUtil extends PlcAppInfoUtil {
	
	/**
	 * Descobre todas as urls de recursos a partir de uma uri (identificador) de caso de uso.
	 * @param uri
	 * @return
	 */
	@Override
	public List<PlcAppMBInfo> findAllUCWithURI(String uri) {
		List<PlcAppMBInfo> appMBInfos = new ArrayList<PlcAppMBInfo>();
		PlcConfigCollaborationPOJO configCollaborationPOJO = configUtil.getConfigCollaboration(uri);
		
		PlcAppMBInfo info;
		String url = null;
		String dirBase = null;		
		String casoUso = uri;
		File file = null;
		File[] pages = null;
		
		if (configCollaborationPOJO != null) {
			// obtendo diretorio base dos xhtmls
			dirBase = configCollaborationPOJO.formLayout().dirBase();
			file = getBaseDirectory(dirBase, uri);
			
			FormPattern pattern = configUtil.getConfigAggregation(uri).pattern().formPattern();
			
			if(!pattern.equals(FormPattern.Ctl)){
				//adiciona a logica sem o sufixo:
				info = new PlcAppMBInfo("f/n/" + uri, casoUso);
				appMBInfos.add(info);

				//adiciona a logica sem o sufixo:
				info = new PlcAppMBInfo("soa/service/grid." + uri, casoUso);
				appMBInfos.add(info);

				//adiciona a logica sem o sufixo:
				info = new PlcAppMBInfo("soa/service/autocomplete." + uri, casoUso);
				appMBInfos.add(info);				
				
				//adiciona com o sufixo
				url = uri+StringUtils.uncapitalize(pattern.toString());
				info = new PlcAppMBInfo("f/n/" + url, casoUso);
				appMBInfos.add(info);
				
				//verifica se tem pagina sel
				if (!pattern.equals(FormPattern.Sel) && (pages = file.listFiles()) != null) {
					
					for (File page : pages) {
						if (page.getName().contains("sel.")) {
							
							//adiciona a logica sel
							url = uri + "sel";
							info = new PlcAppMBInfo("f/n/" + url, casoUso);
							appMBInfos.add(info);
						}
					}
				}
				
			} else {	

				// obtendo as paginas.
				if ((pages = file.listFiles()) != null) {
					
					for (File page : pages) {
						
						for (String suffix : PlcConfigUtil.SUFIXOS_URL) {
							// descobrindo qual o caso de uso (man, mdt...)
	
							if (page.getName().contains(StringUtils.capitalize(suffix+"."))) {
								
								/*
								 * Garante que estou trantando arquivos para a uri em questão,
								 * pois um mesmo diretorio pode servir 2 ou + uri's
								 * Padrão de arquivos: {identificadorCDU}{sufixo}.{extensao}
								 */
								if (StringUtils.startsWithIgnoreCase(page.getName(), uri) ){
									// monta a url a partir do nome do xhtml
									url = uri + (uri.endsWith(suffix)?"":suffix);
									
									//TODO: Fábio Mendes => Rever esta parte do código, partnner de URL
									info = new PlcAppMBInfo("f/n/" + url, casoUso);
									appMBInfos.add(info);
								}
							}
						}
					}
				}
			}
		}
		
		return appMBInfos;
	}
}
