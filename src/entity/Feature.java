package entity;

/**
 * 定义feature所有的属性
 * @author Administrator
 *
 */
public class Feature {
	
	private String layerName;
	
	private String fid;
	
	private String islandId;
	
	private String islandName;
	
	private String thumbnail;

	public String getLayerName() {
		return layerName;
	}

	public void setLayerName(String layerName) {
		this.layerName = layerName;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getIslandId() {
		return islandId;
	}

	public void setIslandId(String islandId) {
		this.islandId = islandId;
	}

	public String getIslandName() {
		return islandName;
	}

	public void setIslandName(String islandName) {
		this.islandName = islandName;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	@Override
	public String toString() {
		return "Feature [layerName=" + layerName + ", fid=" + fid
				+ ", islandId=" + islandId + ", islandName=" + islandName
				+ ", thumbnail=" + thumbnail + "]";
	}
	
	
	
	
	
	
}
