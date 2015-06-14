package common.component.cms.service;

import java.util.List;
import java.util.Map;

import org.extremecomponents.table.limit.FilterSet;
import org.extremecomponents.table.limit.Sort;

import common.component.cms.entity.Attribute;
import common.component.ectable.service.EcTableService;
import common.exception.ExistException;

public interface AttributeService extends EcTableService{
	/**
	 * 功能：属性新增、修改
	 * 描述：
	 */
	public Attribute save(Attribute attribute) throws ExistException;
	
	/**
	 * 功能：属性删除
	 * 描述：
	 */
	public void delete(String id);

	/**
	 * 功能：属性修改
	 * 描述：
	 */
	public Attribute update(Attribute bean);


	/**
	 * 功能：属性查询
	 * 描述：
	 */
	public Attribute getById(String id);
	
	/**
	 * 功能：属性列表查询
	 * 描述：
	 */
	public List<Attribute> getList(String condition);
	
	/**
	 * 功能：属性列表查询
	 * 描述：
	 */
	public List<Attribute> getAttributeList(String cataid);
	
	/**
	 * 功能：属性列表查询
	 * 描述：
	 */
	public List<Attribute> getList();
	
	/**
	 * 功能：属性列表查询
	 * 描述：
	 */
	public Map search(String hql, int page, int pageSize, Sort sort,
			FilterSet filterSet, boolean exported);
}
