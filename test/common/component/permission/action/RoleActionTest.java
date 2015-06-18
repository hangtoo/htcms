package common.component.permission.action;

import java.util.List;

import org.junit.Test;

import common.base.BaseStrutsTestCase;

import common.component.permission.action.RoleAction;
import common.component.permission.entity.Role;
import common.component.permission.service.RoleService;

public class RoleActionTest extends BaseStrutsTestCase {

	public RoleActionTest(String name) {
		super(name);
	}

	@Test
	public void testSave() throws Exception{
		RoleAction action = createAction(RoleAction.class, "/permission","roledb_save");
		
		Role bean =new Role();
		bean.setName("test");

		action.setBean(bean);
		
		setSession();
		
		String result = proxy.execute();//执行操作，保存记录
		
		assertSuccess(result);

		List<Role> roles=action.getRoleService().getList(" and name='test' and (deleted is null or deleted='')");
		if(roles.size()>=1){
			action.setBean(roles.get(0));
			action.delete();//删除记录
		}
		
	}

	@Test
	public void testDelete() throws Exception{
		RoleAction action = createAction(RoleAction.class, "/permission","roledb_delete");
		//save a record
		
		Role bean =new Role();
		bean.setName("test");
		
		action.setBean(bean);
		
		setSession();
		action.save();//执行操作，保存记录
		//save a record end
		
		String beanid=getOneRoleId(" and name='test' and deleted is null ");
		
		if("-1".equalsIgnoreCase(beanid)){
			fail(" no exist record to deleted ");
		}
		
		bean =new Role();
		bean.setId(beanid);
		action.setBean(bean);
		
		String result = proxy.execute();//执行操作，保存记录
		
		assertSuccess(result);

	}



	@Test
	public void testUpdate() throws Exception {
		RoleAction action = createAction(RoleAction.class, "/permission",
				"roledb_update");

		String beanid = getOneRoleId(" and name='test'");

		if ("-1".equalsIgnoreCase(beanid)) {
			fail(" no exist record to update ");
		}

		Role bean = new Role();
		bean.setId(beanid);
		action.setBean(bean);
		action.edit();//必须选择修改的记录
		
		bean.setName("test1");
		
		action.setBean(bean);
		setSession();
		String result = proxy.execute();// 修改记录
		assertSuccess(result);

	}
	
	@Test
	public void testSearch() throws Exception {
		RoleAction action = createAction(RoleAction.class, "/permission","role_search");
		String result = proxy.execute();
		assertNotNull(request.getAttribute("ec_totalRows"));
		assertNotNull(request.getAttribute("page"));
		assertSuccess(result);
	}
	
	@Test
	public void testNewOne() throws Exception {
		RoleAction action = createAction(RoleAction.class, "/permission","role_newOne");
		String result = proxy.execute();//获取角色信息
		assertSuccess(result);
	}
	
	@Test
	public void testEdit() throws Exception{
		RoleAction action = createAction(RoleAction.class, "/permission","role_edit");

		String beanid=getOneRoleId(" and name='test'");
		
		if("-1".equalsIgnoreCase(beanid)){
			return;
		}

		Role editbean=new Role();
		editbean.setId(beanid);
		action.setBean(editbean);
		
		String result = proxy.execute();//修改记录
		Role bean=action.getBean();
		
		assertNotNull(bean.getName());
		assertSuccess(result);

		
	}


	
	private String getOneRoleId(String condition){
		RoleService service=(RoleService) this.getInstance("roleService");
		List<Role> roles=service.getList(condition);//" and (deleted is null or deleted ='')"
		
		if(roles.size()>=1){
			Role bean=roles.get(0);
			return bean.getId();
		}
		return "-1";
	}

}
