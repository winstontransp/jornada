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
@Table(name = "CRITICA")
@SequenceGenerator(name = "SE_CRITICA", sequenceName = "SE_CRITICA")
@Access(AccessType.FIELD)
@NamedQueries({
	@NamedQuery(name="Critica.queryMan", query="from Critica"), 
	@NamedQuery(name="Critica.obterCriticaPorParam", query="from Critica where campoCriticaParam = :param"), 
	@NamedQuery(name="Critica.querySelLookup", query="select id as id, nome as nome from Critica where id = ? order by id asc") })
public class Critica extends AppBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SE_CRITICA")
	private Long id;

	@NotNull(groups = PlcValGroupEntityList.class)
	@RequiredIf(valueOf = "id", is = RequiredIfType.not_empty)
	@Size(max = 100)
	@PlcReference(testDuplicity = true)
	private String nome;

	@NotNull(groups = PlcValGroupEntityList.class)
	@RequiredIf(valueOf = "nome", is = RequiredIfType.not_empty)
	@Size(max = 255)
	private String descricao;

	@NotNull(groups = PlcValGroupEntityList.class)
	@RequiredIf(valueOf = "nome", is = RequiredIfType.not_empty)
	@Size(max = 80)
	private String campoCriticaParam;

	public Critica() {
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCampoCriticaParam() {
		return campoCriticaParam;
	}

	public void setCampoCriticaParam(String campoCriticaParam) {
		this.campoCriticaParam = campoCriticaParam;
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

}
