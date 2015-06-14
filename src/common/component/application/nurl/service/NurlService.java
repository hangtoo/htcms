package common.component.application.nurl.service;

import java.util.List;
import java.util.Map;

import org.extremecomponents.table.limit.FilterSet;
import org.extremecomponents.table.limit.Sort;

import common.component.application.nurl.entity.Nurl;
import common.component.ectable.service.EcTableService;
import common.exception.ExistException;

/**
 * @author huanglf
 *
 * 卡模块，卡增删改查�û���½��֤���û���ɾ�Ĳ�ȹ���
 */
public interface NurlService extends EcTableService{
	
	/**
	 * 功能：卡新增、修改
	 * 描述：
	 */
	public Nurl save(Nurl Nurl) throws ExistException;
	
	/**
	 * 功能：卡删除
	 * 描述：
	 */
	public void delete(String id);

	/**
	 * 功能：卡修改
	 * 描述：
	 */
	public Nurl update(Nurl Nurl);
	
	/**
	 * 功能：卡查询
	 * 描述：
	 */
	public Nurl getById(String id);

	/**
	 * 功能：卡列表查询
	 * 描述：
	 */
	public List<Nurl> getList(String condition);
	
	/**
	 * 功能：卡列表查询
	 * 描述：
	 */
	public List<Nurl> getList();

	/**
	 * 功能：卡列表查询
	 * 描述：
	 */
	public Map search(String hql, int page, int pageSize, Sort sort,
			FilterSet filterSet, boolean exported);

	
	public List<Nurl> getNurlParas(String nurlkey);
	
	public void initNurl();
	public void initNurl(String nurlkey,Nurl nurlvalue);
	public void initNurl(String nurlkey);
}
