package bean;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Tabdatatype entity. @author MyEclipse Persistence Tools
 */

public class Tabdatatype implements java.io.Serializable {

	// Fields

	private BigDecimal datatypeId;
	private String typename;
	private String descr;
	private Set tabresultdatas = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tabdatatype() {
	}

	/** minimal constructor */
	public Tabdatatype(BigDecimal datatypeId, String typename) {
		this.datatypeId = datatypeId;
		this.typename = typename;
	}

	/** full constructor */
	public Tabdatatype(BigDecimal datatypeId, String typename, String descr,
			Set tabresultdatas) {
		this.datatypeId = datatypeId;
		this.typename = typename;
		this.descr = descr;
		this.tabresultdatas = tabresultdatas;
	}

	// Property accessors

	public BigDecimal getDatatypeId() {
		return this.datatypeId;
	}

	public void setDatatypeId(BigDecimal datatypeId) {
		this.datatypeId = datatypeId;
	}

	public String getTypename() {
		return this.typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Set getTabresultdatas() {
		return this.tabresultdatas;
	}

	public void setTabresultdatas(Set tabresultdatas) {
		this.tabresultdatas = tabresultdatas;
	}

}