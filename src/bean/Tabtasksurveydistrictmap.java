package bean;

import java.math.BigDecimal;

/**
 * Tabtasksurveydistrictmap entity. @author MyEclipse Persistence Tools
 */

public class Tabtasksurveydistrictmap implements java.io.Serializable {

	// Fields

	private BigDecimal id;
	private Tabrstask tabrstask;
	private Tabsurveydistrict tabsurveydistrict;

	// Constructors

	/** default constructor */
	public Tabtasksurveydistrictmap() {
	}

	/** minimal constructor */
	public Tabtasksurveydistrictmap(BigDecimal id) {
		this.id = id;
	}

	/** full constructor */
	public Tabtasksurveydistrictmap(BigDecimal id, Tabrstask tabrstask,
			Tabsurveydistrict tabsurveydistrict) {
		this.id = id;
		this.tabrstask = tabrstask;
		this.tabsurveydistrict = tabsurveydistrict;
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

	public Tabsurveydistrict getTabsurveydistrict() {
		return this.tabsurveydistrict;
	}

	public void setTabsurveydistrict(Tabsurveydistrict tabsurveydistrict) {
		this.tabsurveydistrict = tabsurveydistrict;
	}

}