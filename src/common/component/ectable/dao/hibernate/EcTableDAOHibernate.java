package common.component.ectable.dao.hibernate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.extremecomponents.table.limit.Filter;
import org.extremecomponents.table.limit.FilterSet;
import org.extremecomponents.table.limit.Sort;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import common.base.dao.hibernate.BaseHibernateDao;
import common.component.ectable.dao.EcTableDAO;
import common.util.StringUtil;

/**
 * @author itfish
 */
public class EcTableDAOHibernate extends BaseHibernateDao implements EcTableDAO {

	public Map getCollectionData(String hql, int page, int pageSize, Sort sort,
			FilterSet filterSet, boolean exported) {
		Map map = new HashMap();

		Session session = getHibernateTemplate().getSessionFactory()
				.openSession();
		try {
			StringBuffer conStr = new StringBuffer("SELECT COUNT(*) ");
			conStr.append(hql);
			// �õ��ܼ�¼��
			//int totalNumer = ((Integer) session.createQuery(conStr.toString()).iterate().next()).intValue();
			int totalNumer =Integer.valueOf(session.createQuery(conStr.toString()).iterate().next()+"").intValue();

			map.put("totalRows", new Integer(totalNumer + ""));
			// ������ֵ
			int startNumber = (page - 1) * pageSize;// + 1;
			StringBuffer hqlSb = new StringBuffer();
			hqlSb.append(hql);
			//hqlSb.append(" where 1=1 ");
			if (filterSet != null) {
				// ������SQL
				Filter[] filters = filterSet.getFilters();
				for (int i = 0; i < filters.length; i++) {
					if(!StringUtil.isEmptyString(filters[i].getValue())){
					hqlSb.append(" AND " + filters[i].getProperty()
							+ " LIKE '%" + filters[i].getValue() + "%' ");
					}else{
						hqlSb.append(" AND " + filters[i].getProperty()
								+ " IS NULL ");
					}
				}
			}
			// ����SQL
			if (sort.getProperty() != null) {
				hqlSb.append("  ORDER BY " + sort.getProperty() + " "
						+ sort.getSortOrder());
			}
			logger.info("ECTABLE HIBERNATE :" + hqlSb.toString());

			Query query = session.createQuery(hqlSb.toString());

			query.setFirstResult(startNumber);

			query.setMaxResults(pageSize);

			List list = query.list();
			map.put("page", list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return map;
	}

	public void execute(String sql){
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		session.createSQLQuery(sql).executeUpdate();
	}
	
	public Object executeBetchSQL(final ArrayList<String> sqlList) {
		return getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				Connection CurConn = session.connection();
				int count = sqlList.size();
				for (int i = 0; i < count; i++) {
					PreparedStatement ps = CurConn.prepareStatement(sqlList.get(i));
					ps.execute();
					ps.close();
					session.flush();
				}
				return null;

			}
		});
	}
	
	@Override
	protected Class getPersistentClass() {
		return null;
	}
}
