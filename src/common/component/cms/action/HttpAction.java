package common.component.cms.action;


import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpStatus;

import common.component.IConstants;
import common.component.cms.entity.Catalogue;
import common.component.cms.service.AttributeService;
import common.component.cms.service.CatalogueService;
import common.component.cms.service.HttpService;
import common.component.cms.util.GlobeData;
import common.component.permission.entity.Admin;
import common.exception.ExistException;
import common.util.DateUtil;

public class HttpAction extends ContentAction {
	private static final long serialVersionUID = 19L;
	
	private String retMsg="0";
	
	private String retCode="200";
	
	public HttpAction(HttpService excelService,CatalogueService catalogueService,AttributeService attributeService) throws Exception {
		super(excelService, catalogueService, attributeService);
	}
	
	public String edit() throws SQLException, IOException {
		if (bean.getId() != null) {
			
			if(bean.getCatalogue()!=null){
				Catalogue cat=getCat(bean.getCatalogue().getId());
				bean.setCatalogue(cat);
			}
			
			//columns = contentService.getById(bean);
			List<Map> columnlist= contentService.getList(bean, "*"," p_id='"+bean.getId()+"' and p_deleted is null");
			
			if(columnlist.size()>0)// to test
				columns.putAll(columnlist.get(0));
			
			for(int i=1;i<columnlist.size();i++){// to test
				
				Map ele=columnlist.get(i);
				
				Iterator<String> keys= ele.keySet().iterator();

				while(keys.hasNext()){
					String key=keys.next();
					columns.put(key+"_"+i,""+ele.get(key));
				}
			}
			
			attributes=getAttributes();
			
			clobblobs=contentService.getClobBlobs(bean,attributes,columns);
			
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATESUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEERROR));
		}
		return SUCCESS;
	}

	public String save() throws ExistException, SQLException, IOException,Exception {
		try{
			Admin user=GlobeData.getUser();
			
			if(user!=null){
				columns.put("p_creator", user.getUsername());
			}
			
			String now=DateUtil.getTimeNow();
			
			columns.put("p_createTime", now);
			
			columns.put("p_modifyTime", now);
	
			attributes = getAttributes();
			contentService.save(bean, attributes, columns);
	
			parameters.put(IConstants.RETURNMSG,
					getText(IConstants.CREATEDBSUCCESS));
			
			retMsg="";
			retCode=""+HttpStatus.SC_OK;
			
		}catch(Exception e){
			retMsg=common.component.config.GlobeData.getError(e.getMessage());
			//retMsg=URLEncoder.encode(e.getMessage());
			retCode=""+HttpStatus.SC_MOVED_TEMPORARILY;
			log.error(e);
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

	public String update() throws SQLException, IOException,Exception{
		try{
			if (bean.getId() != null) {
				
				Admin user=GlobeData.getUser();
				
				if(user!=null){
					columns.put("p_modifier",user.getUsername());
				}
				columns.put("p_modifyTime",DateUtil.getTimeNow());
				
				attributes=getAttributes();
				contentService.update(bean,attributes,columns,clobblobs);
				parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEDBSUCCESS));
				
				retMsg="";
				retCode=""+HttpStatus.SC_OK;
				
			} else {
				parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEDBERROR));
				
				retMsg="请选择所需更新的记录！";
				retCode=""+HttpStatus.SC_MOVED_TEMPORARILY;
			}
		}catch(Exception e){
			retMsg=common.component.config.GlobeData.getError(e.getMessage());
			//retMsg=URLEncoder.encode(""+e);
			retCode=""+HttpStatus.SC_MOVED_TEMPORARILY;
			log.error(e);
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
		
		String sql="select * from "+contentService.getTableName(bean) +" where p_deleted is null group by p_id";
		try{
			super.search(contentService,sql);
		}catch(Exception e){
			log.error(e);
			parameters.put(IConstants.RETURNMSG,getText(IConstants.RETURNERRORMSG));
		}
		
		return SUCCESS;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
}
