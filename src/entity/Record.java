package entity;

/**
 * 图层的信息包括：
 * id
 * 名称
 * 发布时间
 * @author she
 * 
 */
public class Record {

	private String id;

	private String name;

	private String dateStr;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
}
