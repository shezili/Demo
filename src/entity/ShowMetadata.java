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
	
	//Ԫ���ݲ�ѯ  ҳ�����������
	
	private BigDecimal scale;    //	�����߷�ĸ/�ֱ���(m)  Tabresultmetadata
	private String distributeDeptName;	//	�ַ���λ  Tabresultmetadata->Tabdepartment
	
	private String mapName;	//	���ơ��ɹ���������	Tabresultmetadata
	private String dataTypeDescr;   //    �����ɹ���Դ���͡��ɹ���������	tabresultdata->Tabdatatype
	
	//���ݹ���----�ɹ�����tabҳ��������
	private  String state;				//	�ɹ�����״̬	tabresultdata
	private String resolution;		//	�ֱ���/�������(��) 	tabresultdata
	private String prostarttime;	//	������ʼ����		Tabresultmetadata	->tabresultdata ->Tabproresult
	private String proendtime;		//	������������		Tabresultmetadata ->tabresultdata ->Tabproresult
	private String resultdatapeople;    //		������	Tabresultmetadata -> tabresultdata->Tabproresult
	
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
