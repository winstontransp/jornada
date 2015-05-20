package com.winston.jornada.entity;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.apache.myfaces.extensions.validator.crossval.annotation.RequiredIf;
import org.apache.myfaces.extensions.validator.crossval.annotation.RequiredIfType;
import org.hibernate.annotations.ForeignKey;

import com.powerlogic.jcompany.commons.config.stereotypes.SPlcEntity;
import com.powerlogic.jcompany.domain.validation.PlcValGroupEntityList;

@SPlcEntity
@Entity
@Table(name = "DIRECAO")
@SequenceGenerator(name = "SE_DIRECAO", sequenceName = "SE_DIRECAO")
@Access(AccessType.FIELD)
@NamedQueries({ @NamedQuery(name = "Direcao.querySelLookup", query = "select id as id, motorista as motorista from Direcao where id = ? order by id asc") })
public class Direcao extends AppBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SE_DIRECAO")
	private Long id;

	@NotNull(groups = PlcValGroupEntityList.class)
	@RequiredIf(valueOf = "motorista", is = RequiredIfType.not_empty)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataJornada;

	@ManyToOne(targetEntity = Motorista.class, fetch = FetchType.LAZY)
	@ForeignKey(name = "FK_DIRECAO_MOTORISTA")
	@NotNull(groups = PlcValGroupEntityList.class)
	@RequiredIf(valueOf = "id", is = RequiredIfType.not_empty)
	@JoinColumn(name = "MOTORISTA")
	private Motorista motorista;

	@NotNull(groups = PlcValGroupEntityList.class)
	@RequiredIf(valueOf = "motorista", is = RequiredIfType.not_empty)
	@Temporal(TemporalType.TIMESTAMP)
	private Date tempoDirecao;

	@NotNull(groups = PlcValGroupEntityList.class)
	@RequiredIf(valueOf = "motorista", is = RequiredIfType.not_empty)
	@Temporal(TemporalType.TIMESTAMP)
	private Date tempoIntrajornada;

	@ManyToOne(targetEntity = Importacao.class, fetch = FetchType.LAZY)
	@ForeignKey(name = "FK_DIRECAO_IMPORTACAO")
	@NotNull(groups = PlcValGroupEntityList.class)
	@RequiredIf(valueOf = "motorista", is = RequiredIfType.not_empty)
	private Importacao importacao;

	@ManyToOne(targetEntity = Jornada.class, fetch = FetchType.LAZY)
	@ForeignKey(name = "FK_DIRECAO_JORNADA")
	private Jornada jornada;

	public Direcao() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataJornada() {
		return dataJornada;
	}

	public void setDataJornada(Date dataJornada) {
		this.dataJornada = dataJornada;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public Date getTempoDirecao() {
		return tempoDirecao;
	}

	public void setTempoDirecao(Date tempoDirecao) {
		this.tempoDirecao = tempoDirecao;
	}

	public Date getTempoIntrajornada() {
		return tempoIntrajornada;
	}

	public void setTempoIntrajornada(Date tempoIntrajornada) {
		this.tempoIntrajornada = tempoIntrajornada;
	}

	public Importacao getImportacao() {
		return importacao;
	}

	public void setImportacao(Importacao importacao) {
		this.importacao = importacao;
	}

	public Jornada getJornada() {
		return jornada;
	}

	public void setJornada(Jornada jornada) {
		this.jornada = jornada;
	}

	@Override
	public String toString() {
		return getMotorista().toString();
	}

	@Transient
	private String indExcPlc = "N";

	public void setIndExcPlc(String indExcPlc) {
		this.indExcPlc = indExcPlc;
	}

	public String getIndExcPlc() {
		return indExcPlc;
	}

}
