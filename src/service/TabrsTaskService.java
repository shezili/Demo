package service;

import org.springframework.beans.factory.annotation.Autowired;

import dao.TabrstaskDAO;

/**
 * 架次数据的操作
 * @author Administrator
 *
 */
public class TabrsTaskService {

	@Autowired
	private TabrstaskDAO tabrstaskDAO;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	public TabrstaskDAO getTabrstaskDAO() {
		return tabrstaskDAO;
	}

	public void setTabrstaskDAO(TabrstaskDAO tabrstaskDAO) {
		this.tabrstaskDAO = tabrstaskDAO;
	}
	
	

}
