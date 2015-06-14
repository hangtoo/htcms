package common.component.cms.action;


import java.util.List;

import common.base.action.BaseAction;
import common.component.IConstants;
import common.component.cms.entity.Catalogright;
import common.component.cms.entity.Catalogue;
import common.component.cms.service.CatalogrightService;
import common.component.cms.service.CatalogueService;
import common.component.cms.util.GlobeData;
import common.exception.ExistException;

public class CatalogueAction extends BaseAction {
	private static final long serialVersionUID = 18L;

	private CatalogueService catalogueService;
	
	private Catalogue bean=new Catalogue();
	
	private String checkrecords;
	
	//private List<Catalogue> tree;
	
	private CatalogrightService catalogrightService;

	public CatalogueAction(CatalogueService catalogueService) {
		super();
		this.catalogueService = catalogueService;
	}
	
	public CatalogueAction(CatalogueService catalogueService,CatalogrightService catalogrightService) {
		super();
		this.catalogueService = catalogueService;
		this.catalogrightService= catalogrightService;
	}
	
	public String newOne() {
		parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATESUCCESS));
		return SUCCESS;
	}
	
	public String edit() {
		if (bean.getId() != null) {
			bean = catalogueService.getById(bean.getId());
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATESUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEERROR));
		}
		return SUCCESS;
	}
	
	public String save(){
		try {
			catalogueService.save(bean);
			refreshCatalogrightTree(bean.getId());
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
			catalogueService.deleteAll(checkrecords);
			refreshCatalogrightTree(bean.getId());
			parameters.put(IConstants.RETURNMSG,getText(IConstants.DELETEDBSUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.DELETEDBERROR));
		}
		return SUCCESS;
	}

	public String update(){
		if (bean.getId() != null) {
			catalogueService.update(bean);
			refreshCatalogrightTree(bean.getId());
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEDBSUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEDBERROR));
		}
		return SUCCESS;
	}
	
	//刷新指定角色权限菜单树
	private void refreshCatalogrightTree(String beanid){
		String roleId=GlobeData.getRoleid();
		//catalogrightService.save(roleId,beanid);//同步更新权限树
		catalogrightService.save(roleId);
		List<Catalogright> tree= catalogrightService.getTree(roleId);
		GlobeData.catalogrightTree.put(roleId, tree);
	}
	
	public String search(){
		try {
			//tree=catalogueService.getTree();
			//parameters.put(IConstants.RETURNMSG,getText(IConstants.RETURNMSG));
		}catch(Exception e){
			parameters.put(IConstants.RETURNMSG,getText(IConstants.RETURNERRORMSG));
		}
		return SUCCESS;
	}
	
	public String createTable(){
		try {
			catalogueService.createTable(bean.getId());
			parameters.put(IConstants.RETURNMSG,getText(IConstants.RETURNMSG));
		} catch (Exception e) {
			log.error(e);
			parameters.put(IConstants.RETURNMSG,getText(IConstants.RETURNERRORMSG));
		}
		return SUCCESS;
	}
	
	public Catalogue getBean() {
		return bean;
	}

	public void setBean(Catalogue bean) {
		this.bean = bean;
	}

	public CatalogueService getCatalogueService() {
		return catalogueService;
	}

	public void setCatalogueService(CatalogueService catalogueService) {
		this.catalogueService = catalogueService;
	}

/*	public List<Catalogue> getTree() {
		if(tree==null)
			tree=catalogueService.getTree();
		return tree;
	}

	public void setTree(List<Catalogue> tree) {
		this.tree = tree;
	}*/

	public String getCheckrecords() {
		return checkrecords;
	}

	public void setCheckrecords(String checkrecords) {
		this.checkrecords = checkrecords;
	}

}
