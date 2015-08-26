package bean;

import java.math.BigDecimal;

/**
 * Tabtaskprojectmap entity. @author MyEclipse Persistence Tools
 */

public class Tabtaskprojectmap implements java.io.Serializable {

	// Fields

	private BigDecimal id;
	private Tabrstask tabrstask;
	private Tabproject tabproject;
	private BigDecimal isprimary;

	// Constructors

	/** default constructor */
	public Tabtaskprojectmap() {
	}

	/** minimal constructor */
	public Tabtaskprojectmap(BigDecimal id) {
		this.id = id;
	}

	/** full constructor */
	public Tabtaskprojectmap(BigDecimal id, Tabrstask tabrstask,
			Tabproject tabproject, BigDecimal isprimary) {
		this.id = id;
		this.tabrstask = tabrstask;
		this.tabproject = tabproject;
		this.isprimary = isprimary;
	}

	// Property accessors

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Tabrstask getTabrstask() {
		return this.tabrstask;
	}

	public void setTabrstask(Tabrstask tabrstask) {
		this.tabrstask = tabrstask;
	}

	public Tabproject getTabproject() {
		return this.tabproject;
	}

	public void setTabproject(Tabproject tabproject) {
		this.tabproject = tabproject;
	}

	public BigDecimal getIsprimary() {
		return this.isprimary;
	}

	public void setIsprimary(BigDecimal isprimary) {
		this.isprimary = isprimary;
	}

}