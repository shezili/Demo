package web;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.ServletActionContext;

import java.io.*;
import java.util.UUID;

public class UploadAction extends ActionSupport {
	// 封装海岛的属性
	private String islandName;

	private String islandNo;
	// 封装上传文件域的属性
	private File upload;
	// 封装上传文件类型的属性
	private String uploadContentType;
	// 封装上传文件名的属性
	private String uploadFileName;
	
	private String time;
	
	// 直接在struts.xml文件中配置的属性
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
			//System.out.println("原始文件名："+getUploadFileName());
			String[] temp = getUploadFileName().split("\\.");
			fileName = preFileName+"_"+getTime()+"_"+"secothumbnail"+"."+temp[1];  //舍弃了原来的文件名用uuid代替
			usePath = path_img;
			//System.out.println("修改后的文件名为："+fileName);
		}
		System.out.println(toString());
		System.out.println("目标文件夹"+usePath);
		System.out.println(fileName);
		// 以服务器的文件保存地址和原文件名建立上传文件输出流
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

	// 接受struts.xml文件配置值的方法
	public void setSavePath(String value) {
		this.savePath = value;
	}

	// 返回上传文件的保存位置
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

	// 上传文件对应文件内容的setter和getter方法
	public void setUpload(File upload) {
		this.upload = upload;
	}

	public File getUpload() {
		return (this.upload);
	}

	// 上传文件的文件类型的setter和getter方法
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadContentType() {
		return (this.uploadContentType);
	}

	// 上传文件的文件名的setter和getter方法
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