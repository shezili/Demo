package action;

import org.springframework.beans.factory.annotation.Autowired;

import service.LoginService;


public class LoginAction {

	@Autowired
	private LoginService loginService;

	private String name;
	
	private String password;
	
	public String getName(){
		System.out.println("name:"+name+"   "+"password:"+password);
		String info = loginService.getUserName(name,password);
		System.out.println(info);
		return "ok";
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
