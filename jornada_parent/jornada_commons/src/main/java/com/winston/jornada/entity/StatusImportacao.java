package com.winston.jornada.entity;

/**
 * Enum de domínio discreto gerada automaticamente pelo assistente do jCompany.
 */
public enum StatusImportacao {

	S("{statusImportacao.S}"), 
	F("{statusImportacao.F}");

	/**
	 * @return Retorna o codigo.
	 */

	private String label;

	private StatusImportacao(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
