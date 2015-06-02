package com.winston.jornada.model.seguranca;

import java.util.List;

import javax.enterprise.event.Observes;

import com.powerlogic.jcompany.commons.PlcBaseContextVO;
import com.powerlogic.jcompany.commons.PlcException;
import com.powerlogic.jcompany.commons.annotation.PlcAggregationIoC;
import com.powerlogic.jcompany.commons.config.stereotypes.SPlcRepository;
import com.powerlogic.jcompany.model.bindingtype.PlcInsertBefore;
import com.powerlogic.jcompany.model.bindingtype.PlcUpdateBefore;
import com.winston.jornada.entity.seguranca.SegPerfil;
import com.winston.jornada.entity.seguranca.SegPerfilUrl;
import com.winston.jornada.entity.seguranca.SegUsuarioPerfil;
import com.winston.jornada.model.AppBaseRepository;

@SPlcRepository
@PlcAggregationIoC(clazz=SegPerfil.class)
public class SegPerfilRepository extends AppBaseRepository {

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
//		if (context.getUrl().contains("segperfil")) {
//			SegPerfil perfil = (SegPerfil) context.getEntityForExtension();
//			
//			List<SegUsuarioPerfil> usuarios = perfil.getSegUsuarioPerfil();
//			
//			for (SegUsuarioPerfil usuario : usuarios) {
//				
//				if (usuario.getSegPerfil() == null || usuario.getSegPerfil().getId() == null) {
//					usuario.setIndExcPlc("S");
//				}
//			}
//			
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
