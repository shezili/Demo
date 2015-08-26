package bean;

import java.math.BigDecimal;

/**
 * Tabrsproductstatus entity. @author MyEclipse Persistence Tools
 */

public class Tabrsproductstatus implements java.io.Serializable {

	// Fields

	private BigDecimal id;
	private Tabrstask tabrstask;
	private Tabtaskdatastatus tabtaskdatastatus;

	// Constructors

	/** default constructor */
	public Tabrsproductstatus() {
	}

	/** minimal constructor */
	public Tabrsproductstatus(BigDecimal id) {
		this.id = id;
	}

	/** full constructor */
	public Tabrsproductstatus(BigDecimal id, Tabrstask tabrstask,
			Tabtaskdatastatus tabtaskdatastatus) {
		this.id = id;
		this.tabrstask = tabrstask;
		this.tabtaskdatastatus = tabtaskdatastatus;
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

	public Tabtaskdatastatus getTabtaskdatastatus() {
		return this.tabtaskdatastatus;
	}

	public void setTabtaskdatastatus(Tabtaskdatastatus tabtaskdatastatus) {
		this.tabtaskdatastatus = tabtaskdatastatus;
	}

}