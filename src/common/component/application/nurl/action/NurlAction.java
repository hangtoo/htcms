package common.component.application.nurl.action;

import common.base.action.BaseAction;
import common.component.IConstants;
import common.component.application.nurl.entity.Nurl;
import common.component.application.nurl.service.NurlService;
import common.exception.ExistException;

public class NurlAction extends BaseAction{

	private static final long serialVersionUID = 12L;

	private NurlService nurlService;
	
	private Nurl bean=new Nurl();


	public NurlAction(NurlService nurlService) {
		super();
		this.nurlService = nurlService;
	}
	
	public String newOne() {
		parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATESUCCESS));
		return SUCCESS;
	}
	
	public String edit() throws Exception {
		if (bean.getId() != null) {
			bean = nurlService.getById(bean.getId());
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATESUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEERROR));
		}
		return SUCCESS;
	}
	
	public String save(){
		
		try {
			nurlService.save(bean);
			parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATEDBSUCCESS));
		} catch (ExistException e) {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATEDBERROR));
		}

		return SUCCESS;
	}
	
	public String delete(){
		if (bean.getId() != null) {
			nurlService.delete(bean.getId());
			parameters.put(IConstants.RETURNMSG,getText(IConstants.DELETEDBSUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.DELETEDBERROR));
		}
		return SUCCESS;
	}

	public String update(){
		try{
			if (bean.getId() != null) {
				nurlService.update(bean);
				parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEDBSUCCESS));
			} else {
				parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEDBERROR));
			}
		} catch (Exception e) {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEDBERROR));
			return INPUT;
		}
		return SUCCESS;
	}
	
	public String search() throws Exception {
		super.search(nurlService,"select t.* from t_nurl t where 1=1 and t.p_deleted is null ");
		return SUCCESS;
	}
	
	public Nurl getBean() {
		return bean;
	}

	public void setBean(Nurl bean) {
		this.bean = bean;
	}

	public NurlService getNurlService() {
		return nurlService;
	}

	public void setNurlService(NurlService nurlService) {
		this.nurlService = nurlService;
	}

}
