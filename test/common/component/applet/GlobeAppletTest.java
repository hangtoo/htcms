package common.component.applet;

import static org.junit.Assert.fail;

import org.junit.Test;

import common.component.applet.GlobeApplet;

public class GlobeAppletTest {

	@Test
	public void testNewOneExcel() throws Exception {
		GlobeApplet applet=new GlobeApplet();
		applet.init();
		applet.setTemplatePath("c:\\");
		applet.newOneExcel("11","新闻");
		
		try {
			Thread.sleep(10000);
		} catch (Exception e) {
		}
		
		applet.closeExcel();
	}

	@Test
	public void testEditExcel() throws Exception {
		GlobeApplet applet=new GlobeApplet();
		applet.init();
		applet.setTemplatePath("c:\\");
		applet.editExcel("11","新闻","http://localhost:8888/struts2/cms/http_edit.action?bean.id=4028814621451597012145179a600002&bean.catalogue.id=11");
		
		try {
			Thread.sleep(6000);
		} catch (Exception e) {
		}
		
		applet.closeExcel();
	}

}
