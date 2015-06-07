package com.winston.jornada.persistence.jpa.segurl;

import java.util.List;

import com.winston.jornada.persistence.jpa.AppJpaDAO;
import com.winston.jornada.entity.seguranca.SegUrl;


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

/**
 * Classe de Persistência gerada pelo assistente
 */

@PlcAggregationDAOIoC(SegUrl.class)
@SPlcDataAccessObject
@PlcQueryService
public class SegUrlDAO extends AppJpaDAO  {

	@PlcQuery("querySel")
	public native List<SegUrl> findList(
			PlcBaseContextVO context,
			@PlcQueryOrderBy String dynamicOrderByPlc,
			@PlcQueryFirstLine Integer primeiraLinhaPlc, 
			@PlcQueryLineAmount Integer numeroLinhasPlc,		   
			
			@PlcQueryParameter(name="id", expression="id = :id") Long id,
			@PlcQueryParameter(name="casoUso", expression="casoUso like :casoUso || '%' ") String casoUso,
			@PlcQueryParameter(name="url", expression="url like :url || '%' ") String url,
			@PlcQueryParameter(name="bloqueado", expression="bloqueado = :bloqueado") PlcYesNo bloqueado
	);

	@PlcQuery("querySel")
	public native Long findCount(
			PlcBaseContextVO context,
			
			@PlcQueryParameter(name="id", expression="id = :id") Long id,
			@PlcQueryParameter(name="casoUso", expression="casoUso like :casoUso || '%' ") String casoUso,
			@PlcQueryParameter(name="url", expression="url like :url || '%' ") String url,
			@PlcQueryParameter(name="bloqueado", expression="bloqueado = :bloqueado") PlcYesNo bloqueado
	);
		
	@PlcQuery("recuperarUrlsPorLogin")
	public native List<SegUrl> recuperarUrlsPorLogin(
			PlcBaseContextVO context,
			@PlcQueryFirstLine Integer primeiraLinhaPlc, 
			@PlcQueryLineAmount Integer numeroLinhasPlc,		   
			@PlcQueryParameter(name="login", expression="usuario.login = :login") String login
	);

}
