package entity;

/**
 * �����ļ��Լ�ͼƬԤ������Ҫ����Ϣ,ֻ��ͼƬ�ļ���previewInfo
 * @author Administrator
 *
 */

public class FileInfo {
	
	private String simpleName;
	
	private String fullName;
	
	private String previewInfo;

	public String getSimpleName() {
		return simpleName;
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPreviewInfo() {
		return previewInfo;
	}

	public void setPreviewInfo(String previewInfo) {
		this.previewInfo = previewInfo;
	}

	@Override
	public String toString() {
		return "FileInfo [simpleName=" + simpleName + ", fullName=" + fullName
				+ ", previewInfo=" + previewInfo + "]";
	}
	
	
}
