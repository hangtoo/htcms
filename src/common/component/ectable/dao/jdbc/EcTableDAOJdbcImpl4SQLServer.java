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

import common.component.ectable.dao.EcTableDAOJdbc;
import common.util.StringUtil;



/**
 * JDBC接口的EC分页DAO的实现
 * 
 * @author itfish
 * 
 */
public class EcTableDAOJdbcImpl4SQLServer extends JdbcDaoSupport implements
		EcTableDAOJdbc{

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
		strSql.append("  FROM ( " + sql + " ) ECTABLE ");
		strSql.append(" WHERE 1=1 ");
		strSql.append(filferSql.toString());
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
		
		String strSqlsort="";
		String strSortorder="ASC";
		String strSortorderRev="DESC";
		// 排序SQL
		if (sort.getProperty() != null) {
			strSqlsort="  ORDER BY ECTABLE." + sort.getProperty() + " ";
			if("desc".equalsIgnoreCase(sort.getSortOrder().toLowerCase())){
				strSortorderRev=" ASC ";
				strSortorder=" DESC ";
			}else{
				strSortorderRev=" DESC ";
				strSortorder=" ASC ";
			}
		}else{
			strSqlsort=" ORDER BY ECTABLE.P_ID ";
			strSortorderRev=" ASC ";
			strSortorder=" DESC ";
		}
		
		String strSqldata="SELECT  TOP "+pageSize+" * FROM " +
				"(SELECT TOP "+(totalNumer-startNumber+1)+" ECTABLE.* from ("+sql+") ECTABLE where 1=1 "+filferSql.toString()+strSqlsort+strSortorderRev+") ECTABLE "+ 
				strSqlsort+strSortorder;
		
		//strSql.append(" limit "+(startNumber-1)+","+pageSize);
		logger.info("ECTABLE JDBC :" + strSqldata.toString());
		List list = getJdbcTemplate().query(strSqldata.toString(), new ecTableRowMapper());
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
				dynaBean1.set(rdm.getColumnLabel(i).toUpperCase(), arg0
						.getString(i));
			}
			return dynaBean1.getMap();
		}
	}
	
	public List getSimpleList(String strSql) {
		logger.info("ECTABLE JDBC :" + strSql.toString());
		List list = getJdbcTemplate().query(strSql, new ecTableRowMapper());
		return list;
	}
	
	public void execute(String strSql) {
		logger.info("ECTABLE JDBC :" + strSql.toString());
		getJdbcTemplate().execute(strSql);
	}

	@Override
	public void executeBatch(List<String> list) {
		String[] str=new String[list.size()];
		list.toArray(str);
		logger.info("ECTABLE JDBC :" + list);
		getJdbcTemplate().batchUpdate(str);
	}
}

