package com.winston.jornada.entity;

/**
 * Enum de domínio discreto gerada automaticamente pelo assistente do jCompany.
 */
public enum StatusJornada {
    
	F("{statusJornada.F}"),
	P("{statusJornada.P}"),
	C("{statusJornada.C}");

	
    /**
     * @return Retorna o codigo.
     */
     
	private String label;
    
    private StatusJornada(String label) {
    	this.label = label;
    }
     
    public String getLabel() {
        return label;
    }
	
}
