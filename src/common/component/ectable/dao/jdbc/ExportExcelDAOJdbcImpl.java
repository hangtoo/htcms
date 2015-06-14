package common.component.ectable.dao.jdbc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import common.component.ectable.dao.ExportExcelDAOJdbc;



/**
 * 
 * @author itfish
 */
public class ExportExcelDAOJdbcImpl extends JdbcDaoSupport implements
		ExportExcelDAOJdbc {

	public List getExportDataList(String strSql) {
		logger.info("ExportExcelSql="+strSql);
		return getJdbcTemplate().query(strSql, new ExportRowProcess());
	}

	class ExportRowProcess implements RowMapper {
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			ResultSetMetaData rdm = arg0.getMetaData();
			int conCount = rdm.getColumnCount();
			List list = new ArrayList();
			for (int i = 1; i <= conCount; i++) {
				list.add(arg0.getString(i));
			}
			return list;
		}
	}

	// ///////////////////////////////////////////////////////////////////////////
	public List getExportDataList(String strSql, List filterName) {
		logger.info("ExportExcelSql="+strSql);
		return getJdbcTemplate().query(strSql,
				new ExportWithFilterRowProcess(filterName));
	}

	class ExportWithFilterRowProcess implements RowMapper {
		private List filterName;

		public ExportWithFilterRowProcess(List filterName) {
			this.filterName = filterName;
		}

		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			ResultSetMetaData rdm = arg0.getMetaData();
			int conCount = rdm.getColumnCount();
			List list = new ArrayList();
			Iterator filterIt = filterName.iterator();
			// �����Ҫ������ֶξ͹��˵�
			while (filterIt.hasNext()) {
				String str = (String) filterIt.next();
				for (int i = 1; i <= conCount; i++) {
					if (rdm.getColumnName(i).toUpperCase().equals(str)) {
						list.add(arg0.getString(i));
					}
				}
			}
			return list;
		}
	}

	// //////////////////////////////////////////////////////////////////////////
	public List getExportDataList(String strSql, RowMapper rowMapper) {
		logger.info("ExportExcelSql="+strSql);
		return getJdbcTemplate().query(strSql, rowMapper);
	}
}
