package com.winston.jornada.entity;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.myfaces.extensions.validator.crossval.annotation.RequiredIf;
import org.apache.myfaces.extensions.validator.crossval.annotation.RequiredIfType;
import org.hibernate.annotations.ForeignKey;

import com.powerlogic.jcompany.commons.config.stereotypes.SPlcEntity;
import com.powerlogic.jcompany.domain.validation.PlcValGroupEntityList;

@SPlcEntity
@Entity
@Table(name = "MOTORISTA_FERIAS")
@SequenceGenerator(name = "SE_MOTORISTA_FERIAS", sequenceName = "SE_MOTORISTA_FERIAS")
@Access(AccessType.FIELD)
@NamedQueries({ @NamedQuery(name = "MotoristaFerias.querySelLookup", query = "select id as id, inicio as inicio from MotoristaFerias where id = ? order by id asc") })
public class MotoristaFerias extends AppBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SE_MOTORISTA_FERIAS")
	private Long id;

	@ManyToOne(targetEntity = Motorista.class, fetch = FetchType.LAZY)
	@ForeignKey(name = "FK_MOTORISTAFERIAS_MOTORISTA")
	private Motorista motorista;

	@NotNull(groups = PlcValGroupEntityList.class)
	@RequiredIf(valueOf = "id", is = RequiredIfType.not_empty)
	@Temporal(TemporalType.TIMESTAMP)
	private Date inicio;

	@NotNull(groups = PlcValGroupEntityList.class)
	@RequiredIf(valueOf = "inicio", is = RequiredIfType.not_empty)
	@Temporal(TemporalType.TIMESTAMP)
	private Date termino;

	@NotNull
	@Size(max = 1)
	private String sitHistoricoPlc = "A";

	public MotoristaFerias() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getTermino() {
		return termino;
	}

	public void setTermino(Date termino) {
		this.termino = termino;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	@Override
	public String toString() {
		return getInicio().toString();
	}

	@Transient
	private String indExcPlc = "N";

	public void setIndExcPlc(String indExcPlc) {
		this.indExcPlc = indExcPlc;
	}

	public String getIndExcPlc() {
		return indExcPlc;
	}

	public String getSitHistoricoPlc() {
		return sitHistoricoPlc;
	}

	public void setSitHistoricoPlc(String sitHistoricoPlc) {
		this.sitHistoricoPlc = sitHistoricoPlc;
	}

	
}
