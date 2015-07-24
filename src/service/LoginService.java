package service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.TabUser;

import com.opensymphony.xwork2.ActionContext;

import dao.TabUserDAO;

@Service
public class LoginService {
	@Autowired
	private TabUserDAO tabUserDAO;

	public String getUserName(String name, String password) {
		System.out.println("getUserName started");
		System.out.println("name:" + name + "   " + "password:" + password);
		@SuppressWarnings("unchecked")
		List<TabUser> tables = tabUserDAO.findByUsername(name);
		System.out.println("查询完毕");
		if (tables.size() == 0) {
			System.out.println("1");
			return "user not exist";
		} else if (tables.size() != 1) {
			System.out.println("2");
			return "not only one";
		} else {
			System.out.println("3");
			TabUser table = tables.get(0);
			System.out.println("密码是" + table.getPassword() + "输入是" + password);
			System.out.println(table.getTabRole());
			if (table.getPassword().equals(password)) {
				ActionContext context = ActionContext.getContext();
				Map<String, Object> session = context.getSession();
				session.put("user", name);
				session.put("password", password);
				return "login success";
			}
			return "login failed";
		}
	}

	public TabUserDAO getTabUserDAO() {
		return tabUserDAO;
	}

	public void setTabUserDAO(TabUserDAO tabUserDAO) {
		this.tabUserDAO = tabUserDAO;
	}



	

}
