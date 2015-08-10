package com.winston.jornada.entity.importacao;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.myfaces.extensions.validator.crossval.annotation.RequiredIf;
import org.apache.myfaces.extensions.validator.crossval.annotation.RequiredIfType;
import org.hibernate.annotations.ForeignKey;

import com.powerlogic.jcompany.commons.config.stereotypes.SPlcEntity;
import com.powerlogic.jcompany.domain.validation.PlcValGroupEntityList;
import com.winston.jornada.entity.AppBaseEntity;

@SPlcEntity
@Entity
@Table(name = "exc_imp_ctrl_jornada_det")
@SequenceGenerator(name = "se_exc_imp_ctrl_jornada_det", sequenceName = "se_exc_imp_ctrl_jornada_det")
@Access(AccessType.FIELD)
@NamedQueries({ @NamedQuery(name = "ExcecaoImportCtrlJornadaDetalhe.querySelLookup", query = "select id as id, nomeColunaExcel as nomeColunaExcel from ExcecaoImportCtrlJornadaDetalhe where id = ? order by id asc") })
public class ExcecaoImportCtrlJornadaDetalhe extends AppBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "se_exc_imp_ctrl_jornada_det")
	private Long id;

	@ManyToOne(targetEntity = ExcecaoImportCtrlJornada.class, fetch = FetchType.LAZY)
	@ForeignKey(name = "FK_EICJD_EICJ")
	@NotNull
	private ExcecaoImportCtrlJornada excecao;

	@NotNull(groups = PlcValGroupEntityList.class)
	@RequiredIf(valueOf = "nomeColunaExcel", is = RequiredIfType.not_empty)
	@Digits(integer = 8, fraction = 0)
	private Integer linhaExcel;

	@NotNull(groups = PlcValGroupEntityList.class)
	@RequiredIf(valueOf = "nomeColunaExcel", is = RequiredIfType.not_empty)
	@Digits(integer = 5, fraction = 0)
	private Integer colunaExcel;

	@NotNull(groups = PlcValGroupEntityList.class)
	@RequiredIf(valueOf = "id", is = RequiredIfType.not_empty)
	@Size(max = 100)
	private String nomeColunaExcel;

	@NotNull(groups = PlcValGroupEntityList.class)
	@RequiredIf(valueOf = "nomeColunaExcel", is = RequiredIfType.not_empty)
	@Size(max = 255)
	private String descricaoExcecao;

	@NotNull(groups = PlcValGroupEntityList.class)
	@RequiredIf(valueOf = "nomeColunaExcel", is = RequiredIfType.not_empty)
	@Size(max = 100)
	private String valorImportado;

	public ExcecaoImportCtrlJornadaDetalhe() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getLinhaExcel() {
		return linhaExcel;
	}

	public void setLinhaExcel(Integer linhaExcel) {
		this.linhaExcel = linhaExcel;
	}

	public Integer getColunaExcel() {
		return colunaExcel;
	}

	public void setColunaExcel(Integer colunaExcel) {
		this.colunaExcel = colunaExcel;
	}

	public String getNomeColunaExcel() {
		return nomeColunaExcel;
	}

	public void setNomeColunaExcel(String nomeColunaExcel) {
		this.nomeColunaExcel = nomeColunaExcel;
	}

	public String getDescricaoExcecao() {
		return descricaoExcecao;
	}

	public void setDescricaoExcecao(String descricaoExcecao) {
		this.descricaoExcecao = descricaoExcecao;
	}

	public String getValorImportado() {
		return valorImportado;
	}

	public void setValorImportado(String valorImportado) {
		this.valorImportado = valorImportado;
	}

	public ExcecaoImportCtrlJornada getExcecao() {
		return excecao;
	}

	public void setExcecao(ExcecaoImportCtrlJornada excecao) {
		this.excecao = excecao;
	}

	@Override
	public String toString() {
		return getNomeColunaExcel();
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
