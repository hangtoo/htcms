package common.component.permission.service;

import java.util.List;
import java.util.Map;

import org.extremecomponents.table.limit.FilterSet;
import org.extremecomponents.table.limit.Sort;

import common.component.ectable.service.EcTableService;
import common.component.permission.entity.Admin;
import common.exception.ExistException;

/**
 * @author huanglf
 *
 * 用户模块，用户登陆验证、用户增删改查�û���½��֤���û���ɾ�Ĳ�ȹ���
 */
public interface AdminService extends EcTableService{
	
	/**
	 * 功能：用户新增、修改
	 * 描述：
	 */
	public Admin save(Admin admin) throws ExistException;
	
	/**
	 * 功能：用户删除
	 * 描述：
	 */
	public void delete(String id);

	/**
	 * 功能：用户修改
	 * 描述：
	 */
	public Admin update(Admin admin);
	
	/**
	 * 功能：用户登陆验证
	 * 描述：根据登录用户名获取数据库信息，并验证密码是否正确
	 */
	public Admin valid(Admin admin);
	
	/**
	 * 功能：用户查询
	 * 描述：
	 */
	public Admin getById(String id);

	/**
	 * 功能：用户列表查询
	 * 描述：
	 */
	public List<Admin> getList(String condition);
	
	/**
	 * 功能：用户列表查询
	 * 描述：
	 */
	public List<Admin> getList();

	/**
	 * 功能：用户列表查询
	 * 描述：
	 */
	public Map search(String hql, int page, int pageSize, Sort sort,
			FilterSet filterSet, boolean exported);

}
