package common.component.cms.dao;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import common.base.dao.Dao;
import common.component.cms.entity.BLOB;

public interface BlobDao extends Dao{
	public BLOB saveBlob(File documentFile,String documentFileContentType,String documentFileFileName) throws SQLException, IOException;
	public BLOB getBlob(String id) throws SQLException, IOException ;
}
