package common.component.upload.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletOutputStream;

import org.apache.struts2.ServletActionContext;

import common.base.action.BaseAction;
import common.component.cms.entity.BLOB;
import common.component.upload.service.UploadService;

public class UploadFileAction  extends BaseAction{

	private static final long serialVersionUID = 4134427522424814157L;

    
	// 输出流Content Type   
    private String documentFileContentType;
    
    // 输出流的生成的文件名
    private String documentFileFileName;
    
    private File documentFile;
    
    private String column;
    
    private UploadService uploadService ;
    
    private BLOB bean;
    
    // 输出流   
    public InputStream stream;   
	
	public UploadFileAction(UploadService uploadService){
		this.uploadService=uploadService;
	}
	
	public String save() throws Exception {
		bean=uploadService.save(documentFile,documentFileContentType,documentFileFileName);
        return SUCCESS;
	}
	
	public String download() throws SQLException, IOException{
		bean=uploadService.download(bean.getId());
		
		Blob content=bean.getContent();
		
		stream=content.getBinaryStream();
		
		documentFileContentType = bean.getType();//"text/html";//"application/vnd.ms-excel";
		
		byte[] fileNameByte=bean.getName().getBytes("GBK");
		String fileName=new String(fileNameByte,"8859_1");

        //inline表示文件直接输出到网页上，不出现保存打开对话框   
        //"inline; filename=\"Report.htm\"";  
		// attachment表示网页会出现保存、打开对话框 
		documentFileFileName = "attachment; filename=\""+fileName+"\"";//"inline; filename=\""+bean.getName()+"\"";//;

		return SUCCESS;
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

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public UploadService getUploadService() {
		return uploadService;
	}

	public void setUploadService(UploadService uploadService) {
		this.uploadService = uploadService;
	}

	public BLOB getBean() {
		return bean;
	}

	public void setBean(BLOB bean) {
		this.bean = bean;
	}

	public InputStream getStream() {
		return stream;
	}

	public void setStream(InputStream stream) {
		this.stream = stream;
	}   
}
