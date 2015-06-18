package common.component.application.share.service;

import java.util.List;
import java.util.Map;

import org.extremecomponents.table.limit.FilterSet;
import org.extremecomponents.table.limit.Sort;

import common.component.application.share.entity.Stock;
import common.component.ectable.service.EcTableService;
import common.exception.ExistException;

/**
 * @author huanglf
 *
 * 股票模块，股票增删改查�û���½��֤���û���ɾ�Ĳ�ȹ���
 */
public interface StockService extends EcTableService{
	/**
	 * 功能：属性新增、修改
	 * 描述：
	 */
	public Stock save(Stock attribute) throws ExistException;
	
	/**
	 * 功能：属性删除
	 * 描述：
	 */
	public void delete(String id);

	/**
	 * 功能：属性修改
	 * 描述：
	 */
	public Stock update(Stock bean);


	/**
	 * 功能：属性查询
	 * 描述：
	 */
	public Stock getById(String id);
	
	/**
	 * 功能：属性列表查询
	 * 描述：
	 */
	public List<Stock> getList(String condition);
	
	/**
	 * 功能：属性列表查询
	 * 描述：
	 */
	public List<Stock> getList();
	
	/**
	 * 功能：属性列表查询
	 * 描述：
	 */
	public Map search(String hql, int page, int pageSize, Sort sort,
			FilterSet filterSet, boolean exported);
}
