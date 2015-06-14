package common.component.permission.service.impl;

import java.io.Serializable;
import java.util.List;

import common.component.ectable.dao.EcTableDAOJdbc;
import common.component.ectable.service.impl.EcTableServiceImpl;
import common.component.permission.dao.AdminDao;
import common.component.permission.entity.Admin;
import common.component.permission.service.AdminService;
import common.exception.ExistException;
import common.util.MD5;
/**
 * @author huanglf
 *
 * �û�����ӿ�用户模块，用户登陆验证、用户增删改查�û���½��֤���û���ɾ�Ĳ�ȹ���
 */
public class AdminServiceImpl extends EcTableServiceImpl implements AdminService{
	private AdminDao adminDao;
	
	public AdminServiceImpl(AdminDao adminDao,EcTableDAOJdbc ecTableDAO) {
		this.adminDao = adminDao;
		this.ecTableDAO=ecTableDAO;
	}
	
	@Override
	public Admin save(Admin admin) throws ExistException {
		
		//在新增时，用户名相同的记录//或者修改时候除本身外
		String condition = " and deleted is null and username='" + admin.getUsername()+"'";// and ('"+admin.getId()+"'='-1' or id!='"+admin.getId()+"')
		
		List beans=adminDao.gets(condition);
		
		if(beans!=null&&beans.size()>=1){//新增存在多条记录的情况//或修改
			throw new ExistException("reduplicate username");
		}
		
		admin.setPassword(new MD5().getMD5ofStr(""+admin.getPassword()));
		adminDao.save(admin);
		return admin;
	}
	

	@Override
	public void delete(String id) {
		adminDao.deleteTag(id);
	}

	@Override
	public Admin update(Admin newAdmin) {
		
	    Admin admin = getById(newAdmin.getId());
	    admin.setName(newAdmin.getName());
	    admin.setGroup(newAdmin.getGroup());
	    admin.setPosition(newAdmin.getPosition());
	    admin.setRole(newAdmin.getRole());
	    admin.setRemark(newAdmin.getRemark());
		
		return (Admin)adminDao.update(admin);
	}
	
	public Admin getById(final Serializable id){
		
		Object obj=this.adminDao.getById(id);
		
		if(obj instanceof Admin)
			return (Admin)obj;
		return null;
	}

	@Override
	public Admin getById(String id) {
		Object obj=this.adminDao.getById(id);
		
		if(obj instanceof Admin)
			return (Admin)obj;
		return null;
	}

	@Override
	public List<Admin> getList() {
		String condition=" and deleted is null";
		List list = adminDao.gets(condition);
		return list;
	}
	
	@Override
	public List<Admin> getList(String condition) {
		List list = adminDao.gets(condition);
		return list;
	}
	
	@Override
	public Admin valid(Admin admin) {
		String condition = " and deleted is null and username='" + admin.getUsername()+"'";
		List beans=adminDao.gets(condition);

		if(beans!=null&&beans.size()==1){
			Admin bean=(Admin)beans.get(0);
			if(new MD5().getMD5ofStr(""+admin.getPassword()).equalsIgnoreCase(bean.getPassword())){
				return bean;
			}
		}
		
		return null;
	}
}
