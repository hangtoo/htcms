package common.component.cms.dao;

import java.io.IOException;
import java.sql.SQLException;

import common.base.dao.Dao;

public interface ClobDao extends Dao{
	
	public String saveClob(String src) throws SQLException, IOException;

	public String updateClob(String id, String src)  throws SQLException, IOException;
}
