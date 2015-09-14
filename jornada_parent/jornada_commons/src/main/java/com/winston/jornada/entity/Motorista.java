package com.winston.jornada.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ForeignKey;

import com.powerlogic.jcompany.commons.config.stereotypes.SPlcEntity;
import com.powerlogic.jcompany.domain.validation.PlcValDuplicity;
import com.powerlogic.jcompany.domain.validation.PlcValMultiplicity;

@SPlcEntity
@Entity
@Table(name = "MOTORISTA")
@SequenceGenerator(name = "SE_MOTORISTA", sequenceName = "SE_MOTORISTA")
@Access(AccessType.FIELD)
@NamedQueries({
	@NamedQuery(name = "Motorista.queryMan", query = "from Motorista where sitHistoricoPlc='A'"),
	@NamedQuery(name = "Motorista.querySel", query = "select obj.id as id, obj.matricula as matricula, obj.nome as nome, obj.dataDesligamento as dataDesligamento, obj1.id as operacao_id, obj1.nome as operacao_nome from Motorista obj left outer join obj.operacao as obj1 where obj.sitHistoricoPlc='A' order by obj.nome asc"),
	@NamedQuery(name = "Motorista.querySelLookup", query = "select id as id, nome as nome, matricula as matricula from Motorista where id = ? and sitHistoricoPlc='A' order by id asc"),
	@NamedQuery(name = "Motorista.queryBuscaMotoristaPorMatricula", query = "from Motorista where matricula = :matricula and sitHistoricoPlc='A'") })
public class Motorista extends AppBaseEntity {

	@OneToMany(targetEntity = MotoristaAfastamento.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "motorista")
	@ForeignKey(name = "FK_MOTORISTAAFASTAMENTO_MOTORISTA")
	@PlcValDuplicity(property = "inicio")
	@PlcValMultiplicity(referenceProperty = "inicio", message = "{jcompany.aplicacao.mestredetalhe.multiplicidade.MotoristaAfastamento}")
	@Valid
	private List<MotoristaAfastamento> motoristaAfastamento;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SE_MOTORISTA")
	private Long id;

	@NotNull
	@Digits(integer = 6, fraction = 0)
	private Long matricula;

	@NotNull
	@Size(max = 100)
	private String nome;

	@ManyToOne(targetEntity = Operacao.class, fetch = FetchType.LAZY)
	@ForeignKey(name = "FK_MOTORISTA_OPERACAO")
	@NotNull
	private Operacao operacao;

	private Date dataDesligamento;
	
	@NotNull
	@Size(max = 1)
	private String sitHistoricoPlc = "A";

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
	
	public Date getDataDesligamento() {
		return dataDesligamento;
	}
	
	public void setDataDesligamento(Date dataDesligamento) {
		this.dataDesligamento = dataDesligamento;
	}
	
	public List<MotoristaAfastamento> getMotoristaAfastamento() {
		return motoristaAfastamento;
	}

	public void setMotoristaAfastamento(
			List<MotoristaAfastamento> motoristaAfastamento) {
		this.motoristaAfastamento = motoristaAfastamento;
	}

	@Override
	public String toString() {
		return getNome();
	}

	public String getSitHistoricoPlc() {
		return sitHistoricoPlc;
	}

	public void setSitHistoricoPlc(String sitHistoricoPlc) {
		this.sitHistoricoPlc = sitHistoricoPlc;
	}

}
