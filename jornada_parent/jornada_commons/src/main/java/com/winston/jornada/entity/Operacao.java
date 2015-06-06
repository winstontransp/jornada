package com.winston.jornada.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.myfaces.extensions.validator.crossval.annotation.RequiredIf;
import org.apache.myfaces.extensions.validator.crossval.annotation.RequiredIfType;

import com.powerlogic.jcompany.commons.config.stereotypes.SPlcEntity;
import com.powerlogic.jcompany.config.domain.PlcReference;
import com.powerlogic.jcompany.domain.validation.PlcValGroupEntityList;

@SPlcEntity
@Entity
@Table(name = "OPERACAO")
@SequenceGenerator(name = "SE_OPERACAO", sequenceName = "SE_OPERACAO")
@Access(AccessType.FIELD)
@NamedQueries({
	@NamedQuery(name="Operacao.queryMan", query="from Operacao where sitHistoricoPlc='A'"), @NamedQuery(name = "Operacao.querySelLookup", query = "select id as id, nome as nome from Operacao where id = ? order by id asc") })
public class Operacao extends AppBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SE_OPERACAO")
	private Long id;

	@NotNull(groups = PlcValGroupEntityList.class)
	@RequiredIf(valueOf = "id", is = RequiredIfType.not_empty)
	@Size(max = 80)
	@PlcReference(testDuplicity = true)
	private String nome;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(length = 1)
	private Turno turno;
	
	@NotNull
	@Size(max = 1)
	private String sitHistoricoPlc="A";

	public Operacao() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	@Override
	public String toString() {
		return getNome();
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
		this.sitHistoricoPlc=sitHistoricoPlc;
	}
	
}
