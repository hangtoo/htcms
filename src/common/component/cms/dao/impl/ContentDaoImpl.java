/**
 * 
 */
package common.component.cms.dao.impl;

import common.base.dao.hibernate.BaseHibernateDao;
import common.component.cms.dao.ContentDao;
import common.component.cms.entity.Content;

public class ContentDaoImpl extends BaseHibernateDao implements ContentDao {

	@Override
	protected Class getPersistentClass() {
		return Content.class;
	}

}
