package common.component.cms.action;


import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import common.base.action.BaseAction;
import common.component.IConstants;
import common.component.cms.entity.Attribute;
import common.component.cms.entity.Catalogue;
import common.component.cms.entity.Content;
import common.component.cms.service.AttributeService;
import common.component.cms.service.CatalogueService;
import common.component.cms.service.ContentService;
import common.component.cms.util.GlobeData;
import common.component.permission.entity.Admin;
import common.exception.ExistException;
import common.util.DateUtil;

public class ContentAction extends BaseAction {
	private static final long serialVersionUID = 18L;

	protected ContentService contentService;
	
	protected CatalogueService catalogueService;
	
	protected AttributeService attributeService;
	
	protected static List<Catalogue> tree;//栏目列表

	protected List<Attribute> attributes;//指定栏目的属性列表
	
	protected Content bean=new Content();
	
	protected Map<String,String> columns=new HashMap<String,String>();//指定栏目中记录的内容,格式为column-列名,value-内容
	
	protected Map<String,String> clobblobs=new HashMap<String,String>();//指定blob clob中记录的内容 ,格式为id-blob主键,name/content-blob数值

	public ContentAction(ContentService contentService,CatalogueService catalogueService,AttributeService attributeService) {
		super();
		this.contentService = contentService;
		this.catalogueService = catalogueService;
		this.attributeService = attributeService;
	}
	
	protected Catalogue getCat(String catid){//缓存
		Catalogue cat=GlobeData.catalogues.get(catid);
		if(cat==null){
			cat=catalogueService.getById(catid);
			GlobeData.catalogues.put(catid,cat);
		}
		return cat;
	}
	
	public String newOne() {
		
		if(bean.getCatalogue()!=null){
			Catalogue cat=getCat(bean.getCatalogue().getId());
			bean.setCatalogue(cat);
		}
		
		return SUCCESS;
	}
	
	public String edit() throws SQLException, IOException {
		if (bean.getId() != null) {
			
			if(bean.getCatalogue()!=null){
				Catalogue cat=getCat(bean.getCatalogue().getId());
				bean.setCatalogue(cat);
			}
			
			columns = contentService.getById(bean);
			
			attributes=getAttributes();
			
			clobblobs=contentService.getClobBlobs(bean,attributes,columns);
			
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATESUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEERROR));
		}
		return SUCCESS;
	}
	
	public String save() throws ExistException, SQLException, IOException,Exception{
		try {
			Admin user=GlobeData.getUser();
			
			if(user!=null){
				columns.put("p_creator",user.getUsername());
			}
			columns.put("p_createtime",DateUtil.getTimeNow());
			
			attributes=getAttributes();
			contentService.save(bean,attributes,columns);

			parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATEDBSUCCESS));
		} catch (Exception e) {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATEDBERROR));
		}
		return SUCCESS;
	}
	
	public String delete(){
		if (bean.getId()!= null) {
			contentService.delete(bean);
			parameters.put(IConstants.RETURNMSG,getText(IConstants.DELETEDBSUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.DELETEDBERROR));
		}
		return SUCCESS;
	}

	public String update() throws SQLException, IOException, Exception{
		if (bean.getId() != null) {
			
			Admin user=GlobeData.getUser();
			
			if(user!=null){
				columns.put("p_modifier",user.getUsername());
			}
			columns.put("p_modifytime",DateUtil.getTimeNow());
			
			attributes=getAttributes();
			contentService.update(bean,attributes,columns,clobblobs);
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEDBSUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEDBERROR));
		}
		return SUCCESS;
	}
	
	public String search(){
		
		if(bean.getCatalogue()==null){
			parameters.put(IConstants.RETURNMSG,getText(IConstants.RETURNERRORMSG));
			return SUCCESS;
		}

		Catalogue cat=getCat(bean.getCatalogue().getId());
		bean.setCatalogue(cat);
		
		String sql="select * from "+contentService.getTableName(bean) +" where p_deleted is null";
		try{
			super.search(contentService,sql);
		}catch(Exception e){
			log.error(e);
			parameters.put(IConstants.RETURNMSG,getText(IConstants.RETURNERRORMSG));
		}
		
		return SUCCESS;
	}

	public ContentService getContentService() {
		return contentService;
	}

	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

	public List<Catalogue> getTree() {
		if(tree==null)
			tree = catalogueService.getTree();
		return tree;
	}

	public List<Attribute> getAttributes() {
		String cataid=bean.getCatalogue().getId();
		
		if (cataid!= null) {
			attributes=GlobeData.cataAttrMap.get(cataid);
			if(attributes==null){
				attributes=attributeService.getAttributeList(cataid);
				GlobeData.cataAttrMap.put(cataid, attributes);
			}
		}
		
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public Content getBean() {
		return bean;
	}

	public void setBean(Content bean) {
		this.bean = bean;
	}

	public Map<String, String> getColumns() {
		return columns;
	}

	public void setColumns(Map<String, String> columns) {
		this.columns = columns;
	}

	public Map<String, String> getClobblobs() {
		return clobblobs;
	}

	public void setClobblobs(Map<String, String> clobblobs) {
		this.clobblobs = clobblobs;
	}
}
