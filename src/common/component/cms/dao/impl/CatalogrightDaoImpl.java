package common.component.cms.dao.impl;

import common.base.dao.hibernate.BaseHibernateDao;
import common.component.cms.dao.CatalogrightDao;
import common.component.cms.entity.Catalogright;

public class CatalogrightDaoImpl extends BaseHibernateDao implements
		CatalogrightDao {
	protected Class getPersistentClass() {
		return Catalogright.class;
	}

}
