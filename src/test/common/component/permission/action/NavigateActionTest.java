package test.common.component.permission.action;

import java.util.List;

import org.junit.Test;

import test.common.base.BaseStrutsTestCase;

import common.component.permission.action.NavigateAction;
import common.component.permission.entity.Navigate;
import common.component.permission.service.NavigateService;


public class NavigateActionTest extends BaseStrutsTestCase{

	public NavigateActionTest(String name) {
		super(name);
	}

	@Test
	public void testSave() throws Exception{
		NavigateAction action = createAction(NavigateAction.class, "/permission","navigatedb_save");
		
		action.newOne();//获取角色信息
		
		Navigate bean =new Navigate();
		bean.setName("test");
		bean.setRemark("备注");
		bean.setUrl("permission/role_search.action");
		
		action.setBean(bean);
		
		setSession();
		String result = proxy.execute();//执行操作，保存记录
		
		assertSuccess(result);

		List<Navigate> navigates=action.getNavigateService().getList(" and name='test' and (deleted is null or deleted='')");
		if(navigates.size()>=1){
			action.setBean(navigates.get(0));
			action.delete();//删除记录
		}
		
	}

	@Test
	public void testDelete() throws Exception{
		NavigateAction action = createAction(NavigateAction.class, "/permission","navigatedb_delete");
		//save a record
		action.newOne();//获取角色信息
		
		Navigate bean =new Navigate();
		bean.setName("test");
		bean.setUrl("permission/admin_search.action");
		
		action.setBean(bean);
		
		setSession();
		action.save();//执行操作，保存记录
		//save a record end
		
		String beanid=getOneNavigateId(" and name='test' and deleted is null ");
		
		if("-1".equalsIgnoreCase(beanid)){
			fail(" no exist record to deleted ");
		}
		
		action.setCheckrecords("'"+beanid+"'");
		
		setSession();
		
		String result = proxy.execute();//执行操作，保存记录
		
		assertSuccess(result);

	}

	@Test
	public void testUpdate() throws Exception {
		NavigateAction action = createAction(NavigateAction.class, "/permission",
				"navigatedb_update");

		String beanid = getOneNavigateId(" and name='test'");

		if ("-1".equalsIgnoreCase(beanid)) {
			fail(" no exist record to update ");
		}

		Navigate bean = new Navigate();
		bean.setId(beanid);
		action.setBean(bean);
		action.edit();//必须选择修改的记录
		
		bean.setName("test1");
		bean.setRemark("备注");
		bean.setUrl("permission/login.action");
		
		action.setBean(bean);

		setSession();
		
		String result = proxy.execute();// 修改记录
		assertSuccess(result);

	}
	
	@Test
	public void testSearch() throws Exception {
		NavigateAction action = createAction(NavigateAction.class, "/permission","navigate_search");
		String result = proxy.execute();
		assertNotNull(action.getTree());
		List<Navigate> tree=action.getTree();
		
		assertNotNull(tree);
		
		for(int i=0;i<tree.size();i++){
			if(tree.get(i).getParent()!=null)
				assertNotNull(tree.get(i).getParent().getName());
		}
		
		assertSuccess(result);
	}
	
	@Test
	public void testNewOne() throws Exception {
		NavigateAction action = createAction(NavigateAction.class, "/permission","navigate_newOne");
		String result = proxy.execute();
		assertSuccess(result);
	}
	
	@Test
	public void testEdit() throws Exception{
		NavigateAction action = createAction(NavigateAction.class, "/permission","navigate_edit");

		String beanid=getOneNavigateId(" and name='test'");
		
		if("-1".equalsIgnoreCase(beanid)){
			return;
		}

		Navigate editbean=new Navigate();
		editbean.setId(beanid);
		action.setBean(editbean);
		
		String result = proxy.execute();//修改记录
		Navigate bean=action.getBean();
		
		assertNotNull(bean.getName());
		assertNotNull(bean.getUrl());
		assertSuccess(result);

		
	}


	
	private String getOneNavigateId(String condition){
		NavigateService service=(NavigateService) this.getInstance("navigateService");
		List<Navigate> navigates=service.getList(condition);//" and (deleted is null or deleted ='')"
		
		if(navigates.size()>=1){
			Navigate bean=navigates.get(0);
			return bean.getId();
		}
		return "-1";
	}
}
