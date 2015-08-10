package com.winston.jornada.persistence.jpa.returnmessage;

import java.util.Date;

import com.powerlogic.jcompany.commons.PlcBaseContextVO;
import com.powerlogic.jcompany.commons.annotation.PlcAggregationDAOIoC;
import com.powerlogic.jcompany.commons.config.stereotypes.SPlcDataAccessObject;
import com.powerlogic.jcompany.persistence.jpa.PlcQuery;
import com.powerlogic.jcompany.persistence.jpa.PlcQueryParameter;
import com.powerlogic.jcompany.persistence.jpa.PlcQueryService;
import com.winston.jornada.entity.ReturnMessage;
import com.winston.jornada.persistence.jpa.AppJpaDAO;

/**
 * Classe de Persistência gerada pelo assistente
 */

@PlcAggregationDAOIoC(ReturnMessage.class)
@SPlcDataAccessObject
@PlcQueryService
public class ReturnMessageDAO extends AppJpaDAO {
	
	@PlcQuery("obterInicioInterjornada")
	public native Date obterInicioInterjornada(
		PlcBaseContextVO context,
		
		@PlcQueryParameter(name="mctAddress", expression="mctAddress = :mctAddress") Long mctAddress,
		@PlcQueryParameter(name="fimInterjornada", expression="fimInterjornada = :fimInterjornada") Date fimInterjornada
	);
			
}
