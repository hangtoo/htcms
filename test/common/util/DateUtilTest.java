package common.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.LocalizedResourceHelper;

import common.util.DateUtil;

public class DateUtilTest {

	@Test
	public void testGetTimeNow() {
		System.out.println(DateUtil.getTimeNow());
	}
	
	@Test
	public void testPattern() throws IOException {
		Pattern pattern=Pattern.compile("$\\{(.+?)\\}");
		LocalizedResourceHelper helper = new LocalizedResourceHelper();
		Resource inputFile = helper.findLocalizedResource("/common/component/cms/template/新闻", ".xls",null);
		
		POIFSFileSystem fs = new POIFSFileSystem(inputFile.getInputStream());
		HSSFWorkbook workBook = new HSSFWorkbook(fs);
	}
	
	
	public class Ele{
		private String id;
		private String pid;
		private String name;
		public Ele(String id,String pid,String name){
			this.id=id;
			this.pid=pid;
			this.name=name;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getPid() {
			return pid;
		}
		public void setPid(String pid) {
			this.pid = pid;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
	Map<String,Ele> p=new HashMap();
	private String heh(Ele ele){
		
		if(ele.getPid()!=null){
			return heh(p.get(ele.getPid()));
		}else
			return ele.getName();
		
	}

}
