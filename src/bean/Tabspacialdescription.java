package bean;

import java.math.BigDecimal;

/**
 * Tabspacialdescription entity. @author MyEclipse Persistence Tools
 */

public class Tabspacialdescription implements java.io.Serializable {

	// Fields

	private BigDecimal resultdataId;
	private Tabresultmetadata tabresultmetadata;
	private Double westLongitude;
	private Double eastLongitude;
	private Double southLatitude;
	private Double northLatitude;
	private String regionName;
	private Double area;
	private String projectionType;
	private String ellipsoid;
	private String coordinateType;
	private String projectionParameter;
	private String ellipsoidParameter;

	// Constructors

	/** default constructor */
	public Tabspacialdescription() {
	}

	/** minimal constructor */
	public Tabspacialdescription(BigDecimal resultdataId,
			Tabresultmetadata tabresultmetadata) {
		this.resultdataId = resultdataId;
		this.tabresultmetadata = tabresultmetadata;
	}

	/** full constructor */
	public Tabspacialdescription(BigDecimal resultdataId,
			Tabresultmetadata tabresultmetadata, Double westLongitude,
			Double eastLongitude, Double southLatitude, Double northLatitude,
			String regionName, Double area, String projectionType,
			String ellipsoid, String coordinateType,
			String projectionParameter, String ellipsoidParameter) {
		this.resultdataId = resultdataId;
		this.tabresultmetadata = tabresultmetadata;
		this.westLongitude = westLongitude;
		this.eastLongitude = eastLongitude;
		this.southLatitude = southLatitude;
		this.northLatitude = northLatitude;
		this.regionName = regionName;
		this.area = area;
		this.projectionType = projectionType;
		this.ellipsoid = ellipsoid;
		this.coordinateType = coordinateType;
		this.projectionParameter = projectionParameter;
		this.ellipsoidParameter = ellipsoidParameter;
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

	public Double getWestLongitude() {
		return this.westLongitude;
	}

	public void setWestLongitude(Double westLongitude) {
		this.westLongitude = westLongitude;
	}

	public Double getEastLongitude() {
		return this.eastLongitude;
	}

	public void setEastLongitude(Double eastLongitude) {
		this.eastLongitude = eastLongitude;
	}

	public Double getSouthLatitude() {
		return this.southLatitude;
	}

	public void setSouthLatitude(Double southLatitude) {
		this.southLatitude = southLatitude;
	}

	public Double getNorthLatitude() {
		return this.northLatitude;
	}

	public void setNorthLatitude(Double northLatitude) {
		this.northLatitude = northLatitude;
	}

	public String getRegionName() {
		return this.regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Double getArea() {
		return this.area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public String getProjectionType() {
		return this.projectionType;
	}

	public void setProjectionType(String projectionType) {
		this.projectionType = projectionType;
	}

	public String getEllipsoid() {
		return this.ellipsoid;
	}

	public void setEllipsoid(String ellipsoid) {
		this.ellipsoid = ellipsoid;
	}

	public String getCoordinateType() {
		return this.coordinateType;
	}

	public void setCoordinateType(String coordinateType) {
		this.coordinateType = coordinateType;
	}

	public String getProjectionParameter() {
		return this.projectionParameter;
	}

	public void setProjectionParameter(String projectionParameter) {
		this.projectionParameter = projectionParameter;
	}

	public String getEllipsoidParameter() {
		return this.ellipsoidParameter;
	}

	public void setEllipsoidParameter(String ellipsoidParameter) {
		this.ellipsoidParameter = ellipsoidParameter;
	}

}