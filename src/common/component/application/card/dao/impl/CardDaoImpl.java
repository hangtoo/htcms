package common.component.application.card.dao.impl;



import common.base.dao.hibernate.BaseHibernateDao;
import common.component.application.card.dao.CardDao;
import common.component.application.card.entity.Card;

public class CardDaoImpl extends BaseHibernateDao implements CardDao {

	@Override
	protected Class getPersistentClass() {
		return Card.class;
	}
}
