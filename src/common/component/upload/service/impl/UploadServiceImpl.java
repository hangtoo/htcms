package common.component.upload.service.impl;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import common.component.cms.dao.BlobDao;
import common.component.cms.entity.BLOB;
import common.component.upload.service.UploadService;

public class UploadServiceImpl implements UploadService {
	private BlobDao blobDao;
	
	public UploadServiceImpl(BlobDao blobDao){
		this.blobDao=blobDao;
	}
	
	@Override
	public BLOB save(File documentFile,String documentFileContentType,String documentFileFileName) throws SQLException, IOException {
		return blobDao.saveBlob(documentFile,documentFileContentType,documentFileFileName);
	}
	@Override
	public BLOB download(String id) throws SQLException, IOException {
		return blobDao.getBlob(id);
	}

	public BlobDao getBlobDao() {
		return blobDao;
	}

	public void setBlobDao(BlobDao blobDao) {
		this.blobDao = blobDao;
	}


	
}
