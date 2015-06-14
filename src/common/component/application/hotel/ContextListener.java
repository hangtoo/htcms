package common.component.application.hotel;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

import common.component.application.hotel.service.HotelService;

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
		HotelService service=(HotelService)getContextLoader().getCurrentWebApplicationContext().getBean("hotelService");
		service.initHotel();
		
	}

}
