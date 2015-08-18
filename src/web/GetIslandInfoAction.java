package web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import entity.Island;

public class GetIslandInfoAction extends ActionSupport{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private String islandName;
	
	private String islandNo;
	
	private String result;
	
	/**
	 * 获取所有海岛资料文件夹名，将名称解析为    海岛名_编号，再一一进行比对
	 * @return
	 */
	public String getName(){
//		System.out.println(islandNo+"=====");
		List<Island> islands = this.getIslands();
		System.out.println(islands.size());
		for(int i=0;i<islands.size();i++){
			if(islands.get(i).getNumber().equals(islandNo)){
				result = islands.get(i).getName();
			}
		}
		System.out.println(getResult());
		return "success";
	}
	
	public String getNumber(){
//		System.out.println(islandName);
		List<Island> islands = this.getIslands();
		for(int i=0;i<islands.size();i++){
			if(islands.get(i).getName().equals(islandName)){
				result = islands.get(i).getNumber();
			}
		}
		System.out.println(getResult());
		return "success";
 	}
	
	/**
	 * 获取island_data文件夹下的所有海岛资料文件夹名称
	 * @return
	 */
	public List<Island> getIslands(){
		List<Island> islands = new ArrayList<Island>();
		String path = this.getClass().getClassLoader().getResource("")
				.getPath();
		path = path.replace("/classes", "").replace("%20", " ")
				.replace("/WEB-INF", "");
		path = path+"island_data";
//		System.out.println(path);
		File root = new File(path);
		String[] list = root.list();
//		System.out.println("====list==="+list.length);
		for(int i=0;i<list.length;i++){
			//System.out.println(list[i]);
			Island tempIsland = new Island();
			String[] temp1 = list[i].split("_");
			tempIsland.setName(temp1[0]);
			tempIsland.setNumber(temp1[1]);
//			System.out.println("temp1[0]"+temp1[0]);
//			System.out.println("temp1[1]"+temp1[1]);
//			System.out.println("length"+temp1.length);
			islands.add(tempIsland);
		}
//		for(Island i : islands){
//			System.out.println(islandName.toString());
//		}
		return islands;
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	
	
}
