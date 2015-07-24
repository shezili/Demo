package web;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.FileInfo;
import entity.Record;
import net.sf.json.JSONObject;

public class OperationFileAction {
	
	private String currentDirectory;

	private String parentDirectory;

	private String deleteDirectory;
	
	private JSONObject result;

	public static void main(String[] args) {
		Test test = new Test();
		String path = test.getClass().getClassLoader().getResource("")
				.getPath();
		path = path.replace("/WebRoot/WEB-INF/classes", "").replace("%20", " ");
		path = path + "island_data";
		//System.out.println(path);
	}

	private String rows;

	private String page;
	
	public String browseFile() {
		if ((getCurrentDirectory() == null)||(getCurrentDirectory().equals(""))) {
			String path = this.getClass().getClassLoader().getResource("")
					.getPath();
			path = path.replace("/WEB-INF/classes", "").replace("%20", " ");
			path = path + "island_data";
			setCurrentDirectory(path);
		} 
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		setFiles(getCurrentDirectory(),intPage,number);
		return "success";

	}

	public String deleteFile() {
		File currentFile = new File(getDeleteDirectory());
		setParentDirectory(currentFile.getParent());
		setParentDirectory(parentDirectory.replace("\\", "/"));
		setParentDirectory("/" + getParentDirectory());
		delFolder(getDeleteDirectory());
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("parent", getParentDirectory());
		result = JSONObject.fromObject(jsonMap);
		return "success";
	}

	public void setFiles(String fileName,int intPage,int number) {
		System.out.println("传入的文件名"+fileName);
		setCurrentDirectory(fileName);
		
		List<FileInfo> fileInfos = new ArrayList<FileInfo>();
		File currentFile = new File(getCurrentDirectory());
		setParentDirectory(currentFile.getParent());
		setParentDirectory(getParentDirectory().replace("\\", "/"));
		setParentDirectory("/" + getParentDirectory());
		if (currentFile.isFile()) {
			setCurrentDirectory(getParentDirectory());
			currentFile = new File(getCurrentDirectory());
			setParentDirectory(currentFile.getParent());
			setParentDirectory(parentDirectory.replace("\\", "/"));
			setParentDirectory("/" + getParentDirectory());
		}
		String[] temp = currentFile.list();
		for (int i = 0; i < temp.length; i++) {
			System.out.println(temp[i]);
			FileInfo tempFileInfo = new FileInfo();
			tempFileInfo.setSimpleName(temp[i]);
			tempFileInfo.setFullName(getCurrentDirectory()+"/"+temp[i]);
			if(getCurrentDirectory().contains("image")){
				tempFileInfo.setPreviewInfo("../"+getCurrentDirectory().substring(getCurrentDirectory().indexOf("island_data"))+"/"+temp[i]);
			}
			fileInfos.add(tempFileInfo);
		}
		for(FileInfo f:fileInfos){
			System.out.println(f.toString());
		}
		List<FileInfo> pageList = findFileInfos(intPage, number, fileInfos);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", fileInfos.size());
		jsonMap.put("rows", pageList);
		jsonMap.put("parent", getParentDirectory());
		jsonMap.put("current", getCurrentDirectory());
		result = JSONObject.fromObject(jsonMap);
	}
	
	public List<FileInfo> findFileInfos(int pageNo,int pageSize,List<FileInfo> result){		
		List<FileInfo> list=new ArrayList<FileInfo>();
		int bottom = pageNo*pageSize;
		if(bottom>result.size()){
			bottom = result.size();
		}
		for(int i = (pageNo-1)*pageSize;i<bottom;i++){
			list.add(result.get(i));
		}
		return list;
	}


	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除指定文件夹下所有文件
	// param path 文件夹完整绝对路径
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}

	public String getCurrentDirectory() {
		return currentDirectory;
	}

	public void setCurrentDirectory(String currentDirectory) {
		this.currentDirectory = currentDirectory;
	}

	public String getParentDirectory() {
		return parentDirectory;
	}

	public void setParentDirectory(String parentDirectory) {
		this.parentDirectory = parentDirectory;
	}

	public String getDeleteDirectory() {
		return deleteDirectory;
	}

	public void setDeleteDirectory(String deleteDirectory) {
		this.deleteDirectory = deleteDirectory;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "OperationFileAction [currentDirectory=" + currentDirectory
				+ ", parentDirectory=" + parentDirectory + ", deleteDirectory="
				+ deleteDirectory + ", result=" + result + ", rows=" + rows
				+ ", page=" + page + "]";
	}
	
	

	
}
