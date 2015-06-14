package common.component.config.dao.impl;

import common.base.dao.hibernate.BaseHibernateDao;
import common.component.config.dao.ConfigDao;
import common.component.config.entity.Config;

public class ConfigDaoImpl extends BaseHibernateDao implements ConfigDao {

	@Override
	protected Class getPersistentClass() {
		return Config.class;
	}
}
