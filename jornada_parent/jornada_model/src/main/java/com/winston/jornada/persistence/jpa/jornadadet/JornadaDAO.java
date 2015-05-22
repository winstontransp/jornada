package com.winston.jornada.persistence.jpa.jornadadet;

import com.winston.jornada.persistence.jpa.AppJpaDAO;
import com.winston.jornada.entity.Jornada;
import com.powerlogic.jcompany.persistence.jpa.PlcQueryParameter;
import com.winston.jornada.entity.StatusJornada;
import java.util.Date;
import com.winston.jornada.entity.Motorista;

import java.util.List;

import com.powerlogic.jcompany.persistence.jpa.PlcQuery;
import com.powerlogic.jcompany.persistence.jpa.PlcQueryLineAmount;
import com.powerlogic.jcompany.persistence.jpa.PlcQueryOrderBy;
import com.powerlogic.jcompany.persistence.jpa.PlcQueryFirstLine;
import com.powerlogic.jcompany.commons.annotation.PlcAggregationDAOIoC;
import com.powerlogic.jcompany.commons.config.stereotypes.SPlcDataAccessObject;
import com.powerlogic.jcompany.persistence.jpa.PlcQueryService;
import com.powerlogic.jcompany.commons.PlcBaseContextVO;
/**
 * Classe de Persistência gerada pelo assistente
 */

@PlcAggregationDAOIoC(Jornada.class)
@SPlcDataAccessObject
@PlcQueryService
public class JornadaDAO extends AppJpaDAO  {

	@PlcQuery("querySel")
	public native List<Jornada> findList(
			PlcBaseContextVO context,
			@PlcQueryOrderBy String dynamicOrderByPlc,
			@PlcQueryFirstLine Integer primeiraLinhaPlc, 
			@PlcQueryLineAmount Integer numeroLinhasPlc,		   
			
			@PlcQueryParameter(name="id", expression="obj.id = :id") Long id,
			@PlcQueryParameter(name="mctAddress", expression="obj.mctAddress = :mctAddress") Long mctAddress,
			@PlcQueryParameter(name="status", expression="obj.status = :status") StatusJornada status,
			@PlcQueryParameter(name="data", expression="obj.data >= :data") Date data,
			@PlcQueryParameter(name="motorista", expression="obj1 = :motorista") Motorista motorista
	);

	@PlcQuery("querySel")
	public native Long findCount(
			PlcBaseContextVO context,
			
			@PlcQueryParameter(name="id", expression="obj.id = :id") Long id,
			@PlcQueryParameter(name="mctAddress", expression="obj.mctAddress = :mctAddress") Long mctAddress,
			@PlcQueryParameter(name="status", expression="obj.status = :status") StatusJornada status,
			@PlcQueryParameter(name="data", expression="obj.data >= :data") Date data,
			@PlcQueryParameter(name="motorista", expression="obj1 = :motorista") Motorista motorista
	);
	
}
