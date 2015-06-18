package common.component.applet.httpclient;

import java.io.IOException;

import junit.framework.TestCase;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.junit.Test;

import common.component.applet.httpclient.HttpPostGet;

public class HttpPostGetTest extends TestCase{

	@Test
	public void testGetData()  throws HttpException, IOException{
		HttpPostGet http=new HttpPostGet();
		String str=http.getData("http://www.baidu.com");
		//String str=http.getData("http://localhost:8888/struts2/cms/http_newOne.action?bean.catalogue.id=11");
		//System.out.println("newone "+str);
		//str=http.getData("http://localhost:8888/struts2/cms/http_edit.action?bean.id=4028814621b310110121b316d2530001&bean.catalogue.id=11");
		str=http.getData("http://www.szse.cn//szseWeb/FrontController.szse?ACTIONID=7&AJAX=AJAX-TRUE&CATALOGID=1803&TABKEY=tab1&txtQueryDate=2015-04-01&REPORT_ACTION=search","GB2312",true);
		System.out.println("edit "+str);
		
	}

	@Test
	public void testPostData() throws HttpException, IOException {
		HttpPostGet http=new HttpPostGet();
		NameValuePair[] data ={
                new NameValuePair("bean.catalogue.id", "11"),
                new NameValuePair("bean.id", "4028814621b310110121b316d2530001"),
        };
		String ret=http.postData("http://localhost:8888/struts2/cms/httpdb_save.action",data);
		System.out.println("postdata "+ret);
		
		ret=http.postData("http://localhost:8888/struts2/cms/httpdb_update.action",data);
		System.out.println("postdata1 "+ret);
	}
	
	@Test
	public void testGetURI()  throws HttpException, IOException{
		HttpPostGet http=new HttpPostGet();
		
		String url="http://localhost:8888/struts2/cms/http_newOne.action?bean.catalogue.id=11";
		//url="http://localhost:8888/struts2/cms/http_newOne.action";
		String jsessionid="CDB561840D9143AA6B56A7A882DA4E9E";
		
		System.out.println(url.indexOf("?"));
		
		int q=url.indexOf("?");
		
		if(q!=-1){
			url=url.substring(0,q)+";jsessionid="+jsessionid+url.substring(q);
		}else
			url=url+";jsession="+jsessionid;

		System.out.println("url "+url);
		
		
	}

}
