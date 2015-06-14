package common.component.permission.util;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import common.component.IConstants;
import common.component.permission.entity.Admin;
import common.component.permission.entity.Navigate;
import common.component.permission.entity.Permission;
import common.util.StringUtil;

public class GlobeData {
	//角色菜单权限树缓存
	public static Map<String,List<Permission>> menuTree=new Hashtable<String,List<Permission>>();
	
	public static List<Permission> getMenuTree(){
		String roleid=getRoleid();
		return getMenuTree(roleid);
	}
	
	public static List<Permission> getMenuTree(String roleid){
		return menuTree.get(roleid);
	}
	
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
	
	public static String getDesktopMenu(){
		Admin bean =getUser();
		return getDesktopMenu(bean);
	}
	
	public static String getDesktopMenu(Admin bean){
		List<Permission> menu=getMenuTree(bean.getRole().getId());
		NodeTree tree=new NodeTree();
		for(Permission permission:menu){
			if(!StringUtil.isEmptyString(permission.getChecked())||IConstants.USERNAME.equalsIgnoreCase(bean.getName())){//对于有权限的节点
				Node ele=new Node();
				Navigate nav=permission.getNavigate();
				
				if(nav.getParent()!=null)
					ele.setPid(nav.getParent().getId());

				ele.setId(nav.getId());
				ele.setName(nav.getName());
				ele.setUrl(nav.getUrl());
				tree.addChildNode(ele);
			}
		}
		return tree.getChildNode("-1");
	}
	
/*	public static String getDesktopMenu(String roleid){
		List<Permission> menu=getMenuTree(roleid);
		NodeTree tree=new NodeTree();
		for(Permission permission:menu){
			if(!StringUtil.isEmptyString(permission.getChecked())){//对于有权限的节点
				Node ele=new Node();
				Navigate nav=permission.getNavigate();
				
				if(nav.getParent()!=null)
					ele.setPid(nav.getParent().getId());

				ele.setId(nav.getId());
				ele.setName(nav.getName());
				ele.setUrl(nav.getUrl());
				tree.addChildNode(ele);
			}
		}
		return tree.getChildNode("-1");
	}*/
	
}
