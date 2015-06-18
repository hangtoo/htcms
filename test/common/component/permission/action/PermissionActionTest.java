package common.component.permission.action;

import java.util.List;

import org.junit.Test;

import common.base.BaseStrutsTestCase;

import common.component.permission.action.PermissionAction;
import common.component.permission.entity.Navigate;
import common.component.permission.entity.Permission;
import common.component.permission.entity.Role;
import common.util.StringUtil;

public class PermissionActionTest extends BaseStrutsTestCase{
	public PermissionActionTest(String name) {
		super(name);
	}
	@Test
	public void testSave() throws Exception{
		PermissionAction action = createAction(PermissionAction.class, "/permission","permissiondb_save");
		
		action.search();//获取角色信息
		
		List<Navigate> tree= action.getTree();//获取系统权限信息
		StringBuffer records=new StringBuffer();
		for(int i=0;i<tree.size();i++){
			records.append(",'"+tree.get(i).getId()+"'");
		}
		
		Role bean=null;
		List<Role> role= action.getRoles();
		if(role.size()<1){
			fail("no role to edit");
			return;
		}
		bean=role.get(0);
		action.setBean(bean);
		
		action.edit();//获取指定role的权限信息
		List<Permission> permissions= action.getPermissions();
		StringBuffer checkrecords=new StringBuffer();
		for(int i=0;i<permissions.size();i++){
			if(!StringUtil.isEmptyString(permissions.get(i).getChecked()))
				checkrecords.append(",'"+permissions.get(i).getNavigate().getId()+"'");
		}
		if(records.indexOf(",")!=-1)
			action.setCheckrecords(records.toString().substring(1));//设置指定角色，为其设置所有权限信息
		
		setSession();
		String result = proxy.execute();//执行操作，保存记录
		
		assertSuccess(result);
		if(checkrecords.indexOf(",")!=-1)
			action.setCheckrecords(checkrecords.toString().substring(1));//设置指定角色，为其设置所有权限信息
		else
			action.setCheckrecords("");
		action.save();//还原其原有权限信息
		
	}
	@Test
	public void testEdit() throws Exception{
		PermissionAction action = createAction(PermissionAction.class, "/permission","permission_edit");

		action.search();//获取角色信息
		
		Role bean=null;
		List<Role> role= action.getRoles();
		if(role.size()<1){
			fail("no role to edit");
			return;
		}
		bean=role.get(0);
		action.setBean(bean);
		
		String result = proxy.execute();//获取指定角色的权限信息

		List<Permission> tree= action.getPermissions();
		
		assertNotNull(tree);
		assertSuccess(result);
	}
	@Test
	public void testSearch() throws Exception {
		PermissionAction action = createAction(PermissionAction.class, "/permission","permission_search");
		String result = proxy.execute();
		
		List<Role> role=action.getRoles();
		List<Navigate> tree=action.getTree();
		
		assertNotNull(role);
		assertNotNull(tree);
		assertSuccess(result);
	}
	
}
