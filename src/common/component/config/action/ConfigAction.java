package common.component.config.action;

import common.base.action.BaseAction;
import common.component.IConstants;
import common.component.config.entity.Config;
import common.component.config.service.ConfigService;
import common.exception.ExistException;

public class ConfigAction extends BaseAction{

	private static final long serialVersionUID = 12L;

	private ConfigService configService;
	
	private Config bean=new Config();


	public ConfigAction(ConfigService configService) {
		super();
		this.configService = configService;
	}
	
	public String newOne() {
		parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATESUCCESS));
		return SUCCESS;
	}
	
	public String edit() throws Exception {
		if (bean.getId() != null) {
			bean = configService.getById(bean.getId());
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATESUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEERROR));
		}
		return SUCCESS;
	}
	
	public String save(){
		
		try {
			configService.save(bean);
			parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATEDBSUCCESS));
		} catch (ExistException e) {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATEDBERROR));
		}

		return SUCCESS;
	}
	
	public String delete(){
		if (bean.getId() != null) {
			configService.delete(bean.getId());
			parameters.put(IConstants.RETURNMSG,getText(IConstants.DELETEDBSUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.DELETEDBERROR));
		}
		return SUCCESS;
	}

	public String update(){
		if (bean.getId() != null) {
			configService.update(bean);
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEDBSUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEDBERROR));
		}
		return SUCCESS;
	}
	
	public String search() throws Exception {
		super.search(configService,"select t.p_id,t.p_configkey,t.p_configvalue,t.p_modifytime,t.p_creator,t.p_createtime from t_config t where 1=1 and t.p_deleted is null ");
		return SUCCESS;
	}
	
	public Config getBean() {
		return bean;
	}

	public void setBean(Config bean) {
		this.bean = bean;
	}

	public ConfigService getConfigService() {
		return configService;
	}

	public void setConfigService(ConfigService configService) {
		this.configService = configService;
	}

}
