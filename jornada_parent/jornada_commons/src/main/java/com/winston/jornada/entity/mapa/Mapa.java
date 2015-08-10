package com.winston.jornada.entity.mapa;

import java.util.Date;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.winston.jornada.entity.Motorista;
import com.winston.jornada.entity.Operacao;
import com.winston.jornada.entity.Turno;

@NamedQueries({ @NamedQuery(name = "Mapa.querySel", query = "select dataInicio as dataInicio, dataFim as dataFim, motorista as motorista, turno as turno, operacao as operacao from Mapa order by dataInicio asc") })
public class Mapa {

	private Date dataInicio;
	private Date dataFim;
	private Motorista motorista;
	private Turno turno;
	private Operacao operacao;
	private StatusDiaMapa statusDia01 = StatusDiaMapa.I;
	private StatusDiaMapa statusDia02 = StatusDiaMapa.I;
	private StatusDiaMapa statusDia03 = StatusDiaMapa.I;
	private StatusDiaMapa statusDia04 = StatusDiaMapa.I;
	private StatusDiaMapa statusDia05 = StatusDiaMapa.I;
	private StatusDiaMapa statusDia06 = StatusDiaMapa.I;
	private StatusDiaMapa statusDia07 = StatusDiaMapa.I;
	private StatusDiaMapa statusDia08 = StatusDiaMapa.I;
	private StatusDiaMapa statusDia09 = StatusDiaMapa.I;
	private StatusDiaMapa statusDia10 = StatusDiaMapa.I;
	private StatusDiaMapa statusDia11 = StatusDiaMapa.I;
	private StatusDiaMapa statusDia12 = StatusDiaMapa.I;
	private StatusDiaMapa statusDia13 = StatusDiaMapa.I;
	private StatusDiaMapa statusDia14 = StatusDiaMapa.I;
	private StatusDiaMapa statusDia15 = StatusDiaMapa.I;
	private StatusDiaMapa statusDia16 = StatusDiaMapa.I;
	private StatusDiaMapa statusDia17 = StatusDiaMapa.I;
	private StatusDiaMapa statusDia18 = StatusDiaMapa.I;
	private StatusDiaMapa statusDia19 = StatusDiaMapa.I;
	private StatusDiaMapa statusDia20 = StatusDiaMapa.I;
	private StatusDiaMapa statusDia21 = StatusDiaMapa.I;
	private StatusDiaMapa statusDia22 = StatusDiaMapa.I;
	private StatusDiaMapa statusDia23 = StatusDiaMapa.I;
	private StatusDiaMapa statusDia24 = StatusDiaMapa.I;
	private StatusDiaMapa statusDia25 = StatusDiaMapa.I;
	private StatusDiaMapa statusDia26 = StatusDiaMapa.I;
	private StatusDiaMapa statusDia27 = StatusDiaMapa.I;
	private StatusDiaMapa statusDia28 = StatusDiaMapa.I;
	private StatusDiaMapa statusDia29 = StatusDiaMapa.I;
	private StatusDiaMapa statusDia30 = StatusDiaMapa.I;
	private StatusDiaMapa statusDia31 = StatusDiaMapa.I;

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public Operacao getOperacao() {
		return operacao;
	}

	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
	}

	public StatusDiaMapa getStatusDia01() {
		return statusDia01;
	}

	public void setStatusDia01(StatusDiaMapa statusDia01) {
		this.statusDia01 = statusDia01;
	}

	public StatusDiaMapa getStatusDia02() {
		return statusDia02;
	}

	public void setStatusDia02(StatusDiaMapa statusDia02) {
		this.statusDia02 = statusDia02;
	}

	public StatusDiaMapa getStatusDia03() {
		return statusDia03;
	}

	public void setStatusDia03(StatusDiaMapa statusDia03) {
		this.statusDia03 = statusDia03;
	}

	public StatusDiaMapa getStatusDia04() {
		return statusDia04;
	}

	public void setStatusDia04(StatusDiaMapa statusDia04) {
		this.statusDia04 = statusDia04;
	}

	public StatusDiaMapa getStatusDia05() {
		return statusDia05;
	}

	public void setStatusDia05(StatusDiaMapa statusDia05) {
		this.statusDia05 = statusDia05;
	}

	public StatusDiaMapa getStatusDia06() {
		return statusDia06;
	}

	public void setStatusDia06(StatusDiaMapa statusDia06) {
		this.statusDia06 = statusDia06;
	}

	public StatusDiaMapa getStatusDia07() {
		return statusDia07;
	}

	public void setStatusDia07(StatusDiaMapa statusDia07) {
		this.statusDia07 = statusDia07;
	}

	public StatusDiaMapa getStatusDia08() {
		return statusDia08;
	}

	public void setStatusDia08(StatusDiaMapa statusDia08) {
		this.statusDia08 = statusDia08;
	}

	public StatusDiaMapa getStatusDia09() {
		return statusDia09;
	}

	public void setStatusDia09(StatusDiaMapa statusDia09) {
		this.statusDia09 = statusDia09;
	}

	public StatusDiaMapa getStatusDia10() {
		return statusDia10;
	}

	public void setStatusDia10(StatusDiaMapa statusDia10) {
		this.statusDia10 = statusDia10;
	}

	public StatusDiaMapa getStatusDia11() {
		return statusDia11;
	}

	public void setStatusDia11(StatusDiaMapa statusDia11) {
		this.statusDia11 = statusDia11;
	}

	public StatusDiaMapa getStatusDia12() {
		return statusDia12;
	}

	public void setStatusDia12(StatusDiaMapa statusDia12) {
		this.statusDia12 = statusDia12;
	}

	public StatusDiaMapa getStatusDia13() {
		return statusDia13;
	}

	public void setStatusDia13(StatusDiaMapa statusDia13) {
		this.statusDia13 = statusDia13;
	}

	public StatusDiaMapa getStatusDia14() {
		return statusDia14;
	}

	public void setStatusDia14(StatusDiaMapa statusDia14) {
		this.statusDia14 = statusDia14;
	}

	public StatusDiaMapa getStatusDia15() {
		return statusDia15;
	}

	public void setStatusDia15(StatusDiaMapa statusDia15) {
		this.statusDia15 = statusDia15;
	}

	public StatusDiaMapa getStatusDia16() {
		return statusDia16;
	}

	public void setStatusDia16(StatusDiaMapa statusDia16) {
		this.statusDia16 = statusDia16;
	}

	public StatusDiaMapa getStatusDia17() {
		return statusDia17;
	}

	public void setStatusDia17(StatusDiaMapa statusDia17) {
		this.statusDia17 = statusDia17;
	}

	public StatusDiaMapa getStatusDia18() {
		return statusDia18;
	}

	public void setStatusDia18(StatusDiaMapa statusDia18) {
		this.statusDia18 = statusDia18;
	}

	public StatusDiaMapa getStatusDia19() {
		return statusDia19;
	}

	public void setStatusDia19(StatusDiaMapa statusDia19) {
		this.statusDia19 = statusDia19;
	}

	public StatusDiaMapa getStatusDia20() {
		return statusDia20;
	}

	public void setStatusDia20(StatusDiaMapa statusDia20) {
		this.statusDia20 = statusDia20;
	}

	public StatusDiaMapa getStatusDia21() {
		return statusDia21;
	}

	public void setStatusDia21(StatusDiaMapa statusDia21) {
		this.statusDia21 = statusDia21;
	}

	public StatusDiaMapa getStatusDia22() {
		return statusDia22;
	}

	public void setStatusDia22(StatusDiaMapa statusDia22) {
		this.statusDia22 = statusDia22;
	}

	public StatusDiaMapa getStatusDia23() {
		return statusDia23;
	}

	public void setStatusDia23(StatusDiaMapa statusDia23) {
		this.statusDia23 = statusDia23;
	}

	public StatusDiaMapa getStatusDia24() {
		return statusDia24;
	}

	public void setStatusDia24(StatusDiaMapa statusDia24) {
		this.statusDia24 = statusDia24;
	}

	public StatusDiaMapa getStatusDia25() {
		return statusDia25;
	}

	public void setStatusDia25(StatusDiaMapa statusDia25) {
		this.statusDia25 = statusDia25;
	}

	public StatusDiaMapa getStatusDia26() {
		return statusDia26;
	}

	public void setStatusDia26(StatusDiaMapa statusDia26) {
		this.statusDia26 = statusDia26;
	}

	public StatusDiaMapa getStatusDia27() {
		return statusDia27;
	}

	public void setStatusDia27(StatusDiaMapa statusDia27) {
		this.statusDia27 = statusDia27;
	}

	public StatusDiaMapa getStatusDia28() {
		return statusDia28;
	}

	public void setStatusDia28(StatusDiaMapa statusDia28) {
		this.statusDia28 = statusDia28;
	}

	public StatusDiaMapa getStatusDia29() {
		return statusDia29;
	}

	public void setStatusDia29(StatusDiaMapa statusDia29) {
		this.statusDia29 = statusDia29;
	}

	public StatusDiaMapa getStatusDia30() {
		return statusDia30;
	}

	public void setStatusDia30(StatusDiaMapa statusDia30) {
		this.statusDia30 = statusDia30;
	}

	public StatusDiaMapa getStatusDia31() {
		return statusDia31;
	}

	public void setStatusDia31(StatusDiaMapa statusDia31) {
		this.statusDia31 = statusDia31;
	}

	
	public void setStatusDia(int dia, StatusDiaMapa status) {
		
		 switch (dia) {
		 	case  1: setStatusDia01(status); break;
		 	case  2: setStatusDia02(status); break;
		 	case  3: setStatusDia03(status); break;
		 	case  4: setStatusDia04(status); break;
		 	case  5: setStatusDia05(status); break;
		 	case  6: setStatusDia06(status); break;
		 	case  7: setStatusDia07(status); break;
		 	case  8: setStatusDia08(status); break;
		 	case  9: setStatusDia09(status); break;
		 	case 10: setStatusDia10(status); break;
		 	case 11: setStatusDia11(status); break;
		 	case 12: setStatusDia12(status); break;
		 	case 13: setStatusDia13(status); break;
		 	case 14: setStatusDia14(status); break;
		 	case 15: setStatusDia15(status); break;
		 	case 16: setStatusDia16(status); break;
		 	case 17: setStatusDia17(status); break;
		 	case 18: setStatusDia18(status); break;
		 	case 19: setStatusDia19(status); break;
		 	case 20: setStatusDia20(status); break;
		 	case 21: setStatusDia21(status); break;
		 	case 22: setStatusDia22(status); break;
		 	case 23: setStatusDia23(status); break;
		 	case 24: setStatusDia24(status); break;
		 	case 25: setStatusDia25(status); break;
		 	case 26: setStatusDia26(status); break;
		 	case 27: setStatusDia27(status); break;
		 	case 28: setStatusDia28(status); break;
		 	case 29: setStatusDia29(status); break;
		 	case 30: setStatusDia30(status); break;
		 	case 31: setStatusDia31(status); break;
		 }
	}
	
	public StatusDiaMapa getStatusDia(int dia) {
		StatusDiaMapa status = null;
		
		 switch (dia) {
		 	case  1: status = getStatusDia01(); break;
		 	case  2: status = getStatusDia02(); break;
		 	case  3: status = getStatusDia03(); break;
		 	case  4: status = getStatusDia04(); break;
		 	case  5: status = getStatusDia05(); break;
		 	case  6: status = getStatusDia06(); break;
		 	case  7: status = getStatusDia07(); break;
		 	case  8: status = getStatusDia08(); break;
		 	case  9: status = getStatusDia09(); break;
		 	case 10: status = getStatusDia10(); break;
		 	case 11: status = getStatusDia11(); break;
		 	case 12: status = getStatusDia12(); break;
		 	case 13: status = getStatusDia13(); break;
		 	case 14: status = getStatusDia14(); break;
		 	case 15: status = getStatusDia15(); break;
		 	case 16: status = getStatusDia16(); break;
		 	case 17: status = getStatusDia17(); break;
		 	case 18: status = getStatusDia18(); break;
		 	case 19: status = getStatusDia19(); break;
		 	case 20: status = getStatusDia20(); break;
		 	case 21: status = getStatusDia21(); break;
		 	case 22: status = getStatusDia22(); break;
		 	case 23: status = getStatusDia23(); break;
		 	case 24: status = getStatusDia24(); break;
		 	case 25: status = getStatusDia25(); break;
		 	case 26: status = getStatusDia26(); break;
		 	case 27: status = getStatusDia27(); break;
		 	case 28: status = getStatusDia28(); break;
		 	case 29: status = getStatusDia29(); break;
		 	case 30: status = getStatusDia30(); break;
		 	case 31: status = getStatusDia31(); break;
		 }
		 
		 return status;
	}
}