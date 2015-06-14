package common.component.permission.service;

import java.util.List;
import java.util.Map;

import org.extremecomponents.table.limit.FilterSet;
import org.extremecomponents.table.limit.Sort;

import common.component.ectable.service.EcTableService;
import common.component.permission.entity.Admin;
import common.component.permission.entity.Role;
import common.exception.ExistException;

/**
 * @author itfish
 *
 *角色模块
 */
public interface RoleService extends EcTableService{
	/**
	 * 功能：角色新增、修改
	 * 描述：
	 */
	public Role save(Role role) throws ExistException;
	
	/**
	 * 功能：角色删除
	 * 描述：
	 */
	public void delete(String id);

	/**
	 * 功能：角色修改
	 * 描述：
	 */
	public Role update(Role Role);

	/**
	 * 功能：角色查询
	 * 描述：
	 */
	public Role getById(String id);
	
	/**
	 * 功能：角色列表查询
	 * 描述：
	 */
	public List<Role> getList(String condition);
	
	/**
	 * 功能：角色列表查询
	 * 描述：
	 */
	public List<Role> getList();
	
	/**
	 * 功能：角色列表查询
	 * 描述：
	 */
	public Map search(String hql, int page, int pageSize, Sort sort,
			FilterSet filterSet, boolean exported);
}

