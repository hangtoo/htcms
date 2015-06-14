package common.component.permission.dao.impl;

import common.base.dao.hibernate.BaseHibernateDao;
import common.component.permission.dao.PermissionDao;
import common.component.permission.entity.Permission;

public class PermissionDaoImpl extends BaseHibernateDao implements
		PermissionDao {
	protected Class getPersistentClass() {
		return Permission.class;
	}

}
