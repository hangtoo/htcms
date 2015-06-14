package common.component.application.user.service;

import java.util.List;
import java.util.Map;

import org.extremecomponents.table.limit.FilterSet;
import org.extremecomponents.table.limit.Sort;

import common.component.application.user.entity.User;
import common.component.ectable.service.EcTableService;
import common.exception.ExistException;

/**
 * @author huanglf
 *
 * 酒店模块，酒店增删改查�û���½��֤���û���ɾ�Ĳ�ȹ���
 */
public interface UserService extends EcTableService{
	
	/**
	 * 功能：酒店新增、修改
	 * 描述：
	 */
	public User save(User User) throws ExistException;
	
	/**
	 * 功能：酒店删除
	 * 描述：
	 */
	public void delete(String id);

	/**
	 * 功能：酒店修改
	 * 描述：
	 */
	public User update(User User) throws ExistException;
	
	/**
	 * 功能：酒店查询
	 * 描述：
	 */
	public User getById(String id);

	/**
	 * 功能：酒店列表查询
	 * 描述：
	 */
	public List<User> getList(String condition);
	
	/**
	 * 功能：酒店列表查询
	 * 描述：
	 */
	public List<User> getList();

	/**
	 * 功能：酒店列表查询
	 * 描述：
	 */
	public Map search(String hql, int page, int pageSize, Sort sort,
			FilterSet filterSet, boolean exported);

	
	public List<User> getUserParas(String userkey);
	
	public void initUser();
	public void initUser(String userkey,User uservalue);
	public void initUser(String userkey);

	public User valid(User user);
}
