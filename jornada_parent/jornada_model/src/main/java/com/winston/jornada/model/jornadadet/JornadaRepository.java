package com.winston.jornada.model.jornadadet;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.powerlogic.jcompany.commons.PlcBaseContextVO;
import com.powerlogic.jcompany.commons.PlcException;
import com.powerlogic.jcompany.commons.annotation.PlcAggregationIoC;
import com.powerlogic.jcompany.commons.config.stereotypes.SPlcRepository;
import com.powerlogic.jcompany.model.bindingtype.PlcUpdateBefore;
import com.winston.jornada.entity.Jornada;
import com.winston.jornada.model.AppBaseRepository;

/**
 * Classe de Modelo gerada pelo assistente
 */

@SPlcRepository
@PlcAggregationIoC(clazz = Jornada.class)
public class JornadaRepository extends AppBaseRepository {

	@Inject
	private JornadaEventoRepository eventoRepository;
	
	@Inject
	private JornadaCriticaRepository criticaRepository;
	
	public void antesAtualizar (@Observes @PlcUpdateBefore PlcBaseContextVO context) throws PlcException {
		
		if (context.getUrl().contains("jornadadet")) {
			Jornada jornada = (Jornada) context.getEntityForExtension();
			
			if (jornada != null) {
				eventoRepository.antesAtualizar(context, jornada);
				criticaRepository.antesAtualizar(context, jornada);
			}
		}
	}
	
}
