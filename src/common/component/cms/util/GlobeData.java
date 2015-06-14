package common.component.cms.util;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts2.ServletActionContext;

import common.component.IConstants;
import common.component.cms.entity.Attribute;
import common.component.cms.entity.Catalogright;
import common.component.cms.entity.Catalogue;
import common.component.permission.entity.Admin;
import common.component.permission.entity.Permission;

public class GlobeData {
	//栏目属性列表缓存
	public static Map<String,List<Attribute>> cataAttrMap=new Hashtable<String,List<Attribute>>();
	
	//栏目模版文件流缓存,用于excel js操作方式
	public static Map<String,POIFSFileSystem> templateBooks=new Hashtable<String,POIFSFileSystem>();
	
	//角色栏目权限树缓存
	public static Map<String,List<Catalogright>> catalogrightTree=new Hashtable<String,List<Catalogright>>();
	
	
	public static List<Catalogright> getCatalogrightTree(){

		String roleid=getRoleid();
		
		return getCatalogrightTree(roleid);
	}
	
	public static List<Catalogright> getCatalogrightTree(String roleid){
		return catalogrightTree.get(roleid);
	}
	
	//栏目id 栏目对象缓存,增删改操作待处理
	public static Map<String,Catalogue> catalogues=new Hashtable<String,Catalogue>();
	
	
	public static String getRoleid(){
		Admin admin=getUser();
		
		if(admin==null)
			return null;
		
		String roleid=admin.getRole().getId();
		return roleid;
	}
	
	public static Admin getUser(){
		Object obj=ServletActionContext.getContext().getSession().get(IConstants.USERKEY);
		if(obj==null||!(obj instanceof Admin))
			return null;
		Admin admin=(Admin)obj;
		return admin;
	}
	
	
}
