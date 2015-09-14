/* ************************* META-DADOS GLOBAIS DA APLICAÇÃO ******************************
  ********************** Configurações padrão para toda a aplicação *************************
  ************ Obs: configurações corporativas devem estar no nível anterior,****************
  ************              preferencialmente na camada Bridge               ****************
  *******************************************************************************************/


@PlcConfigApplication(
	definition=@PlcConfigApplicationDefinition(name="Controle de Jornada",acronym="jornada",version=1,release=0),

	classesDiscreteDomain={com.winston.jornada.entity.StatusImportacao.class, 
		com.winston.jornada.entity.StatusJornada.class, 
		com.winston.jornada.entity.TipoEvento.class,
		com.winston.jornada.entity.MotivoAfastamento.class,
		com.winston.jornada.entity.Turno.class},

	classesLookup={com.winston.jornada.entity.Operacao.class, 
		com.winston.jornada.entity.Macro.class}
)


package com.powerlogic.jcompany.config.app;

import com.powerlogic.jcompany.config.application.PlcConfigApplication;
import com.powerlogic.jcompany.config.application.PlcConfigApplicationDefinition;
