package common.component.application.nurl.dao.impl;



import common.base.dao.hibernate.BaseHibernateDao;
import common.component.application.nurl.dao.NurlDao;
import common.component.application.nurl.entity.Nurl;

public class NurlDaoImpl extends BaseHibernateDao implements NurlDao {

	@Override
	protected Class getPersistentClass() {
		return Nurl.class;
	}
}
