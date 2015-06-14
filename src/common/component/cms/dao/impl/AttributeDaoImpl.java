package common.component.cms.dao.impl;

import common.base.dao.hibernate.BaseHibernateDao;
import common.component.cms.dao.AttributeDao;
import common.component.cms.entity.Attribute;

public class AttributeDaoImpl extends BaseHibernateDao implements
	AttributeDao {
	protected Class getPersistentClass() {
		return Attribute.class;
	}

}
