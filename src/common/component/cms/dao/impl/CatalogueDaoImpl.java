/**
 * 
 */
package common.component.cms.dao.impl;

import common.base.dao.hibernate.BaseHibernateDao;
import common.component.cms.dao.CatalogueDao;
import common.component.cms.entity.Catalogue;

public class CatalogueDaoImpl extends BaseHibernateDao implements CatalogueDao {

	@Override
	protected Class getPersistentClass() {
		return Catalogue.class;
	}
}
