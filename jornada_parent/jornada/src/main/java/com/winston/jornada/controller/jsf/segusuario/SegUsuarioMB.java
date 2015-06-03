package com.winston.jornada.controller.jsf.segusuario;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import com.powerlogic.jcompany.commons.PlcException;
import com.powerlogic.jcompany.commons.annotation.PlcUriIoC;
import com.powerlogic.jcompany.commons.config.stereotypes.SPlcMB;
import com.powerlogic.jcompany.config.aggregation.PlcConfigAggregation;
import com.powerlogic.jcompany.config.collaboration.FormPattern;
import com.powerlogic.jcompany.config.collaboration.PlcConfigForm;
import com.powerlogic.jcompany.config.collaboration.PlcConfigFormLayout;
import com.powerlogic.jcompany.controller.bindingtype.PlcGravaValidarAntes;
import com.powerlogic.jcompany.controller.jsf.PlcBaseMB;
import com.powerlogic.jcompany.controller.jsf.annotations.PlcHandleException;
import com.winston.jornada.controller.jsf.AppMB;
import com.winston.jornada.entity.seguranca.SegUsuario;

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
		
//	public boolean antesGravar(@Observes @PlcGravaValidarAntes PlcBaseMB action) throws PlcException {
//
//		if (action.getEntityPlc() instanceof SegUsuario) {
//			SegUsuario usuario = (SegUsuario) action.getEntityPlc();
//
//			if (usuario.getSenhaAux() != null && usuario.getSegUsuarioPerfil() == null) {
//				throw new PlcException("seguranca.usuario.informar.ambas.senhas");
//			}
//
//			if (usuario.getSenhaAux() == null && usuario.getSegUsuarioPerfil() != null) {
//				throw new PlcException("seguranca.usuario.informar.ambas.senhas");
//			}
//			
//			
//			if (usuario.getSenhaAux() != null && usuario.getSegUsuarioPerfil() != null) {
//				
//				if (usuario.getSenhaAux().equals(usuario.getSenhaAuxConfirmacao())) {
//					throw new PlcException("seguranca.usuario.senhas.nao.conferem");
//				} else {
//				
//					try {
//						MessageDigest md = MessageDigest.getInstance("SHA1");
//	
//						md.update(usuario.getSenhaAux().getBytes());
//						byte[] hash = md.digest();
//	
//						String senha = bytesToHex(hash);
//	
//						usuario.setSenha(senha);
//					} catch (NoSuchAlgorithmException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}
//		
//		return true;
//	}
//	
//	private static String bytesToHex(byte[] b) {
//		char hexDigit[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
//				'A', 'B', 'C', 'D', 'E', 'F' };
//		StringBuffer buf = new StringBuffer();
//		
//		for (int j = 0; j < b.length; j++) {
//			buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
//			buf.append(hexDigit[b[j] & 0x0f]);
//		}
//		
//		return buf.toString();
//	}
	
}
