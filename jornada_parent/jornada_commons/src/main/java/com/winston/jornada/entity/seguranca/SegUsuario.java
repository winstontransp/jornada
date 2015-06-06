package com.winston.jornada.entity.seguranca;

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
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ForeignKey;

import com.powerlogic.jcompany.commons.config.stereotypes.SPlcEntity;
import com.powerlogic.jcompany.domain.type.PlcYesNo;
import com.powerlogic.jcompany.domain.validation.PlcValDuplicity;
import com.powerlogic.jcompany.domain.validation.PlcValMultiplicity;
import com.winston.jornada.entity.AppBaseEntity;

@SPlcEntity
@Entity
@Table(name = "SEG_USUARIO")
@SequenceGenerator(name = "SE_SEG_USUARIO", sequenceName = "SE_SEG_USUARIO")
@Access(AccessType.FIELD)
@NamedQueries({
		@NamedQuery(name = "SegUsuario.queryMan", query = "from SegUsuario"),
		@NamedQuery(name = "SegUsuario.querySel", query = "select id as id, nome as nome, bloqueado as bloqueado, login as login from SegUsuario order by nome asc"),
		@NamedQuery(name = "SegUsuario.querySel2", query = "from SegUsuario where login = :login"),
		@NamedQuery(name = "SegUsuario.querySelLookup", query = "select id as id, nome as nome from SegUsuario where id = ? order by id asc") })
public class SegUsuario extends AppBaseEntity {

	@OneToMany(targetEntity = SegUsuarioPerfil.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "segUsuario")
	@ForeignKey(name = "FK_USUARIOPERFIL_USUARIO")
	@PlcValDuplicity(property = "segPerfil")
	@PlcValMultiplicity(referenceProperty = "segPerfil", message = "{jcompany.aplicacao.mestredetalhe.multiplicidade.SegUsuarioPerfil}")
	@Valid
	private List<SegUsuarioPerfil> segUsuarioPerfil;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SE_SEG_USUARIO")
	private Long id;

	@NotNull
	@Size(max = 150)
	private String nome;

	@NotNull
	@Size(max = 20)
	private String login;

	@NotNull
	@Size(max = 150)
	private String senha;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(length = 1)
	private PlcYesNo bloqueado;

	public SegUsuario() {
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public PlcYesNo getBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(PlcYesNo bloqueado) {
		this.bloqueado = bloqueado;
	}

	@Override
	public String toString() {
		return getNome();
	}

	public List<SegUsuarioPerfil> getSegUsuarioPerfil() {
		return segUsuarioPerfil;
	}

	public void setSegUsuarioPerfil(List<SegUsuarioPerfil> segUsuarioPerfil) {
		this.segUsuarioPerfil = segUsuarioPerfil;
	}
	
	@Transient
	private transient String senhaAux = "";

	@Transient
	private transient String senhaAuxConfirmacao = "";

	public String getSenhaAux() {
		return senhaAux;
	}

	public void setSenhaAux(String senhaAux) {
		this.senhaAux = senhaAux;
	}

	public String getSenhaAuxConfirmacao() {
		return senhaAuxConfirmacao;
	}

	public void setSenhaAuxConfirmacao(String senhaAuxConfirmacao) {
		this.senhaAuxConfirmacao = senhaAuxConfirmacao;
	}
	
}
