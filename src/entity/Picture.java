package entity;

/**
 * 图片文件信息，包括：
 * 名字前部分
 * 类型（是否为主缩略图）
 * 上传时间
 * 		以上三项之间以下划线相连构成图片文件的名称
 * @author Administrator
 *
 */
public class Picture {
	
	private String preName;
	
	private String type;
	
	private String time;
	
	private String url;
	
	private String fullPath;

	public String getPreName() {
		return preName;
	}

	public void setPreName(String preName) {
		this.preName = preName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}
	
	
	
	
}
