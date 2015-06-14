package common.component.permission.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.context.HttpServletRequestContext;
import org.extremecomponents.table.limit.Limit;
import org.extremecomponents.table.limit.LimitFactory;
import org.extremecomponents.table.limit.TableLimit;
import org.extremecomponents.table.limit.TableLimitFactory;

import common.base.action.BaseAction;
import common.component.IConstants;
import common.component.permission.entity.Role;
import common.component.permission.service.RoleService;
import common.exception.ExistException;

public class RoleAction extends BaseAction {

	private static final long serialVersionUID = 12L;
	
	private RoleService roleService;
	
	private Role bean=new Role();
	
	private List<Role> roles;

	public RoleAction(RoleService roleService) {
		super();
		this.roleService = roleService;
	}
	
	public String newOne() {
		roles = roleService.getList();
		parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATESUCCESS));
		return SUCCESS;
	}
	
	public String edit() throws Exception {
		if (bean.getId() != null) {
			bean = roleService.getById(bean.getId());
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATESUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEERROR));
		}
		return SUCCESS;
	}
	
	public String save(){
		
		try {
			roleService.save(bean);
			parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATEDBSUCCESS));
		} catch (ExistException e) {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATEDBERROR));
		}
		return SUCCESS;
	}
	
	public String delete(){
		if (bean.getId() != null) {
			roleService.delete(bean.getId());
			parameters.put(IConstants.RETURNMSG,getText(IConstants.DELETEDBSUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.DELETEDBERROR));
		}
		return SUCCESS;
	}

	public String update(){
		if (bean.getId() != null) {
			roleService.update(bean);
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEDBSUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEDBERROR));
		}
		return SUCCESS;
	}
	
	public String search() throws Exception {
		super.search(roleService,
				"select t.p_id,t.p_name,t.p_createtime,t.p_modifytime,t.p_creator,t.p_modifier from t_role t where 1=1 and t.p_deleted is null ");
		return SUCCESS;

	}
	
	public Role getBean() {
		return bean;
	}

	public void setBean(Role bean) {
		this.bean = bean;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

}
