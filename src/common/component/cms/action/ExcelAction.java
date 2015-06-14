package common.component.cms.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts2.ServletActionContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.LocalizedResourceHelper;

import common.component.IConstants;
import common.component.cms.entity.Catalogue;
import common.component.cms.service.AttributeService;
import common.component.cms.service.CatalogueService;
import common.component.cms.service.ContentService;
import common.component.cms.util.GlobeData;
import common.component.permission.entity.Admin;
import common.util.DateUtil;
import common.util.ExcelUtil;

public class ExcelAction extends ContentAction {
	private static final long serialVersionUID = 18L;
	
	private static final String CONTENT_TYPE = "application/vnd.ms-excel";
	private static final String EXTENSION = ".xls";

	// 输出流Content Type   
    private String documentFileContentType=CONTENT_TYPE;
    
    // 输出流的生成的文件名
    private String documentFileFileName;
    
    private File documentFile;
    
    // 输出流
    public InputStream stream;

    private ExcelUtil excelgen=new ExcelUtil();
	
	public ExcelAction(ContentService contentService,CatalogueService catalogueService,AttributeService attributeService) throws Exception {
		super(contentService, catalogueService, attributeService);
		
		if(GlobeData.templateBooks.isEmpty()){//初始化模版列表 templateBooks
			getTree();
			for(int i=0;i<tree.size();i++){
				Catalogue ele=tree.get(i);
				POIFSFileSystem filesystem;
				try {
					filesystem = getTemplateSource(getUrl(ele));
					GlobeData.templateBooks.put(ele.getId(),filesystem);
				} catch (Exception e) {
					log.error(e);
					continue;
				}
			}
		}
	}
	
	public String newOne() {
		return SUCCESS;
	}

	public String newOnefile() {
		
		if(bean.getCatalogue()!=null){
			Catalogue cat=getCat(bean.getCatalogue().getId());
			bean.setCatalogue(cat);
		}
		
		try {
			excelView(null);
		} catch (Exception e) {
			log.error(e);
		}
		return SUCCESS;
	}
	
	public String edit() throws SQLException, IOException {
		return SUCCESS;
	}
	
	public String editfile() throws SQLException, IOException {
		if (bean.getId() != null) {

			columns = contentService.getById(bean);
			
			attributes=getAttributes();
			
			clobblobs=contentService.getClobBlobs(bean,attributes,columns);
			
			Map model=new HashMap();
			model.putAll(columns);
			model.putAll(clobblobs);
			
			if(bean.getCatalogue()!=null){
				Catalogue cat=getCat(bean.getCatalogue().getId());
				bean.setCatalogue(cat);
			}
			
			//获取模板，并填入数据
			try {
				excelView(model);
			} catch (Exception e) {
				log.error(e);
				parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEERROR));
			}
			
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATESUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEERROR));
		}
		return SUCCESS;
	}
	
	public String save(){
		try {
			
			//比对上传的文件及模板文件，获取相应的数据存入columns中
			FileInputStream fin=new FileInputStream(documentFile);
			
			POIFSFileSystem fs = new POIFSFileSystem(fin);
			HSSFWorkbook workBook = new HSSFWorkbook(fs);
			
			Map model= excelgen.getExcelModel(workBook,new HSSFWorkbook(GlobeData.templateBooks.get(bean.getCatalogue().getId())));
			
			Admin user=GlobeData.getUser();
			
			if(user!=null){
				model.put("p_creator",user.getUsername());
			}
			model.put("p_createtime",DateUtil.getTimeNow());

			attributes=getAttributes();
			contentService.save(bean,attributes,model);

			parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATEDBSUCCESS));
		} catch (Exception e) {
			log.error(e);
			parameters.put(IConstants.RETURNMSG,getText(IConstants.CREATEDBERROR));
		}
		return SUCCESS;
	}
	
	public String delete(){
		if (bean.getId()!= null) {
			contentService.delete(bean);
			parameters.put(IConstants.RETURNMSG,getText(IConstants.DELETEDBSUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.DELETEDBERROR));
		}
		return SUCCESS;
	}

	public String update() throws SQLException, IOException{
		if (bean.getId() != null) {
			
			//比对上传的文件及模板文件，获取相应的数据存入columns中
			FileInputStream fin=new FileInputStream(documentFile);
			
			POIFSFileSystem fs = new POIFSFileSystem(fin);
			HSSFWorkbook workBook = new HSSFWorkbook(fs);
			
			Map model;
			try {
				model = excelgen.getExcelModel(workBook,new HSSFWorkbook(GlobeData.templateBooks.get(bean.getCatalogue().getId())));
				
				Admin user=GlobeData.getUser();
				
				if(user!=null){
					model.put("p_modifier",user.getUsername());
				}
				model.put("p_modifytime",DateUtil.getTimeNow());
				
				attributes=getAttributes();
				contentService.update(bean,attributes,model,clobblobs);
				
			} catch (Exception e) {
				log.error(e);
				parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEDBERROR));
			}
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEDBSUCCESS));
		} else {
			parameters.put(IConstants.RETURNMSG,getText(IConstants.UPDATEDBERROR));
		}
		return SUCCESS;
	}
	
	public String search(){
		
		if(bean.getCatalogue()==null){
			parameters.put(IConstants.RETURNMSG,getText(IConstants.RETURNERRORMSG));
			return SUCCESS;
		}
		
		String sql="select * from "+contentService.getTableName(bean) +" where p_deleted is null";
		try{
			super.search(contentService,sql);
		}catch(Exception e){
			log.error(e);
			parameters.put(IConstants.RETURNMSG,getText(IConstants.RETURNERRORMSG));
		}
		
		return SUCCESS;
	}

