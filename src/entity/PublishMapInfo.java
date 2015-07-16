package entity;

/**
 * 调用webservice发布栅格地图所需要的信息
 * @author Administrator
 *
 */

public class PublishMapInfo {
	
	private String url;
	
	private String userName;
	
	private String password;
	
	private String pathName;
	
	private String workspace;
	
	private String storeName;
	
	private String coverageName;
	
	private String format;
	
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

	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
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

	public String getCoverageName() {
		return coverageName;
	}

	public void setCoverageName(String coverageName) {
		this.coverageName = coverageName;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getIsRepublish() {
		return isRepublish;
	}

	public void setIsRepublish(String isRepublish) {
		this.isRepublish = isRepublish;
	}

	@Override
	public String toString() {
		return "PublishMapInfo [url=" + url + ", userName=" + userName
				+ ", password=" + password + ", pathName=" + pathName
				+ ", workspace=" + workspace + ", storeName=" + storeName
				+ ", coverageName=" + coverageName + ", format=" + format
				+ ", isRepublish=" + isRepublish + "]";
	}
	
	
	
	
}
