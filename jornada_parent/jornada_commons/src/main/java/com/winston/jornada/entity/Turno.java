package com.winston.jornada.entity;

/**
 * Enum de domínio discreto gerada automaticamente pelo assistente do jCompany.
 */
public enum Turno {
    
	N("{turno.N}"),
	V("{turno.V}");

	
    /**
     * @return Retorna o codigo.
     */
     
	private String label;
    
    private Turno(String label) {
    	this.label = label;
    }
     
    public String getLabel() {
        return label;
    }
	
}
