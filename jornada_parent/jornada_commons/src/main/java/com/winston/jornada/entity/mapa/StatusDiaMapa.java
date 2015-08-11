package com.winston.jornada.entity.mapa;

/**
 * Enum de domínio discreto gerada automaticamente pelo assistente do jCompany.
 */
public enum StatusDiaMapa {
    
	T("{statusDiaMapa.T}"),
	E("{statusDiaMapa.E}"),
	D("{statusDiaMapa.D}"),
	F("{statusDiaMapa.F}"),
	A("{statusDiaMapa.A}"),
	I("{statusDiaMapa.I}");

	
    /**
     * @return Retorna o codigo.
     */
     
	private String label;
    
    private StatusDiaMapa(String label) {
    	this.label = label;
    }
     
    public String getLabel() {
        return label;
    }
	
}
