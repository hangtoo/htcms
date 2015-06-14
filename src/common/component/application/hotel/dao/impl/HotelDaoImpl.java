package common.component.application.hotel.dao.impl;

import common.base.dao.hibernate.BaseHibernateDao;
import common.component.application.hotel.dao.HotelDao;
import common.component.application.hotel.entity.Hotel;

public class HotelDaoImpl extends BaseHibernateDao implements HotelDao {

	@Override
	protected Class getPersistentClass() {
		return Hotel.class;
	}
}
