package com.winston.jornada.model.seguranca;

import com.powerlogic.jcompany.commons.annotation.PlcAggregationIoC;
import com.powerlogic.jcompany.commons.config.stereotypes.SPlcRepository;
import com.winston.jornada.entity.seguranca.SegPerfil;
import com.winston.jornada.model.AppBaseRepository;

@SPlcRepository
@PlcAggregationIoC(clazz=SegPerfil.class)
public class SegPerfilRepository extends AppBaseRepository {
	
}
