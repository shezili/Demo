package entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import bean.Tabdepartment;

public class ShowMetadata {

	private BigDecimal resultdataId;
	
//	private String mapIdentifier;
//	private String language;
//	private String characterSet;
//	private String creationDate;
//	private String inputDate;
//	private String latestmodiDate;
//	private String dataVersion;
//	private String savingAddr;
//	private String resultQuality;
//	private String history;
//	private BigDecimal district;
	
	//元数据查询  页面所需的数据
	
	private BigDecimal scale;    //	比例尺分母/分辨率(m)  Tabresultmetadata
	private String distributeDeptName;	//	分发单位  Tabresultmetadata->Tabdepartment
	
	private String mapName;	//	名称、成果数据名称	Tabresultmetadata
	private String dataTypeDescr;   //    测量成果资源类型、成果数据类型	tabresultdata->Tabdatatype
	
	//数据管理----成果数据tab页所需数据
	private  String state;				//	成果数据状态	tabresultdata
	private String resolution;		//	分辨率/采样间距(米) 	tabresultdata
	private String prostarttime;	//	生产开始日期		Tabresultmetadata	->tabresultdata ->Tabproresult
	private String proendtime;		//	生产结束日期		Tabresultmetadata ->tabresultdata ->Tabproresult
	private String resultdatapeople;    //		生产者	Tabresultmetadata -> tabresultdata->Tabproresult
	
	public BigDecimal getResultdataId() {
		return resultdataId;
	}
	public void setResultdataId(BigDecimal resultdataId) {
		this.resultdataId = resultdataId;
	}
	public BigDecimal getScale() {
		return scale;
	}
	public void setScale(BigDecimal scale) {
		this.scale = scale;
	}
	public String getDistributeDeptName() {
		return distributeDeptName;
	}
	public void setDistributeDeptName(String distributeDeptName) {
		this.distributeDeptName = distributeDeptName;
	}
	public String getMapName() {
		return mapName;
	}
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	public String getDataTypeDescr() {
		return dataTypeDescr;
	}
	public void setDataTypeDescr(String dataTypeDescr) {
		this.dataTypeDescr = dataTypeDescr;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getProstarttime() {
		return prostarttime;
	}
	public void setProstarttime(String prostarttime) {
		this.prostarttime = prostarttime;
	}
	public String getProendtime() {
		return proendtime;
	}
	public void setProendtime(String proendtime) {
		this.proendtime = proendtime;
	}
	public String getResultdatapeople() {
		return resultdatapeople;
	}
	public void setResultdatapeople(String resultdatapeople) {
		this.resultdatapeople = resultdatapeople;
	}	

}
