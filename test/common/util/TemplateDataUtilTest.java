package common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;

import org.junit.Test;

import common.component.applet.httpclient.HttpDataAnalyse;
import common.component.applet.httpclient.HttpPostGet;
import common.util.TemplateDataUtil;

public class TemplateDataUtilTest {

	@Test
	public void testGetData() {
		
		String ret="<table><tr><td>信息查询</td><td>信息查询内容</td></tr></table><table><tr><td>信息查询</td><td>信息查询内容</td></tr></table>";
		
		String template="<table><tr><td>{title}</td><td>{content}</td></tr></table><table><tr><td>{title1}</td><td>{content1}</td></tr></table>";
		Map<String,String> data=TemplateDataUtil.getData(ret, template);
		
		for (String key : data.keySet()) {
			   System.out.println("key= "+ key + " and value= " + data.get(key));
		}
	}

	@Test
	public void testGetData1() throws FileNotFoundException {
		
		HttpPostGet http=new HttpPostGet();
		String ret=http.getData("http://www.szse.cn//szseWeb/FrontController.szse?ACTIONID=7&AJAX=AJAX-TRUE&CATALOGID=1803&TABKEY=tab1&txtQueryDate=2015-04-01&REPORT_ACTION=search","GB2312",true);

		TemplateDataUtil.setFileTemplate("G:/workspace/struts2/resources/common/util/template.txt");
	
		Map<String,String> data=TemplateDataUtil.getData(ret);
		
		for (String key : data.keySet()) {
			   System.out.println("key= "+ key + " and value= " + data.get(key));
		}
	}
	
}
