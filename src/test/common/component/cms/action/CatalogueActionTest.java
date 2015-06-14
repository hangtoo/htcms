package test.common.component.cms.action;

import java.util.List;

import org.junit.Test;

import test.common.base.BaseStrutsTestCase;

import common.component.IConstants;
import common.component.cms.action.CatalogueAction;
import common.component.cms.entity.Catalogue;
import common.component.cms.service.CatalogueService;


public class CatalogueActionTest extends BaseStrutsTestCase{

	public CatalogueActionTest(String name) {
		super(name);
	}

	@Test
	public void testSave() throws Exception{
		CatalogueAction action = createAction(CatalogueAction.class, "/cms","cataloguedb_save");
		
		action.newOne();//获取角色信息
		
		Catalogue bean =new Catalogue();
		bean.setName("test");
		bean.setTablename("cms_test");
		bean.setRemark("备注");
		
		action.setBean(bean);
		
		setSession();
		String result = proxy.execute();//执行操作，保存记录
		
		assertSuccess(result);

		List<Catalogue> catalogues=action.getCatalogueService().getList(" and tablename='cms_test' and (deleted is null or deleted='')");
		if(catalogues.size()>=1){
			action.setBean(catalogues.get(0));
			action.delete();//删除记录
		}
		
	}

	@Test
	public void testDelete() throws Exception{
		CatalogueAction action = createAction(CatalogueAction.class, "/cms","cataloguedb_delete");
		//save a record
		action.newOne();//获取角色信息
		
		Catalogue bean =new Catalogue();
		bean.setName("test");
		bean.setTablename("cms_test");
		bean.setRemark("备注");
		
		action.setBean(bean);
		
		setSession();
		action.save();//执行操作，保存记录
		//save a record end
		
		String beanid=getOneCatalogueId(" and tablename='cms_test' and deleted is null ");
		
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
		CatalogueAction action = createAction(CatalogueAction.class, "/cms",
				"cataloguedb_update");

		String beanid = getOneCatalogueId(" and tablename='cms_test'");

		if ("-1".equalsIgnoreCase(beanid)) {
			fail(" no exist record to update ");
		}

		Catalogue bean = new Catalogue();
		bean.setId(beanid);
		action.setBean(bean);
		action.edit();//必须选择修改的记录
		
		bean.setName("test1");
		bean.setTablename("cms_test");
		bean.setRemark("备注");
		
		action.setBean(bean);

		setSession();
		
		String result = proxy.execute();// 修改记录
		assertSuccess(result);

	}
	
	@Test
	public void testSearch() throws Exception {
		CatalogueAction action = createAction(CatalogueAction.class, "/cms","catalogue_search");

		String result = proxy.execute();
		
		/*List<Catalogue> tree=action.getTree();
		
		assertNotNull(tree);
		
		for(int i=0;i<tree.size();i++){
			if(tree.get(i).getParent()!=null)
				assertNotNull(tree.get(i).getParent().getName());
		}*/
		
		assertSuccess(result);
	}
	
	@Test
	public void testNewOne() throws Exception {
		CatalogueAction action = createAction(CatalogueAction.class, "/cms","catalogue_newOne");
		String result = proxy.execute();
		assertSuccess(result);
	}
	
	@Test
	public void testEdit() throws Exception{
		CatalogueAction action = createAction(CatalogueAction.class, "/cms","catalogue_edit");

		String beanid=getOneCatalogueId(" and tablename='cms_test'");
		
		if("-1".equalsIgnoreCase(beanid)){
			return;
		}

		Catalogue editbean=new Catalogue();
		editbean.setId(beanid);
		editbean.setName("test1");
		editbean.setTablename("cms_test1");
		action.setBean(editbean);
		
		String result = proxy.execute();//修改记录
		Catalogue bean=action.getBean();
		
		assertNotNull(bean.getName());
		assertNotNull(bean.getTablename());
		assertSuccess(result);
		
	}

	@Test
	public void testCreateTable() throws Exception{
		CatalogueAction action = createAction(CatalogueAction.class, "/cms","cataloguedb_createTable");

		Catalogue bean=getOneCatalogue(" and tablename='cms_test'");
		
		if(bean==null){
			fail(" no exist record to operate ");
		}
		
		action.setBean(bean);
		
		String result = proxy.execute();//修改记录

		assertSuccess(result);
		
	}
	
	private Catalogue getOneCatalogue(String condition){
		CatalogueService service=(CatalogueService) this.getInstance("catalogueService");
		List<Catalogue> catalogues=service.getList(condition);//" and (deleted is null or deleted ='')"
		
		if(catalogues.size()>=1){
			Catalogue bean=catalogues.get(0);
			return bean;
		}
		return null;
	}
	
	private String getOneCatalogueId(String condition){
		CatalogueService service=(CatalogueService) this.getInstance("catalogueService");
		List<Catalogue> catalogues=service.getList(condition);//" and (deleted is null or deleted ='')"
		
		if(catalogues.size()>=1){
			Catalogue bean=catalogues.get(0);
			return bean.getId();
		}
		return "-1";
	}
}
