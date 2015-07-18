package bean;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Tabresultdata entity. @author MyEclipse Persistence Tools
 */

public class Tabresultdata implements java.io.Serializable {

	// Fields

	private BigDecimal resultdataId;
	private Tabresultmetadata tabresultmetadata;
	private Tabsurveydistrict tabsurveydistrict;
	private Tabdatatype tabdatatype;
	private Tabresultdatafolder tabresultdatafolder;
	private String path;
	private String filename;
	private String extension;
	private BigDecimal ismosaic;
	private String state;
	private String resolution;
	private Set tabresultdatataskmaps = new HashSet(0);
	private Set tabproresults = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tabresultdata() {
	}

	/** minimal constructor */
	public Tabresultdata(BigDecimal resultdataId,
			Tabresultmetadata tabresultmetadata) {
		this.resultdataId = resultdataId;
		this.tabresultmetadata = tabresultmetadata;
	}

	/** full constructor */
	public Tabresultdata(BigDecimal resultdataId,
			Tabresultmetadata tabresultmetadata,
			Tabsurveydistrict tabsurveydistrict, Tabdatatype tabdatatype,
			Tabresultdatafolder tabresultdatafolder, String path,
			String filename, String extension, BigDecimal ismosaic,
			String state, String resolution, Set tabresultdatataskmaps,
			Set tabproresults) {
		this.resultdataId = resultdataId;
		this.tabresultmetadata = tabresultmetadata;
		this.tabsurveydistrict = tabsurveydistrict;
		this.tabdatatype = tabdatatype;
		this.tabresultdatafolder = tabresultdatafolder;
		this.path = path;
		this.filename = filename;
		this.extension = extension;
		this.ismosaic = ismosaic;
		this.state = state;
		this.resolution = resolution;
		this.tabresultdatataskmaps = tabresultdatataskmaps;
		this.tabproresults = tabproresults;
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

	public Tabsurveydistrict getTabsurveydistrict() {
		return this.tabsurveydistrict;
	}

	public void setTabsurveydistrict(Tabsurveydistrict tabsurveydistrict) {
		this.tabsurveydistrict = tabsurveydistrict;
	}

	public Tabdatatype getTabdatatype() {
		return this.tabdatatype;
	}

	public void setTabdatatype(Tabdatatype tabdatatype) {
		this.tabdatatype = tabdatatype;
	}

	public Tabresultdatafolder getTabresultdatafolder() {
		return this.tabresultdatafolder;
	}

	public void setTabresultdatafolder(Tabresultdatafolder tabresultdatafolder) {
		this.tabresultdatafolder = tabresultdatafolder;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getExtension() {
		return this.extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public BigDecimal getIsmosaic() {
		return this.ismosaic;
	}

	public void setIsmosaic(BigDecimal ismosaic) {
		this.ismosaic = ismosaic;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getResolution() {
		return this.resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public Set getTabresultdatataskmaps() {
		return this.tabresultdatataskmaps;
	}

	public void setTabresultdatataskmaps(Set tabresultdatataskmaps) {
		this.tabresultdatataskmaps = tabresultdatataskmaps;
	}

	public Set getTabproresults() {
		return this.tabproresults;
	}

	public void setTabproresults(Set tabproresults) {
		this.tabproresults = tabproresults;
	}

}