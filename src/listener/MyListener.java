package listener;

import java.util.Enumeration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import entity.ServerConfig;

public class MyListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("MyListener desdroyed");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		Enumeration<String> params = context.getInitParameterNames();
		ServerConfig config = ServerConfig.getInstance();
		while(params.hasMoreElements()){
			String key = params.nextElement();
			String value = context.getInitParameter(key);
			config.addParam(key, value);
		}
	}

}
