package common.component.permission.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import common.base.action.BaseAction;
import common.component.IConstants;
import common.component.cms.entity.Catalogright;
import common.component.cms.service.CatalogrightService;
import common.component.permission.util.GlobeData;
import common.component.permission.entity.Admin;
import common.component.permission.entity.Permission;
import common.component.permission.service.AdminService;
import common.component.permission.service.PermissionService;

public class LoginAction extends BaseAction {
	
	private static final long serialVersionUID = 10L;
	
	private AdminService adminService;
	
	private PermissionService permissionService;
	
	private CatalogrightService catalogrightService;
	
    private Admin bean;

	public LoginAction(AdminService adminService,PermissionService permissionService) {
		super();
		this.adminService = adminService;
		this.permissionService=permissionService;
	}
	
	public LoginAction(AdminService adminService,PermissionService permissionService,CatalogrightService catalogrightService) {
		super();
		this.adminService = adminService;
		this.permissionService=permissionService;
		this.catalogrightService=catalogrightService;
	}
    
    public String execute() {

/*    	if (!isvalidCheckcode(bean.getCheckcode())){//测试阶段，不验证验证码
    		parameters.put(IConstants.RETURNMSG,getText(IConstants.LOGINERROR));
    		return INPUT;
    	}*/
    	
        if (isInvalid(bean.getUsername())) return INPUT;
        if (isInvalid(bean.getPassword())) return INPUT;
        
        Admin user=adminService.valid(bean);
        
        if(user!=null){
        	putSession(user);
        	
        	String roleid=user.getRole().getId();
        	
        	setTree(roleid);
        	setCatalogrightTree(roleid);
        }else{
        	parameters.put(IConstants.RETURNMSG,getText(IConstants.LOGINERROR));
        	return INPUT;
        }
   
        return SUCCESS;
    }
    
    public String logout() {
    	removeSession();
    	parameters.put(IConstants.RETURNMSG,getText(IConstants.LOGOUTSUCCESS));
        return INPUT;
    }
    
    private boolean isvalidCheckcode(String value){
    	if(isInvalid(bean.getCheckcode()))
    		return false;
    	
    	try{
    		if(value.equals(""+ServletActionContext.getContext().getSession().get(IConstants.VERIFYCODE)))
    			return true;
    	}
    	catch(Exception e){
    		log.error(e);
    		return false;
    	}
    	
    	return false;
    }

    private boolean isInvalid(String value) {
        return (value == null || value.length() == 0);
    }
    
    private void putSession(Admin user){
    	try{
    		this.bean=user;
    		ServletActionContext.getContext().getSession().put(IConstants.USERKEY, user);
    	}
    	catch(Exception e){
    		log.error(getText(IConstants.SESSIONERROR));
    	}
    }
    
    private void removeSession(){
    	try{
    		Map session=ServletActionContext.getContext().getSession();
    		session=null;
    		ServletActionContext.getContext().setSession(null);
    	}
    	catch(Exception e){
    		log.error(getText(IConstants.SESSIONERROR));
    	}
    }

	public Admin getBean() {
		return bean;
	}

	public void setBean(Admin bean) {
		this.bean = bean;
	}

	public void setTree(String roleId) {//判断缓存是否有相应的权限树，并根据结果更新权限树
		if(!(GlobeData.menuTree.containsKey(roleId))){
			List<Permission> tree= permissionService.getTree(roleId);
			GlobeData.menuTree.put(roleId, tree);
			//ServletActionContext.getContext().getSession().put(getText(IConstants.TREE), tree);
		}
	}
	
	public void setCatalogrightTree(String roleId){
		
		if(catalogrightService==null){
			log.info("no import the cms component");
			return;
		}
		
		log.info("import the cms component");
		
		if(!(common.component.cms.util.GlobeData.catalogrightTree.containsKey(roleId))){
			List<Catalogright> tree= catalogrightService.getTree(roleId);
			
			if(log.isDebugEnabled()){
				for(int i=0;i<tree.size();i++){
					if(tree.get(i)!=null)
						log.debug(tree.get(i).getCatalogue().getId());
				}
			}
			
			
			common.component.cms.util.GlobeData.catalogrightTree.put(roleId, tree);
		}
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

}