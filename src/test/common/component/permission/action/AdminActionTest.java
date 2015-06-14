package test.common.component.permission.action;

import java.util.List;

import org.junit.Test;

import test.common.base.BaseStrutsTestCase;

import common.component.permission.action.AdminAction;
import common.component.permission.entity.Admin;
import common.component.permission.entity.Role;
import common.component.permission.service.AdminService;

public class AdminActionTest extends BaseStrutsTestCase {

	public AdminActionTest(String name) {
		super(name);
	}

	@Test
	public void testSave() throws Exception{
		AdminAction action = createAction(AdminAction.class, "/permission","admindb_save");
		
		action.newOne();//获取角色信息
		
		Admin bean =new Admin();
		bean.setName("test");
		bean.setUsername("test");
		bean.setPassword("test");
		
		List<Role> roles=action.getRoles();
		if(roles.size()>=1)
			bean.setRole(roles.get(0));
		
		action.setBean(bean);
		
		setSession();
		String result = proxy.execute();//执行操作，保存记录
		
		assertSuccess(result);


		List<Admin> admins=action.getAdminService().getList(" and username='test' and (deleted is null or deleted='')");
		if(admins.size()>=1){
			action.setBean(admins.get(0));
			action.delete();//删除记录
		}
		
	}

	@Test
	public void testDelete() throws Exception{
		AdminAction action = createAction(AdminAction.class, "/permission","admindb_delete");
		//save a record
		action.newOne();//获取角色信息
		
		Admin bean =new Admin();
		bean.setName("test1");
		bean.setUsername("test1");
		bean.setPassword("test1");
		
		List<Role> roles=action.getRoles();
		if(roles.size()>=1)
			bean.setRole(roles.get(0));
		
		action.setBean(bean);
		
		setSession();
		action.save();//执行操作，保存记录
		//save a record end
		
		String beanid=getOneAdminId(" and username='test1' and deleted is null ");
		
		if("-1".equalsIgnoreCase(beanid)){
			fail(" no exist record to deleted ");
		}
		
		bean =new Admin();
		bean.setId(beanid);
		action.setBean(bean);
		
		setSession();
		
		String result = proxy.execute();//执行操作，保存记录
		
		assertSuccess(result);

	}



	@Test
	public void testUpdate() throws Exception {
		AdminAction action = createAction(AdminAction.class, "/permission",
				"admindb_update");

		String beanid = getOneAdminId(" and username='test'");

		if ("-1".equalsIgnoreCase(beanid)) {
			fail(" no exist record to update ");
		}

		Admin bean = new Admin();
		bean.setId(beanid);
		action.setBean(bean);
		action.edit();//必须选择修改的记录
		
		bean.setName("test0");
		bean.setUsername("test0");
		
		List<Role> roles=action.getRoles();
		if(roles.size()>=1)
			bean.setRole(roles.get(0));
		
		action.setBean(bean);

		setSession();
		
		String result = proxy.execute();// 修改记录
		assertSuccess(result);

	}
	
	@Test
	public void testSearch() throws Exception {
		AdminAction action = createAction(AdminAction.class, "/permission","admin_search");
		String result = proxy.execute();
		assertNotNull(request.getAttribute("ec_totalRows"));
		assertNotNull(request.getAttribute("page"));
		assertSuccess(result);
	}
	
	@Test
	public void testNewOne() throws Exception {
		AdminAction action = createAction(AdminAction.class, "/permission","admin_newOne");
		String result = proxy.execute();//获取角色信息
		List roles=action.getRoles();
		assertNotNull(roles);
		assertSuccess(result);
	}
	
	@Test
	public void testEdit() throws Exception{
		AdminAction action = createAction(AdminAction.class, "/permission","admin_edit");

		String beanid=getOneAdminId(" and username='test'");
		
		if("-1".equalsIgnoreCase(beanid)){
			return;
		}

		Admin editbean=new Admin();
		editbean.setId(beanid);
		action.setBean(editbean);
		
		String result = proxy.execute();//修改记录
		List<Role> roles=action.getRoles();
		Admin bean=action.getBean();
		
		assertNotNull(roles);
		assertNotNull(bean.getName());
		assertNotNull(bean.getUsername());
		assertSuccess(result);

		
	}


	
	private String getOneAdminId(String condition){
		AdminService service=(AdminService) this.getInstance("adminService");
		List<Admin> admins=service.getList(condition);//" and (deleted is null or deleted ='')"
		
		if(admins.size()>=1){
			Admin bean=admins.get(0);
			return bean.getId();
		}
		return "-1";
	}

}
