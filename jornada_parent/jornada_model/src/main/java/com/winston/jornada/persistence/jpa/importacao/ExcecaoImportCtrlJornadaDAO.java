package com.winston.jornada.persistence.jpa.importacao;

import java.util.Date;
import java.util.List;

import com.powerlogic.jcompany.commons.PlcBaseContextVO;
import com.powerlogic.jcompany.commons.annotation.PlcAggregationDAOIoC;
import com.powerlogic.jcompany.commons.config.stereotypes.SPlcDataAccessObject;
import com.powerlogic.jcompany.domain.type.PlcYesNo;
import com.powerlogic.jcompany.persistence.jpa.PlcQuery;
import com.powerlogic.jcompany.persistence.jpa.PlcQueryFirstLine;
import com.powerlogic.jcompany.persistence.jpa.PlcQueryLineAmount;
import com.powerlogic.jcompany.persistence.jpa.PlcQueryOrderBy;
import com.powerlogic.jcompany.persistence.jpa.PlcQueryParameter;
import com.powerlogic.jcompany.persistence.jpa.PlcQueryService;
import com.winston.jornada.entity.importacao.ExcecaoImportCtrlJornada;
import com.winston.jornada.entity.importacao.StatusImportacao;
import com.winston.jornada.persistence.jpa.AppJpaDAO;
/**
 * Classe de Persistência gerada pelo assistente
 */

@PlcAggregationDAOIoC(ExcecaoImportCtrlJornada.class)
@SPlcDataAccessObject
@PlcQueryService
public class ExcecaoImportCtrlJornadaDAO extends AppJpaDAO  {

	@PlcQuery("querySel")
	public native List<ExcecaoImportCtrlJornada> findList(
			PlcBaseContextVO context,
			@PlcQueryOrderBy String dynamicOrderByPlc,
			@PlcQueryFirstLine Integer primeiraLinhaPlc, 
			@PlcQueryLineAmount Integer numeroLinhasPlc,		   
			
			@PlcQueryParameter(name="id", expression="id = :id") Long id,
			@PlcQueryParameter(name="dataImportacao", expression="dataImportacao >= :dataImportacao  ") Date dataImportacao,
			@PlcQueryParameter(name="nomeArquivo", expression="nomeArquivo like '%' || :nomeArquivo || '%' ") String nomeArquivo,
			@PlcQueryParameter(name="possuiExcecoes", expression="possuiExcecoes = :possuiExcecoes") PlcYesNo possuiExcecoes,
			@PlcQueryParameter(name="statusImportacao", expression="statusImportacao = :statusImportacao") StatusImportacao statusImportacao
	);

	@PlcQuery("querySel")
	public native Long findCount(
			PlcBaseContextVO context,
			
			@PlcQueryParameter(name="id", expression="id = :id") Long id,
			@PlcQueryParameter(name="dataImportacao", expression="dataImportacao >= :dataImportacao  ") Date dataImportacao,
			@PlcQueryParameter(name="nomeArquivo", expression="nomeArquivo like '%' || :nomeArquivo || '%' ") String nomeArquivo,
			@PlcQueryParameter(name="possuiExcecoes", expression="possuiExcecoes = :possuiExcecoes") PlcYesNo possuiExcecoes,
			@PlcQueryParameter(name="statusImportacao", expression="statusImportacao = :statusImportacao") StatusImportacao statusImportacao
	);
	
}
