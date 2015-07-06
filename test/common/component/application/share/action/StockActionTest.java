package common.component.application.share.action;

import java.util.List;

import org.junit.Test;

import common.base.BaseStrutsTestCase;
import common.component.application.share.entity.Stock;
import common.component.application.share.service.StockService;

public class StockActionTest extends BaseStrutsTestCase {

	public StockActionTest(String name) {
		super(name);
	}

	@Test
	public void testSave() throws Exception{
		StockAction action = createAction(StockAction.class, "/stock","stockdb_save");
		
		action.newOne();
		
		Stock bean =new Stock();
		bean.setName("test");
		
		action.setBean(bean);
		
		setSession();
		String result = proxy.execute();//执行操作，保存记录
		
		assertSuccess(result);


		List<Stock> stocks=action.getStockService().getList(" and name='test' and (deleted is null or deleted='')");
		if(stocks.size()>=1){
			action.setBean(stocks.get(0));
			action.delete();//删除记录
		}
		
	}
 
	
	@Test
	public void testSearch() throws Exception {
		StockAction action = createAction(StockAction.class, "/permission","stock_search");
		String result = proxy.execute();
		assertNotNull(request.getAttribute("ec_totalRows"));
		assertNotNull(request.getAttribute("page"));
		assertSuccess(result);
	}
	

	
	private String getOneStockId(String condition){
		StockService service=(StockService) this.getInstance("stockService");
		List<Stock> stocks=service.getList(condition);//" and (deleted is null or deleted ='')"
		
		if(stocks.size()>=1){
			Stock bean=stocks.get(0);
			return bean.getId();
		}
		return "-1";
	}

}
