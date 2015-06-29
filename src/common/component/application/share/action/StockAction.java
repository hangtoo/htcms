package common.component.application.share.action;

import org.springframework.beans.factory.annotation.Autowired;

import common.base.action.BaseAction;
import common.component.IConstants;
import common.component.application.share.entity.Stock;
import common.component.application.share.service.StockService;
import common.exception.ExistException;


public class StockAction extends BaseAction{

	private static final long serialVersionUID = 12L;

	private StockService stockService;
	
	private Stock bean=new Stock();


	public StockAction(StockService stockService) {
		super();
		this.stockService = stockService;
	}
	
	public String newOne() {
		parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATESUCCESS));
		return "newOne";
	}
	
	public String edit() throws Exception {
		if (bean.getId() != null) {
			bean = stockService.getById(bean.getId());
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATESUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEERROR));
		}
		return "edit";
	}
	
	public String save(){
		
		try {
			stockService.save(bean);
			parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATEDBSUCCESS));
		} catch (ExistException e) {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATEDBERROR));
		}

		return "search";
	}
	
	public String delete(){
		if (bean.getId() != null) {
			stockService.delete(bean.getId());
			parameters.put(IConstants.RETURNMSG,getText(IConstants.DELETEDBSUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.DELETEDBERROR));
		}
		return "search";
	}

	public String update(){
		if (bean.getId() != null) {
			stockService.update(bean);
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEDBSUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEDBERROR));
		}
		return "search";
	}
	
	public String search() throws Exception {
		super.search(stockService,"select t.* from t_stock t where 1=1 and t.p_deleted is null ");
		return "search";
	}
	
	public Stock getBean() {
		return bean;
	}

	public void setBean(Stock bean) {
		this.bean = bean;
	}

	public StockService getStockService() {
		return stockService;
	}
	@Autowired
	public void setStockService(StockService stockService) {
		this.stockService = stockService;
	}

}
