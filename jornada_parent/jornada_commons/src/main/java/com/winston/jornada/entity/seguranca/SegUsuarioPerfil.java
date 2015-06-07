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
@Table(name = "SEG_USUARIO_PERFIL")
@SequenceGenerator(name = "SE_SEG_USUARIO_PERFIL", sequenceName = "SE_SEG_USUARIO_PERFIL")
@Access(AccessType.FIELD)
@NamedQueries({ 
	@NamedQuery(name = "SegUsuarioPerfil.obterPerfilUsuarioPorUsuario", query = "from SegUsuarioPerfil where segUsuario = :usuario"),
	@NamedQuery(name = "SegUsuarioPerfil.querySelLookup", query = "select id as id, segUsuario as segUsuario from SegUsuarioPerfil where id = ? order by id asc") })
public class SegUsuarioPerfil extends AppBaseEntity {

	private static final long serialVersionUID = -141081347170280153L;

	private transient String segUsuarioAuxLookup;

	private transient String segPerfilAuxLookup;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SE_SEG_USUARIO_PERFIL")
	private Long id;

	@ManyToOne(targetEntity = SegUsuario.class, fetch = FetchType.LAZY)
	@ForeignKey(name = "FK_USUARIOPERFIL_USUARIO")
	@NotNull(groups = PlcValGroupEntityList.class)
	@RequiredIf(valueOf = "id", is = RequiredIfType.not_empty)
	private SegUsuario segUsuario;

	@ManyToOne(targetEntity = SegPerfil.class, fetch = FetchType.LAZY)
	@ForeignKey(name = "FK_USUARIOPERFIL_PERFIL")
	@NotNull(groups = PlcValGroupEntityList.class)
	@RequiredIf(valueOf = "id", is = RequiredIfType.not_empty)
	private SegPerfil segPerfil;

	public SegUsuarioPerfil() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SegPerfil getSegPerfil() {
		return segPerfil;
	}

	public void setSegPerfil(SegPerfil segPerfil) {
		this.segPerfil = segPerfil;
	}

	public SegUsuario getSegUsuario() {
		return segUsuario;
	}

	public void setSegUsuario(SegUsuario segUsuario) {
		this.segUsuario = segUsuario;
	}

	@Override
	public String toString() {
		return getSegUsuario().toString(); // + " - " + getSegPerfil().toString();
	}

	@Transient
	private String indExcPlc = "N";

	public void setIndExcPlc(String indExcPlc) {
		this.indExcPlc = indExcPlc;
	}

	public String getIndExcPlc() {
		return indExcPlc;
	}

	public void setSegPerfilAuxLookup(String segPerfilAuxLookup) {
		this.segPerfilAuxLookup = segPerfilAuxLookup;
	}


	public void setSegUsuarioAuxLookup(String segUsuarioAuxLookup) {
		this.segUsuarioAuxLookup=segUsuarioAuxLookup;
	}

}
