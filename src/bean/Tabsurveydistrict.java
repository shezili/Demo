package bean;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Tabsurveydistrict entity. @author MyEclipse Persistence Tools
 */

public class Tabsurveydistrict implements java.io.Serializable {

	// Fields

	private BigDecimal surveydistrictid;
	private String surveydistrictname;
	private Set tabtasksurveydistrictmaps = new HashSet(0);
	private Set tabresultdatas = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tabsurveydistrict() {
	}

	/** minimal constructor */
	public Tabsurveydistrict(BigDecimal surveydistrictid) {
		this.surveydistrictid = surveydistrictid;
	}

	/** full constructor */
	public Tabsurveydistrict(BigDecimal surveydistrictid,
			String surveydistrictname, Set tabtasksurveydistrictmaps,
			Set tabresultdatas) {
		this.surveydistrictid = surveydistrictid;
		this.surveydistrictname = surveydistrictname;
		this.tabtasksurveydistrictmaps = tabtasksurveydistrictmaps;
		this.tabresultdatas = tabresultdatas;
	}

	// Property accessors

	public BigDecimal getSurveydistrictid() {
		return this.surveydistrictid;
	}

	public void setSurveydistrictid(BigDecimal surveydistrictid) {
		this.surveydistrictid = surveydistrictid;
	}

	public String getSurveydistrictname() {
		return this.surveydistrictname;
	}

	public void setSurveydistrictname(String surveydistrictname) {
		this.surveydistrictname = surveydistrictname;
	}

	public Set getTabtasksurveydistrictmaps() {
		return this.tabtasksurveydistrictmaps;
	}

	public void setTabtasksurveydistrictmaps(Set tabtasksurveydistrictmaps) {
		this.tabtasksurveydistrictmaps = tabtasksurveydistrictmaps;
	}

	public Set getTabresultdatas() {
		return this.tabresultdatas;
	}

	public void setTabresultdatas(Set tabresultdatas) {
		this.tabresultdatas = tabresultdatas;
	}

}