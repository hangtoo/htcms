package common.component.cms.action;

import java.sql.Types;
import java.util.List;

import org.junit.Test;

import common.base.BaseStrutsTestCase;
import common.component.cms.entity.Attribute;
import common.component.cms.entity.Catalogright;
import common.component.cms.service.AttributeService;


public class AttributeActionTest  extends BaseStrutsTestCase{
	
	public AttributeActionTest(String name) {
		super(name);
	}

	@Test
	public void testSave() throws Exception{
		AttributeAction action = createAction(AttributeAction.class, "/cms","attributedb_save");
		
		action.newOne();//获取角色信息
		
		Attribute bean =new Attribute();
		bean.setName("test");
		bean.setColumn("test");
		bean.setType(""+Types.VARCHAR);
		bean.setLength("255");
		
		setSession();
		
		List<Catalogright> catas=common.component.cms.util.GlobeData.getCatalogrightTree();
		if(catas.size()>=1)
			bean.setCatalogue(catas.get(0).getCatalogue());
		
		action.setBean(bean);
		
		String result = proxy.execute();//执行操作，保存记录
		
		assertSuccess(result);


		List<Attribute> attributes=action.getAttributeService().getList(" and name='test' and (deleted is null or deleted='')");
		if(attributes.size()>=1){
			action.setBean(attributes.get(0));
			action.delete();//删除记录
		}
		
	}

	@Test
	public void testDelete() throws Exception{
		AttributeAction action = createAction(AttributeAction.class, "/cms","attributedb_delete");
		//save a record
		action.newOne();//获取角色信息
		
		Attribute bean =new Attribute();
		bean.setName("test1");
		bean.setColumn("test1");
		bean.setType(""+Types.VARCHAR);
		bean.setLength("255");
		
		setSession();
		
		List<Catalogright> catas=common.component.cms.util.GlobeData.getCatalogrightTree();
		if(catas.size()>=1)
			bean.setCatalogue(catas.get(0).getCatalogue());
		
		action.setBean(bean);
		
		action.save();//执行操作，保存记录
		//save a record end
		
		String beanid=getOneAttributeId(" and name='test1' and deleted is null ");
		
		if("-1".equalsIgnoreCase(beanid)){
			fail(" no exist record to deleted ");
		}
		
		bean =new Attribute();
		bean.setId(beanid);
		if(catas.size()>=1)
			bean.setCatalogue(catas.get(0).getCatalogue());
		action.setBean(bean);
		
		setSession();
		
		String result = proxy.execute();//执行操作，保存记录
		
		assertSuccess(result);

	}



	@Test
	public void testUpdate() throws Exception {
		AttributeAction action = createAction(AttributeAction.class, "/cms",
				"attributedb_update");

		String beanid = getOneAttributeId(" and name='test'");

		if ("-1".equalsIgnoreCase(beanid)) {
			fail(" no exist record to update ");
		}

		Attribute bean = new Attribute();
		bean.setId(beanid);
		action.setBean(bean);
		action.edit();//必须选择修改的记录
		
		bean.setName("test0");
		bean.setColumn("test0");
		bean.setType(""+Types.VARCHAR);
		bean.setLength("255");

		setSession();
		
		List<Catalogright> catas=common.component.cms.util.GlobeData.getCatalogrightTree();
		if(catas.size()>=1)
			bean.setCatalogue(catas.get(0).getCatalogue());
		
		action.setBean(bean);
		
		String result = proxy.execute();// 修改记录
		assertSuccess(result);

	}
	
	@Test
	public void testSearch() throws Exception {
		AttributeAction action = createAction(AttributeAction.class, "/cms","attribute_search");
		String result = proxy.execute();
		assertNotNull(request.getAttribute("ec_totalRows"));
		assertNotNull(request.getAttribute("page"));
		assertSuccess(result);
	}
	
	@Test
	public void testNewOne() throws Exception {
		AttributeAction action = createAction(AttributeAction.class, "/cms","attribute_newOne");
		String result = proxy.execute();//获取角色信息
		assertSuccess(result);
	}
	
	@Test
	public void testEdit() throws Exception{
		AttributeAction action = createAction(AttributeAction.class, "/cms","attribute_edit");

		String beanid=getOneAttributeId(" and name='test'");
		
		if("-1".equalsIgnoreCase(beanid)){
			return;
		}

		Attribute editbean=new Attribute();
		editbean.setId(beanid);
		action.setBean(editbean);
		
		String result = proxy.execute();//修改记录
		Attribute bean=action.getBean();
		
		assertNotNull(bean.getName());
		assertNotNull(bean.getCatalogue());
		assertSuccess(result);

	}


	
	private String getOneAttributeId(String condition){
		AttributeService service=(AttributeService) this.getInstance("attributeService");
		List<Attribute> attributes=service.getList(condition);//" and (deleted is null or deleted ='')"
		
		if(attributes.size()>=1){
			Attribute bean=attributes.get(0);
			return bean.getId();
		}
		return "-1";
	}
}
