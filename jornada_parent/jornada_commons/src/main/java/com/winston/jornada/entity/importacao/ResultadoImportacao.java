package com.winston.jornada.entity.importacao;

import java.util.List;

public class ResultadoImportacao {

	private Object object;
	private Long idImport;
	private List<String> mensagens;
	private List<String> alertas;
	private List<String> erros;

	public ResultadoImportacao(Object object, Long idImport, List<String> mensagens, List<String> alertas, List<String> erros) {
		this.object = object;
		this.idImport = idImport;
		this.mensagens = mensagens;
		this.alertas = alertas;
		this.erros = erros;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public Long getIdImport() {
		return idImport;
	}

	public void setIdImport(Long idImport) {
		this.idImport = idImport;
	}

	public List<String> getErros() {
		return erros;
	}

	public void setErros(List<String> erros) {
		this.erros = erros;
	}

	public List<String> getAlertas() {
		return alertas;
	}

	public void setAlertas(List<String> alertas) {
		this.alertas = alertas;
	}

	public List<String> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<String> mensagens) {
		this.mensagens = mensagens;
	}
	
}
