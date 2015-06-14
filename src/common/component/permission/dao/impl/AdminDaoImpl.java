package common.component.permission.dao.impl;

import common.base.dao.hibernate.BaseHibernateDao;
import common.component.permission.dao.AdminDao;
import common.component.permission.entity.Admin;

public class AdminDaoImpl extends BaseHibernateDao implements AdminDao {

	@Override
	protected Class getPersistentClass() {
		return Admin.class;
	}
}
