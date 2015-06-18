package common.component.application.share.dao.impl;

import common.base.dao.hibernate.BaseHibernateDao;
import common.component.application.share.dao.StockDao;
import common.component.application.user.entity.User;

public class StockDaoImpl extends BaseHibernateDao implements StockDao {

	@Override
	protected Class getPersistentClass() {
		return User.class;
	}
}
