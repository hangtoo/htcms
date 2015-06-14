package test.common.component.permission.action;

import test.common.base.BaseStrutsTestCase;

import common.component.permission.action.LoginAction;
import common.component.permission.entity.Admin;

public class HelloWorldTest extends BaseStrutsTestCase {
	
	private String verifycode="999999";

	public HelloWorldTest(String name) {
		super(name);
	}

	/**
	 * Invoke all interceptors and specify value of the action class' domain
	 * objects directly.
	 * 
	 * @throws Exception
	 *             Exception
	 */
	public void testInterceptorsBySettingDomainObjects() throws Exception {
		LoginAction action = createAction(LoginAction.class, "/permission",
				"login");
		setSession();
		Admin bean = new Admin();
		bean.setUsername("admin");
		bean.setPassword("admin");
		bean.setCheckcode(verifycode);
		action.setBean(bean);
		
		String result = proxy.execute();
		assertEquals(result, "success");
	}

	/**
	 * Invoke all interceptors and specify value of action class' domain objects
	 * through request parameters.
	 * 
	 * @throws Exception
	 *             Exception
	 */
	public void testInterceptorsBySettingRequestParameters() throws Exception {
		createAction(LoginAction.class, "/permission", "login");
		request.addParameter("title", "heh");
		String result = proxy.execute();
		assertEquals(result, "input");
	}

	/**
	 * Skip interceptors and specify value of action class' domain objects by
	 * setting them directly.
	 * 
	 * @throws Exception
	 *             Exception
	 */
	public void testActionAndSkipInterceptors() throws Exception {
		LoginAction action = createAction(LoginAction.class, "/permission",
				"login");
		setSession();
		Admin bean = new Admin();
		bean.setUsername("admin");
		bean.setPassword("admin");
		bean.setCheckcode(verifycode);
		action.setBean(bean);
		
		String result = action.execute();
		assertEquals(result, "success");
	}
	
/*	public void testValidation() throws Exception {
		SomeAction action = createAction(SomeAction.class, "/site",
				"someAction");
		// lets forget to set a required field: action.setId(123);   
		String result = proxy.execute();
		assertEquals(result, "input");
		assertTrue("Must have one field error",
				action.getFieldErrors().size() == 1);
	}*/
}
