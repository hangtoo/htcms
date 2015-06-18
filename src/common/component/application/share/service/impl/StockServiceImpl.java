package common.component.application.share.service.impl;

import java.io.Serializable;
import java.util.List;

import common.component.application.share.dao.StockDao;
import common.component.application.share.entity.Stock;
import common.component.application.share.service.StockService;
import common.component.ectable.dao.EcTableDAOJdbc;
import common.component.ectable.service.impl.EcTableServiceImpl;
import common.exception.ExistException;
/**
 * @author huanglf
 *
 * �û�����ӿ�股票模块，股票登陆验证、股票增删改查�û���½��֤���û���ɾ�Ĳ�ȹ���
 */
public class StockServiceImpl extends EcTableServiceImpl implements StockService{
	private StockDao stockDao;

	public StockServiceImpl(StockDao stockDao,EcTableDAOJdbc ecTableDAO) {
		this.stockDao = stockDao;
		this.ecTableDAO=ecTableDAO;
	}
	
	@Override
	public Stock save(Stock stock) throws ExistException {
		//在新增时，股票名相同的记录//或者修改时候除本身外
		String condition = " and deleted is null and name='" + stock.getName()+"' and date='"+stock.getDate()+"'";// and ('"+stock.getId()+"'='-1' or id!='"+stock.getId()+"')
		
		List beans=stockDao.gets(condition);
		
		if(beans!=null&&beans.size()>=1){//新增存在多条记录的情况//或修改
			throw new ExistException("reduplicate name");
		}
		
		stockDao.save(stock);
		return stock;
	}
	
	@Override
	public void delete(String id) {
		stockDao.deleteTag(id);
	}

	@Override
	public Stock update(Stock newStock) {
		
	    Stock stock = getById(newStock.getId());
	    stock.setName(newStock.getName());
	    
	    stock.setData(newStock.getData());
	    stock.setDate(newStock.getDate());
	    
	    stock.setAdd(newStock.getAdd());
	    stock.setRate(newStock.getRate());
	    
	    stock.setHighdata(newStock.getHighdata());
	    stock.setHighdate(newStock.getHighdate());
	    
	    stock.setRemark(newStock.getRemark());
		
		return (Stock)stockDao.update(stock);
	}
	
	public Stock getById(final Serializable id){
		
		Object obj=this.stockDao.getById(id);
		
		if(obj instanceof Stock)
			return (Stock)obj;
		return null;
	}

	@Override
	public Stock getById(String id) {
		Object obj=this.stockDao.getById(id);
		
		if(obj instanceof Stock)
			return (Stock)obj;
		return null;
	}
	
	@Override
	public List<Stock> getList() {
		String condition=" and deleted is null";
		List list = stockDao.gets(condition);
		return list;
	}
	
	@Override
	public List<Stock> getList(String condition) {
		List list = stockDao.gets(condition);
		return list;
	}
}