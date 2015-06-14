package common.component.cms.service;

import java.util.List;
import java.util.Map;

import org.extremecomponents.table.limit.FilterSet;
import org.extremecomponents.table.limit.Sort;

import common.component.cms.entity.Catalogue;
import common.component.ectable.service.EcTableService;
import common.exception.ExistException;
/**
 * @author huanglf
 *
 * �û�����ӿ�栏目模块，获取栏目树结构
 */
public interface CatalogueService  extends EcTableService{
	/**
	 * 栏目：栏目新增、修改
	 * 描述：
	 */
	public Catalogue save(Catalogue catalog) throws ExistException;
	
	/**
	 * 栏目：栏目删除
	 * 描述：
	 */
	public void delete(String id);
	
	/**
	 * 栏目：栏目删除所有 id以,隔开
	 * 描述：
	 */
	public void deleteAll(String ids);

	/**
	 * 栏目：栏目修改
	 * 描述：
	 */
	public Catalogue update(Catalogue Catalogue);

	/**
	 * 栏目：栏目查询
	 * 描述：
	 */
	public Catalogue getById(String id);
	
	/**
	 * 栏目：栏目列表查询
	 * 描述：
	 */
	public List<Catalogue> getTree();
	
	/**
	 * 栏目：栏目列表查询
	 * 描述：
	 */
	public List<Catalogue> getList(String condition);
	
	/**
	 * 栏目：栏目列表查询
	 * 描述：
	 */
	public List<Catalogue> getList();
	
	/**
	 * 栏目：栏目列表查询
	 * 描述：
	 */
	public Map search(String hql, int page, int pageSize, Sort sort,
			FilterSet filterSet, boolean exported);
	
	/**
	 * 栏目：建立栏目表
	 * 描述：
	 */
	public void createTable(String catalogueId) throws Exception;
}
