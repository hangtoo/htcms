package common.component.permission.service.impl;

import java.io.Serializable;
import java.util.List;

import common.component.ectable.dao.EcTableDAO;
import common.component.ectable.dao.EcTableDAOJdbc;
import common.component.ectable.service.impl.EcTableServiceImpl;
import common.component.permission.dao.NavigateDao;
import common.component.permission.entity.Navigate;
import common.component.permission.entity.Role;
import common.component.permission.service.NavigateService;
import common.exception.ExistException;
/**
 * @author itfish
 *
 * �û�����ӿ�功能模块，获取功能树结构
 */
public class NavigateServiceImpl extends EcTableServiceImpl implements NavigateService{
	private NavigateDao navigateDao;
	
	public NavigateServiceImpl(NavigateDao navigateDao,EcTableDAOJdbc ecTableDAO) {
		this.navigateDao = navigateDao;
		this.ecTableDAO=ecTableDAO;
	}
	
	@Override
	public Navigate save(Navigate role) throws ExistException {
		
		//在新增时，用户名相同的记录//或者修改时候除本身外
		String condition = " and deleted is null and name='" + role.getName()+"'";// and ('"+role.getId()+"'='-1' or id!='"+role.getId()+"')
		
		List beans=navigateDao.gets(condition);
		
		if(beans!=null&&beans.size()>=1){//新增存在多条记录的情况//或修改
			throw new ExistException("reduplicate name");
		}
		
		navigateDao.save(role);
		return role;
	}
	
	@Override
	public void deleteAll(String ids) {
		ecTableDAO.execute("update t_navigate set p_deleted=1 where p_id in("+ids+")");
	}
	
	@Override
	public void delete(String id) {
		navigateDao.deleteTag(id);
	}

	@Override
	public Navigate update(Navigate newRole) {
		
	    Navigate role = getById(newRole.getId());
	    role.setName(newRole.getName());
	    role.setRemark(newRole.getRemark());
		
		return (Navigate)navigateDao.update(role);
	}
	
	public Navigate getById(final Serializable id){
		
		Object obj=this.navigateDao.getById(id);
		
		if(obj instanceof Navigate)
			return (Navigate)obj;
		return null;
	}

	@Override
	public Navigate getById(String id) {
		Object obj=this.navigateDao.getById(id);
		
		if(obj instanceof Navigate)
			return (Navigate)obj;
		return null;
	}

	@Override
	public List<Navigate> getTree() {
		List list = navigateDao.gets(" and deleted is null order by id");
		return list;
	}
	
	@Override
	public List<Navigate> getList() {
		String condition=" and deleted is null";
		List list = navigateDao.gets(condition);
		return list;
	}
	
	@Override
	public List<Navigate> getList(String condition) {
		List list = navigateDao.gets(condition);
		return list;
	}
}
