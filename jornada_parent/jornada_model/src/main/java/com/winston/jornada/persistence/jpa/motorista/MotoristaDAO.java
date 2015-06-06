package com.winston.jornada.persistence.jpa.motorista;

import java.util.List;

import javax.persistence.NoResultException;

import com.powerlogic.jcompany.commons.PlcBaseContextVO;
import com.powerlogic.jcompany.commons.annotation.PlcAggregationDAOIoC;
import com.powerlogic.jcompany.commons.config.stereotypes.SPlcDataAccessObject;
import com.powerlogic.jcompany.persistence.jpa.PlcQuery;
import com.powerlogic.jcompany.persistence.jpa.PlcQueryFirstLine;
import com.powerlogic.jcompany.persistence.jpa.PlcQueryLineAmount;
import com.powerlogic.jcompany.persistence.jpa.PlcQueryOrderBy;
import com.powerlogic.jcompany.persistence.jpa.PlcQueryParameter;
import com.powerlogic.jcompany.persistence.jpa.PlcQueryService;
import com.winston.jornada.entity.Motorista;
import com.winston.jornada.entity.Operacao;
import com.winston.jornada.persistence.jpa.AppJpaDAO;

@PlcAggregationDAOIoC(Motorista.class)
@SPlcDataAccessObject
@PlcQueryService
public class MotoristaDAO extends AppJpaDAO {

	@PlcQuery("querySel")
	public native List<Motorista> findList(
			PlcBaseContextVO context,
			@PlcQueryOrderBy String dynamicOrderByPlc,
			@PlcQueryFirstLine Integer primeiraLinhaPlc, 
			@PlcQueryLineAmount Integer numeroLinhasPlc,		   
			
			@PlcQueryParameter(name="id", expression="obj.id = :id") Long id,
			@PlcQueryParameter(name="nome", expression="obj.nome = :nome") String nome,
			@PlcQueryParameter(name="matricula", expression="obj.matricula = :matricula") Long matricula,
			@PlcQueryParameter(name="operacao", expression="obj1 = :operacao") Operacao operacao
	);

	@PlcQuery("querySel")
	public native Long findCount(
			PlcBaseContextVO context,
			
			@PlcQueryParameter(name="id", expression="obj.id = :id") Long id,
			@PlcQueryParameter(name="nome", expression="obj.nome = :nome") String nome,
			@PlcQueryParameter(name="matricula", expression="obj.matricula = :matricula") Long matricula,
			@PlcQueryParameter(name="operacao", expression="obj1 = :operacao") Operacao operacao
	);
	
	@PlcQuery("queryBuscaMotoristaPorMatricula")
	public native Motorista findMotoristaPorMatricula(
			PlcBaseContextVO context,
			@PlcQueryParameter(name="matricula", expression="matricula = :matricula") Long matricula //,
	) throws NoResultException;
	
	
}
