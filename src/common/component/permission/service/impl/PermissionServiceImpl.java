package common.component.permission.service.impl;

import java.util.ArrayList;
import java.util.List;

import common.component.ectable.dao.EcTableDAOJdbc;
import common.component.ectable.service.impl.EcTableServiceImpl;
import common.component.permission.dao.PermissionDao;
import common.component.permission.entity.Permission;
import common.component.permission.entity.Role;
import common.component.permission.service.PermissionService;
import common.util.StringUtil;

public class PermissionServiceImpl extends EcTableServiceImpl implements PermissionService {
	private PermissionDao permissionDao;

	public PermissionServiceImpl(PermissionDao permissionDao,EcTableDAOJdbc ecTableDAO) {
		this.permissionDao = permissionDao;
		this.ecTableDAO=ecTableDAO;
	}

	@Override
	public List<Permission> getTree(String roleId) {
		String condition="";
		
		if(!StringUtil.isEmptyString(roleId)){
			condition=" and t.role.id='"+roleId+"'";
			//condition=" where t.p_role='"+roleId+"'";
		}
		condition+=" and t.deleted is null ";
		//return permissionDao.gets(condition);
		return permissionDao.gets("From Permission as t left join fetch t.navigate nav left join fetch nav.parent where 1=1 "+condition);
		//return permissionDao.gets("From Permission as t Left Join t.navigate as t1 where 1=1 "+condition); //has bug
		//return permissionDao.gets("select t.checked,t1.name,t1.id,t1.parent from navigate t1 left outer join permission t "+condition);
		//return ecTableDAO.getSimpleList("select t.p_checked,t1.p_name,t1.p_id,t1.p_parent from t_permission t right outer join t_navigate t1 on t.p_navigate=t1.p_id "+condition);
	}

	@Override
	public List<Permission> getTree() {
		return getTree(null);
	}

	@Override
	public void save(Role bean,String checkrecords) {
		
		List<String> list=new ArrayList<String>();
		list.add("insert into t_permission(p_id,p_role,p_navigate,p_checked) select CONCAT('"+bean.getId()+"',p_id),'"+bean.getId()+"',p_id,null from t_navigate t where t.p_deleted is null and t.p_id not in (select p_navigate from t_permission where p_role='"+bean.getId()+"')");
		
		String checksql="update t_permission set p_checked=1 where p_role='"+bean.getId()+"'";
		String unchecksql="update t_permission set p_checked=null where p_role='"+bean.getId()+"'";
		if(!StringUtil.isEmptyString(checkrecords)){
			checksql=checksql+" and p_navigate in("+checkrecords+")";
			unchecksql=unchecksql+" and p_navigate not in("+checkrecords+")";
		}
		list.add(checksql);
		list.add(unchecksql);
		
		ecTableDAO.executeBatch(list);
		
	}
}
