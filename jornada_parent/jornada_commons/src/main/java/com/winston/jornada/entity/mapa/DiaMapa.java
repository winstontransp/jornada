package com.winston.jornada.entity.mapa;

import java.util.Date;

public class DiaMapa {

	private int dia;
	private StatusDiaMapa statusDia = StatusDiaMapa.I;
	private Long jornadaId;
	private Date data;
	private String cor;
	
	public DiaMapa(int dia) {
		this.dia = dia;
		setStatusDia(StatusDiaMapa.I);
	}
	
	public DiaMapa(int dia, StatusDiaMapa status) {
		this.dia = dia;
		setStatusDia(status);
	}
	
	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public StatusDiaMapa getStatusDia() {
		return statusDia;
	}

	public void setStatusDia(StatusDiaMapa statusDia) {
		this.statusDia = statusDia;
		setCorPorStatus(statusDia);
	}

	public Long getJornadaId() {
		return jornadaId;
	}

	public void setJornadaId(Long jornadaId) {
		this.jornadaId = jornadaId;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getCor() {
		return cor;
	}

	private void setCorPorStatus(StatusDiaMapa status) {
		
		if (StatusDiaMapa.T.equals(status)) {
			this.cor ="#034803";
		} else if (StatusDiaMapa.E.equals(status)) {
			this.cor = "yellow";
		} else if (StatusDiaMapa.D.equals(status)) {
			this.cor = "grey";
		} else if (StatusDiaMapa.F.equals(status)) {
			this.cor = "brown";
		} else if (StatusDiaMapa.A.equals(status)) {
			this.cor = "black";
		} else if (StatusDiaMapa.I.equals(status)) {
			this.cor = "lightgrey";
		} else {
			this.cor = "red";
		} 		
	}

	public String getDescricaoStatus() {
		
		String descricao = "";
		
		if (StatusDiaMapa.T.equals(statusDia)) {
			descricao = "Jornada=" + jornadaId;
		} else if (StatusDiaMapa.E.equals(statusDia)) {
			descricao = "Excesso-Jornada=" + jornadaId;
		} else if (StatusDiaMapa.D.equals(statusDia)) {
			descricao = "Descanso";
		} else if (StatusDiaMapa.F.equals(statusDia)) {
			descricao = "Férias";
		} else if (StatusDiaMapa.A.equals(statusDia)) {
			descricao = "Afastamento";
		} else if (StatusDiaMapa.I.equals(statusDia)) {
			descricao = "Indefinido";
		} else {
			descricao = "ERRO";
		} 		
		
		return descricao;
	}
}
