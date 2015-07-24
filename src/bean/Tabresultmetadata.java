package bean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Tabresultmetadata entity. @author MyEclipse Persistence Tools
 */

public class Tabresultmetadata implements java.io.Serializable {

	// Fields

	private BigDecimal resultdataId;
	private Tabdepartment tabdepartmentByProductDept;
	private Tabdepartment tabdepartmentByDistributeDept;
	private Tabdepartment tabdepartmentByMaintainDept;
	private String mapName;
	private String mapIdentifier;
	private String language;
	private String characterSet;
	private Timestamp creationDate;
	private Timestamp inputDate;
	private Timestamp latestmodiDate;
	private BigDecimal scale;
	private String dataVersion;
	private String savingAddr;
	private String resultQuality;
	private String history;
	private BigDecimal district;
	private Set tabresultdatas = new HashSet(0);
	private Set tabidentifierInformations = new HashSet(0);
	private Set tabspacialdescriptions = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tabresultmetadata() {
	}

	/** minimal constructor */
	public Tabresultmetadata(BigDecimal resultdataId, String mapName,
			Timestamp inputDate, Timestamp latestmodiDate) {
		this.resultdataId = resultdataId;
		this.mapName = mapName;
		this.inputDate = inputDate;
		this.latestmodiDate = latestmodiDate;
	}

	/** full constructor */
	public Tabresultmetadata(BigDecimal resultdataId,
			Tabdepartment tabdepartmentByProductDept,
			Tabdepartment tabdepartmentByDistributeDept,
			Tabdepartment tabdepartmentByMaintainDept, String mapName,
			String mapIdentifier, String language, String characterSet,
			Timestamp creationDate, Timestamp inputDate,
			Timestamp latestmodiDate, BigDecimal scale, String dataVersion,
			String savingAddr, String resultQuality, String history,
			BigDecimal district, Set tabresultdatas,
			Set tabidentifierInformations, Set tabspacialdescriptions) {
		this.resultdataId = resultdataId;
		this.tabdepartmentByProductDept = tabdepartmentByProductDept;
		this.tabdepartmentByDistributeDept = tabdepartmentByDistributeDept;
		this.tabdepartmentByMaintainDept = tabdepartmentByMaintainDept;
		this.mapName = mapName;
		this.mapIdentifier = mapIdentifier;
		this.language = language;
		this.characterSet = characterSet;
		this.creationDate = creationDate;
		this.inputDate = inputDate;
		this.latestmodiDate = latestmodiDate;
		this.scale = scale;
		this.dataVersion = dataVersion;
		this.savingAddr = savingAddr;
		this.resultQuality = resultQuality;
		this.history = history;
		this.district = district;
		this.tabresultdatas = tabresultdatas;
		this.tabidentifierInformations = tabidentifierInformations;
		this.tabspacialdescriptions = tabspacialdescriptions;
	}

	// Property accessors

	public BigDecimal getResultdataId() {
		return this.resultdataId;
	}

	public void setResultdataId(BigDecimal resultdataId) {
		this.resultdataId = resultdataId;
	}

	public Tabdepartment getTabdepartmentByProductDept() {
		return this.tabdepartmentByProductDept;
	}

	public void setTabdepartmentByProductDept(
			Tabdepartment tabdepartmentByProductDept) {
		this.tabdepartmentByProductDept = tabdepartmentByProductDept;
	}

	public Tabdepartment getTabdepartmentByDistributeDept() {
		return this.tabdepartmentByDistributeDept;
	}

	public void setTabdepartmentByDistributeDept(
			Tabdepartment tabdepartmentByDistributeDept) {
		this.tabdepartmentByDistributeDept = tabdepartmentByDistributeDept;
	}

	public Tabdepartment getTabdepartmentByMaintainDept() {
		return this.tabdepartmentByMaintainDept;
	}

	public void setTabdepartmentByMaintainDept(
			Tabdepartment tabdepartmentByMaintainDept) {
		this.tabdepartmentByMaintainDept = tabdepartmentByMaintainDept;
	}

	public String getMapName() {
		return this.mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public String getMapIdentifier() {
		return this.mapIdentifier;
	}

	public void setMapIdentifier(String mapIdentifier) {
		this.mapIdentifier = mapIdentifier;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCharacterSet() {
		return this.characterSet;
	}

	public void setCharacterSet(String characterSet) {
		this.characterSet = characterSet;
	}

	public Timestamp getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public Timestamp getInputDate() {
		return this.inputDate;
	}

	public void setInputDate(Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	public Timestamp getLatestmodiDate() {
		return this.latestmodiDate;
	}

	public void setLatestmodiDate(Timestamp latestmodiDate) {
		this.latestmodiDate = latestmodiDate;
	}

	public BigDecimal getScale() {
		return this.scale;
	}

	public void setScale(BigDecimal scale) {
		this.scale = scale;
	}

	public String getDataVersion() {
		return this.dataVersion;
	}

	public void setDataVersion(String dataVersion) {
		this.dataVersion = dataVersion;
	}

	public String getSavingAddr() {
		return this.savingAddr;
	}

	public void setSavingAddr(String savingAddr) {
		this.savingAddr = savingAddr;
	}

	public String getResultQuality() {
		return this.resultQuality;
	}

	public void setResultQuality(String resultQuality) {
		this.resultQuality = resultQuality;
	}

	public String getHistory() {
		return this.history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public BigDecimal getDistrict() {
		return this.district;
	}

	public void setDistrict(BigDecimal district) {
		this.district = district;
	}

	public Set getTabresultdatas() {
		return this.tabresultdatas;
	}

	public void setTabresultdatas(Set tabresultdatas) {
		this.tabresultdatas = tabresultdatas;
	}

	public Set getTabidentifierInformations() {
		return this.tabidentifierInformations;
	}

	public void setTabidentifierInformations(Set tabidentifierInformations) {
		this.tabidentifierInformations = tabidentifierInformations;
	}

	public Set getTabspacialdescriptions() {
		return this.tabspacialdescriptions;
	}

	public void setTabspacialdescriptions(Set tabspacialdescriptions) {
		this.tabspacialdescriptions = tabspacialdescriptions;
	}

}