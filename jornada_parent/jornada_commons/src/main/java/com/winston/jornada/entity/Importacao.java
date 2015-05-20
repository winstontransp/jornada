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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ForeignKey;

import com.powerlogic.jcompany.commons.config.stereotypes.SPlcEntity;
import com.powerlogic.jcompany.domain.validation.PlcValDuplicity;
import com.powerlogic.jcompany.domain.validation.PlcValMultiplicity;

@SPlcEntity
@Entity
@Table(name = "IMPORTACAO")
@SequenceGenerator(name = "SE_IMPORTACAO", sequenceName = "SE_IMPORTACAO")
@Access(AccessType.FIELD)
@NamedQueries({ @NamedQuery(name = "Importacao.querySelLookup", query = "select id as id, total as total from Importacao where id = ? order by id asc") })
public class Importacao extends AppBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SE_IMPORTACAO")
	private Long id;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@NotNull
	@Digits(integer = 5, fraction = 0)
	private Integer total;

	@NotNull
	@Digits(integer = 5, fraction = 0)
	private Integer sucesso;

	@NotNull
	@Digits(integer = 5, fraction = 0)
	private Integer erro;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(length = 1)
	private StatusImportacao status;

	@OneToMany(targetEntity = Direcao.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "importacao")
	@ForeignKey(name = "FK_DIRECAO_IMPORTACAO")
	@PlcValDuplicity(property = "motorista")
	@PlcValMultiplicity(referenceProperty = "motorista", message = "{jcompany.aplicacao.mestredetalhe.multiplicidade.Direcao}")
	@Valid
	private List<Direcao> direcao;

	public List<Direcao> getDirecao() {
		return direcao;
	}

	public void setDirecao(List<Direcao> direcao) {
		this.direcao = direcao;
	}

	public Importacao() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getSucesso() {
		return sucesso;
	}

	public void setSucesso(Integer sucesso) {
		this.sucesso = sucesso;
	}

	public Integer getErro() {
		return erro;
	}

	public void setErro(Integer erro) {
		this.erro = erro;
	}

	public StatusImportacao getStatus() {
		return status;
	}

	public void setStatus(StatusImportacao status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return getTotal().toString();
	}

}
