package com.winston.jornada.entity.importacao.apoio;

import com.powerlogic.jcompany.config.domain.PlcFileAttach;

public class RelatCtrlJornada {

	private static final long serialVersionUID = -4886171903512129769L;

	private Long id;
	
	@PlcFileAttach(extension={"xlsx"}, multiple=false,  maximumLength=2048)
	private RelatCtrlJornadaArquivo relatCtrlJornadaArquivo;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public RelatCtrlJornadaArquivo getRelatCtrlJornadaArquivo() {
		return relatCtrlJornadaArquivo;
	}
	
	public void setRelatCtrlJornadaArquivo(
			RelatCtrlJornadaArquivo relatCtrlJornadaArquivo) {
		this.relatCtrlJornadaArquivo = relatCtrlJornadaArquivo;
	}
	
}
