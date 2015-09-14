package com.winston.jornada.entity.mapa;

/**
 * Enum de domínio discreto gerada automaticamente pelo assistente do jCompany.
 */
public enum StatusDiaMapa {
    
	T("{statusDiaMapa.T}"), // Trabalho
	E("{statusDiaMapa.E}"), // Excesso
	D("{statusDiaMapa.D}"), // Descanso
	F("{statusDiaMapa.F}"), // Férias
	A("{statusDiaMapa.A}"), // Atestado
	L("{statusDiaMapa.L}"), // Licença
	S("{statusDiaMapa.S}"), // Suspensão
	I("{statusDiaMapa.I}"); // Indefinido

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
