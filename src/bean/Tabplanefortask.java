package bean;

import java.math.BigDecimal;

/**
 * Tabplanefortask entity. @author MyEclipse Persistence Tools
 */

public class Tabplanefortask implements java.io.Serializable {

	// Fields

	private BigDecimal id;
	private Tabrstask tabrstask;
	private Tabplane tabplane;

	// Constructors

	/** default constructor */
	public Tabplanefortask() {
	}

	/** minimal constructor */
	public Tabplanefortask(BigDecimal id) {
		this.id = id;
	}

	/** full constructor */
	public Tabplanefortask(BigDecimal id, Tabrstask tabrstask, Tabplane tabplane) {
		this.id = id;
		this.tabrstask = tabrstask;
		this.tabplane = tabplane;
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

	public Tabplane getTabplane() {
		return this.tabplane;
	}

	public void setTabplane(Tabplane tabplane) {
		this.tabplane = tabplane;
	}

}