package com.winston.jornada.persistence.jpa.segusuario;

import com.winston.jornada.persistence.jpa.AppJpaDAO;
import com.winston.jornada.entity.seguranca.SegUrl;
import com.winston.jornada.entity.seguranca.SegUsuario;
import com.powerlogic.jcompany.persistence.jpa.PlcQueryParameter;
import com.powerlogic.jcompany.domain.type.PlcYesNo;

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

@PlcAggregationDAOIoC(SegUsuario.class)
@SPlcDataAccessObject
@PlcQueryService
public class SegUsuarioDAO extends AppJpaDAO  {

	@PlcQuery("querySel")
	public native List<SegUsuario> findList(
			PlcBaseContextVO context,
			@PlcQueryOrderBy String dynamicOrderByPlc,
			@PlcQueryFirstLine Integer primeiraLinhaPlc, 
			@PlcQueryLineAmount Integer numeroLinhasPlc,		   
			
			@PlcQueryParameter(name="id", expression="id = :id") Long id,
			@PlcQueryParameter(name="nome", expression="nome like :nome || '%' ") String nome,
			@PlcQueryParameter(name="bloqueado", expression="bloqueado = :bloqueado") PlcYesNo bloqueado,
			@PlcQueryParameter(name="login", expression="login like :login || '%' ") String login
	);

	@PlcQuery("querySel")
	public native Long findCount(
			PlcBaseContextVO context,
			
			@PlcQueryParameter(name="id", expression="id = :id") Long id,
			@PlcQueryParameter(name="nome", expression="nome like :nome || '%' ") String nome,
			@PlcQueryParameter(name="bloqueado", expression="bloqueado = :bloqueado") PlcYesNo bloqueado,
			@PlcQueryParameter(name="login", expression="login like :login || '%' ") String login
	);
	
	@PlcQuery("obterUsuarioPorLogin")
	public native SegUsuario obterUsuarioPorLogin(
			PlcBaseContextVO context,

			@PlcQueryParameter(name="login", expression="login like :login || '%' ") String login
	);
	
}
