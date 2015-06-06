package com.winston.jornada.persistence.jpa.macro;

import com.winston.jornada.persistence.jpa.AppJpaDAO;
import com.winston.jornada.entity.Macro;


import com.powerlogic.jcompany.commons.annotation.PlcAggregationDAOIoC;
import com.powerlogic.jcompany.commons.config.stereotypes.SPlcDataAccessObject;
import com.powerlogic.jcompany.persistence.jpa.PlcQueryService;

/**
 * Classe de Persistência gerada pelo assistente
 */

@PlcAggregationDAOIoC(Macro.class)
@SPlcDataAccessObject
@PlcQueryService
public class MacroDAO extends AppJpaDAO  {

	
}
