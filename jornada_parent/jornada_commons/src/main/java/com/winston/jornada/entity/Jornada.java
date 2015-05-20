package com.winston.jornada.entity;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ForeignKey;

import com.powerlogic.jcompany.commons.config.stereotypes.SPlcEntity;
import java.util.List;
import com.powerlogic.jcompany.domain.validation.PlcValDuplicity;
import javax.validation.Valid;
import com.powerlogic.jcompany.domain.validation.PlcValMultiplicity;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

@SPlcEntity
@Entity
@Table(name = "JORNADA")
@SequenceGenerator(name = "SE_JORNADA", sequenceName = "SE_JORNADA")
@Access(AccessType.FIELD)
@NamedQueries({ @NamedQuery(name = "Jornada.querySelLookup", query = "select id as id, mctAddress as mctAddress from Jornada where id = ? order by id asc") })
public class Jornada extends AppBaseEntity {

	
	@OneToMany (targetEntity = ReturnMessage.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="jornada")
	@ForeignKey(name="FK_RETURNMESSAGE_JORNADA")
	@PlcValDuplicity(property="landmark")
	@PlcValMultiplicity(referenceProperty="landmark",  message="{jcompany.aplicacao.mestredetalhe.multiplicidade.ReturnMessage}")
	@Valid
	private List<ReturnMessage> returnMessage;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SE_JORNADA")
	private Long id;

	@NotNull
	@Digits(integer = 5, fraction = 0)
	private Long mctAddress;

	@ManyToOne(targetEntity = Motorista.class, fetch = FetchType.LAZY)
	@ForeignKey(name = "FK_JORNADA_MOTORISTA")
	@NotNull
	@JoinColumn(name = "MOTORISTA")
	private Motorista motorista;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(length = 1)
	private StatusJornada status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date tempoForaServico;

	@Temporal(TemporalType.TIMESTAMP)
	private Date tempoInterjornada;

	public Jornada() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMctAddress() {
		return mctAddress;
	}

	public void setMctAddress(Long MctAddress) {
		this.mctAddress = MctAddress;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public StatusJornada getStatus() {
		return status;
	}

	public void setStatus(StatusJornada status) {
		this.status = status;
	}

	public Date getTempoForaServico() {
		return tempoForaServico;
	}

	public void setTempoForaServico(Date tempoForaServico) {
		this.tempoForaServico = tempoForaServico;
	}

	public Date getTempoInterjornada() {
		return tempoInterjornada;
	}

	public void setTempoInterjornada(Date tempoInterjornada) {
		this.tempoInterjornada = tempoInterjornada;
	}

	@Override
	public String toString() {
		return getMctAddress().toString();
	}

	public List<ReturnMessage> getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(List<ReturnMessage> returnMessage) {
		this.returnMessage=returnMessage;
	}

}
