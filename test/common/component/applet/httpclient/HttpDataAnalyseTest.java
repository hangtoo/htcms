package common.component.applet.httpclient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Test;

import common.component.applet.httpclient.HttpDataAnalyse;

public class HttpDataAnalyseTest extends TestCase{
	
	@Test
	public void testGetModel(){
		HttpDataAnalyse ana=new HttpDataAnalyse();
		Map ret;
		//ret=ana.getMap("{a=1,b=2}");
		//System.out.println(ret);
		
		ret=ana.getModel("BEAN.RETMSG=STATEMENTCALLBACK; BAD SQL GRAMMAR [{CALL SYS(131)}]; " +
		"NESTED EXCEPTION IS JAVA.SQL.BATCHUPDATEEXCEPTION: UNKNOWN COLUMN 'E001' IN 'FIELD LIST',BEAN.RETCODE=302");
		System.out.println(ret);
		
		ret=ana.getModel("{a=1,b=2,d=[31,32]}");
		
		assertEquals(ret.get("A"),"1");
		System.out.println(ret);
		
		ret=ana.getModel("{o=[31,32],a=1,b=2,c=[{cd=1,ce=2},{cd=11,ce=22}],ddddd=[31,32]}");
		assertEquals(ret.get("A"),"1");
		System.out.println(ret);
		

	}
	

}
