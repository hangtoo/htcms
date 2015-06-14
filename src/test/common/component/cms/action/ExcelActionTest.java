package test.common.component.cms.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.UUIDHexGenerator;
import org.junit.Test;

import test.common.base.BaseStrutsTestCase;

import common.component.cms.action.ExcelAction;
import common.component.cms.entity.Catalogue;
import common.component.cms.entity.Content;
import common.component.cms.service.ContentService;

public class ExcelActionTest extends BaseStrutsTestCase{

	public ExcelActionTest(String name) {
		super(name);
	}

	@Test
	public void testNewOne() throws Exception{
		ExcelAction action = createAction(ExcelAction.class, "/cms","excelfile_newOne");
		
		Catalogue cata=new Catalogue();
		cata.setId("11");
		
		Content bean=new Content();
		bean.setCatalogue(cata);
		
		action.setBean(bean);
		String result = proxy.execute();
		
		assertNotNull(action.getAttributes());
		assertSuccess(result);
	}

	@Test
	public void testEdit() throws Exception{
		ExcelAction action = createAction(ExcelAction.class, "/cms","excelfile_edit");
		
		Catalogue cata=new Catalogue();
		cata.setId("11");
		
		Content bean=new Content();
		bean.setCatalogue(cata);
		bean.setId("1");//to do
		
		action.setBean(bean);
		String result = action.execute();
		
		assertNotNull(action.getAttributes());
		assertNotNull(action.getBean());
		assertSuccess(result);
	}

	@Test
	public void testSave() throws Exception{
		ExcelAction action = createAction(ExcelAction.class, "/cms","exceldb_save");

		Catalogue cata=new Catalogue();
		cata.setId("11");
		
		IdentifierGenerator gen = new UUIDHexGenerator();
		Serializable id=gen.generate(null, null);
		
		Content bean =new Content();
		bean.setId(id.toString());
		bean.setCatalogue(cata);
		action.setBean(bean);
		
		action.newOne();
		
		Map columns=new HashMap();
		columns.put("p_id", "'"+id+"'");
		columns.put("p_title", "test");
		//columns.put("p_content", "");
		action.setColumns(columns);
		
		//File file=new File("test1");
		//ServletActionContext.getRequest().setAttribute("p_content",file);
		
		setSession();
		String result = proxy.execute();//执行操作，保存记录
		
		assertSuccess(result);

		action.getContentService().delete(bean);
	}

	@Test
	public void testDelete() throws Exception {
		ExcelAction action = createAction(ExcelAction.class, "/cms","exceldb_delete");
		//save a record
		
		Catalogue cata=new Catalogue();
		cata.setId("11");
		
		IdentifierGenerator gen = new UUIDHexGenerator();
		Serializable id=gen.generate(null, null);
		
		Content bean =new Content();
		bean.setId(id.toString());
		bean.setCatalogue(cata);
		action.setBean(bean);
		
		action.newOne();
		
		Map columns=new HashMap();
		columns.put("p_id", "'"+id+"'");
		columns.put("p_title", "test1");
		action.setColumns(columns);
		
		setSession();
		action.save();//执行操作，保存记录
		//save a record end
		
		String beanid=getOneContentId(bean," p_id,p_title "," p_title='test1' and p_deleted is null ");
		
		if(beanid==null||"-1".equalsIgnoreCase(beanid)){
			fail(" no exist record to deleted ");
		}
		
		bean =new Content();
		bean.setId(beanid);
		bean.setCatalogue(cata);
		action.setBean(bean);
		
		setSession();
		
		String result = proxy.execute();//执行操作，保存记录
		
		assertSuccess(result);
	}

	@Test
	public void testUpdate() throws Exception {
		ExcelAction action = createAction(ExcelAction.class, "/cms","exceldb_update");
		
		Catalogue cata=new Catalogue();
		cata.setId("11");
		Content bean = new Content();
		bean.setCatalogue(cata);
		
		String beanid=getOneContentId(bean," p_id,p_title "," p_title='test' ");
		
		if (beanid==null||"-1".equalsIgnoreCase(beanid)) {
			fail(" no exist record to update ");
		}
		bean.setId(beanid);
		
		action.setBean(bean);
		action.edit();//必须选择修改的记录
		
		Map columns=new HashMap();
		columns.put("p_title", "test0");
		action.setColumns(columns);
		
		setSession();
		
		String result = proxy.execute();// 修改记录
		assertSuccess(result);
	}

	@Test
	public void testSearch()  throws Exception{
		ExcelAction action = createAction(ExcelAction.class, "/cms","excel_search");
		
		Catalogue cata=new Catalogue();
		cata.setId("11");
		Content bean = new Content();
		bean.setCatalogue(cata);
		action.setBean(bean);
		
		String result = proxy.execute();
		assertNotNull(request.getAttribute("ec_totalRows"));
		assertNotNull(request.getAttribute("page"));
		assertSuccess(result);
	}
	
	private String getOneContentId(Content content,String selectClause,String whereClause){
		ContentService service=(ContentService) this.getInstance("contentService");
		List<Map> contents=service.getList(content,selectClause,whereClause);//" and (deleted is null or deleted ='')"
		
		if(contents.size()>=1){
			Map map=contents.get(0);
			return ""+map.get("P_ID");
		}
		return "-1";
	}
}
