package bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Tabproresult entity. @author MyEclipse Persistence Tools
 */

public class Tabproresult implements java.io.Serializable {

	// Fields

	private BigDecimal proresultid;
	private Tabresultdata tabresultdata;
	private String propeople;
	private Timestamp prostarttime;
	private Timestamp proendtime;
	private String resultdatapeople;

	// Constructors

	/** default constructor */
	public Tabproresult() {
	}

	/** minimal constructor */
	public Tabproresult(BigDecimal proresultid) {
		this.proresultid = proresultid;
	}

	/** full constructor */
	public Tabproresult(BigDecimal proresultid, Tabresultdata tabresultdata,
			String propeople, Timestamp prostarttime, Timestamp proendtime,
			String resultdatapeople) {
		this.proresultid = proresultid;
		this.tabresultdata = tabresultdata;
		this.propeople = propeople;
		this.prostarttime = prostarttime;
		this.proendtime = proendtime;
		this.resultdatapeople = resultdatapeople;
	}

	// Property accessors

	public BigDecimal getProresultid() {
		return this.proresultid;
	}

	public void setProresultid(BigDecimal proresultid) {
		this.proresultid = proresultid;
	}

	public Tabresultdata getTabresultdata() {
		return this.tabresultdata;
	}

	public void setTabresultdata(Tabresultdata tabresultdata) {
		this.tabresultdata = tabresultdata;
	}

	public String getPropeople() {
		return this.propeople;
	}

	public void setPropeople(String propeople) {
		this.propeople = propeople;
	}

	public Timestamp getProstarttime() {
		return this.prostarttime;
	}

	public void setProstarttime(Timestamp prostarttime) {
		this.prostarttime = prostarttime;
	}

	public Timestamp getProendtime() {
		return this.proendtime;
	}

	public void setProendtime(Timestamp proendtime) {
		this.proendtime = proendtime;
	}

	public String getResultdatapeople() {
		return this.resultdatapeople;
	}

	public void setResultdatapeople(String resultdatapeople) {
		this.resultdatapeople = resultdatapeople;
	}

}