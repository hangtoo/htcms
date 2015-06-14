package common.component.application.nurl;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

import common.component.application.nurl.service.NurlService;

public class ContextListener extends ContextLoaderListener{

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		//super.contextDestroyed(event);
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		//初始化酒店列表
		
		//super.contextInitialized(event);
		NurlService service=(NurlService)getContextLoader().getCurrentWebApplicationContext().getBean("nurlService");
		service.initNurl();
		
	}

}
