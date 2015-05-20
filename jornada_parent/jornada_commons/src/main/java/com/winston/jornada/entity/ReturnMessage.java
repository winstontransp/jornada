package com.winston.jornada.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.myfaces.extensions.validator.crossval.annotation.RequiredIf;
import org.apache.myfaces.extensions.validator.crossval.annotation.RequiredIfType;
import org.hibernate.annotations.ForeignKey;

import com.powerlogic.jcompany.commons.config.stereotypes.SPlcEntity;
import com.powerlogic.jcompany.domain.validation.PlcValGroupEntityList;


@SPlcEntity
@Entity
@Table(name="RETURN_MESSAGE")
@SequenceGenerator(name="SE_RETURN_MESSAGE", sequenceName="SE_RETURN_MESSAGE")
@Access(AccessType.FIELD)

@NamedQueries({
	@NamedQuery(name="ReturnMessage.querySelLookup", query="select id as id, landmark as landmark from ReturnMessage where id = ? order by id asc")
})
public class ReturnMessage extends AppBaseEntity {

	
	@Id 
 	@GeneratedValue(strategy=GenerationType.AUTO, generator = "SE_RETURN_MESSAGE")
	private Long id;
	
	@ManyToOne (targetEntity = Jornada.class, fetch = FetchType.LAZY)
	@ForeignKey(name="FK_RETURNMESSAGE_JORNADA")
	@NotNull

	@JoinColumn(name="JORNADA")
	private Jornada jornada;

	
	@NotNull(groups=PlcValGroupEntityList.class)
	@RequiredIf(valueOf="landmark",is=RequiredIfType.not_empty)
	@Digits(integer=5, fraction=0)
	private Long mctAddress;
	
	@NotNull(groups=PlcValGroupEntityList.class)
	@RequiredIf(valueOf="landmark",is=RequiredIfType.not_empty)
	@Digits(integer=5, fraction=0)
	private Integer macroNumber;
	
	@NotNull(groups=PlcValGroupEntityList.class)
	@RequiredIf(valueOf="landmark",is=RequiredIfType.not_empty)
	@Digits(integer=8, fraction=2)
	private BigDecimal latitude;
	
	@NotNull(groups=PlcValGroupEntityList.class)
	@RequiredIf(valueOf="landmark",is=RequiredIfType.not_empty)
	@Digits(integer=8, fraction=2)
	private BigDecimal longitude;
	
	@NotNull(groups=PlcValGroupEntityList.class)
	@RequiredIf(valueOf="landmark",is=RequiredIfType.not_empty)
	@Temporal(TemporalType.TIMESTAMP)
	private Date positionTime;
	
	@Size(max = 5)
	private String landmark;
	
	@Size(max = 5)
	private String macroText;
	
	@NotNull(groups=PlcValGroupEntityList.class)
	@RequiredIf(valueOf="landmark",is=RequiredIfType.not_empty)
	private Boolean proc = Boolean.FALSE;
	
	public ReturnMessage() {
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public Long getMctAddress() {
		return mctAddress;
	}

	public void setMctAddress(Long mctAddress) {
		this.mctAddress=mctAddress;
	}

	public Integer getMacroNumber() {
		return macroNumber;
	}

	public void setMacroNumber(Integer macroNumber) {
		this.macroNumber=macroNumber;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude=latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude=longitude;
	}

	public Date getPositionTime() {
		return positionTime;
	}

	public void setPositionTime(Date positionTime) {
		this.positionTime=positionTime;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark=landmark;
	}

	public String getMacroText() {
		return macroText;
	}

	public void setMacroText(String macroText) {
		this.macroText=macroText;
	}

	public Boolean getProc() {
		return proc;
	}

	public void setProc(Boolean proc) {
		this.proc=proc;
	}

	public Jornada getJornada() {
		return jornada;
	}

	public void setJornada(Jornada jornada) {
		this.jornada=jornada;
	}

	@Override
	public String toString() {
		return getLandmark();
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
