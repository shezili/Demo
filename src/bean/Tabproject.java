package bean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Tabproject entity. @author MyEclipse Persistence Tools
 */

public class Tabproject implements java.io.Serializable {

	// Fields

	private BigDecimal projectid;
	private String projectname;
	private Timestamp starttime;
	private Timestamp endtime;
	private String describe;
	private String incharge;
	private String aliasname;
	private Set tabtaskprojectmaps = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tabproject() {
	}

	/** minimal constructor */
	public Tabproject(BigDecimal projectid, String projectname) {
		this.projectid = projectid;
		this.projectname = projectname;
	}

	/** full constructor */
	public Tabproject(BigDecimal projectid, String projectname,
			Timestamp starttime, Timestamp endtime, String describe,
			String incharge, String aliasname, Set tabtaskprojectmaps) {
		this.projectid = projectid;
		this.projectname = projectname;
		this.starttime = starttime;
		this.endtime = endtime;
		this.describe = describe;
		this.incharge = incharge;
		this.aliasname = aliasname;
		this.tabtaskprojectmaps = tabtaskprojectmaps;
	}

	// Property accessors

	public BigDecimal getProjectid() {
		return this.projectid;
	}

	public void setProjectid(BigDecimal projectid) {
		this.projectid = projectid;
	}

	public String getProjectname() {
		return this.projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public Timestamp getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}

	public Timestamp getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

	public String getDescribe() {
		return this.describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getIncharge() {
		return this.incharge;
	}

	public void setIncharge(String incharge) {
		this.incharge = incharge;
	}

	public String getAliasname() {
		return this.aliasname;
	}

	public void setAliasname(String aliasname) {
		this.aliasname = aliasname;
	}

	public Set getTabtaskprojectmaps() {
		return this.tabtaskprojectmaps;
	}

	public void setTabtaskprojectmaps(Set tabtaskprojectmaps) {
		this.tabtaskprojectmaps = tabtaskprojectmaps;
	}

}