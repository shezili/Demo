package entity;

import java.util.Properties;

public class ServerConfig {

	private static Properties config = null;
	
	private static ServerConfig instance = null;
	
	
	public static ServerConfig getInstance(){
		if(instance == null){
			syncInit();
		}
		return instance;
	}
	
	public Properties getConfig(){
		return config;
	}
	
	public void addParam(String key,String value){
		config.setProperty(key, value);
	}
	
	private synchronized static void syncInit(){
		if(instance == null){
			instance = new ServerConfig();
		}
	}
	
	
	private ServerConfig(){
		config = new Properties();
	}
}
