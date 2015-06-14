package common.component.permission.action;

import java.util.List;

import common.base.action.BaseAction;
import common.component.IConstants;
import common.component.permission.util.GlobeData;
import common.component.permission.entity.Navigate;
import common.component.permission.entity.Permission;
import common.component.permission.entity.Role;
import common.component.permission.service.NavigateService;
import common.component.permission.service.PermissionService;
import common.component.permission.service.RoleService;

public class PermissionAction extends BaseAction{
	
	private RoleService roleService;
	
	private NavigateService navigateService;
	
	private PermissionService permissionService;

	private Role bean;
	
	private String checkrecords;
	
	private List<Role> roles;
	
	private List<Permission> permissions;
	
	private List<Navigate> tree;
	
	public PermissionAction(RoleService roleService,NavigateService navigateService,PermissionService permissionService){
		super();
		this.roleService=roleService;
		this.navigateService=navigateService;
		this.permissionService=permissionService;
	}
	
	public String edit() throws Exception {
		if (bean.getId() != null) {
			permissions=getMenuTree(bean.getId());
			roles = roleService.getList();
			tree=navigateService.getTree();
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
			permissionService.save(bean,checkrecords);
			
			refreshMenuTree(bean.getId());
			
			parameters.put(IConstants.RETURNMSG,getText(IConstants.RETURNMSG));
		} catch (Exception e) {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.RETURNERRORMSG));
		}
		
		return SUCCESS;
	}
	
	//刷新指定角色权限菜单树
	private void refreshMenuTree(String roleId){
		List<Permission> tree= permissionService.getTree(roleId);
		GlobeData.menuTree.put(roleId, tree);
	}
	
	//获取角色权限菜单树
	private List<Permission> getMenuTree(String roleId){
		List<Permission> tree=null;
		if(GlobeData.menuTree.containsKey(roleId)){
			tree=GlobeData.menuTree.get(roleId);
		}else{
			tree= permissionService.getTree(roleId);
			GlobeData.menuTree.put(roleId, tree);
		}
		return tree;
	}
	
	
	//因多对多关系 newOne一起处理
	public String search() {
		if (bean!=null&&bean.getId() != null) 
			permissions=permissionService.getTree(bean.getId());
		roles = roleService.getList();
		tree=navigateService.getTree();
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

	public PermissionService getPermissionService() {
		return permissionService;
	}

	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	public Role getBean() {
		return bean;
	}

	public void setBean(Role bean) {
		this.bean = bean;
	}

	public NavigateService getNavigateService() {
		return navigateService;
	}

	public void setNavigateService(NavigateService navigateService) {
		this.navigateService = navigateService;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public List<Navigate> getTree() {
		return tree;
	}

	public void setTree(List<Navigate> tree) {
		this.tree = tree;
	}
	


}