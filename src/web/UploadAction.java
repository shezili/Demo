package web;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.ServletActionContext;

import java.io.*;
import java.util.UUID;

public class UploadAction extends ActionSupport {
	// ��װ����������
	private String islandName;

	private String islandNo;
	// ��װ�ϴ��ļ��������
	private File upload;
	// ��װ�ϴ��ļ����͵�����
	private String uploadContentType;
	// ��װ�ϴ��ļ���������
	private String uploadFileName;
	
	private String time;
	
	// ֱ����struts.xml�ļ������õ�����
	private String savePath;
	

	@Override
	public String execute() throws Exception {
		
		if ((getIslandName() == null) || (getIslandNo() == null)
				|| (getUpload() == null) ) {
			return ERROR;
		}
		String fileName;
		String path_text = getSavePath() + "\\" + islandName + "_" + islandNo;
		File file_1 = new File(path_text);
		if (!file_1.exists() && !file_1.isDirectory()) {
			file_1.mkdirs();
			System.out.println("file1 created");
		}
		String path_img = path_text + "\\image";
		File file_2 = new File(path_img);
		if (!file_2.exists() && !file_2.isDirectory()) {
			file_2.mkdirs();
			System.out.println("file2 created");
		}
		String usePath;
		UUID uuid = UUID.randomUUID();  
		String preFileName = uuid.toString();
		// System.out.println(getUploadContentType());
		if (getUploadContentType().equals("text/plain")) {
			fileName = preFileName+"_"+getUploadFileName();
			usePath = path_text;
		} else {
			//System.out.println("ԭʼ�ļ�����"+getUploadFileName());
			String[] temp = getUploadFileName().split("\\.");
			fileName = preFileName+"_"+getTime()+"_"+"secothumbnail"+"."+temp[1];  //������ԭ�����ļ�����uuid����
			usePath = path_img;
			//System.out.println("�޸ĺ���ļ���Ϊ��"+fileName);
		}
		System.out.println(toString());
		System.out.println("Ŀ���ļ���"+usePath);
		System.out.println(fileName);
		// �Է��������ļ������ַ��ԭ�ļ��������ϴ��ļ������
		FileOutputStream fos = new FileOutputStream(usePath + "\\"
				+ fileName);
		FileInputStream fis = new FileInputStream(getUpload());
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fis.read(buffer)) > 0) {
			fos.write(buffer, 0, len);
		}
		fos.flush();
		fos.close();
		fis.close();
		return SUCCESS;
	}

	// ����struts.xml�ļ�����ֵ�ķ���
	public void setSavePath(String value) {
		this.savePath = value;
	}

	// �����ϴ��ļ��ı���λ��
	private String getSavePath() throws Exception {
		return ServletActionContext.getServletContext().getRealPath(savePath);
	}

	public String getIslandName() {
		return islandName;
	}

	public void setIslandName(String islandName) {
		this.islandName = islandName;
	}

	public String getIslandNo() {
		return islandNo;
	}

	public void setIslandNo(String islandNo) {
		this.islandNo = islandNo;
	}

	// �ϴ��ļ���Ӧ�ļ����ݵ�setter��getter����
	public void setUpload(File upload) {
		this.upload = upload;
	}

	public File getUpload() {
		return (this.upload);
	}

	// �ϴ��ļ����ļ����͵�setter��getter����
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadContentType() {
		return (this.uploadContentType);
	}

	// �ϴ��ļ����ļ�����setter��getter����
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadFileName() {
		return (this.uploadFileName);
	}


	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "UploadAction [islandName=" + islandName + ", islandNo="
				+ islandNo + ", upload=" + upload + ", uploadContentType="
				+ uploadContentType + ", uploadFileName=" + uploadFileName
				+ ", time=" + time + ", savePath=" + savePath + "]";
	}





	
	

}