package test.common.util;

import java.util.Map;

import org.junit.Test;

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

}
