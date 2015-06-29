package common.component.application.share.dao.impl;

import org.springframework.stereotype.Repository;

import common.base.dao.hibernate.BaseHibernateDao;
import common.component.application.share.dao.StockDao;
import common.component.application.share.entity.Stock;
@Repository("stockDao")
public class StockDaoImpl extends BaseHibernateDao implements StockDao {

	@Override
	protected Class getPersistentClass() {
		return Stock.class;
	}
}
