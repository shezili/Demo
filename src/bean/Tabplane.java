package bean;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Tabplane entity. @author MyEclipse Persistence Tools
 */

public class Tabplane implements java.io.Serializable {

	// Fields

	private BigDecimal planeid;
	private String planenum;
	private String planemsg;
	private Set tabplanefortasks = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tabplane() {
	}

	/** minimal constructor */
	public Tabplane(BigDecimal planeid, String planenum) {
		this.planeid = planeid;
		this.planenum = planenum;
	}

	/** full constructor */
	public Tabplane(BigDecimal planeid, String planenum, String planemsg,
			Set tabplanefortasks) {
		this.planeid = planeid;
		this.planenum = planenum;
		this.planemsg = planemsg;
		this.tabplanefortasks = tabplanefortasks;
	}

	// Property accessors

	public BigDecimal getPlaneid() {
		return this.planeid;
	}

	public void setPlaneid(BigDecimal planeid) {
		this.planeid = planeid;
	}

	public String getPlanenum() {
		return this.planenum;
	}

	public void setPlanenum(String planenum) {
		this.planenum = planenum;
	}

	public String getPlanemsg() {
		return this.planemsg;
	}

	public void setPlanemsg(String planemsg) {
		this.planemsg = planemsg;
	}

	public Set getTabplanefortasks() {
		return this.tabplanefortasks;
	}

	public void setTabplanefortasks(Set tabplanefortasks) {
		this.tabplanefortasks = tabplanefortasks;
	}

}