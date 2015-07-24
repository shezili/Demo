package action;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;

import entity.MyThread;
import service.LoginService;


public class LoginAction {

	@Autowired
	private LoginService loginService;

	private String name;
	
	private String password;
	
	public String login(){
		System.out.println("name:"+name+"   "+"password:"+password);
		String info = loginService.getUserName(name,password);
		System.out.println(info);
		return "loginsuccess";
	}
	
	public String logout(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		session.put("user", null);
		return "logoutsuccess";
	}
	
	@SuppressWarnings("static-access")
	public String jump(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		String name =  (String) session.getAttribute("user");
		String password =  (String) session.getAttribute("password");
		MyThread thread = new MyThread(name, password);
		File file = new File("D:/loginInfo.txt");
		System.out.println("文件是否存在？"+file.exists());
		while(file.exists()){
			System.out.println(name+"的登录操作暂缓执行");
			try {
				thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		thread.start();
		return "jumpsuccess";
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
