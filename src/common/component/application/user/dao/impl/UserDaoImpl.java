package common.component.application.user.dao.impl;

import common.base.dao.hibernate.BaseHibernateDao;
import common.component.application.user.dao.UserDao;
import common.component.application.user.entity.User;

public class UserDaoImpl extends BaseHibernateDao implements UserDao {

	@Override
	protected Class getPersistentClass() {
		return User.class;
	}
}
