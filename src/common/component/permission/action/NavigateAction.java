package common.component.permission.action;


import java.util.List;

import common.base.action.BaseAction;
import common.component.IConstants;
import common.component.permission.entity.Navigate;
import common.component.permission.service.NavigateService;
import common.exception.ExistException;

public class NavigateAction extends BaseAction {
	private static final long serialVersionUID = 11L;

	private NavigateService navigateService;
	
	private Navigate bean=new Navigate();
	
	private String checkrecords;
	
	private List<Navigate> tree;

	public NavigateAction(NavigateService navigateService) {
		super();
		this.navigateService = navigateService;
	}
	
	public String newOne() {
		return SUCCESS;
	}
	
	public String edit() throws Exception {
		if (bean.getId() != null) {
			bean = navigateService.getById(bean.getId());
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATESUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEERROR));
		}
		return SUCCESS;
	}
	
	public String save(){
		try {
			navigateService.save(bean);
			parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATEDBSUCCESS));
		} catch (ExistException e) {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATEDBERROR));
		}
		return SUCCESS;
	}
	
	public String delete(){
		if (checkrecords!= null) {
			if(checkrecords.startsWith(","))
				checkrecords=checkrecords.substring(1);
			navigateService.deleteAll(checkrecords);
			parameters.put(IConstants.RETURNMSG,getText(IConstants.DELETEDBSUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.DELETEDBERROR));
		}
		return SUCCESS;
	}

	public String update(){
		if (bean.getId() != null) {
			navigateService.update(bean);
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEDBSUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEDBERROR));
		}
		return SUCCESS;
	}
	
	public String search() throws Exception {
		tree=navigateService.getTree();
		return SUCCESS;
	}
	
	public Navigate getBean() {
		return bean;
	}

	public void setBean(Navigate bean) {
		this.bean = bean;
	}

	public NavigateService getNavigateService() {
		return navigateService;
	}

	public void setNavigateService(NavigateService navigateService) {
		this.navigateService = navigateService;
	}

	public List<Navigate> getTree() {
		if(tree==null)
			tree=navigateService.getTree();
		return tree;
	}

	public void setTree(List<Navigate> tree) {
		this.tree = tree;
	}

	public String getCheckrecords() {
		return checkrecords;
	}

	public void setCheckrecords(String checkrecords) {
		this.checkrecords = checkrecords;
	}

}
