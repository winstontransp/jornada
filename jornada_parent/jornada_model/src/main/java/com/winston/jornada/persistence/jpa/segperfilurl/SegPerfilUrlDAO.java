package com.winston.jornada.persistence.jpa.segperfilurl;

import java.util.List;

import com.powerlogic.jcompany.commons.PlcBaseContextVO;
import com.powerlogic.jcompany.commons.annotation.PlcAggregationDAOIoC;
import com.powerlogic.jcompany.commons.config.stereotypes.SPlcDataAccessObject;
import com.powerlogic.jcompany.persistence.jpa.PlcQuery;
import com.powerlogic.jcompany.persistence.jpa.PlcQueryParameter;
import com.powerlogic.jcompany.persistence.jpa.PlcQueryService;
import com.winston.jornada.entity.seguranca.SegPerfil;
import com.winston.jornada.entity.seguranca.SegPerfilUrl;
import com.winston.jornada.persistence.jpa.AppJpaDAO;

@PlcAggregationDAOIoC(SegPerfilUrl.class)
@SPlcDataAccessObject
@PlcQueryService
public class SegPerfilUrlDAO extends AppJpaDAO {

	@PlcQuery("obterPerfilUrlPorPerfil")
	public native List<SegPerfilUrl> obterPerfilUrlPorPerfil(
			PlcBaseContextVO context,

			@PlcQueryParameter(name="perfil", expression="segPerfil = :perfil") SegPerfil perfil
	);
		
}
