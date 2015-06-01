package com.winston.jornada.entity.seguranca;

import com.powerlogic.jcompany.domain.type.PlcYesNo;
import com.winston.jornada.entity.AppBaseEntity;

public class SegUrl extends AppBaseEntity {
	private String url;
	private String casoUso;
	private PlcYesNo bloqueado;
}
