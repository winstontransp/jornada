package com.winston.jornada.entity;

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
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ForeignKey;

import com.powerlogic.jcompany.commons.config.stereotypes.SPlcEntity;

@SPlcEntity
@Entity
@Table(name = "MOTORISTA")
@SequenceGenerator(name = "SE_MOTORISTA", sequenceName = "SE_MOTORISTA")
@Access(AccessType.FIELD)
@NamedQueries({ @NamedQuery(name = "Motorista.querySelLookup", query = "select id as id, nome as nome from Motorista where id = ? order by id asc") })
public class Motorista extends AppBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SE_MOTORISTA")
	private Long id;

	@NotNull
	@Digits(integer = 5, fraction = 0)
	private Long matricula;

	@NotNull
	@Size(max = 100)
	private String nome;

	@ManyToOne(targetEntity = Operacao.class, fetch = FetchType.LAZY)
	@ForeignKey(name = "FK_MOTORISTA_OPERACAO")
	@NotNull
	private Operacao operacao;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(length = 1)
	private Turno turno;

	public Motorista() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Operacao getOperacao() {
		return operacao;
	}

	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
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

}
