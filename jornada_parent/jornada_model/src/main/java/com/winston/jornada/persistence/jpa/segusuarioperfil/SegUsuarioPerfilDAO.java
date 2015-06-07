package com.winston.jornada.persistence.jpa.segusuarioperfil;

import java.util.List;

import com.powerlogic.jcompany.commons.PlcBaseContextVO;
import com.powerlogic.jcompany.commons.annotation.PlcAggregationDAOIoC;
import com.powerlogic.jcompany.commons.config.stereotypes.SPlcDataAccessObject;
import com.powerlogic.jcompany.persistence.jpa.PlcQuery;
import com.powerlogic.jcompany.persistence.jpa.PlcQueryParameter;
import com.powerlogic.jcompany.persistence.jpa.PlcQueryService;
import com.winston.jornada.entity.seguranca.SegUsuario;
import com.winston.jornada.entity.seguranca.SegUsuarioPerfil;
import com.winston.jornada.persistence.jpa.AppJpaDAO;

@PlcAggregationDAOIoC(SegUsuarioPerfil.class)
@SPlcDataAccessObject
@PlcQueryService
public class SegUsuarioPerfilDAO extends AppJpaDAO {

	@PlcQuery("obterPerfilUsuarioPorUsuario")
	public native List<SegUsuarioPerfil> obterPerfilUsuarioPorUsuario(
			PlcBaseContextVO context,

			@PlcQueryParameter(name="usuario", expression="segUsuario = :usuario") SegUsuario usuario
	);
		
}
