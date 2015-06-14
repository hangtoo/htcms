package common.component.applet.excel;

import java.util.Map;

import org.apache.log4j.Logger;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class ExcelDispatch {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	private ActiveXComponent excel;
	
	private Dispatch workbooks;
	
	private Dispatch workbook;
	
	private String templatePath;

	public ExcelDispatch(String templatePath) {
		excel = new ActiveXComponent("Excel.Application");
		// ComThread.InitSTA();
		ComThread.InitMTA();
		
		this.templatePath=templatePath;
	}

	public void openExcelfile(String templateName, String password) {
		
		String path=templateName;

		if(log.isDebugEnabled())
			log.debug(path);
		
		excel.setProperty("Visible", true);
		
		workbooks = excel.getProperty("Workbooks").toDispatch();

		workbook = Dispatch.call(workbooks, "Open", path, // FileName
				3, // UpdateLinks
				false, // Readonly
				5, // Format
				password // Password
				).toDispatch();
	}
	
	public void closeExcelfile(){
		try{
			if(workbook!=null){
				Dispatch.call(workbook, "Close", new Variant(false));
			}
			Thread.sleep(10);
		}catch(Exception e){
			log.error(e);
		}
		
		try{
			if(workbooks!=null){
				Dispatch.call(workbooks, "Close");
			}
		}catch(Exception e){
			log.error(e);
		}
		
		try{
			Thread.sleep(10);
			
			if(excel!=null){
				excel.invoke("Quit", new Variant[] {});
				log.info("Quit");
			}
			
			Thread.sleep(10);
		}catch(Exception e){
			log.error(e);
		}
		ComThread.Release();
	}

}
