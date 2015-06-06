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
@Table(name = "FROTA")
@SequenceGenerator(name = "SE_FROTA", sequenceName = "SE_FROTA")
@Access(AccessType.FIELD)
@NamedQueries({
	@NamedQuery(name = "Frota.queryMan", query = "from Frota"),
	@NamedQuery(name = "Frota.querySelLookup", query = "select id as id, placa as placa from Frota where id = ? order by id asc") })
public class Frota extends AppBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SE_FROTA")
	private Long id;

	@NotNull(groups = PlcValGroupEntityList.class)
	@RequiredIf(valueOf = "placa", is = RequiredIfType.not_empty)
	@Digits(integer = 5, fraction = 0)
	private Integer numero;

	@NotNull(groups = PlcValGroupEntityList.class)
	@RequiredIf(valueOf = "id", is = RequiredIfType.not_empty)
	@Size(max = 10)
	@PlcReference(testDuplicity = true)
	private String placa;

	@Digits(integer = 5, fraction = 0)
	private Long mctAddress;

	public Frota() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Long getMctAddress() {
		return mctAddress;
	}

	public void setMctAddress(Long mctAddress) {
		this.mctAddress = mctAddress;
	}

	@Override
	public String toString() {
		return getPlaca();
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
