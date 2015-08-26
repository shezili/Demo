package bean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Tabrstask entity. @author MyEclipse Persistence Tools
 */

public class Tabrstask implements java.io.Serializable {

	// Fields

	private BigDecimal taskid;
	private Timestamp taskdate;
	private String taskname;
	private Timestamp inputdate;
	private String state;
	private String filestructure;
	private Timestamp latiestModiDate;
	private String latiestModiUser;
	private BigDecimal occupationstate;
	private String ftpPathname;
	private Set tabresultdatataskmaps = new HashSet(0);
	private Set tabbackups = new HashSet(0);
	private Set tabtaskprojectmaps = new HashSet(0);
	private Set tabrsproductstatuses = new HashSet(0);
	private Set tabplanefortasks = new HashSet(0);
	private Set tabtasksurveydistrictmaps = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tabrstask() {
	}

	/** minimal constructor */
	public Tabrstask(BigDecimal taskid, String filestructure, String ftpPathname) {
		this.taskid = taskid;
		this.filestructure = filestructure;
		this.ftpPathname = ftpPathname;
	}

	/** full constructor */
	public Tabrstask(BigDecimal taskid, Timestamp taskdate, String taskname,
			Timestamp inputdate, String state, String filestructure,
			Timestamp latiestModiDate, String latiestModiUser,
			BigDecimal occupationstate, String ftpPathname,
			Set tabresultdatataskmaps, Set tabbackups, Set tabtaskprojectmaps,
			Set tabrsproductstatuses, Set tabplanefortasks,
			Set tabtasksurveydistrictmaps) {
		this.taskid = taskid;
		this.taskdate = taskdate;
		this.taskname = taskname;
		this.inputdate = inputdate;
		this.state = state;
		this.filestructure = filestructure;
		this.latiestModiDate = latiestModiDate;
		this.latiestModiUser = latiestModiUser;
		this.occupationstate = occupationstate;
		this.ftpPathname = ftpPathname;
		this.tabresultdatataskmaps = tabresultdatataskmaps;
		this.tabbackups = tabbackups;
		this.tabtaskprojectmaps = tabtaskprojectmaps;
		this.tabrsproductstatuses = tabrsproductstatuses;
		this.tabplanefortasks = tabplanefortasks;
		this.tabtasksurveydistrictmaps = tabtasksurveydistrictmaps;
	}

	// Property accessors

	public BigDecimal getTaskid() {
		return this.taskid;
	}

	public void setTaskid(BigDecimal taskid) {
		this.taskid = taskid;
	}

	public Timestamp getTaskdate() {
		return this.taskdate;
	}

	public void setTaskdate(Timestamp taskdate) {
		this.taskdate = taskdate;
	}

	public String getTaskname() {
		return this.taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public Timestamp getInputdate() {
		return this.inputdate;
	}

	public void setInputdate(Timestamp inputdate) {
		this.inputdate = inputdate;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFilestructure() {
		return this.filestructure;
	}

	public void setFilestructure(String filestructure) {
		this.filestructure = filestructure;
	}

	public Timestamp getLatiestModiDate() {
		return this.latiestModiDate;
	}

	public void setLatiestModiDate(Timestamp latiestModiDate) {
		this.latiestModiDate = latiestModiDate;
	}

	public String getLatiestModiUser() {
		return this.latiestModiUser;
	}

	public void setLatiestModiUser(String latiestModiUser) {
		this.latiestModiUser = latiestModiUser;
	}

	public BigDecimal getOccupationstate() {
		return this.occupationstate;
	}

	public void setOccupationstate(BigDecimal occupationstate) {
		this.occupationstate = occupationstate;
	}

	public String getFtpPathname() {
		return this.ftpPathname;
	}

	public void setFtpPathname(String ftpPathname) {
		this.ftpPathname = ftpPathname;
	}

	public Set getTabresultdatataskmaps() {
		return this.tabresultdatataskmaps;
	}

	public void setTabresultdatataskmaps(Set tabresultdatataskmaps) {
		this.tabresultdatataskmaps = tabresultdatataskmaps;
	}

	public Set getTabbackups() {
		return this.tabbackups;
	}

	public void setTabbackups(Set tabbackups) {
		this.tabbackups = tabbackups;
	}

	public Set getTabtaskprojectmaps() {
		return this.tabtaskprojectmaps;
	}

	public void setTabtaskprojectmaps(Set tabtaskprojectmaps) {
		this.tabtaskprojectmaps = tabtaskprojectmaps;
	}

	public Set getTabrsproductstatuses() {
		return this.tabrsproductstatuses;
	}

	public void setTabrsproductstatuses(Set tabrsproductstatuses) {
		this.tabrsproductstatuses = tabrsproductstatuses;
	}

	public Set getTabplanefortasks() {
		return this.tabplanefortasks;
	}

	public void setTabplanefortasks(Set tabplanefortasks) {
		this.tabplanefortasks = tabplanefortasks;
	}

	public Set getTabtasksurveydistrictmaps() {
		return this.tabtasksurveydistrictmaps;
	}

	public void setTabtasksurveydistrictmaps(Set tabtasksurveydistrictmaps) {
		this.tabtasksurveydistrictmaps = tabtasksurveydistrictmaps;
	}

}