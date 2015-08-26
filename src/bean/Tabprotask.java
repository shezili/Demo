package bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Tabprotask entity. @author MyEclipse Persistence Tools
 */

public class Tabprotask implements java.io.Serializable {

	// Fields

	private BigDecimal protaskid;
	private BigDecimal taskid;
	private String taskpropeople;
	private Timestamp taskprostarttime;
	private Timestamp taskproendtime;

	// Constructors

	/** default constructor */
	public Tabprotask() {
	}

	/** minimal constructor */
	public Tabprotask(BigDecimal protaskid) {
		this.protaskid = protaskid;
	}

	/** full constructor */
	public Tabprotask(BigDecimal protaskid, BigDecimal taskid,
			String taskpropeople, Timestamp taskprostarttime,
			Timestamp taskproendtime) {
		this.protaskid = protaskid;
		this.taskid = taskid;
		this.taskpropeople = taskpropeople;
		this.taskprostarttime = taskprostarttime;
		this.taskproendtime = taskproendtime;
	}

	// Property accessors

	public BigDecimal getProtaskid() {
		return this.protaskid;
	}

	public void setProtaskid(BigDecimal protaskid) {
		this.protaskid = protaskid;
	}

	public BigDecimal getTaskid() {
		return this.taskid;
	}

	public void setTaskid(BigDecimal taskid) {
		this.taskid = taskid;
	}

	public String getTaskpropeople() {
		return this.taskpropeople;
	}

	public void setTaskpropeople(String taskpropeople) {
		this.taskpropeople = taskpropeople;
	}

	public Timestamp getTaskprostarttime() {
		return this.taskprostarttime;
	}

	public void setTaskprostarttime(Timestamp taskprostarttime) {
		this.taskprostarttime = taskprostarttime;
	}

	public Timestamp getTaskproendtime() {
		return this.taskproendtime;
	}

	public void setTaskproendtime(Timestamp taskproendtime) {
		this.taskproendtime = taskproendtime;
	}

}