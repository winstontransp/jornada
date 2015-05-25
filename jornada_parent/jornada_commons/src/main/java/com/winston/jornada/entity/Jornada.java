package com.winston.jornada.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ForeignKey;

import com.powerlogic.jcompany.commons.config.stereotypes.SPlcEntity;
import com.powerlogic.jcompany.domain.validation.PlcValDuplicity;
import com.powerlogic.jcompany.domain.validation.PlcValMultiplicity;

@SPlcEntity
@Entity
@Table(name = "JORNADA")
@SequenceGenerator(name = "SE_JORNADA", sequenceName = "SE_JORNADA")
@Access(AccessType.FIELD)
@NamedQueries({
	@NamedQuery(name="Jornada.queryMan", query="from Jornada"),
	@NamedQuery(name="Jornada.querySel", query="select obj.id as id, obj.mctAddress as mctAddress, obj.status as status, obj.data as data, obj1.id as motorista_id , obj1.nome as motorista_nome from Jornada obj left outer join obj.motorista as obj1 order by obj.mctAddress asc"), 
	@NamedQuery(name="Jornada.querySelLookup", query = "select id as id, mctAddress as mctAddress from Jornada where id = ? order by id asc") })
public class Jornada extends AppBaseEntity {

	@Transient
	private transient DateFormat fmtHora = new SimpleDateFormat("HH:mm:ss");	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SE_JORNADA")
	private Long id;

	@NotNull
	@Digits(integer = 5, fraction = 0)
	private Long mctAddress;

	@ManyToOne(targetEntity = Motorista.class, fetch = FetchType.LAZY)
	@ForeignKey(name = "FK_JORNADA_MOTORISTA")
	@NotNull
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
	
	@OneToMany (targetEntity = ReturnMessage.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="jornada")
	@ForeignKey(name="FK_RETURNMESSAGE_JORNADA")
	@PlcValDuplicity(property="positionTime")
	@PlcValMultiplicity(referenceProperty="positionTime",  message="{jcompany.aplicacao.mestredetalhe.multiplicidade.ReturnMessage}")
	@Valid
	private List<ReturnMessage> returnMessage;
	
	@OneToMany (targetEntity = JornadaEvento.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="jornada")
	@ForeignKey(name="FK_JORNADAEVENTO_JORNADA")
	@OrderBy("inicio")
	@PlcValDuplicity(property="id")
	@PlcValMultiplicity(referenceProperty="id", message="{jcompany.aplicacao.mestredetalhe.multiplicidade.Eventos}")
	@Valid
	private List<JornadaEvento> eventos;
	
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

	public List<ReturnMessage> getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(List<ReturnMessage> returnMessage) {
		this.returnMessage = returnMessage;
	}

	public List<JornadaEvento> getEventos() {
		return eventos;
	}

	public void setEventos(List<JornadaEvento> eventos) {
		this.eventos = eventos;
	}

	
	@Override
	public String toString() {
		
		if (getMctAddress() != null) {
			return getMctAddress().toString();
		}
		
		return " ";
	}
	
	@Transient
	private String indExcPlc = "N";

	public void setIndExcPlc(String indExcPlc) {
		this.indExcPlc = indExcPlc;
	}

	public String getIndExcPlc() {
		return indExcPlc;
	}

	@Transient
	public String getTempoInterjornadaFormatado() {
		return getTempo(getTempoInterjornada());
	}
	
	@Transient
	public String getTempoForaServicoFormatado() {
		return getTempo(getTempoForaServico());
	}
	
	private String getTempo(Date dataHora) {
		
		if (dataHora != null) {
			return fmtHora.format(dataHora);
		}
		
		return null;
	}
}