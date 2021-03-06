package com.winston.jornada.entity;

/**
 * Enum de domínio discreto gerada automaticamente pelo assistente do jCompany.
 */
public enum StatusJornada {
    
	I("{statusJornada.I}"), // Incompleta
	F("{statusJornada.F}"), // Falha
	C("{statusJornada.C}"), // Criticas
	O("{statusJornada.O}"); // Ok
	
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
