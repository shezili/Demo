package entity;

/**
 * 调用 web service 发布  shape file 所需的信息 
 * @author Administrator
 *
 */

public class PublishShapefileInfo {

	private String url;
	
	private String userName;
	
	private String password;
	
	private  String shpPath;
	
	private  String fileName;
	
	private  String workspace;
	
	private  String storeName;
	
	private  String layerName;
	
	private String srs;
	
	private String isRepublish;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getShpPath() {
		return shpPath;
	}

	public void setShpPath(String shpPath) {
		this.shpPath = shpPath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getWorkspace() {
		return workspace;
	}

	public void setWorkspace(String workspace) {
		this.workspace = workspace;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getLayerName() {
		return layerName;
	}

	public void setLayerName(String layerName) {
		this.layerName = layerName;
	}

	public String getSrs() {
		return srs;
	}

	public void setSrs(String srs) {
		this.srs = srs;
	}

	public String getIsRepublish() {
		return isRepublish;
	}

	public void setIsRepublish(String isRepublish) {
		this.isRepublish = isRepublish;
	}

	@Override
	public String toString() {
		return "PublishShapefileInfo [url=" + url + ", userName=" + userName
				+ ", password=" + password + ", shpPath=" + shpPath
				+ ", fileName=" + fileName + ", workspace=" + workspace
				+ ", storeName=" + storeName + ", layerName=" + layerName
				+ ", srs=" + srs + ", isRepublish=" + isRepublish + "]";
	}
	
	
}
