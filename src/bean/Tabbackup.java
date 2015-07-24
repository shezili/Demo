package bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Tabbackup entity. @author MyEclipse Persistence Tools
 */

public class Tabbackup implements java.io.Serializable {

	// Fields

	private BigDecimal backupid;
	private Tabrstask tabrstask;
	private String oripeople;
	private Timestamp oritime;
	private String oriroute;
	private String prepeople;
	private Timestamp pretime;
	private String preroute;
	private String resultpeople;
	private Timestamp resulttime;
	private String resultroute;

	// Constructors

	/** default constructor */
	public Tabbackup() {
	}

	/** minimal constructor */
	public Tabbackup(BigDecimal backupid) {
		this.backupid = backupid;
	}

	/** full constructor */
	public Tabbackup(BigDecimal backupid, Tabrstask tabrstask,
			String oripeople, Timestamp oritime, String oriroute,
			String prepeople, Timestamp pretime, String preroute,
			String resultpeople, Timestamp resulttime, String resultroute) {
		this.backupid = backupid;
		this.tabrstask = tabrstask;
		this.oripeople = oripeople;
		this.oritime = oritime;
		this.oriroute = oriroute;
		this.prepeople = prepeople;
		this.pretime = pretime;
		this.preroute = preroute;
		this.resultpeople = resultpeople;
		this.resulttime = resulttime;
		this.resultroute = resultroute;
	}

	// Property accessors

	public BigDecimal getBackupid() {
		return this.backupid;
	}

	public void setBackupid(BigDecimal backupid) {
		this.backupid = backupid;
	}

	public Tabrstask getTabrstask() {
		return this.tabrstask;
	}

	public void setTabrstask(Tabrstask tabrstask) {
		this.tabrstask = tabrstask;
	}

	public String getOripeople() {
		return this.oripeople;
	}

	public void setOripeople(String oripeople) {
		this.oripeople = oripeople;
	}

	public Timestamp getOritime() {
		return this.oritime;
	}

	public void setOritime(Timestamp oritime) {
		this.oritime = oritime;
	}

	public String getOriroute() {
		return this.oriroute;
	}

	public void setOriroute(String oriroute) {
		this.oriroute = oriroute;
	}

	public String getPrepeople() {
		return this.prepeople;
	}

	public void setPrepeople(String prepeople) {
		this.prepeople = prepeople;
	}

	public Timestamp getPretime() {
		return this.pretime;
	}

	public void setPretime(Timestamp pretime) {
		this.pretime = pretime;
	}

	public String getPreroute() {
		return this.preroute;
	}

	public void setPreroute(String preroute) {
		this.preroute = preroute;
	}

	public String getResultpeople() {
		return this.resultpeople;
	}

	public void setResultpeople(String resultpeople) {
		this.resultpeople = resultpeople;
	}

	public Timestamp getResulttime() {
		return this.resulttime;
	}

	public void setResulttime(Timestamp resulttime) {
		this.resulttime = resulttime;
	}

	public String getResultroute() {
		return this.resultroute;
	}

	public void setResultroute(String resultroute) {
		this.resultroute = resultroute;
	}

}