package common.component.config;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

import common.component.config.service.ConfigService;

public class ContextListener extends ContextLoaderListener
 {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		//ConfigService service=new ConfigServiceImpl();
		//service.initConfig();//初始化参数列表
		
		super.contextInitialized(event);
		ConfigService service=(ConfigService)getContextLoader().getCurrentWebApplicationContext().getBean("configService");
		service.initConfig();
		
	}

}
