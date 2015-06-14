package common.component.ectable.service.impl;

import java.util.Map;

import org.apache.log4j.Logger;
import org.extremecomponents.table.limit.FilterSet;
import org.extremecomponents.table.limit.Sort;
import org.hibernate.dialect.Dialect;

import common.component.ectable.dao.EcTableDAO;
import common.component.ectable.dao.EcTableDAOJdbc;
import common.component.ectable.service.EcTableService;

public class EcTableServiceImpl implements EcTableService{
	
	protected Logger log = Logger.getLogger(this.getClass()); 
	
	protected EcTableDAOJdbc ecTableDAO;
	
	protected String dialectName;
	
	protected Dialect dialect;
	
	public Map search(String hql, int page, int pageSize, Sort sort,
			FilterSet filterSet, boolean exported){
		
		return ecTableDAO.getCollectionData(hql, page, pageSize, sort, filterSet, exported);
	}

	public EcTableDAO getEcTableDAO() {
		return ecTableDAO;
	}

	public void setEcTableDAO(EcTableDAOJdbc ecTableDAO) {
		this.ecTableDAO = ecTableDAO;
	}



}
