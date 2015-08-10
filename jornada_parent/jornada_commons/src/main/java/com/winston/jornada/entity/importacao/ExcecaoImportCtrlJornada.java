package com.winston.jornada.entity.importacao;

import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
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
import javax.validation.constraints.Size;

import org.hibernate.annotations.ForeignKey;

import com.powerlogic.jcompany.commons.config.stereotypes.SPlcEntity;
import com.powerlogic.jcompany.domain.type.PlcYesNo;
import com.powerlogic.jcompany.domain.validation.PlcValDuplicity;
import com.powerlogic.jcompany.domain.validation.PlcValMultiplicity;
import com.winston.jornada.entity.AppBaseEntity;

@SPlcEntity
@Entity
@Table(name = "exc_imp_ctrl_jornada")
@SequenceGenerator(name = "se_exc_imp_ctrl_jornada", sequenceName = "se_exc_imp_ctrl_jornada")
@Access(AccessType.FIELD)
@NamedQueries({
	@NamedQuery(name="ExcecaoImportCtrlJornada.queryMan", query="from ExcecaoImportCtrlJornada"),
	@NamedQuery(name="ExcecaoImportCtrlJornada.querySel", query="select id as id, dataImportacao as dataImportacao, nomeArquivo as nomeArquivo, jornadasAtualizadas as jornadasAtualizadas, possuiExcecoes as possuiExcecoes, statusImportacao as statusImportacao from ExcecaoImportCtrlJornada order by nomeArquivo asc"),
	@NamedQuery(name="ExcecaoImportCtrlJornada.querySelLookup", query="select id as id, nomeArquivo as nomeArquivo from ExcecaoImportCtrlJornada where id = ? order by id asc") })
public class ExcecaoImportCtrlJornada extends AppBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "se_exc_imp_ctrl_jornada")
	private Long id;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataImportacao;

	@NotNull
	@Size(max = 100)
	private String nomeArquivo;

	@NotNull
	@Size(max = 255)
	private String caminhoArquivo;

	@NotNull
	@Digits(integer = 8, fraction = 0)
	private Integer jornadasAtualizadas;

	@Enumerated(EnumType.STRING)
	@NotNull
	private PlcYesNo possuiExcecoes;

	@Enumerated(EnumType.STRING)
	@NotNull
	private StatusImportacao statusImportacao;

	@OneToMany(targetEntity = ExcecaoImportCtrlJornadaDetalhe.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "excecaoImportCtrlJornada")
	@ForeignKey(name = "FK_EICJD_EICJ")
	@PlcValDuplicity(property = "nomeColunaExcel")
	@PlcValMultiplicity(referenceProperty = "nomeColunaExcel", message = "{jcompany.aplicacao.mestredetalhe.multiplicidade.ExcecaoImportCtrlJornadaDetalhe}")
	@Valid
	private List<ExcecaoImportCtrlJornadaDetalhe> detalheExcecao;
	
	public ExcecaoImportCtrlJornada() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataImportacao() {
		return dataImportacao;
	}

	public void setDataImportacao(Date dataImportacao) {
		this.dataImportacao = dataImportacao;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getCaminhoArquivo() {
		return caminhoArquivo;
	}

	public void setCaminhoArquivo(String caminhoArquivo) {
		this.caminhoArquivo = caminhoArquivo;
	}

	public Integer getJornadasAtualizadas() {
		return jornadasAtualizadas;
	}

	public void setJornadasAtualizadas(Integer jornadasAtualizadas) {
		this.jornadasAtualizadas = jornadasAtualizadas;
	}

	public PlcYesNo getPossuiExcecoes() {
		return possuiExcecoes;
	}

	public void setPossuiExcecoes(PlcYesNo possuiExcecoes) {
		this.possuiExcecoes = possuiExcecoes;
	}

	public StatusImportacao getStatusImportacao() {
		return statusImportacao;
	}

	public void setStatusImportacao(StatusImportacao statusImportacao) {
		this.statusImportacao = statusImportacao;
	}

	public List<ExcecaoImportCtrlJornadaDetalhe> getDetalheExcecao() {
		return detalheExcecao;
	}

	public void setDetalheExcecao(
			List<ExcecaoImportCtrlJornadaDetalhe> detalheExcecao) {
		this.detalheExcecao = detalheExcecao;
	}

	@Override
	public String toString() {
		return getNomeArquivo();
	}

}
