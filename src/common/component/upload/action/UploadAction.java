package common.component.upload.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import common.base.action.BaseAction;

public class UploadAction  extends BaseAction{

	private static final long serialVersionUID = 4134427522424814157L;

    private String documentFileContentType;
    private String documentFileFileName;
    private File documentFile;
    
    private String fileRealName;
    
    private String column;
	
	public UploadAction(){
	}
	
	public String save() throws Exception {
		
        if(!documentFileFileName.equals("")){
            
            String folder = ServletActionContext.getServletContext()
                                .getRealPath("/archives");
            
            File rootDir = new File(folder);
            
            if(!rootDir.exists())
                rootDir.mkdirs();
            
            String fileEx = documentFileFileName.substring(
                                documentFileFileName.indexOf("."), 
                                documentFileFileName.length());
            //documentFileFileName.substring(0, documentFileFileName.indexOf(".")) +
            fileRealName =  String.valueOf(new Date().getTime())+fileEx;
            
            String fileName = folder + "\\"+fileRealName;
            copy(documentFile,new File(fileName));
        }
        return SUCCESS;
		
	}

    private void copy(File src, File dst){
        InputStream in = null;
        OutputStream out = null;
        try{                
            in = new BufferedInputStream( new FileInputStream(src));
            out = new BufferedOutputStream( new FileOutputStream(dst)); 
            
            byte [] buffer = new byte [1024];    
            while (in.read(buffer) > 0 )    
                out.write(buffer);      
            in.close();
            out.close(); 
        }catch (Exception e){    
            e.printStackTrace();    
        }     
            
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

	public String getFileRealName() {
		return fileRealName;
	}

	public void setFileRealName(String fileRealName) {
		this.fileRealName = fileRealName;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}
	
}
