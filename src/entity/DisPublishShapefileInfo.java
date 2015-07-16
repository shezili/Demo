package entity;

public class DisPublishShapefileInfo {

	
	private String url;

	private String userName;

	private String password;
	
	private String workspace;
	
	private String storeName;
	
	private String layerName;

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

	@Override
	public String toString() {
		return "DisPublishShapefile [url=" + url + ", userName=" + userName
				+ ", password=" + password + ", workspace=" + workspace
				+ ", storeName=" + storeName + ", layerName=" + layerName + "]";
	}
	
	

}
