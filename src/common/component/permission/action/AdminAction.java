package common.component.permission.action;

import java.util.List;

import common.base.action.BaseAction;
import common.component.IConstants;
import common.component.permission.entity.Admin;
import common.component.permission.entity.Role;
import common.component.permission.service.AdminService;
import common.component.permission.service.RoleService;
import common.exception.ExistException;

public class AdminAction extends BaseAction{

	private static final long serialVersionUID = 11L;

	private AdminService adminService;
	
	private RoleService roleService;
	
	private Admin bean=new Admin();
	
	private List<Role> roles;

	public AdminAction(AdminService adminService,RoleService roleService) {
		super();
		this.adminService = adminService;
		this.roleService = roleService;
	}
	
	public String newOne() {
		roles = roleService.getList();
		parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATESUCCESS));
		return SUCCESS;
	}
	
	public String edit() throws Exception {
		if (bean.getId() != null) {
			roles = roleService.getList();
			bean = adminService.getById(bean.getId());
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATESUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEERROR));
		}
		return SUCCESS;
	}
	
	public String save(){
		
		try {
			adminService.save(bean);
			parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATEDBSUCCESS));
		} catch (ExistException e) {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATEDBERROR));
		}

		return SUCCESS;
	}
	
	public String delete(){
		if (bean.getId() != null) {
			adminService.delete(bean.getId());
			parameters.put(IConstants.RETURNMSG,getText(IConstants.DELETEDBSUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.DELETEDBERROR));
		}
		return SUCCESS;
	}

	public String update(){
		if (bean.getId() != null) {
			adminService.update(bean);
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEDBSUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEDBERROR));
		}
		return SUCCESS;
	}
	
	public String search() throws Exception {
		super.search(adminService,"select t.p_id,t.p_username,t.p_name,t.p_position,t.p_modifytime,t.p_creator,t.p_createtime,t.p_group,role.p_name p_rolename from t_admin t left join t_role role on t.p_role=role.p_id  where 1=1 and t.p_deleted is null and role.p_deleted is null ");
		return SUCCESS;
	}
	
	public Admin getBean() {
		return bean;
	}

	public void setBean(Admin bean) {
		this.bean = bean;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
