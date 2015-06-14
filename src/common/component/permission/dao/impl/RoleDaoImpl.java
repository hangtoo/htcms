package common.component.permission.dao.impl;

import common.base.dao.hibernate.BaseHibernateDao;
import common.component.permission.dao.RoleDao;
import common.component.permission.entity.Role;

public class RoleDaoImpl extends BaseHibernateDao implements RoleDao {
	protected Class getPersistentClass() {
		return Role.class;
	}
}
