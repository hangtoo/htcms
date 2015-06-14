package common.component.application.card;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

import common.component.application.card.service.CardService;

public class ContextListener extends ContextLoaderListener{

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		//super.contextDestroyed(event);
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		//初始化酒店列表
		
		//super.contextInitialized(event);
		CardService service=(CardService)getContextLoader().getCurrentWebApplicationContext().getBean("hotelService");
		service.initCard();
		
	}

}
