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
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.myfaces.extensions.validator.crossval.annotation.RequiredIf;
import org.apache.myfaces.extensions.validator.crossval.annotation.RequiredIfType;

import com.powerlogic.jcompany.commons.config.stereotypes.SPlcEntity;
import com.powerlogic.jcompany.config.domain.PlcReference;
import com.powerlogic.jcompany.domain.validation.PlcValGroupEntityList;

@SPlcEntity
@Entity
@Table(name = "MACRO")
@SequenceGenerator(name = "SE_MACRO", sequenceName = "SE_MACRO")
@Access(AccessType.FIELD)
@NamedQueries({
		@NamedQuery(name = "Macro.queryMan", query = "from Macro"),
		@NamedQuery(name = "Macro.querySelLookup", query = "select id as id, nome as nome from Macro where id = ? order by id asc") })
public class Macro extends AppBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SE_MACRO")
	private Long id;

	@NotNull(groups = PlcValGroupEntityList.class)
	@RequiredIf(valueOf = "nome", is = RequiredIfType.not_empty)
	@Digits(integer = 5, fraction = 0)
	private Integer codigo;

	@NotNull(groups = PlcValGroupEntityList.class)
	@RequiredIf(valueOf = "id", is = RequiredIfType.not_empty)
	@Size(max = 50)
	@PlcReference(testDuplicity = true)
	private String nome;

	public Macro() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
