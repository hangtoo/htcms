package common.component.application.user;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

import common.component.application.user.service.UserService;

public class ContextListener extends ContextLoaderListener
 {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		//super.contextDestroyed(event);
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		//初始化酒店列表
		
		//super.contextInitialized(event);
		UserService service=(UserService)getContextLoader().getCurrentWebApplicationContext().getBean("userService");
		service.initUser();
		
	}

}
