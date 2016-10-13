package sy.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import sy.service.RepairServiceI;

//在系统启动时，修复数据库
public class RepairListener implements ServletContextListener,ServletContextAttributeListener
	,HttpSessionListener,HttpSessionActivationListener,HttpSessionAttributeListener,HttpSessionBindingListener,ServletRequestListener,ServletRequestAttributeListener{

	private static final Logger logger = Logger.getLogger(RepairListener.class);
	
	private static ApplicationContext ctx = null;
	
	
	@Override
	public void attributeRemoved(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	 //修复数据库
	public void contextInitialized(ServletContextEvent svt) {
		// TODO Auto-generated method stub
		logger.info("修复数据库");
		ctx = WebApplicationContextUtils.getWebApplicationContext(svt.getServletContext());
		RepairServiceI repairServiceI = (RepairServiceI) ctx.getBean("repairService");
		repairServiceI.repair();
		
	}

	@Override
	public void attributeAdded(ServletRequestAttributeEvent arg0) {
		
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent arg0) {
		
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent arg0) {
		
	}

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		
	}

	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		
	}

	@Override
	public void sessionDidActivate(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionWillPassivate(HttpSessionEvent arg0) {
		
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		
	}
	@Override
	public void attributeAdded(ServletContextAttributeEvent arg0) {
		
	}
	
	
	

}
