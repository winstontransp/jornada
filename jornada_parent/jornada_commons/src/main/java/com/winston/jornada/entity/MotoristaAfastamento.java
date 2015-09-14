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
@Table(name = "MOTORISTA_AFASTAMENTO")
@SequenceGenerator(name = "SE_MOTORISTA_AFASTAMENTO", sequenceName = "SE_MOTORISTA_AFASTAMENTO")
@Access(AccessType.FIELD)
@NamedQueries({ 
	@NamedQuery(name="MotoristaAfastamento.querySelLookup", query="select id as id, inicio as inicio from MotoristaAfastamento where id = ? order by id asc"),
	@NamedQuery(name="MotoristaAfastamento.querySelByFaixa", query="select f.id as id, f.inicio as inicio, f.termino as termino, f.motorista as motorista, f.motivo as motivo from MotoristaAfastamento f where f.sitHistoricoPlc = 'A' and (" + 
			"(:inicio <= inicio and termino <= :termino) or " +
			"(:inicio >= inicio and termino <= :termino) or " +
			"(:inicio <= inicio and termino >= :termino) or " +
			"(:inicio >= inicio and termino >= :termino)) ")})
public class MotoristaAfastamento extends AppBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SE_MOTORISTA_AFASTAMENTO")
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

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(length = 1)
	private MotivoAfastamento motivo;
	
	@NotNull
	@Size(max = 1)
	private String sitHistoricoPlc = "A";

	public MotoristaAfastamento() {
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

	public MotivoAfastamento getMotivo() {
		return motivo;
	}

	public void setMotivo(MotivoAfastamento motivo) {
		this.motivo = motivo;
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
