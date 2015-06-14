package common.component.permission.service;

import java.util.List;
import java.util.Map;

import org.extremecomponents.table.limit.FilterSet;
import org.extremecomponents.table.limit.Sort;

import common.component.ectable.service.EcTableService;
import common.component.permission.entity.Navigate;
import common.component.permission.entity.Role;
import common.exception.ExistException;
/**
 * @author huanglf
 *
 * �û�����ӿ�功能模块，获取功能树结构
 */
public interface NavigateService extends EcTableService{
	/**
	 * 功能：功能新增、修改
	 * 描述：
	 */
	public Navigate save(Navigate navigate) throws ExistException;
	
	/**
	 * 功能：功能删除
	 * 描述：
	 */
	public void delete(String id);
	
	/**
	 * 功能：功能删除所有 id以,隔开
	 * 描述：
	 */
	public void deleteAll(String ids);

	/**
	 * 功能：功能修改
	 * 描述：
	 */
	public Navigate update(Navigate Navigate);

	/**
	 * 功能：功能查询
	 * 描述：
	 */
	public Navigate getById(String id);
	
	/**
	 * 功能：功能列表查询
	 * 描述：
	 */
	public List<Navigate> getTree();
	
	/**
	 * 功能：功能列表查询
	 * 描述：
	 */
	public List<Navigate> getList(String condition);
	
	/**
	 * 功能：功能列表查询
	 * 描述：
	 */
	public List<Navigate> getList();
	
	/**
	 * 功能：功能列表查询
	 * 描述：
	 */
	public Map search(String hql, int page, int pageSize, Sort sort,
			FilterSet filterSet, boolean exported);

	
}
