package com.henc.geoserver;

public class GeoserverOperationResult {

	private GeoserverOperationStatus status;
	private String info;
	private String desUrl;
	private String dataUrl;

	public GeoserverOperationResult(GeoserverOperationStatus status, String info) {
		this.status = status;
		this.info = info;
	}
	
	public GeoserverOperationResult(GeoserverOperationStatus status, String desUrl,String dataUrl) {
		this.status = status;
		this.desUrl = desUrl;
		this.dataUrl = dataUrl;
	}

	public GeoserverOperationStatus getStatus() {
		return status;
	}

	public void setStatus(GeoserverOperationStatus status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getDesUrl() {
		return desUrl;
	}

	public void setDesUrl(String desUrl) {
		this.desUrl = desUrl;
	}

	public String getDataUrl() {
		return dataUrl;
	}

	public void setWDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}

}
