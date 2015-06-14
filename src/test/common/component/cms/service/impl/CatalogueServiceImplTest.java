package test.common.component.cms.service.impl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import test.common.base.BaseStrutsTestCase;

import common.component.IConstants;
import common.component.cms.entity.Catalogue;
import common.component.cms.service.CatalogueService;
import common.component.permission.entity.Role;
import common.component.permission.service.RoleService;


public class CatalogueServiceImplTest extends BaseStrutsTestCase{
	public CatalogueServiceImplTest(String name) {
		super(name);
	}

	@Test
	public void testSave() throws Exception {
		CatalogueService service=(CatalogueService)this.getInstance("catalogService");
		Catalogue bean =new Catalogue();
		bean.setName("test");
		bean.setTablename("table");
		setSession();
		Catalogue Catalogue=service.save(bean);
		assertNotNull(Catalogue);
		
		List<Catalogue> catalogs=service.getList(" and tablename='table' and deleted is null ");
		if(catalogs.size()>=1){
			service.delete(catalogs.get(0).getId());//删除记录
		}
	}
	
	@Test
	public void testGetTree() throws Exception {
		CatalogueService service=(CatalogueService)this.getInstance("catalogService");
		Catalogue bean =new Catalogue();
		bean.setName("test");
		bean.setTablename("table");
		
		assertNotNull(service.getTree());
	}
}
