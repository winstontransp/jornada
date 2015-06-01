package com.winston.jornada.entity.seguranca;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.powerlogic.jcompany.commons.config.stereotypes.SPlcEntity;
import com.powerlogic.jcompany.domain.type.PlcYesNo;
import com.winston.jornada.entity.AppBaseEntity;

@SPlcEntity
@Entity
@Table(name = "SEG_URL")
@SequenceGenerator(name = "SE_SEG_URL", sequenceName = "SE_SEG_URL")
@Access(AccessType.FIELD)
@NamedQueries({ @NamedQuery(name = "SegUrl.querySelLookup", query = "select id as id, url as url from SegUrl where id = ? order by id asc") })
public class SegUrl extends AppBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SE_SEG_URL")
	private Long id;

	@NotNull
	@Size(max = 1000)
	private String url;

	@NotNull
	@Size(max = 100)
	private String casoUso;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(length = 1)
	private PlcYesNo bloqueado;

	public SegUrl() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCasoUso() {
		return casoUso;
	}

	public void setCasoUso(String casoUso) {
		this.casoUso = casoUso;
	}

	public PlcYesNo getBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(PlcYesNo bloqueado) {
		this.bloqueado = bloqueado;
	}

	@Override
	public String toString() {
		return getUrl();
	}

}
