package com.winston.jornada.entity;

/**
 * Enum de domínio discreto gerada automaticamente pelo assistente do jCompany.
 */
public enum MotivoAfastamento {
    
	F("{motivoAfastamento.F}"),  // Férias
	L("{motivoAfastamento.L}"),  // Licença
	A("{motivoAfastamento.A}"),  // Afastamento
	S("{motivoAfastamento.S}");  // Suspensão 

	
    /**
     * @return Retorna o codigo.
     */
     
	private String label;
    
    private MotivoAfastamento(String label) {
    	this.label = label;
    }
     
    public String getLabel() {
        return label;
    }
	
}
