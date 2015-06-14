package common.component.permission.service.impl;

import java.io.Serializable;
import java.util.List;

import common.component.ectable.dao.EcTableDAOJdbc;
import common.component.ectable.service.impl.EcTableServiceImpl;
import common.component.permission.dao.RoleDao;
import common.component.permission.entity.Role;
import common.component.permission.service.RoleService;
import common.exception.ExistException;

/**
 * @author itfish
 *
 * �û�����ӿ�角色模块
 */

public class RoleServiceImpl extends EcTableServiceImpl implements RoleService {
	private RoleDao roleDao;
	
	public RoleServiceImpl(RoleDao roleDao,EcTableDAOJdbc ecTableDAO) {
		this.roleDao = roleDao;
		this.ecTableDAO=ecTableDAO;
	}
	
	@Override
	public Role save(Role role) throws ExistException {
		
		//在新增时，用户名相同的记录//或者修改时候除本身外
		String condition = " and deleted is null and name='" + role.getName()+"'";// and ('"+role.getId()+"'='-1' or id!='"+role.getId()+"')
		
		List beans=roleDao.gets(condition);
		
		if(beans!=null&&beans.size()>=1){//新增存在多条记录的情况//或修改
			throw new ExistException("reduplicate name");
		}
		
		roleDao.save(role);
		return role;
	}
	

	@Override
	public void delete(String id) {
		roleDao.deleteTag(id);
	}

	@Override
	public Role update(Role newRole) {
		
	    Role role = getById(newRole.getId());
	    role.setName(newRole.getName());
	    role.setRemark(newRole.getRemark());
		
		return (Role)roleDao.update(role);
	}
	
	public Role getById(final Serializable id){
		
		Object obj=this.roleDao.getById(id);
		
		if(obj instanceof Role)
			return (Role)obj;
		return null;
	}

	@Override
	public Role getById(String id) {
		Object obj=this.roleDao.getById(id);
		
		if(obj instanceof Role)
			return (Role)obj;
		return null;
	}

	@Override
	public List<Role> getList() {
		String condition=" and deleted is null";
		List list = roleDao.gets(condition);
		return list;
	}
	
	@Override
	public List<Role> getList(String condition) {
		List list = roleDao.gets(condition);
		return list;
	}
}
