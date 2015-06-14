package common.component.ectable.dao.jdbc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.LazyDynaMap;
import org.extremecomponents.table.limit.Filter;
import org.extremecomponents.table.limit.FilterSet;
import org.extremecomponents.table.limit.Sort;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import common.component.ectable.dao.EcTableDAO;
import common.util.StringUtil;



/**
 * JDBC接口的EC分页DAO的实现
 * 
 * @author itfish
 * 
 */
public class EcTableDAOJdbcImpl4Oracle extends JdbcDaoSupport implements
		EcTableDAO{

	public Map getCollectionData(String sql, int page, int pageSize, Sort sort,
			FilterSet filterSet, boolean exported) {
		Map map = new HashMap();
		StringBuffer filferSql = new StringBuffer();
		// 得到SQL
		if (filterSet != null) {
			// 过滤器SQL
			Filter[] filters = filterSet.getFilters();
			for (int i = 0; i < filters.length; i++) {
				if (!StringUtil.isEmptyString(filters[i].getValue())) {
					filferSql.append(" AND " + filters[i].getProperty()
							+ " LIKE '%" + filters[i].getValue() + "%' ");
				} else {
					filferSql.append(" AND " + filters[i].getProperty()
							+ " IS NULL ");
				}
			}
		}
		StringBuffer strSql = new StringBuffer();
		strSql.append("    SELECT ECTABLE.* ");
		strSql.append(" FROM ( SELECT ECTABLE.*, ROWNUM ECRN ");
		strSql.append("  FROM ( " + sql + " ) ECTABLE ");
		strSql.append(" WHERE 1=1 ");
		strSql.append(filferSql.toString());
		strSql.append("  ) ECTABLE ");
		// 计算数值
		int startNumber = (page - 1) * pageSize + 1;
		int endNumber = page * pageSize;
		// 得到总记录数
		int totalNumer = getTotalRowsJdbc(strSql.toString());
		// 结束记录比总记录大
		if (endNumber > totalNumer) {
			endNumber = totalNumer;
		}
		// 判断是否导出
		if (exported) {
			startNumber = 1;
			endNumber = totalNumer + 1;
			if (endNumber > 20000) {
				endNumber = 20000;
			}
		}
		strSql.append(" WHERE ECTABLE.ECRN <=" + String.valueOf(endNumber));
		strSql.append(" AND ECTABLE.ECRN >=" + String.valueOf(startNumber));
		// 排序SQL
		if (sort.getProperty() != null) {
			strSql.append("  ORDER BY ECTABLE." + sort.getProperty() + " "
					+ sort.getSortOrder());
		}
		logger.info("ECTABLE JDBC :" + strSql.toString());
		List list = getJdbcTemplate().query(strSql.toString(), new ecTableRowMapper());
		map.put("totalRows", new Integer(totalNumer + ""));
		map.put("page", list);
		return map;
	}

	private int getTotalRowsJdbc(String sql) {
		StringBuffer totalSQL = new StringBuffer(" SELECT count(*) FROM ( ");
		totalSQL.append(sql);
		totalSQL.append(" ) totalTable ");
		logger.info("JDBC :" + totalSQL.toString());
		int totalNumber = getJdbcTemplate().queryForInt(totalSQL.toString());
		return totalNumber;
	}
	/**ECTABLE的行处理内部类
	 * @author itfish
	 *
	 */
	class ecTableRowMapper implements RowMapper {

		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			ResultSetMetaData rdm = arg0.getMetaData();
			int conCount = rdm.getColumnCount();
			LazyDynaMap dynaBean1 = new LazyDynaMap();
			for (int i = 1; i <= conCount; i++) {
				dynaBean1.set(rdm.getColumnName(i).toUpperCase(), arg0
						.getString(i));
			}
			return dynaBean1.getMap();
		}
	}
}

