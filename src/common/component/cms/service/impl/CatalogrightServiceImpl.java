package common.component.cms.service.impl;

import java.util.ArrayList;
import java.util.List;

import common.component.cms.dao.CatalogrightDao;
import common.component.cms.entity.Catalogright;
import common.component.cms.service.CatalogrightService;
import common.component.ectable.dao.EcTableDAOJdbc;
import common.component.ectable.service.impl.EcTableServiceImpl;
import common.component.permission.entity.Role;
import common.util.StringUtil;

public class CatalogrightServiceImpl extends EcTableServiceImpl implements CatalogrightService {
	private CatalogrightDao catalogrightDao;

	public CatalogrightServiceImpl(CatalogrightDao catalogrightDao,EcTableDAOJdbc ecTableDAO) {
		this.catalogrightDao = catalogrightDao;
		this.ecTableDAO=ecTableDAO;
	}

	@Override
	public List<Catalogright> getTree(String roleId) {
		String condition="";
		
		if(!StringUtil.isEmptyString(roleId)){
			condition=" and t.role.id='"+roleId+"'";
			//condition=" and (t.role.id='"+roleId+"' or t.role.id is null)";
			//condition=" where t.p_role='"+roleId+"'";
		}
		condition+=" and t.deleted is null and nav.deleted is null ";
		//return catalogrightDao.gets(condition);
		return catalogrightDao.gets("From Catalogright as t left join fetch t.catalogue nav left join fetch nav.parent where 1=1 "+condition);
		//return catalogrightDao.gets("From Catalogright as t right join fetch t.catalogue nav left join fetch nav.parent where 1=1 "+condition);
		//return catalogrightDao.gets("From Catalogright as t Left Join t.catalogue as t1 where 1=1 "+condition); //has bug
		//return catalogrightDao.gets("select t.checked,t1.name,t1.id,t1.parent from catalogue t1 left outer join catalogright t "+condition);
		//return ecTableDAO.getSimpleList("select t.p_checked,t1.p_name,t1.p_id,t1.p_parent from t_catalogright t right outer join t_catalogue t1 on t.p_catalogue=t1.p_id "+condition);
	}

	@Override
	public List<Catalogright> getTree() {
		return getTree(null);
	}

	@Override
	public void save(Role bean,String checkrecords) {
		
		List<String> list=new ArrayList<String>();
		
		//改为在操作栏目时，进行同步
		//list.add("insert into t_catalogright(p_id,p_role,p_catalogue,p_checked) select CONCAT('"+bean.getId()+"',p_id),'"+bean.getId()+"',p_id,null from t_catalogue t where t.p_deleted is null and t.p_id not in (select p_catalogue from t_catalogright where p_role='"+bean.getId()+"')");
		
		String checksql="update t_catalogright set p_checked=1 where p_role='"+bean.getId()+"'";
		String unchecksql="update t_catalogright set p_checked=null where p_role='"+bean.getId()+"'";
		if(!StringUtil.isEmptyString(checkrecords)){
			checksql=checksql+" and p_catalogue in("+checkrecords+")";
			unchecksql=unchecksql+" and p_catalogue not in("+checkrecords+")";
		}
		list.add(checksql);
		list.add(unchecksql);
		
		ecTableDAO.executeBatch(list);
		
	}
	
	@Override
	public void save(String roleid) {
		
		String sql="insert into t_catalogright(p_id,p_role,p_catalogue,p_checked) select CONCAT('"+roleid+"',p_id),'"+roleid+"',p_id,null from t_catalogue t where t.p_deleted is null and t.p_id not in (select p_catalogue from t_catalogright where p_role='"+roleid+"')";
		
		ecTableDAO.execute(sql);
		
	}
	
	@Override
	public void save(String roleid,String catalogueid) {
		
		String sql="insert into t_catalogright(p_id,p_role,p_catalogue,p_checked) select CONCAT('"+roleid+"',p_id),'"+roleid+"',p_id,null from t_catalogue t where t.p_deleted is null and t.p_id ='"+catalogueid+"'";
		
		ecTableDAO.execute(sql);
		
	}
}
