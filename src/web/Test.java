package web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import entity.Island;


public class Test extends ActionSupport{
	



	public static void main(String[] args) {
		Test test = new Test();
		String path = test.getClass().getClassLoader().getResource("")
				.getPath();
		path = path.replace("/classes", "").replace("%20", " ")
				.replace("/WEB-INF", "");
		
		path = path+"island_data";
		
		System.out.println(path);
		File root = new File(path);
		String[] list = root.list();
		List<Island> islands = new ArrayList<Island>();
		for(int i=0;i<list.length;i++){
			System.out.println(list[i]);
			Island tempIsland = new Island();
			String[] temp1 = list[i].split("_");
			tempIsland.setName(temp1[0]);
			tempIsland.setNumber(temp1[1]);
//			System.out.println("temp1[0]"+temp1[0]);
//			System.out.println("temp1[1]"+temp1[1]);
//			System.out.println("length"+temp1.length);
			islands.add(tempIsland);
		}
		for(int j=0;j<islands.size();j++){
			
			System.out.println(islands.get(j).toString());
		}
	}
	
	public String execute() throws Exception{
		
		return SUCCESS;
	}

	

	

	
}
