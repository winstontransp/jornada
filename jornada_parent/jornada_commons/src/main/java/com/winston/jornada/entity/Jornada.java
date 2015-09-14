package com.winston.jornada.entity;

import java.util.Date;
import java.util.List;

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
import javax.validation.constraints.Size;

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
		@NamedQuery(name = "Jornada.queryMan", query = "from Jornada"),
		@NamedQuery(name = "Jornada.querySel", query = "select obj.id as id, obj.mctAddress as mctAddress, obj.frota as frota, obj.placa as placa, obj.status as status, obj.data as data, obj1.id as motorista_id , obj1.nome as motorista_nome from Jornada obj left outer join obj.motorista as obj1 order by obj.id asc"),
		@NamedQuery(name = "Jornada.querySel2", query = "select j.id as id, j.mctAddress as mctAddress, j.frota as frota, j.placa as placa, j.status as status, j.data as data, m.id as motorista_id, m.nome as motorista_nome, o.id as operacao_id, o.nome as operacao_nome, o.turno as operacao_turno from Jornada j left outer join j.operacao as o left outer join j.motorista as m order by m.nome, j.data asc"),
		@NamedQuery(name = "Jornada.querySelLookup", query = "select id as id, mctAddress as mctAddress from Jornada where id = ? order by id asc") })
public class Jornada extends AppBaseEntity {

	private static final long serialVersionUID = -8870708044368951560L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SE_JORNADA")
	private Long id;

	@NotNull
	private Long mctAddress;

	@ManyToOne(targetEntity = Motorista.class, fetch = FetchType.LAZY)
	@ForeignKey(name = "FK_JORNADA_MOTORISTA")
	@NotNull
	private Motorista motorista;

	@Digits(integer = 5, fraction = 0)
	private Integer frota;

	@Size(max = 10)
	private String placa;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(length = 1)
	private StatusJornada status;

	private String tempoForaServico;

	private String tempoInterjornada;

	@ManyToOne(targetEntity = Operacao.class, fetch = FetchType.LAZY)
	@ForeignKey(name = "FK_JORNADA_OPERACAO")
	@NotNull
	private Operacao operacao;

	@OneToMany(targetEntity = ReturnMessage.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "jornada")
	@ForeignKey(name = "FK_RETURNMESSAGE_JORNADA")
	@PlcValDuplicity(property = "positionTime")
	@PlcValMultiplicity(referenceProperty = "positionTime", message = "{jcompany.aplicacao.mestredetalhe.multiplicidade.ReturnMessage}")
	@Valid
	private List<ReturnMessage> returnMessage;

	@OneToMany(targetEntity = JornadaEvento.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "jornada")
	@ForeignKey(name = "FK_JORNADAEVENTO_JORNADA")
	@OrderBy("inicio")
	@PlcValDuplicity(property = "id")
	@PlcValMultiplicity(referenceProperty = "id", message = "{jcompany.aplicacao.mestredetalhe.multiplicidade.Eventos}")
	@Valid
	private List<JornadaEvento> eventos;

	@OneToMany(targetEntity = JornadaCritica.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "jornada")
	@ForeignKey(name = "FK_JORNADACRITICA_JORNADA")
	@PlcValDuplicity(property = "mensagem")
	@PlcValMultiplicity(referenceProperty = "mensagem", message = "{jcompany.aplicacao.mestredetalhe.multiplicidade.JornadaCritica}")
	@Valid
	private List<JornadaCritica> criticas;

	@OneToMany(targetEntity = JornadaFalha.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "jornada")
	@ForeignKey(name = "FK_JORNADAFALHA_JORNADA")
	@PlcValDuplicity(property = "assunto")
	@PlcValMultiplicity(referenceProperty = "assunto", message = "{jcompany.aplicacao.mestredetalhe.multiplicidade.JornadaFalha}")
	@Valid
	private List<JornadaFalha> falhas;

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

	public Integer getFrota() {
		return frota;
	}

	public void setFrota(Integer frota) {
		this.frota = frota;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
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

	public String getTempoForaServico() {
		return tempoForaServico;
	}

	public void setTempoForaServico(String tempoForaServico) {
		this.tempoForaServico = tempoForaServico;
	}

	public String getTempoInterjornada() {
		return tempoInterjornada;
	}

	public void setTempoInterjornada(String tempoInterjornada) {
		this.tempoInterjornada = tempoInterjornada;
	}

	public Operacao getOperacao() {
		return operacao;
	}

	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
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

	public List<JornadaCritica> getCriticas() {
		return criticas;
	}

	public void setCriticas(List<JornadaCritica> criticas) {
		this.criticas = criticas;
	}

	public List<JornadaFalha> getFalhas() {
		return falhas;
	}

	public void setFalhas(List<JornadaFalha> falhas) {
		this.falhas = falhas;
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

}