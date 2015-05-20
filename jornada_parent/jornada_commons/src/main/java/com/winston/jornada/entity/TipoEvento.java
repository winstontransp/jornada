package com.winston.jornada.entity;

/**
 * Enum de domínio discreto gerada automaticamente pelo assistente do jCompany.
 */
public enum TipoEvento {
    
	J("{tipoEvento.J}"),
	R("{tipoEvento.R}"),
	C("{tipoEvento.C}"),
	D("{tipoEvento.D}");

	
    /**
     * @return Retorna o codigo.
     */
     
	private String label;
    
    private TipoEvento(String label) {
    	this.label = label;
    }
     
    public String getLabel() {
        return label;
    }
	
}
