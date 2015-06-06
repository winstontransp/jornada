package com.winston.jornada.persistence.jpa.operacao;

import com.winston.jornada.persistence.jpa.AppJpaDAO;
import com.winston.jornada.entity.Operacao;


import com.powerlogic.jcompany.commons.annotation.PlcAggregationDAOIoC;
import com.powerlogic.jcompany.commons.config.stereotypes.SPlcDataAccessObject;
import com.powerlogic.jcompany.persistence.jpa.PlcQueryService;

/**
 * Classe de Persistência gerada pelo assistente
 */

@PlcAggregationDAOIoC(Operacao.class)
@SPlcDataAccessObject
@PlcQueryService
public class OperacaoDAO extends AppJpaDAO  {

	
}
