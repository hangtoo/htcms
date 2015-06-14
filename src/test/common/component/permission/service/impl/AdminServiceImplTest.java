package test.common.component.permission.service.impl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import test.common.base.BaseStrutsTestCase;

import common.component.IConstants;
import common.component.permission.entity.Admin;
import common.component.permission.entity.Role;
import common.component.permission.service.AdminService;
import common.component.permission.service.RoleService;

public class AdminServiceImplTest extends BaseStrutsTestCase{

	public AdminServiceImplTest(String name) {
		super(name);
	}

	@Test
	public void testSave() throws Exception {
		AdminService service=(AdminService)this.getInstance("adminService");
		Admin bean =new Admin();
		bean.setName("test");
		bean.setUsername("test");
		bean.setPassword("test");
		
		Map session=new Hashtable();
		session.put(IConstants.USER, bean);
		ServletActionContext.getContext().setSession(session);
		
		RoleService roleservice=(RoleService)this.getInstance("roleService");
		List<Role> roles=roleservice.getList();
		if(roles.size()>=1)
			bean.setRole(roles.get(0));
		
		Admin admin=service.save(bean);
		assertNotNull(admin);
		
		List<Admin> admins=service.getList(" and name='test' and deleted is null ");
		if(admins.size()>=1){
			service.delete(admins.get(0).getId());//删除记录
		}
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		AdminService service=(AdminService)this.getInstance("adminService");

		List<Admin> admins=service.getList(" and username='test' ");
		
		if(admins.size()>=1){
			Admin bean=admins.get(0);
			
			bean.setUsername("test1");
			
			Map session=new Hashtable();
			session.put(IConstants.USER, bean);
			ServletActionContext.getContext().setSession(session);

			Admin admin=service.update(bean);
			assertNotNull(admin);
		}
	}

	@Test
	public void testGetByIdSerializable() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetByIdString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetListString() {
		fail("Not yet implemented");
	}

	@Test
	public void testValid() {
		fail("Not yet implemented");
	}

}
