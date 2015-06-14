package common.component.application.user.action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import common.base.action.BaseAction;
import common.component.IConstants;
import common.component.application.user.entity.User;
import common.component.application.user.service.UserService;
import common.exception.ExistException;

public class UserAction extends BaseAction{

	private static final long serialVersionUID = 12L;

	private UserService userService;
	
	private User bean=new User();


	public UserAction(UserService userService) {
		super();
		this.userService = userService;
	}
	
	public String newOne() {
		parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATESUCCESS));
		return SUCCESS;
	}
	
	public String edit() throws Exception {
		if (bean.getId() != null) {
			bean = userService.getById(bean.getId());
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATESUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEERROR));
		}
		return SUCCESS;
	}
	
	public String save(){
		
		try {
			userService.save(bean);
			parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATEDBSUCCESS));
		} catch (ExistException e) {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATEDBERROR));
			return INPUT;
		}

		return SUCCESS;
	}
	
	public String delete(){
		if (bean.getId() != null) {
			userService.delete(bean.getId());
			parameters.put(IConstants.RETURNMSG,getText(IConstants.DELETEDBSUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.DELETEDBERROR));
		}
		return SUCCESS;
	}

	public String update(){
		try {
			userService.update(bean);
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEDBSUCCESS));
		} catch (Exception e) {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEDBERROR));
		}
		return SUCCESS;
	}
	
	public String search() throws Exception {
		super.search(userService,"select t.* from t_user t where 1=1 and t.p_deleted is null ");
		return SUCCESS;
	}
	
	public User getBean() {
		return bean;
	}

	public void setBean(User bean) {
		this.bean = bean;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
    public String execute() {

/*		if (!isvalidCheckcode(bean.getCheckcode())) {// 测试阶段，不验证验证码
			parameters.put(IConstants.RETURNMSG, getText(IConstants.LOGINERROR));
			return INPUT;
		}*/

		if (isInvalid(bean.getUsername()))
			return INPUT;
		if (isInvalid(bean.getPassword()))
			return INPUT;
		
		User user=userService.valid(bean);

		if (user != null) {
			putSession(user);
		} else {
			parameters.put(IConstants.RETURNMSG, getText(IConstants.LOGINERROR));
			return INPUT;
		}

		return SUCCESS;
	}
    
	public String register() {
		return INPUT;
	}

	public String logout() {
		removeSession();
		parameters.put(IConstants.RETURNMSG, getText(IConstants.LOGOUTSUCCESS));
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

    private void putSession(User user){
    	try{
    		this.bean=user;
    		ServletActionContext.getContext().getSession().put(IConstants.CLIENTKEY, user);
    	}
    	catch(Exception e){
    		log.error(getText(IConstants.SESSIONERROR));
    	}
    }
	
    private void removeSession(){
    	try{
    		Map session=ServletActionContext.getContext().getSession();
    		session.remove(IConstants.CLIENTKEY);
    		session=null;
    		ServletActionContext.getContext().setSession(null);
    	}
    	catch(Exception e){
    		log.error(getText(IConstants.SESSIONERROR));
    	}
    }
	
}
