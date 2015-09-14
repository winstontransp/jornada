package com.winston.jornada.entity.mapa;

import java.util.Date;

import com.winston.jornada.entity.StatusJornada;

public class DiaMapa {

	private int dia;
	private StatusDiaMapa statusDia = StatusDiaMapa.I;
	private StatusJornada statusJornada = StatusJornada.C;
	private Long jornadaId;
	private Long motoristaId;
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

	public StatusJornada getStatusJornada() {
		return statusJornada;
	}

	public void setStatusJornada(StatusJornada statusJornada) {
		this.statusJornada = statusJornada;
	}

	public Long getJornadaId() {
		return jornadaId;
	}

	public void setJornadaId(Long jornadaId) {
		this.jornadaId = jornadaId;
	}

	public Long getMotoristaId() {
		return motoristaId;
	}

	public void setMotoristaId(Long motoristaId) {
		this.motoristaId = motoristaId;
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
		
//		T("{statusDiaMapa.T}"), // Trabalho
//		E("{statusDiaMapa.E}"), // Excesso
//		D("{statusDiaMapa.D}"), // Descanso
//		F("{statusDiaMapa.F}"), // Férias
//		A("{statusDiaMapa.A}"), // Atestado
//		L("{statusDiaMapa.L}"), // Licença
//		S("{statusDiaMapa.S}"), // Suspensão
//		I("{statusDiaMapa.I}"); // Indefinido
		
		if (StatusDiaMapa.T.equals(status)) {
			this.cor ="#034803";
		} else if (StatusDiaMapa.E.equals(status)) {
			this.cor = "#CCCC0F";
		} else if (StatusDiaMapa.D.equals(status)) {
			this.cor = "grey";
		} else if (StatusDiaMapa.F.equals(status)) {
			this.cor = "#4DBC4D";
		} else if (StatusDiaMapa.A.equals(status)) {
			this.cor = "blue";
		} else if (StatusDiaMapa.L.equals(status)) {
			this.cor = "#AA6FBC";
		} else if (StatusDiaMapa.S.equals(status)) {
			this.cor = "brown";
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
			descricao = "Excesso=" + jornadaId;
		} else if (StatusDiaMapa.D.equals(statusDia)) {
			descricao = "Descanso";
		} else if (StatusDiaMapa.F.equals(statusDia)) {
			descricao = "Férias";
		} else if (StatusDiaMapa.A.equals(statusDia)) {
			descricao = "Atestado";
		} else if (StatusDiaMapa.L.equals(statusDia)) {
			descricao = "Licença";
		} else if (StatusDiaMapa.S.equals(statusDia)) {
			descricao = "Suspensão";
		} else if (StatusDiaMapa.I.equals(statusDia)) {
			descricao = "Indefinido";
		} else {
			descricao = "ERRO";
		} 		
		
		return descricao;
	}
}
