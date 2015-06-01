package com.winston.jornada.entity.seguranca;

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
import com.winston.jornada.entity.AppBaseEntity;
import java.util.List;
import com.powerlogic.jcompany.domain.validation.PlcValDuplicity;
import javax.validation.Valid;
import com.powerlogic.jcompany.domain.validation.PlcValMultiplicity;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import org.hibernate.annotations.ForeignKey;

@SPlcEntity
@Entity
@Table(name = "SEG_PERFIL")
@SequenceGenerator(name = "SE_SEG_PERFIL", sequenceName = "SE_SEG_PERFIL")
@Access(AccessType.FIELD)
@NamedQueries({ @NamedQuery(name = "SegPerfil.querySelLookup", query = "select id as id, nome as nome from SegPerfil where id = ? order by id asc") })
public class SegPerfil extends AppBaseEntity {

	@OneToMany(targetEntity = SegPerfilUrl.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "segPerfil")
	@ForeignKey(name = "FK_SEGPERFILURL_PERFIL")
	@PlcValDuplicity(property = "segPerfil")
	@PlcValMultiplicity(referenceProperty = "segPerfil", message = "{jcompany.aplicacao.mestredetalhe.multiplicidade.SegPerfilUrl}")
	@Valid
	private List<SegPerfilUrl> segPerfilUrl;

	@OneToMany(targetEntity = SegUsuarioPerfil.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "segPerfil")
	@ForeignKey(name = "FK_USUARIOPERFIL_PERFIL")
	@PlcValDuplicity(property = "segPerfil")
	@PlcValMultiplicity(referenceProperty = "segPerfil", message = "{jcompany.aplicacao.mestredetalhe.multiplicidade.SegUsuarioPerfil}")
	@Valid
	private List<SegUsuarioPerfil> segUsuarioPerfil;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SE_SEG_PERFIL")
	private Long id;

	@NotNull
	@Size(max = 100)
	private String nome;

	public SegPerfil() {
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

	@Override
	public String toString() {
		return getNome();
	}

	public List<SegPerfilUrl> getSegPerfilUrl() {
		return segPerfilUrl;
	}

	public void setSegPerfilUrl(List<SegPerfilUrl> segPerfilUrl) {
		this.segPerfilUrl = segPerfilUrl;
	}
	
	public List<SegUsuarioPerfil> getSegUsuarioPerfil() {
		return segUsuarioPerfil;
	}

	public void setSegUsuarioPerfil(List<SegUsuarioPerfil> segUsuarioPerfil) {
		this.segUsuarioPerfil = segUsuarioPerfil;
	}

}
