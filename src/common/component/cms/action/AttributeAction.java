package common.component.cms.action;

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
import common.component.cms.entity.Attribute;
import common.component.cms.entity.Catalogue;
import common.component.cms.service.AttributeService;
import common.component.cms.service.CatalogueService;
import common.component.cms.sql.OTypes;
import common.component.cms.util.GlobeData;

public class AttributeAction extends BaseAction{
	
	private CatalogueService catalogueService;
	
	private AttributeService attributeService;

	private Attribute bean;
	
	//private List<Catalogue> catalogues;
	
	private List<Attribute> attributes;
	
	public AttributeAction(CatalogueService catalogueService,AttributeService attributeService){
		super();
		this.catalogueService=catalogueService;
		this.attributeService=attributeService;
	}
	
	public String newOne() {
		//catalogues = catalogueService.getList();
		parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATESUCCESS));
		return SUCCESS;
	}
	
	public String save(){
		try {
			attributeService.save(bean);
			parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATEDBSUCCESS));
			
			refreshCataAttr(bean.getCatalogue().getId());
			
		} catch (Exception e) {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATEDBERROR));
		}
		
		return SUCCESS;
	}
	
	public String delete(){
		if (bean.getId() != null) {
			attributeService.delete(bean.getId());
			parameters.put(IConstants.RETURNMSG,getText(IConstants.DELETEDBSUCCESS));
			
			refreshCataAttr(bean.getCatalogue().getId());
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.DELETEDBERROR));
		}
		return SUCCESS;
	}
	
	public String edit() throws Exception {
		if (bean.getId() != null) {
			//catalogues = catalogueService.getList();
			bean=attributeService.getById(bean.getId());
			
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATESUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEERROR));
		}
		return SUCCESS;
	}
	
	public String update(){
		if (bean.getId() != null) {
			attributeService.update(bean);
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEDBSUCCESS));
			
			refreshCataAttr(bean.getCatalogue().getId());
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEDBERROR));
		}
		return SUCCESS;
	}
	
	public String search() {
		//catalogues = catalogueService.getList();//add catalog list
		String catalogueid;
		if(bean==null||bean.getCatalogue()==null){
			catalogueid="";
		}else
			catalogueid=bean.getCatalogue().getId();
		
		super.search(attributeService,"select t.p_id,t.p_name,t.p_catalogue,t.p_column,t.p_type,t.p_modifytime,t.p_creator,t.p_createtime from t_attribute t where 1=1 and t.p_deleted is null and t.p_catalogue='"+catalogueid+"' ");

		return SUCCESS;
	}
	
	public CatalogueService getCatalogueService() {
		return catalogueService;
	}

	public void setCatalogueService(CatalogueService catalogueService) {
		this.catalogueService = catalogueService;
	}

/*	public List<Catalogue> getCatalogues() {
		return catalogues;
	}

	public void setCatalogues(List<Catalogue> catalogues) {
		this.catalogues = catalogues;
	}*/

	public AttributeService getAttributeService() {
		return attributeService;
	}

	public void setAttributeService(AttributeService attributeService) {
		this.attributeService = attributeService;
	}

	public Attribute getBean() {
		return bean;
	}

	public void setBean(Attribute bean) {
		this.bean = bean;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	//刷新cata attribute缓存
	private void refreshCataAttr(final String cataid){
		new Thread(){
			@Override
			public void run() {
				super.run();
				attributes=attributeService.getAttributeList(cataid);
				GlobeData.cataAttrMap.put(cataid, attributes);
			}
		}.start();
	}

}