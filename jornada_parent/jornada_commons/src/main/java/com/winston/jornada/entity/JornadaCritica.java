package com.winston.jornada.entity;

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
@Table(name = "JORNADA_CRITICA")
@SequenceGenerator(name = "SE_JORNADA_CRITICA", sequenceName = "SE_JORNADA_CRITICA")
@Access(AccessType.FIELD)
@NamedQueries({ @NamedQuery(name = "JornadaCritica.querySelLookup", query = "select id as id, mensagem as mensagem from JornadaCritica where id = ? order by id asc") })
public class JornadaCritica extends AppBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SE_JORNADA_CRITICA")
	private Long id;

	@ManyToOne(targetEntity = Jornada.class, fetch = FetchType.LAZY)
	@ForeignKey(name = "FK_JORNADACRITICA_JORNADA")
	@NotNull
	private Jornada jornada;

	@NotNull(groups = PlcValGroupEntityList.class)
	@RequiredIf(valueOf = "id", is = RequiredIfType.not_empty)
	@Size(max = 255)
	private String mensagem;

	@ManyToOne(targetEntity = Critica.class, fetch = FetchType.LAZY)
	@ForeignKey(name = "FK_JORNADACRITICA_CRITICA")
	@NotNull(groups = PlcValGroupEntityList.class)
	@RequiredIf(valueOf = "mensagem", is = RequiredIfType.not_empty)
	private Critica critica;

	public JornadaCritica() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Critica getCritica() {
		return critica;
	}

	public void setCritica(Critica critica) {
		this.critica = critica;
	}

	public Jornada getJornada() {
		return jornada;
	}

	public void setJornada(Jornada jornada) {
		this.jornada = jornada;
	}

	@Override
	public String toString() {
		return getMensagem();
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
