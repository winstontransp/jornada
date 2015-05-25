package com.winston.jornada.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import com.winston.jornada.entity.TipoEvento;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import org.apache.myfaces.extensions.validator.crossval.annotation.RequiredIfType;
import javax.persistence.Enumerated;
import javax.persistence.TemporalType;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import org.hibernate.annotations.ForeignKey;
import com.powerlogic.jcompany.domain.validation.PlcValGroupEntityList;
import javax.persistence.EnumType;
import org.apache.myfaces.extensions.validator.crossval.annotation.RequiredIf;
import javax.persistence.Temporal;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Access;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.AccessType;
import com.powerlogic.jcompany.commons.config.stereotypes.SPlcEntity;
import javax.persistence.Entity;
import javax.persistence.Transient;

@SPlcEntity
@Entity
@Table(name = "JORNADA_EVENTO")
@SequenceGenerator(name = "SE_JORNADA_EVENTO", sequenceName = "SE_JORNADA_EVENTO")
@Access(AccessType.FIELD)
@NamedQueries({ @NamedQuery(name = "JornadaEvento.querySelLookup", query = "select id as id, tipo as tipo from JornadaEvento where id = ? order by id asc") })
public class JornadaEvento extends AppBaseEntity {

	@Transient
	private transient DateFormat fmtHora = new SimpleDateFormat("HH:mm:ss");	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SE_JORNADA_EVENTO")
	private Long id;

	@ManyToOne(targetEntity = Jornada.class, fetch = FetchType.LAZY)
	@ForeignKey(name = "FK_JORNADAEVENTO_JORNADA")
	@NotNull
	private Jornada jornada;

	@Enumerated(EnumType.STRING)
	@NotNull(groups = PlcValGroupEntityList.class)
	@RequiredIf(valueOf = "id", is = RequiredIfType.not_empty)
	@Column(length = 1)
	private TipoEvento tipo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date inicio;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fim;

	@Temporal(TemporalType.TIMESTAMP)
	private Date tempo;

	public JornadaEvento() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoEvento getTipo() {
		return tipo;
	}

	public void setTipo(TipoEvento tipo) {
		this.tipo = tipo;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public Date getTempo() {
		return tempo;
	}

	public void setTempo(Date tempo) {
		this.tempo = tempo;
	}

	public Jornada getJornada() {
		return jornada;
	}

	public void setJornada(Jornada jornada) {
		this.jornada = jornada;
	}

	@Override
	public String toString() {
		return getTipo().toString();
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
	private String horaInicio;
	
	public String getHoraInicio() {
		horaInicio = getHora(getInicio());
		return horaInicio;
	}
	
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	
	@Transient
	private String horaFim;
	
	public String getHoraFim() {
		horaFim = getHora(getFim());
		return horaFim;
	}

	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}
	
	@Transient
	private String duracao;
	
	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

	public String getDuracao() {
		duracao = getHora(getFim());
		return duracao;
	}
	
	private String getHora(Date dataHora) {
		
		if (dataHora != null) {
			return fmtHora.format(dataHora);
		}
		
		return null;
	}
	
}
