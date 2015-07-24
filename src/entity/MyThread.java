package entity;

import java.io.FileWriter;
import java.io.IOException;

public class MyThread extends Thread{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private String name;
	
	private String password;
	
	public MyThread(String name,String password){
		this.name = name;
		this.password = password;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public void run() {
		System.out.println("Ïß³Ìstarted"+name+"/n"+password);
		FileWriter writer;
		try {
			writer = new FileWriter("D:/loginInfo.txt");
			writer.write(name);
			writer.write(":");
			writer.write(password);
			writer.flush();
			writer.close();
			System.out.println(" writed ended");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

}
