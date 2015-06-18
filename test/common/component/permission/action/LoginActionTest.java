package common.component.permission.action;

import java.util.List;

import org.junit.Test;

import common.base.BaseStrutsTestCase;

import common.component.permission.util.GlobeData;
import common.component.permission.action.LoginAction;
import common.component.permission.entity.Admin;
import common.component.permission.entity.Permission;

public class LoginActionTest extends BaseStrutsTestCase {

	private String verifycode="999999";
	
	public LoginActionTest(String name) {
		super(name);
	}

	@Test
	public void testExecute() throws Exception {
		LoginAction action = createAction(LoginAction.class, "/permission",
				"login");
		setSession();
		Admin bean = new Admin();
		bean.setUsername("admin");
		bean.setPassword("admin");
		bean.setCheckcode(verifycode);
		action.setBean(bean);
		String result = proxy.execute();
		
		List<Permission> tree=GlobeData.getMenuTree();//action.getTree();
		for(int i=0;i<tree.size();i++){
			assertNotNull(tree.get(i).getNavigate().getName());
			if(tree.get(i).getNavigate().getParent()!=null)
				assertNotNull(tree.get(i).getNavigate().getParent().getId());
		}
		
		assertEquals(result, "success");
	}

}
