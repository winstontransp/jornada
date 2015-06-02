package com.winston.jornada.controller.jsf.segusuario;

import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.powerlogic.jcompany.commons.PlcException;
import com.powerlogic.jcompany.commons.annotation.PlcUriIoC;
import com.powerlogic.jcompany.commons.config.qualifiers.QPlcSpecific;
import com.powerlogic.jcompany.commons.config.stereotypes.SPlcMB;
import com.powerlogic.jcompany.config.aggregation.PlcConfigAggregation;
import com.powerlogic.jcompany.config.collaboration.FormPattern;
import com.powerlogic.jcompany.config.collaboration.PlcConfigForm;
import com.powerlogic.jcompany.config.collaboration.PlcConfigFormLayout;
import com.powerlogic.jcompany.controller.bindingtype.PlcSaveBefore;
import com.powerlogic.jcompany.controller.jsf.PlcBaseMB;
import com.powerlogic.jcompany.controller.jsf.PlcBaseSaveMB;
import com.powerlogic.jcompany.controller.jsf.annotations.PlcHandleException;
import com.winston.jornada.controller.jsf.AppMB;
import com.winston.jornada.entity.seguranca.SegUsuario;
import com.winston.jornada.entity.seguranca.SegUsuarioPerfil;

@PlcConfigAggregation(
	entity = com.winston.jornada.entity.seguranca.SegUsuario.class, 
	details = { @com.powerlogic.jcompany.config.aggregation.PlcConfigDetail(
		clazz = com.winston.jornada.entity.seguranca.SegUsuarioPerfil.class,
		collectionName = "segUsuarioPerfil", numNew = 4, onDemand = false)
	}
)

@PlcConfigForm (
	formPattern=FormPattern.Mdt,
	formLayout = @PlcConfigFormLayout(dirBase="/WEB-INF/fcls/segusuario")
)

/**
 * Classe de Controle gerada pelo assistente
 */
 
@SPlcMB
@PlcUriIoC("segusuario")
@PlcHandleException
public class SegUsuarioMB extends AppMB  {

	private static final long serialVersionUID = 1L;
     		
	/**
	* Entidade da ação injetado pela CDI
	*/
	@Produces  @Named("segusuario")
	public SegUsuario createEntityPlc() {

		if (this.entityPlc==null) {
              this.entityPlc = new SegUsuario();
              this.newEntity();
        }
		
        return (SegUsuario)this.entityPlc;     	
	}
		
//	public boolean antesGravar(@Observes @PlcSaveBefore PlcBaseMB action) throws PlcException {
//
//		System.out.println("Passou no antesGravar");
//		
//		if (action.getEntityPlc() instanceof SegUsuario) {
//			SegUsuario usuario = (SegUsuario) action.getEntityPlc();
//			
//			List<SegUsuarioPerfil> perfis = usuario.getSegUsuarioPerfil();
//			
//			for (SegUsuarioPerfil perfil : perfis) {
//				
//				if (perfil.getSegPerfil() == null || perfil.getSegPerfil().getId() == null) {
//					perfil.setIndExcPlc("S");
//				}
//			}
//		}
//		
//		return true;
//	}
	
}
