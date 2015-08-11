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
	
	private DiaMapa diaMapa01 = new DiaMapa(1);
	private DiaMapa diaMapa02 = new DiaMapa(2);
	private DiaMapa diaMapa03 = new DiaMapa(3);
	private DiaMapa diaMapa04 = new DiaMapa(4);
	private DiaMapa diaMapa05 = new DiaMapa(5);
	private DiaMapa diaMapa06 = new DiaMapa(6);
	private DiaMapa diaMapa07 = new DiaMapa(7);
	private DiaMapa diaMapa08 = new DiaMapa(8);
	private DiaMapa diaMapa09 = new DiaMapa(9);
	private DiaMapa diaMapa10 = new DiaMapa(10);
	private DiaMapa diaMapa11 = new DiaMapa(11);
	private DiaMapa diaMapa12 = new DiaMapa(12);
	private DiaMapa diaMapa13 = new DiaMapa(13);
	private DiaMapa diaMapa14 = new DiaMapa(14);
	private DiaMapa diaMapa15 = new DiaMapa(15);
	private DiaMapa diaMapa16 = new DiaMapa(16);
	private DiaMapa diaMapa17 = new DiaMapa(17);
	private DiaMapa diaMapa18 = new DiaMapa(18);
	private DiaMapa diaMapa19 = new DiaMapa(19);
	private DiaMapa diaMapa20 = new DiaMapa(20);
	private DiaMapa diaMapa21 = new DiaMapa(21);
	private DiaMapa diaMapa22 = new DiaMapa(22);
	private DiaMapa diaMapa23 = new DiaMapa(23);
	private DiaMapa diaMapa24 = new DiaMapa(24);
	private DiaMapa diaMapa25 = new DiaMapa(25);
	private DiaMapa diaMapa26 = new DiaMapa(26);
	private DiaMapa diaMapa27 = new DiaMapa(27);
	private DiaMapa diaMapa28 = new DiaMapa(28);
	private DiaMapa diaMapa29 = new DiaMapa(29);
	private DiaMapa diaMapa30 = new DiaMapa(30);
	private DiaMapa diaMapa31 = new DiaMapa(31);

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

	public DiaMapa getDiaMapa(int dia) {
		DiaMapa diaMapa = null;
		
		 switch (dia) {
		 	case  1: diaMapa = diaMapa01; break;
		 	case  2: diaMapa = diaMapa02; break;
		 	case  3: diaMapa = diaMapa03; break;
		 	case  4: diaMapa = diaMapa04; break;
		 	case  5: diaMapa = diaMapa05; break;
		 	case  6: diaMapa = diaMapa06; break;
		 	case  7: diaMapa = diaMapa07; break;
		 	case  8: diaMapa = diaMapa08; break;
		 	case  9: diaMapa = diaMapa09; break;
		 	case 10: diaMapa = diaMapa10; break;
		 	case 11: diaMapa = diaMapa11; break;
		 	case 12: diaMapa = diaMapa12; break;
		 	case 13: diaMapa = diaMapa13; break;
		 	case 14: diaMapa = diaMapa14; break;
		 	case 15: diaMapa = diaMapa15; break;
		 	case 16: diaMapa = diaMapa16; break;
		 	case 17: diaMapa = diaMapa17; break;
		 	case 18: diaMapa = diaMapa18; break;
		 	case 19: diaMapa = diaMapa19; break;
		 	case 20: diaMapa = diaMapa20; break;
		 	case 21: diaMapa = diaMapa21; break;
		 	case 22: diaMapa = diaMapa22; break;
		 	case 23: diaMapa = diaMapa23; break;
		 	case 24: diaMapa = diaMapa24; break;
		 	case 25: diaMapa = diaMapa25; break;
		 	case 26: diaMapa = diaMapa26; break;
		 	case 27: diaMapa = diaMapa27; break;
		 	case 28: diaMapa = diaMapa28; break;
		 	case 29: diaMapa = diaMapa29; break;
		 	case 30: diaMapa = diaMapa30; break;
		 	case 31: diaMapa = diaMapa31; break;
		 }
		 
		 return diaMapa;
		
	}
	
	public void setDiaMapa(int dia, DiaMapa diaMapa) {
		 switch (dia) {
		 	case  1: setDiaMapa01(diaMapa); break;
		 	case  2: setDiaMapa02(diaMapa); break;
		 	case  3: setDiaMapa03(diaMapa); break;
		 	case  4: setDiaMapa04(diaMapa); break;
		 	case  5: setDiaMapa05(diaMapa); break;
		 	case  6: setDiaMapa06(diaMapa); break;
		 	case  7: setDiaMapa07(diaMapa); break;
		 	case  8: setDiaMapa08(diaMapa); break;
		 	case  9: setDiaMapa09(diaMapa); break;
		 	case 10: setDiaMapa10(diaMapa); break;
		 	case 11: setDiaMapa11(diaMapa); break;
		 	case 12: setDiaMapa12(diaMapa); break;
		 	case 13: setDiaMapa13(diaMapa); break;
		 	case 14: setDiaMapa14(diaMapa); break;
		 	case 15: setDiaMapa15(diaMapa); break;
		 	case 16: setDiaMapa16(diaMapa); break;
		 	case 17: setDiaMapa17(diaMapa); break;
		 	case 18: setDiaMapa18(diaMapa); break;
		 	case 19: setDiaMapa19(diaMapa); break;
		 	case 20: setDiaMapa20(diaMapa); break;
		 	case 21: setDiaMapa21(diaMapa); break;
		 	case 22: setDiaMapa22(diaMapa); break;
		 	case 23: setDiaMapa23(diaMapa); break;
		 	case 24: setDiaMapa24(diaMapa); break;
		 	case 25: setDiaMapa25(diaMapa); break;
		 	case 26: setDiaMapa26(diaMapa); break;
		 	case 27: setDiaMapa27(diaMapa); break;
		 	case 28: setDiaMapa28(diaMapa); break;
		 	case 29: setDiaMapa29(diaMapa); break;
		 	case 30: setDiaMapa30(diaMapa); break;
		 	case 31: setDiaMapa31(diaMapa); break;
		 }
	}

	public StatusDiaMapa getStatusDia(int dia) {
		StatusDiaMapa status = null;
		
		 switch (dia) {
		 	case  1: status = diaMapa01.getStatusDia(); break;
		 	case  2: status = diaMapa02.getStatusDia(); break;
		 	case  3: status = diaMapa03.getStatusDia(); break;
		 	case  4: status = diaMapa04.getStatusDia(); break;
		 	case  5: status = diaMapa05.getStatusDia(); break;
		 	case  6: status = diaMapa06.getStatusDia(); break;
		 	case  7: status = diaMapa07.getStatusDia(); break;
		 	case  8: status = diaMapa08.getStatusDia(); break;
		 	case  9: status = diaMapa09.getStatusDia(); break;
		 	case 10: status = diaMapa10.getStatusDia(); break;
		 	case 11: status = diaMapa11.getStatusDia(); break;
		 	case 12: status = diaMapa12.getStatusDia(); break;
		 	case 13: status = diaMapa13.getStatusDia(); break;
		 	case 14: status = diaMapa14.getStatusDia(); break;
		 	case 15: status = diaMapa15.getStatusDia(); break;
		 	case 16: status = diaMapa16.getStatusDia(); break;
		 	case 17: status = diaMapa17.getStatusDia(); break;
		 	case 18: status = diaMapa18.getStatusDia(); break;
		 	case 19: status = diaMapa19.getStatusDia(); break;
		 	case 20: status = diaMapa20.getStatusDia(); break;
		 	case 21: status = diaMapa21.getStatusDia(); break;
		 	case 22: status = diaMapa22.getStatusDia(); break;
		 	case 23: status = diaMapa23.getStatusDia(); break;
		 	case 24: status = diaMapa24.getStatusDia(); break;
		 	case 25: status = diaMapa25.getStatusDia(); break;
		 	case 26: status = diaMapa26.getStatusDia(); break;
		 	case 27: status = diaMapa27.getStatusDia(); break;
		 	case 28: status = diaMapa28.getStatusDia(); break;
		 	case 29: status = diaMapa29.getStatusDia(); break;
		 	case 30: status = diaMapa30.getStatusDia(); break;
		 	case 31: status = diaMapa31.getStatusDia(); break;
		 }
		 
		 return status;
	}
	
	public void setStatusDia(int dia, StatusDiaMapa status) {
		 switch (dia) {
		 	case  1: diaMapa01.setStatusDia(status); break;
		 	case  2: diaMapa02.setStatusDia(status); break;
		 	case  3: diaMapa03.setStatusDia(status); break;
		 	case  4: diaMapa04.setStatusDia(status); break;
		 	case  5: diaMapa05.setStatusDia(status); break;
		 	case  6: diaMapa06.setStatusDia(status); break;
		 	case  7: diaMapa07.setStatusDia(status); break;
		 	case  8: diaMapa08.setStatusDia(status); break;
		 	case  9: diaMapa09.setStatusDia(status); break;
		 	case 10: diaMapa10.setStatusDia(status); break;
		 	case 11: diaMapa11.setStatusDia(status); break;
		 	case 12: diaMapa12.setStatusDia(status); break;
		 	case 13: diaMapa13.setStatusDia(status); break;
		 	case 14: diaMapa14.setStatusDia(status); break;
		 	case 15: diaMapa15.setStatusDia(status); break;
		 	case 16: diaMapa16.setStatusDia(status); break;
		 	case 17: diaMapa17.setStatusDia(status); break;
		 	case 18: diaMapa18.setStatusDia(status); break;
		 	case 19: diaMapa19.setStatusDia(status); break;
		 	case 20: diaMapa20.setStatusDia(status); break;
		 	case 21: diaMapa21.setStatusDia(status); break;
		 	case 22: diaMapa22.setStatusDia(status); break;
		 	case 23: diaMapa23.setStatusDia(status); break;
		 	case 24: diaMapa24.setStatusDia(status); break;
		 	case 25: diaMapa25.setStatusDia(status); break;
		 	case 26: diaMapa26.setStatusDia(status); break;
		 	case 27: diaMapa27.setStatusDia(status); break;
		 	case 28: diaMapa28.setStatusDia(status); break;
		 	case 29: diaMapa29.setStatusDia(status); break;
		 	case 30: diaMapa30.setStatusDia(status); break;
		 	case 31: diaMapa31.setStatusDia(status); break;
		 }
		
	}

	public DiaMapa getDiaMapa01() {
		return diaMapa01;
	}

	public void setDiaMapa01(DiaMapa diaMapa01) {
		this.diaMapa01 = diaMapa01;
	}

	public DiaMapa getDiaMapa02() {
		return diaMapa02;
	}

	public void setDiaMapa02(DiaMapa diaMapa02) {
		this.diaMapa02 = diaMapa02;
	}

	public DiaMapa getDiaMapa03() {
		return diaMapa03;
	}

	public void setDiaMapa03(DiaMapa diaMapa03) {
		this.diaMapa03 = diaMapa03;
	}

	public DiaMapa getDiaMapa04() {
		return diaMapa04;
	}

	public void setDiaMapa04(DiaMapa diaMapa04) {
		this.diaMapa04 = diaMapa04;
	}

	public DiaMapa getDiaMapa05() {
		return diaMapa05;
	}

	public void setDiaMapa05(DiaMapa diaMapa05) {
		this.diaMapa05 = diaMapa05;
	}

	public DiaMapa getDiaMapa06() {
		return diaMapa06;
	}

	public void setDiaMapa06(DiaMapa diaMapa06) {
		this.diaMapa06 = diaMapa06;
	}

	public DiaMapa getDiaMapa07() {
		return diaMapa07;
	}

	public void setDiaMapa07(DiaMapa diaMapa07) {
		this.diaMapa07 = diaMapa07;
	}

	public DiaMapa getDiaMapa08() {
		return diaMapa08;
	}

	public void setDiaMapa08(DiaMapa diaMapa08) {
		this.diaMapa08 = diaMapa08;
	}

	public DiaMapa getDiaMapa09() {
		return diaMapa09;
	}

	public void setDiaMapa09(DiaMapa diaMapa09) {
		this.diaMapa09 = diaMapa09;
	}

	public DiaMapa getDiaMapa10() {
		return diaMapa10;
	}

	public void setDiaMapa10(DiaMapa diaMapa10) {
		this.diaMapa10 = diaMapa10;
	}

	public DiaMapa getDiaMapa11() {
		return diaMapa11;
	}

	public void setDiaMapa11(DiaMapa diaMapa11) {
		this.diaMapa11 = diaMapa11;
	}

	public DiaMapa getDiaMapa12() {
		return diaMapa12;
	}

	public void setDiaMapa12(DiaMapa diaMapa12) {
		this.diaMapa12 = diaMapa12;
	}

	public DiaMapa getDiaMapa13() {
		return diaMapa13;
	}

	public void setDiaMapa13(DiaMapa diaMapa13) {
		this.diaMapa13 = diaMapa13;
	}

	public DiaMapa getDiaMapa14() {
		return diaMapa14;
	}

	public void setDiaMapa14(DiaMapa diaMapa14) {
		this.diaMapa14 = diaMapa14;
	}

	public DiaMapa getDiaMapa15() {
		return diaMapa15;
	}

	public void setDiaMapa15(DiaMapa diaMapa15) {
		this.diaMapa15 = diaMapa15;
	}

	public DiaMapa getDiaMapa16() {
		return diaMapa16;
	}

	public void setDiaMapa16(DiaMapa diaMapa16) {
		this.diaMapa16 = diaMapa16;
	}

	public DiaMapa getDiaMapa17() {
		return diaMapa17;
	}

	public void setDiaMapa17(DiaMapa diaMapa17) {
		this.diaMapa17 = diaMapa17;
	}

	public DiaMapa getDiaMapa18() {
		return diaMapa18;
	}

	public void setDiaMapa18(DiaMapa diaMapa18) {
		this.diaMapa18 = diaMapa18;
	}

	public DiaMapa getDiaMapa19() {
		return diaMapa19;
	}

	public void setDiaMapa19(DiaMapa diaMapa19) {
		this.diaMapa19 = diaMapa19;
	}

	public DiaMapa getDiaMapa20() {
		return diaMapa20;
	}

	public void setDiaMapa20(DiaMapa diaMapa20) {
		this.diaMapa20 = diaMapa20;
	}

	public DiaMapa getDiaMapa21() {
		return diaMapa21;
	}

	public void setDiaMapa21(DiaMapa diaMapa21) {
		this.diaMapa21 = diaMapa21;
	}

	public DiaMapa getDiaMapa22() {
		return diaMapa22;
	}

	public void setDiaMapa22(DiaMapa diaMapa22) {
		this.diaMapa22 = diaMapa22;
	}

	public DiaMapa getDiaMapa23() {
		return diaMapa23;
	}

	public void setDiaMapa23(DiaMapa diaMapa23) {
		this.diaMapa23 = diaMapa23;
	}

	public DiaMapa getDiaMapa24() {
		return diaMapa24;
	}

	public void setDiaMapa24(DiaMapa diaMapa24) {
		this.diaMapa24 = diaMapa24;
	}

	public DiaMapa getDiaMapa25() {
		return diaMapa25;
	}

	public void setDiaMapa25(DiaMapa diaMapa25) {
		this.diaMapa25 = diaMapa25;
	}

	public DiaMapa getDiaMapa26() {
		return diaMapa26;
	}

	public void setDiaMapa26(DiaMapa diaMapa26) {
		this.diaMapa26 = diaMapa26;
	}

	public DiaMapa getDiaMapa27() {
		return diaMapa27;
	}

	public void setDiaMapa27(DiaMapa diaMapa27) {
		this.diaMapa27 = diaMapa27;
	}

	public DiaMapa getDiaMapa28() {
		return diaMapa28;
	}

	public void setDiaMapa28(DiaMapa diaMapa28) {
		this.diaMapa28 = diaMapa28;
	}

	public DiaMapa getDiaMapa29() {
		return diaMapa29;
	}

	public void setDiaMapa29(DiaMapa diaMapa29) {
		this.diaMapa29 = diaMapa29;
	}

	public DiaMapa getDiaMapa30() {
		return diaMapa30;
	}

	public void setDiaMapa30(DiaMapa diaMapa30) {
		this.diaMapa30 = diaMapa30;
	}

	public DiaMapa getDiaMapa31() {
		return diaMapa31;
	}

	public void setDiaMapa31(DiaMapa diaMapa31) {
		this.diaMapa31 = diaMapa31;
	}

}