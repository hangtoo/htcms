package common.component.application.hotel.action;

import common.base.action.BaseAction;
import common.component.IConstants;
import common.component.application.hotel.entity.Hotel;
import common.component.application.hotel.service.HotelService;
import common.exception.ExistException;

public class HotelAction extends BaseAction{

	private static final long serialVersionUID = 12L;

	private HotelService hotelService;
	
	private Hotel bean=new Hotel();


	public HotelAction(HotelService hotelService) {
		super();
		this.hotelService = hotelService;
	}
	
	public String newOne() {
		parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATESUCCESS));
		return SUCCESS;
	}
	
	public String edit() throws Exception {
		if (bean.getId() != null) {
			bean = hotelService.getById(bean.getId());
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATESUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEERROR));
		}
		return SUCCESS;
	}
	
	public String save(){
		
		try {
			hotelService.save(bean);
			parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATEDBSUCCESS));
		} catch (ExistException e) {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATEDBERROR));
		}

		return SUCCESS;
	}
	
	public String delete(){
		if (bean.getId() != null) {
			hotelService.delete(bean.getId());
			parameters.put(IConstants.RETURNMSG,getText(IConstants.DELETEDBSUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.DELETEDBERROR));
		}
		return SUCCESS;
	}

	public String update(){
		if (bean.getId() != null) {
			hotelService.update(bean);
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEDBSUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEDBERROR));
		}
		return SUCCESS;
	}
	
	public String search() throws Exception {
		super.search(hotelService,"select t.* from t_hotel t where 1=1 and t.p_deleted is null ");
		return SUCCESS;
	}
	
	public Hotel getBean() {
		return bean;
	}

	public void setBean(Hotel bean) {
		this.bean = bean;
	}

	public HotelService getHotelService() {
		return hotelService;
	}

	public void setHotelService(HotelService hotelService) {
		this.hotelService = hotelService;
	}

}
