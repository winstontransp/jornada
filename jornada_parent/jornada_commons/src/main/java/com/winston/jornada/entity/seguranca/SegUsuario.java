package com.winston.jornada.entity.seguranca;

import com.powerlogic.jcompany.domain.type.PlcYesNo;
import com.winston.jornada.entity.AppBaseEntity;

public class SegUsuario extends AppBaseEntity {
	private String nome;
	private String login;
	private String senha;
	private PlcYesNo bloqueado;
}
