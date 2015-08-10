package com.winston.jornada.persistence.jpa.mapa;

import com.winston.jornada.persistence.jpa.AppJpaDAO;
import com.winston.jornada.entity.mapa.Mapa;
import java.util.Date;
import com.powerlogic.jcompany.persistence.jpa.PlcQueryParameter;
import com.winston.jornada.entity.Motorista;
import com.winston.jornada.entity.Turno;
import com.winston.jornada.entity.Operacao;

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

@PlcAggregationDAOIoC(Mapa.class)
@SPlcDataAccessObject
@PlcQueryService
public class MapaDAO extends AppJpaDAO  {

	@PlcQuery("querySel")
	public native List<Mapa> findList(
		PlcBaseContextVO context,
		@PlcQueryOrderBy String dynamicOrderByPlc,
		@PlcQueryFirstLine Integer primeiraLinhaPlc, 
		@PlcQueryLineAmount Integer numeroLinhasPlc,		   
		
		@PlcQueryParameter(name="dataInicio", expression="dataInicio >= :dataInicio ") Date dataInicio,
		@PlcQueryParameter(name="dataFim", expression="dataFim <= :dataFim ") Date dataFim,
		@PlcQueryParameter(name="motorista", expression="motorista = :motorista ") Motorista motorista,
		@PlcQueryParameter(name="turno", expression="turno = :turno ") Turno turno,
		@PlcQueryParameter(name="operacao", expression="operacao = :operacao ") Operacao operacao
	);

	@PlcQuery("querySel")
	public native Long findCount(
		PlcBaseContextVO context,
		
		@PlcQueryParameter(name="dataInicio", expression="dataInicio >= :dataInicio ") Date dataInicio,
		@PlcQueryParameter(name="dataFim", expression="dataFim <= :dataFim ") Date dataFim,
		@PlcQueryParameter(name="motorista", expression="motorista = :motorista ") Motorista motorista,
		@PlcQueryParameter(name="turno", expression="turno = :turno ") Turno turno,
		@PlcQueryParameter(name="operacao", expression="operacao = :operacao ") Operacao operacao
	);
	
}