/*	public HSSFWorkbook getTemplateSource(String url) throws Exception {
		POIFSFileSystem fs = new POIFSFileSystem(getStream(url));
		HSSFWorkbook workBook = new HSSFWorkbook(fs);
		return workBook;
	}*/
	
	public POIFSFileSystem getTemplateSource(String url) throws Exception {
		POIFSFileSystem fs = new POIFSFileSystem(getStream(url));
		return fs;
	}
	
	public InputStream getStream(String url) throws Exception {
		LocalizedResourceHelper helper = new LocalizedResourceHelper();
		//java.util.Locale userLocale = RequestContextUtils.getLocale(request);
		Resource inputFile = helper.findLocalizedResource(url, EXTENSION,null);
		if (log.isDebugEnabled())
			log.debug("Loading Excel workbook from " + inputFile);
		return inputFile.getInputStream();
	}

    //获取模板
    protected void excelView(Map model) throws Exception{
    	
    	POIFSFileSystem fs=GlobeData.templateBooks.get(bean.getCatalogue().getId());

    	HSSFWorkbook workbook=new HSSFWorkbook(fs);
		
    	excelgen.buildExcelDocument(model, workbook);
		
		String fileName=bean.getCatalogue().getName();
		
		if(fileName==null)
			fileName="";
		
		byte[] fileNameByte=fileName.getBytes("GBK");
		String _fileName=new String(fileNameByte,"8859_1");
		
		documentFile=new File(_fileName);
		FileOutputStream out=new FileOutputStream(documentFile);
		workbook.write(out);
		out.flush();
		
		//文件流
	    stream=new FileInputStream(documentFile);
	    
	    //文件名
		documentFileFileName="inline; filename=\""+_fileName+".xls\"";
    }

	private String getUrl(Catalogue ele){
		return "/common/component/cms/template/"+ele.getName();
	}
    
	public String getDocumentFileContentType() {
		return documentFileContentType;
	}

	public void setDocumentFileContentType(String documentFileContentType) {
		this.documentFileContentType = documentFileContentType;
	}

	public String getDocumentFileFileName() {
		return documentFileFileName;
	}

	public void setDocumentFileFileName(String documentFileFileName) {
		this.documentFileFileName = documentFileFileName;
	}

	public File getDocumentFile() {
		return documentFile;
	}

	public void setDocumentFile(File documentFile) {
		this.documentFile = documentFile;
	}

	public InputStream getStream() {
		return stream;
	}

	public void setStream(InputStream stream) {
		this.stream = stream;
	}
	
	private String getContentType() {
		return CONTENT_TYPE;
	}
	
}
