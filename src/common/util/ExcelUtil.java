package common.util;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class ExcelUtil {
	private Pattern pattern=Pattern.compile("\\$\\{(.+?)\\}");
	private Matcher matcher;
	protected Logger log = Logger.getLogger(this.getClass());
	
	private int COLNUM=255;

	//根据model的数值，填充excel表格
	public void buildExcelDocument(Map<String,String> model, HSSFWorkbook hssfworkbook) throws Exception {
		
		HSSFSheet sheet = hssfworkbook.getSheetAt(0);

        int firstrow=sheet.getFirstRowNum();
        int lastrow=sheet.getLastRowNum();
        
        if(log.isDebugEnabled())
        	log.debug(lastrow);
        
        
        //遍历每个表格，并根据map的数值填充数值
        for(int i=firstrow;i<=lastrow;i++){
        	HSSFRow sheetRow = sheet.getRow(i);
        	
        	if(sheetRow==null)
        		continue;
        	
            if(log.isDebugEnabled())
            	log.debug(i+":"+sheetRow.getLastCellNum());
        	
        	for(int j=sheetRow.getFirstCellNum();j<=sheetRow.getLastCellNum()&&j<=COLNUM;j++){
        		HSSFCell cell=getCell(sheetRow,j);
        		
        		if(cell==null)
        			continue;
        		
        		String cellvalue="";

        		switch(cell.getCellType()){
        		case HSSFCell.CELL_TYPE_STRING:
        			cellvalue=cell.getStringCellValue();
        			break;
        		case HSSFCell.CELL_TYPE_NUMERIC:
        			cellvalue=""+cell.getNumericCellValue();
        			break;
        		case HSSFCell.CELL_TYPE_BOOLEAN:
        			cellvalue=""+cell.getBooleanCellValue();
        			break;
        			
        		case HSSFCell.CELL_TYPE_FORMULA:
        			//取得公式单元格的公式,重新设置
        			cell.setCellFormula(cell.getCellFormula());
        			break;
        		}
        		
        		//为excel设值
        		if(!StringUtil.isEmptyString(cellvalue)){
        			matcher=pattern.matcher(cellvalue);
        			
        			while (matcher.find()){
        				String value="";
        				if(model!=null){
        					value=model.get(matcher.group(1).trim().toUpperCase());
        				}
        				cellvalue=matcher.replaceFirst(getReplaced(value));
        				if(log.isDebugEnabled())
        					log.debug(cellvalue);
        				setText(cell,cellvalue);
        			}
        		}
        	}
        }
	}

/*	public List<Map<String,String>> getSqlValue(HSSFWorkbook hssfworkbook,boolean isRowrec){
		List<Map<String,String>> ret=new ArrayList<Map<String,String>>();
		
    	HSSFSheet hssfsheet = hssfworkbook.getSheetAt(0);

        int firstrow=hssfsheet.getFirstRowNum();
        int lastrow=hssfsheet.getLastRowNum();
		
        if(log.isDebugEnabled())
        	log.debug(lastrow);
        
        //遍历每个表格，并根据获取数值填充map
        for(int i=firstrow;i<=lastrow;i++){
        	HSSFRow sheetRow = hssfsheet.getRow(i);
        	
        	if(sheetRow==null)
        		continue;
        	
            if(log.isDebugEnabled())
            	log.debug(i+":"+sheetRow.getLastCellNum());
        	
        	for(int j=sheetRow.getFirstCellNum();j<=sheetRow.getLastCellNum()&&j<=COLNUM;j++){
        		Map<String,String> ele=new HashMap<String,String>();
        		HSSFCell cell=getCell(sheetRow,j);
        		
        		if(cell==null)
        			continue;
        		
        		String cellvalue=null;
        		switch(cell.getCellType()){
        		case HSSFCell.CELL_TYPE_BOOLEAN:
        			cellvalue=""+cell.getBooleanCellValue();
        			break;
        		case HSSFCell.CELL_TYPE_NUMERIC:
        			cellvalue=""+cell.getNumericCellValue();
        			break;
        		case HSSFCell.CELL_TYPE_STRING:
        			cellvalue=cell.getStringCellValue();
        			break;

                case HSSFCell.CELL_TYPE_BLANK:
                    break;
                case HSSFCell.CELL_TYPE_ERROR:
                    break;

                // CELL_TYPE_FORMULA will never happen
                case HSSFCell.CELL_TYPE_FORMULA: 
                    break;
        		}
        		if(cellvalue!=null){
        			ele.put(""+j,);
        		}
        	}

        	ret.add();
        }
        
        
		return null;
	}*/
	
	
	/**
	 * 获取标题
	 * @param hssfworkbook
	 * @param isRowrec true一行一条记录，还是一行有多条记录
	 * 对标题行中，重复出现的第一项，做特别标记，以此认为为该行第二条记录的开始标志
	 * @return
	 * @throws Exception
	 */
	public List<String> getSqlColumn(HSSFWorkbook hssfworkbook,boolean isRowrec) throws Exception{
		List<String> ret=new ArrayList<String>();
		List<String> titles=getExcelTitle(hssfworkbook,isRowrec);
		String title;
		for(int i=0;i<titles.size();i++){
			title=PinYinUtil.getFullSpell(titles.get(i)).replaceAll(" ", "_").replaceAll(",", "_")+"_"+i;

			if(isRowrec){
				if(ret.contains(title)){//保护处理
					title=title+"0";
					while(ret.contains(title)){
						title=title+"0";
					}
					ret.add(i,title);
					
				}else{
					ret.add(i,title);
				}
				
			}else{
				//需要处理一行分多条记录，比较麻烦
				//遗留给调用者
				ret.add(i,title);
			}
		}
		
		
		return null;
	}
	
	/**
	 * 获取标题
	 * @param hssfworkbook
	 * @param isRowrec true一行一条记录，还是一行有多条记录
	 * @return
	 * @throws Exception
	 */
    public List<String> getExcelTitle(HSSFWorkbook hssfworkbook,boolean isRowrec) throws Exception{
    	
    	List<String> ret=new ArrayList<String>();
    	boolean getTitle=false;
    	
    	HSSFSheet hssfsheet = hssfworkbook.getSheetAt(0);

        int firstrow=hssfsheet.getFirstRowNum();
        int lastrow=hssfsheet.getLastRowNum();
    	
        if(log.isDebugEnabled())
        	log.debug(lastrow);
        
        //遍历每个表格，并根据获取数值填充map
        for(int i=firstrow;i<=lastrow;i++){
        	HSSFRow sheetRow = hssfsheet.getRow(i);
        	
        	if(sheetRow==null)
        		continue;
        	
            if(log.isDebugEnabled())
            	log.debug(i+":"+sheetRow.getLastCellNum());
        	
        	for(int j=sheetRow.getFirstCellNum();j<=sheetRow.getLastCellNum()&&j<=COLNUM;j++){
        		HSSFCell cell=getCell(sheetRow,j);
        		
        		if(cell==null)
        			continue;
        		
        		String cellvalue=null;
        		switch(cell.getCellType()){
        		case HSSFCell.CELL_TYPE_BOOLEAN:
        			cellvalue=""+cell.getBooleanCellValue();
        			break;
        		case HSSFCell.CELL_TYPE_NUMERIC:
        			cellvalue=""+cell.getNumericCellValue();
        			break;
        		case HSSFCell.CELL_TYPE_STRING:
        			cellvalue=cell.getStringCellValue();
        			break;

                case HSSFCell.CELL_TYPE_BLANK:
                    break;
                case HSSFCell.CELL_TYPE_ERROR:
                    break;

                // CELL_TYPE_FORMULA will never happen
                case HSSFCell.CELL_TYPE_FORMULA: 
                    break;
        		}
        		if(cellvalue!=null){
        			if(ret.contains(cellvalue)){
        				if(isRowrec){//简化处理了
        					ret.add(j,cellvalue+j);
        				}else{
        					//遗留给调用者
        					ret.add(j,cellvalue);
        				}
        			}else{
        				ret.add(j,cellvalue);//getCell(hssfsheet,i,j).getStringCellValue()
        			}
        			getTitle=true;
        		}
        	}
        	if(getTitle){
        		break;
        	}
        	
        }
        
    	return ret;
    }
	
    /**
     * 获取用户填写数据，通过用户文档与模板文档比对填入
     * @param hssfworkbook
     * @param templateBook
     * @return
     * @throws Exception
     */
    public Map<String,String> getExcelModel(HSSFWorkbook hssfworkbook,HSSFWorkbook templateBook) throws Exception{

    	Map<String,String> ret=new HashMap<String,String>();
    	
		HSSFSheet sheet = templateBook.getSheetAt(0);
		
		HSSFSheet hssfsheet = hssfworkbook.getSheetAt(0);

        int firstrow=sheet.getFirstRowNum();
        int lastrow=sheet.getLastRowNum();
        
        if(log.isDebugEnabled())
        	log.debug(lastrow);
        
        //遍历每个表格，并根据获取数值填充map
        for(int i=firstrow;i<=lastrow;i++){
        	HSSFRow sheetRow = sheet.getRow(i);
        	
        	if(sheetRow==null)
        		continue;
        	
            if(log.isDebugEnabled())
            	log.debug(i+":"+sheetRow.getLastCellNum());
        	
        	for(int j=sheetRow.getFirstCellNum();j<=sheetRow.getLastCellNum()&&j<=COLNUM;j++){
        		HSSFCell cell=getCell(sheetRow,j);
        		
        		if(cell==null)
        			continue;
        		
        		String cellvalue=null;
        		switch(cell.getCellType()){
        		case HSSFCell.CELL_TYPE_BOOLEAN:
        			cellvalue=""+cell.getBooleanCellValue();
        			break;
        		case HSSFCell.CELL_TYPE_NUMERIC:
        			cellvalue=""+cell.getNumericCellValue();
        			break;
        		case HSSFCell.CELL_TYPE_STRING:
        			cellvalue=cell.getStringCellValue();
        			break;

                case HSSFCell.CELL_TYPE_BLANK:
                    break;
                case HSSFCell.CELL_TYPE_ERROR:
                    break;

                // CELL_TYPE_FORMULA will never happen
                case HSSFCell.CELL_TYPE_FORMULA: 
                    break;
        		}
        		
        		if(!StringUtil.isEmptyString(cellvalue)){
        			matcher=pattern.matcher(cellvalue);
        			
        			while (matcher.find()){
        				
        				String putvalue=null;
        				
        				HSSFCell putcell=getCell(hssfsheet,i,j);
        				
                		switch(putcell.getCellType()){
                		case HSSFCell.CELL_TYPE_BOOLEAN:
                			putvalue=""+putcell.getBooleanCellValue();
                			break;
                		case HSSFCell.CELL_TYPE_NUMERIC:
                			putvalue=""+putcell.getNumericCellValue();
                			break;
                		case HSSFCell.CELL_TYPE_STRING:
                			putvalue=putcell.getStringCellValue();
                			break;

                        case HSSFCell.CELL_TYPE_BLANK:
                            break;
                        case HSSFCell.CELL_TYPE_ERROR:
                            break;

                        // CELL_TYPE_FORMULA will never happen
                        case HSSFCell.CELL_TYPE_FORMULA: 
                            break;
                		}
        				
        				ret.put(matcher.group(1).trim().toLowerCase(),putvalue);//getCell(hssfsheet,i,j).getStringCellValue()
        			}
        			//setText(cell,cellvalue);
        		}
        		
        	}
        }

    	return ret;
    }

    protected HSSFCell getCell(HSSFRow sheetRow,int col)
    {
        HSSFCell cell = sheetRow.getCell((short)col);
        if(cell == null)
            cell = sheetRow.createCell((short)col);
        return cell;
    }
    
    protected HSSFCell getCell(HSSFSheet sheet, int row, int col)
    {
        HSSFRow sheetRow = sheet.getRow(row);
        if(sheetRow == null)
            sheetRow = sheet.createRow(row);
        HSSFCell cell = sheetRow.getCell((short)col);
        if(cell == null)
            cell = sheetRow.createCell((short)col);
        return cell;
    }
	
	private String getReplaced(String value){
		if(value==null)
			return "";
		//return "${"+value+"}";
		
		if(log.isDebugEnabled()){
			//log.debug(value);
			log.debug(URLDecoder.decode(value));
		}
		//return value;
		return URLDecoder.decode(value);
	}
	
    protected void setText(HSSFCell cell, String text)
    {
		if(cell.getCellStyle().getDataFormat()!=176){
	        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        //cell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        cell.setCellValue(text);
		}else{
	        //cell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        try{
	        	cell.setCellValue(Double.valueOf(text));
	        	cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	        }catch(Exception e){
	        	log.error(e);
	        	cell.setCellValue(text);
	        }
		}
			
    }
}
