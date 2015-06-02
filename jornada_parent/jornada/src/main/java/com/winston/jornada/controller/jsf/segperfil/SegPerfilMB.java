package com.winston.jornada.controller.jsf.segperfil;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

import com.powerlogic.jcompany.commons.annotation.PlcUriIoC;
import com.powerlogic.jcompany.commons.config.stereotypes.SPlcMB;
import com.powerlogic.jcompany.config.aggregation.PlcConfigAggregation;
import com.powerlogic.jcompany.config.collaboration.FormPattern;
import com.powerlogic.jcompany.config.collaboration.PlcConfigForm;
import com.powerlogic.jcompany.config.collaboration.PlcConfigFormLayout;
import com.powerlogic.jcompany.controller.jsf.annotations.PlcHandleException;
import com.winston.jornada.controller.jsf.AppMB;
import com.winston.jornada.entity.seguranca.SegPerfil;

@PlcConfigAggregation(
	entity = com.winston.jornada.entity.seguranca.SegPerfil.class, 
	details = { @com.powerlogic.jcompany.config.aggregation.PlcConfigDetail(
					clazz = com.winston.jornada.entity.seguranca.SegUsuarioPerfil.class,
					collectionName = "segUsuarioPerfil", numNew = 4, onDemand = false),
				@com.powerlogic.jcompany.config.aggregation.PlcConfigDetail(
					clazz = com.winston.jornada.entity.seguranca.SegPerfilUrl.class,
					collectionName = "segPerfilUrl", numNew = 4, onDemand = false)					
	}
)

@PlcConfigForm (
	formPattern=FormPattern.Mdt,
	formLayout = @PlcConfigFormLayout(dirBase="/WEB-INF/fcls/segperfil")
)

/**
 * Classe de Controle gerada pelo assistente
 */
 
@SPlcMB
@PlcUriIoC("segperfil")
@PlcHandleException
public class SegPerfilMB extends AppMB {

	private static final long serialVersionUID = 1L;
     		
	/**
	* Entidade da ação injetado pela CDI
	*/
	@Produces  @Named("segperfil")
	public SegPerfil createEntityPlc() {
		
        if (this.entityPlc==null) {
              this.entityPlc = new SegPerfil();
              this.newEntity();
        }
        
        return (SegPerfil)this.entityPlc;     	
	}

	
//	public void antesGravar(@Observes @PlcSaveBefore SegPerfilMB mb) throws PlcException {
//
//		if (mb.getContext().getUrl().contains("segperfil")) {
//			// Marca os Perfis em branco para exclusão
//			SegPerfil perfil = (SegPerfil) mb.getContext().getEntityForExtension();
//			
//			List<SegUsuarioPerfil> usuarios = perfil.getSegUsuarioPerfil();
//			
//			for (SegUsuarioPerfil usuario : usuarios) {
//				
//				if (usuario.getSegUsuario() == null || usuario.getSegUsuario().getId() == null) {
//					usuario.setIndExcPlc("S");
//				}
//			}
//			
//			// Marca as URL em branco para exclusão
//			List<SegPerfilUrl> urls = perfil.getSegPerfilUrl();
//			
//			for (SegPerfilUrl url : urls) {
//				
//				if (url.getId() == null ) {
//					url.setIndExcPlc("S");
//				}
//			}
//			
//		}
//	}
	
}
