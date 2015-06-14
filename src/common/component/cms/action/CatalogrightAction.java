package common.component.cms.action;

import java.util.List;

import common.base.action.BaseAction;
import common.component.IConstants;
import common.component.cms.entity.Catalogright;
import common.component.cms.entity.Catalogue;
import common.component.cms.service.CatalogrightService;
import common.component.cms.service.CatalogueService;
import common.component.cms.util.GlobeData;
import common.component.permission.entity.Role;
import common.component.permission.service.RoleService;

public class CatalogrightAction extends BaseAction{
	
	private RoleService roleService;
	
	private CatalogueService catalogueService;
	
	private CatalogrightService catalogrightService;

	private Role bean;
	
	private String checkrecords;
	
	private List<Role> roles;
	
	private List<Catalogright> catalogrights;
	
	//private List<Catalogue> tree;
	
	public CatalogrightAction(RoleService roleService,CatalogueService catalogueService,CatalogrightService catalogrightService){
		super();
		this.roleService=roleService;
		this.catalogueService=catalogueService;
		this.catalogrightService=catalogrightService;
	}
	
	public String edit() throws Exception {
		if (bean.getId() != null) {
			catalogrights=getCatalogrightTree(bean.getId());
			roles = roleService.getList();
			//tree=catalogueService.getTree();
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATESUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEERROR));
		}
		return SUCCESS;
	}
	
	public String save(){
		try {
			if(checkrecords.startsWith(","))
				checkrecords=checkrecords.substring(1);
			catalogrightService.save(bean,checkrecords);
			
			refreshCatalogrightTree(bean.getId());
			
			parameters.put(IConstants.RETURNMSG,getText(IConstants.RETURNMSG));
		} catch (Exception e) {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.RETURNERRORMSG));
		}
		
		return SUCCESS;
	}
	
	//刷新指定角色权限菜单树
	private void refreshCatalogrightTree(String roleId){
		List<Catalogright> tree= catalogrightService.getTree(roleId);
		GlobeData.catalogrightTree.put(roleId, tree);
	}
	
	//获取角色权限菜单树
	private List<Catalogright> getCatalogrightTree(String roleId){
		List<Catalogright> tree=null;
		if(GlobeData.catalogrightTree.containsKey(roleId)){
			tree=GlobeData.catalogrightTree.get(roleId);
		}else{
			tree= catalogrightService.getTree(roleId);
			GlobeData.catalogrightTree.put(roleId, tree);
		}
		return tree;
	}
	
	
	//因多对多关系 newOne一起处理
	public String search() {
		if (bean!=null&&bean.getId() != null) 
			catalogrights=catalogrightService.getTree(bean.getId());
		roles = roleService.getList();
		//tree=catalogueService.getTree();
		return SUCCESS;
	}
	
	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public String getCheckrecords() {
		return checkrecords;
	}

	public void setCheckrecords(String checkrecords) {
		this.checkrecords = checkrecords;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public CatalogrightService getCatalogrightService() {
		return catalogrightService;
	}

	public void setCatalogrightService(CatalogrightService catalogrightService) {
		this.catalogrightService = catalogrightService;
	}

	public Role getBean() {
		return bean;
	}

	public void setBean(Role bean) {
		this.bean = bean;
	}

	public CatalogueService getCatalogueService() {
		return catalogueService;
	}

	public void setCatalogueService(CatalogueService catalogueService) {
		this.catalogueService = catalogueService;
	}

	public List<Catalogright> getCatalogrights() {
		return catalogrights;
	}

	public void setCatalogrights(List<Catalogright> catalogrights) {
		this.catalogrights = catalogrights;
	}

/*	public List<Catalogue> getTree() {
		return tree;
	}

	public void setTree(List<Catalogue> tree) {
		this.tree = tree;
	}*/
	


}