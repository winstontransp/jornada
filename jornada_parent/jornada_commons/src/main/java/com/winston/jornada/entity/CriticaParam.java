package com.winston.jornada.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.powerlogic.jcompany.commons.config.stereotypes.SPlcEntity;

@SPlcEntity
@Entity
@Table(name = "CRITICA_PARAM")
@SequenceGenerator(name = "SE_CRITICA_PARAM", sequenceName = "SE_CRITICA_PARAM")
@Access(AccessType.FIELD)
@NamedQueries({ @NamedQuery(name = "CriticaParam.querySelLookup", query = "select id as id, horarioMinimoInicioJornada as horarioMinimoInicioJornada from CriticaParam where id = ? order by id asc") })
public class CriticaParam extends AppBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SE_CRITICA_PARAM")
	private Long id;

	@NotNull
	@Size(max = 10)
	private String horarioMinimoInicioJornada;

	@NotNull
	@Size(max = 10)
	private String horarioMaximoFimJornada;

	@NotNull
	@Size(max = 10)
	private String intervaloMinimoRefeicao;

	@NotNull
	@Size(max = 10)
	private String intervaloMinimoInterjornada;

	@NotNull
	@Size(max = 10)
	private String duracaoMaximaJornada;

	public CriticaParam() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHorarioMinimoInicioJornada() {
		return horarioMinimoInicioJornada;
	}

	public void setHorarioMinimoInicioJornada(String horarioMinimoInicioJornada) {
		this.horarioMinimoInicioJornada = horarioMinimoInicioJornada;
	}

	public String getHorarioMaximoFimJornada() {
		return horarioMaximoFimJornada;
	}

	public void setHorarioMaximoFimJornada(String horarioMaximoFimJornada) {
		this.horarioMaximoFimJornada = horarioMaximoFimJornada;
	}

	public String getIntervaloMinimoRefeicao() {
		return intervaloMinimoRefeicao;
	}

	public void setIntervaloMinimoRefeicao(String intervaloMinimoRefeicao) {
		this.intervaloMinimoRefeicao = intervaloMinimoRefeicao;
	}

	public String getIntervaloMinimoInterjornada() {
		return intervaloMinimoInterjornada;
	}

	public void setIntervaloMinimoInterjornada(
			String intervaloMinimoInterjornada) {
		this.intervaloMinimoInterjornada = intervaloMinimoInterjornada;
	}

	public String getDuracaoMaximaJornada() {
		return duracaoMaximaJornada;
	}

	public void setDuracaoMaximaJornada(String duracaoMaximaJornada) {
		this.duracaoMaximaJornada = duracaoMaximaJornada;
	}

	@Override
	public String toString() {
		return getHorarioMinimoInicioJornada();
	}

}
