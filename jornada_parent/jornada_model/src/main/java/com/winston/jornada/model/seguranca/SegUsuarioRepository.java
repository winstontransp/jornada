package com.winston.jornada.model.seguranca;

import java.util.List;

import javax.enterprise.event.Observes;

import com.powerlogic.jcompany.commons.PlcBaseContextVO;
import com.powerlogic.jcompany.commons.PlcException;
import com.powerlogic.jcompany.commons.annotation.PlcAggregationIoC;
import com.powerlogic.jcompany.commons.config.stereotypes.SPlcRepository;
import com.powerlogic.jcompany.model.bindingtype.PlcInsertBefore;
import com.powerlogic.jcompany.model.bindingtype.PlcUpdateBefore;
import com.winston.jornada.entity.seguranca.SegUsuario;
import com.winston.jornada.entity.seguranca.SegUsuarioPerfil;
import com.winston.jornada.model.AppBaseRepository;

@SPlcRepository
@PlcAggregationIoC(clazz=SegUsuario.class)
public class SegUsuarioRepository extends AppBaseRepository {

//	public void antesIncluir (@Observes @PlcInsertBefore PlcBaseContextVO context) throws PlcException {
//		removeItensEmBranco(context);
//	}
//
//	public void antesAtualizar (@Observes @PlcUpdateBefore PlcBaseContextVO context) throws PlcException {
//		removeItensEmBranco(context);
//	}
//		
//	private void removeItensEmBranco(PlcBaseContextVO context) throws PlcException {
//			
//		if (context.getUrl().contains("segusuario")) {
//			SegUsuario usuario = (SegUsuario) context.getEntityForExtension();
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
//	}
	
}
