package bean;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Tabdepartment entity. @author MyEclipse Persistence Tools
 */

public class Tabdepartment implements java.io.Serializable {

	// Fields

	private BigDecimal deptId;
	private String deptName;
	private String deptAddr;
	private String deptCity;
	private String zipcode;
	private String deptWebsite;
	private String telphone;
	private String fox;
	private String maneger;
	private String bookStatement;
	private String others;
	private Set tabresultmetadatasForProductDept = new HashSet(0);
	private Set tabresultmetadatasForMaintainDept = new HashSet(0);
	private Set tabresultmetadatasForDistributeDept = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tabdepartment() {
	}

	/** minimal constructor */
	public Tabdepartment(BigDecimal deptId) {
		this.deptId = deptId;
	}

	/** full constructor */
	public Tabdepartment(BigDecimal deptId, String deptName, String deptAddr,
			String deptCity, String zipcode, String deptWebsite,
			String telphone, String fox, String maneger, String bookStatement,
			String others, Set tabresultmetadatasForProductDept,
			Set tabresultmetadatasForMaintainDept,
			Set tabresultmetadatasForDistributeDept) {
		this.deptId = deptId;
		this.deptName = deptName;
		this.deptAddr = deptAddr;
		this.deptCity = deptCity;
		this.zipcode = zipcode;
		this.deptWebsite = deptWebsite;
		this.telphone = telphone;
		this.fox = fox;
		this.maneger = maneger;
		this.bookStatement = bookStatement;
		this.others = others;
		this.tabresultmetadatasForProductDept = tabresultmetadatasForProductDept;
		this.tabresultmetadatasForMaintainDept = tabresultmetadatasForMaintainDept;
		this.tabresultmetadatasForDistributeDept = tabresultmetadatasForDistributeDept;
	}

	// Property accessors

	public BigDecimal getDeptId() {
		return this.deptId;
	}

	public void setDeptId(BigDecimal deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptAddr() {
		return this.deptAddr;
	}

	public void setDeptAddr(String deptAddr) {
		this.deptAddr = deptAddr;
	}

	public String getDeptCity() {
		return this.deptCity;
	}

	public void setDeptCity(String deptCity) {
		this.deptCity = deptCity;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getDeptWebsite() {
		return this.deptWebsite;
	}

	public void setDeptWebsite(String deptWebsite) {
		this.deptWebsite = deptWebsite;
	}

	public String getTelphone() {
		return this.telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getFox() {
		return this.fox;
	}

	public void setFox(String fox) {
		this.fox = fox;
	}

	public String getManeger() {
		return this.maneger;
	}

	public void setManeger(String maneger) {
		this.maneger = maneger;
	}

	public String getBookStatement() {
		return this.bookStatement;
	}

	public void setBookStatement(String bookStatement) {
		this.bookStatement = bookStatement;
	}

	public String getOthers() {
		return this.others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public Set getTabresultmetadatasForProductDept() {
		return this.tabresultmetadatasForProductDept;
	}

	public void setTabresultmetadatasForProductDept(
			Set tabresultmetadatasForProductDept) {
		this.tabresultmetadatasForProductDept = tabresultmetadatasForProductDept;
	}

	public Set getTabresultmetadatasForMaintainDept() {
		return this.tabresultmetadatasForMaintainDept;
	}

	public void setTabresultmetadatasForMaintainDept(
			Set tabresultmetadatasForMaintainDept) {
		this.tabresultmetadatasForMaintainDept = tabresultmetadatasForMaintainDept;
	}

	public Set getTabresultmetadatasForDistributeDept() {
		return this.tabresultmetadatasForDistributeDept;
	}

	public void setTabresultmetadatasForDistributeDept(
			Set tabresultmetadatasForDistributeDept) {
		this.tabresultmetadatasForDistributeDept = tabresultmetadatasForDistributeDept;
	}

}