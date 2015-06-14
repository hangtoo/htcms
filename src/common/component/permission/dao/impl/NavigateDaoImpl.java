/**
 * 
 */
package common.component.permission.dao.impl;

import common.base.dao.hibernate.BaseHibernateDao;
import common.component.permission.dao.NavigateDao;
import common.component.permission.entity.Navigate;

public class NavigateDaoImpl extends BaseHibernateDao implements NavigateDao {

	@Override
	protected Class getPersistentClass() {
		return Navigate.class;
	}
}
