package common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.LocalizedResourceHelper;

import common.util.ExcelUtil;

public class ExcelUtilTest {
	protected Logger log = Logger.getLogger(this.getClass());
	ExcelUtil excelgen=new ExcelUtil();
	private static final String EXTENSION = ".xls";
	
	private InputStream getStream(String url) throws Exception {
		LocalizedResourceHelper helper = new LocalizedResourceHelper();
		//java.util.Locale userLocale = RequestContextUtils.getLocale(request);
		Resource inputFile = helper.findLocalizedResource(url, EXTENSION,null);
		if (log.isDebugEnabled())
			log.debug("Loading Excel workbook from " + inputFile);
		return inputFile.getInputStream();
	}
	
	@Test
	public void testBuildExcelDocument() throws IOException, Exception {
		POIFSFileSystem fs = new POIFSFileSystem(getStream("/common/component/cms/template/采购订货"));
    	HSSFWorkbook workbook=new HSSFWorkbook(fs);
    	
    	Map model=new HashMap();
    	model.put("USER", "经手人");
    	model.put("NO", "123");
    	model.put("UNIT", "个");
    	model.put("PRICE", "19");
    	model.put("NUM", "120");
    	model.put("SIZE", "111");
    	
		excelgen.buildExcelDocument(model, workbook);
		
		File documentFile=new File("c:/a.xls");
		FileOutputStream out=new FileOutputStream(documentFile);
		workbook.write(out);
		out.flush();
	}

	@Test
	public void testGetExcelModel() throws IOException, Exception{
		POIFSFileSystem templatefs = new POIFSFileSystem(getStream("/common/component/cms/template/采购订货"));
    	HSSFWorkbook templateworkbook=new HSSFWorkbook(templatefs);
    	
		POIFSFileSystem fs = new POIFSFileSystem(getStream("/common/component/cms/template/temp_edit"));
    	HSSFWorkbook workbook=new HSSFWorkbook(fs);
    	
    	Map model=excelgen.getExcelModel(workbook, templateworkbook);
    	
    	Iterator key=model.keySet().iterator();
    	
    	while(key.hasNext()){
    		Object obj=key.next();
    		System.out.println(obj+":"+model.get(obj));
    	}
    	
    	
    	
	}

}
