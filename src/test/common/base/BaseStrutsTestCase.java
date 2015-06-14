package test.common.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.Dispatcher;
import org.apache.struts2.views.JspSupportServlet;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.ContextLoader;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionProxyFactory;
import common.component.IConstants;
import common.component.cms.entity.Catalogright;
import common.component.cms.entity.Catalogue;
import common.component.permission.entity.Admin;
import common.component.permission.entity.Role;

/**
 * @author Zarar Siddiqi struts2.0 action测试基础类
 */
public abstract class BaseStrutsTestCase extends TestCase {
	private static final String CONFIG_LOCATIONS = "classpath:spring/applicationContext.xml"
			+ ",classpath:common/component/permission/spring.xml"
			+ ",classpath:common/component/cms/spring.xml"
			+ ",classpath:common/component/upload/spring.xml"
			+ ",classpath:common/component/ectable/spring.xml";
	private static ApplicationContext applicationContext;
	private Dispatcher dispatcher;
	protected ActionProxy proxy;
	protected static MockServletContext servletContext;
	protected static MockServletConfig servletConfig;
	protected MockHttpServletRequest request;
	protected MockHttpServletResponse response;

	public BaseStrutsTestCase(String name) {
		super(name);
	}

	/**
	 * Created action class based on namespace and name
	 * 
	 * @param clazz
	 *            Class for which to create Action
	 * @param namespace
	 *            Namespace of action
	 * @param name
	 *            Action name
	 * @return Action class
	 * @throws Exception
	 *             Catch-all exception
	 */
	@SuppressWarnings("unchecked")
	protected <T> T createAction(Class<T> clazz, String namespace, String name)
			throws Exception {

		// create a proxy class which is just a wrapper around the action call.
		// The proxy is created by checking the namespace and name against the
		// <B style="COLOR: black; BACKGROUND-COLOR: #a0ffff">struts</B>.xml
		// configuration
		proxy = dispatcher.getContainer().getInstance(ActionProxyFactory.class)
				.createActionProxy(namespace, name, null, true, false);

		// by default, don't pass in any request parameters
		proxy.getInvocation().getInvocationContext().setParameters(
				new HashMap());

		// do not execute the result after executing the action
		proxy.setExecuteResult(true);

		// set the <B style="COLOR: black; BACKGROUND-COLOR:
		// #ff9999">actions</B> context to the one which the proxy is using
		ServletActionContext.setContext(proxy.getInvocation()
				.getInvocationContext());
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		ServletActionContext.setRequest(request);
		ServletActionContext.setResponse(response);
		ServletActionContext.setServletContext(servletContext);
		return (T) proxy.getAction();
	}

	protected void setUp() throws Exception {
		if (applicationContext == null) {
			// this is the first time so initialize <B style="COLOR: black;
			// BACKGROUND-COLOR: #ff66ff">Spring</B> context
			servletContext = new MockServletContext();
			servletContext.addInitParameter(
					ContextLoader.CONFIG_LOCATION_PARAM, CONFIG_LOCATIONS);
			applicationContext = (new ContextLoader())
					.initWebApplicationContext(servletContext);

			// <B style="COLOR: black; BACKGROUND-COLOR: #a0ffff">Struts</B> JSP
			// support servlet (for Freemarker)
			new JspSupportServlet().init(new MockServletConfig(servletContext));
		}
		// Dispatcher is the guy that actually handles all requests. Pass in
		// an empty. Map as the parameters but if you want to change stuff like
		// what config files to read, you need to specify them here. Here's how
		// to
		// scan packages for <B style="COLOR: black; BACKGROUND-COLOR:
		// #ff9999">actions</B> (thanks to Hardy Ferentschik - Comment 66)
		// (see Dispatcher's source code)
		HashMap params = new HashMap();
		params.put("actionPackages", "com.arsenalist.action");
		dispatcher = new Dispatcher(servletContext, params);
		dispatcher.init();
		Dispatcher.setInstance(dispatcher);
	}

	public Object getInstance(String name) {
		return applicationContext.getBean(name);
	}

	// added self
	public void assertSuccess(String str) {
		super.assertEquals(str, "success");
		System.out.println("-------------");
	}

	protected void setSession(){// 设置登陆用户
		String verifycode="999999";
		Admin bean =new Admin();
		bean.setId("1");
		bean.setName("admin");
		bean.setUsername("admin");
		bean.setPassword("admin");
		bean.setCheckcode(verifycode);
		Role role=new Role();
		role.setId("1");
		bean.setRole(role);
		
		Map session=new Hashtable();
		session.put(IConstants.USERKEY, bean);
		session.put(IConstants.VERIFYCODE,verifycode);
		ServletActionContext.getContext().setSession(session);
		
		
		Catalogue cata=new Catalogue();
		cata.setId("11");
		cata.setName("新闻");
		cata.setTablename("cms_news");
		Catalogright e=new Catalogright();
		e.setCatalogue(cata);
		
		List<Catalogright> tree= new ArrayList<Catalogright>();
		tree.add(e);
		
		common.component.cms.util.GlobeData.catalogrightTree.put("1", tree);

	}
}
