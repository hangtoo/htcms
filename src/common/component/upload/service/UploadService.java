package common.component.upload.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import common.component.cms.entity.BLOB;

public interface UploadService {
	public BLOB save(File documentFile,String documentFileContentType,String documentFileFileName) throws SQLException, IOException;
	public BLOB download(String id) throws SQLException, IOException;
}
