package bean;

import java.math.BigDecimal;

/**
 * TabidentifierInformation entity. @author MyEclipse Persistence Tools
 */

public class TabidentifierInformation implements java.io.Serializable {

	// Fields

	private BigDecimal resultdataId;
	private Tabresultmetadata tabresultmetadata;
	private String identifiername;
	private String alias;
	private String abstract_;
	private String language;
	private BigDecimal state;
	private String keyword;
	private String archivesnum;
	private String archivestype;
	private BigDecimal quantity;
	private BigDecimal resultquantity;
	private String carrierstyle;
	private String securitylevel;

	// Constructors

	/** default constructor */
	public TabidentifierInformation() {
	}

	/** minimal constructor */
	public TabidentifierInformation(BigDecimal resultdataId,
			Tabresultmetadata tabresultmetadata, String identifiername) {
		this.resultdataId = resultdataId;
		this.tabresultmetadata = tabresultmetadata;
		this.identifiername = identifiername;
	}

	/** full constructor */
	public TabidentifierInformation(BigDecimal resultdataId,
			Tabresultmetadata tabresultmetadata, String identifiername,
			String alias, String abstract_, String language, BigDecimal state,
			String keyword, String archivesnum, String archivestype,
			BigDecimal quantity, BigDecimal resultquantity,
			String carrierstyle, String securitylevel) {
		this.resultdataId = resultdataId;
		this.tabresultmetadata = tabresultmetadata;
		this.identifiername = identifiername;
		this.alias = alias;
		this.abstract_ = abstract_;
		this.language = language;
		this.state = state;
		this.keyword = keyword;
		this.archivesnum = archivesnum;
		this.archivestype = archivestype;
		this.quantity = quantity;
		this.resultquantity = resultquantity;
		this.carrierstyle = carrierstyle;
		this.securitylevel = securitylevel;
	}

	// Property accessors

	public BigDecimal getResultdataId() {
		return this.resultdataId;
	}

	public void setResultdataId(BigDecimal resultdataId) {
		this.resultdataId = resultdataId;
	}

	public Tabresultmetadata getTabresultmetadata() {
		return this.tabresultmetadata;
	}

	public void setTabresultmetadata(Tabresultmetadata tabresultmetadata) {
		this.tabresultmetadata = tabresultmetadata;
	}

	public String getIdentifiername() {
		return this.identifiername;
	}

	public void setIdentifiername(String identifiername) {
		this.identifiername = identifiername;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAbstract_() {
		return this.abstract_;
	}

	public void setAbstract_(String abstract_) {
		this.abstract_ = abstract_;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public BigDecimal getState() {
		return this.state;
	}

	public void setState(BigDecimal state) {
		this.state = state;
	}

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getArchivesnum() {
		return this.archivesnum;
	}

	public void setArchivesnum(String archivesnum) {
		this.archivesnum = archivesnum;
	}

	public String getArchivestype() {
		return this.archivestype;
	}

	public void setArchivestype(String archivestype) {
		this.archivestype = archivestype;
	}

	public BigDecimal getQuantity() {
		return this.quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getResultquantity() {
		return this.resultquantity;
	}

	public void setResultquantity(BigDecimal resultquantity) {
		this.resultquantity = resultquantity;
	}

	public String getCarrierstyle() {
		return this.carrierstyle;
	}

	public void setCarrierstyle(String carrierstyle) {
		this.carrierstyle = carrierstyle;
	}

	public String getSecuritylevel() {
		return this.securitylevel;
	}

	public void setSecuritylevel(String securitylevel) {
		this.securitylevel = securitylevel;
	}

}