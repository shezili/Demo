package bean;

import java.math.BigDecimal;

/**
 * Tabresultdatataskmap entity. @author MyEclipse Persistence Tools
 */

public class Tabresultdatataskmap implements java.io.Serializable {

	// Fields

	private BigDecimal id;
	private Tabresultdata tabresultdata;
	private Tabrstask tabrstask;

	// Constructors

	/** default constructor */
	public Tabresultdatataskmap() {
	}

	/** minimal constructor */
	public Tabresultdatataskmap(BigDecimal id) {
		this.id = id;
	}

	/** full constructor */
	public Tabresultdatataskmap(BigDecimal id, Tabresultdata tabresultdata,
			Tabrstask tabrstask) {
		this.id = id;
		this.tabresultdata = tabresultdata;
		this.tabrstask = tabrstask;
	}

	// Property accessors

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Tabresultdata getTabresultdata() {
		return this.tabresultdata;
	}

	public void setTabresultdata(Tabresultdata tabresultdata) {
		this.tabresultdata = tabresultdata;
	}

	public Tabrstask getTabrstask() {
		return this.tabrstask;
	}

	public void setTabrstask(Tabrstask tabrstask) {
		this.tabrstask = tabrstask;
	}

}