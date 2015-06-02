package com.winston.jornada.entity.seguranca;

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

import org.apache.myfaces.extensions.validator.crossval.annotation.RequiredIf;
import org.apache.myfaces.extensions.validator.crossval.annotation.RequiredIfType;
import org.hibernate.annotations.ForeignKey;

import com.powerlogic.jcompany.commons.config.stereotypes.SPlcEntity;
import com.powerlogic.jcompany.domain.validation.PlcValGroupEntityList;
import com.winston.jornada.entity.AppBaseEntity;

@SPlcEntity
@Entity
@Table(name = "SEG_PERFIL_URL")
@SequenceGenerator(name = "SE_SEG_PERFIL_URL", sequenceName = "SE_SEG_PERFIL_URL")
@Access(AccessType.FIELD)
@NamedQueries({ @NamedQuery(name = "SegPerfilUrl.querySelLookup", query = "select id as id, segPerfil as segPerfil from SegPerfilUrl where id = ? order by id asc") })
public class SegPerfilUrl extends AppBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SE_SEG_PERFIL_URL")
	private Long id;

	@ManyToOne(targetEntity = SegPerfil.class, fetch = FetchType.LAZY)
	@ForeignKey(name = "FK_SEGPERFILURL_SEGPERFIL")
	@NotNull(groups = PlcValGroupEntityList.class)
	@RequiredIf(valueOf = "id", is = RequiredIfType.not_empty)
	private SegPerfil segPerfil;

	@ManyToOne(targetEntity = SegUrl.class, fetch = FetchType.LAZY)
	@ForeignKey(name = "FK_SEGPERFILURL_URL")
	@NotNull(groups = PlcValGroupEntityList.class)
	@RequiredIf(valueOf = "id", is = RequiredIfType.not_empty)
	private SegUrl url;

	public SegPerfilUrl() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SegUrl getUrl() {
		return url;
	}

	public void setUrl(SegUrl url) {
		this.url = url;
	}

	public SegPerfil getSegPerfil() {
		return segPerfil;
	}

	public void setSegPerfil(SegPerfil segPerfil) {
		this.segPerfil = segPerfil;
	}

	@Override
	public String toString() {
		return getSegPerfil().toString();
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
