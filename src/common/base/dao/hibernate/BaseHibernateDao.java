package common.base.dao.hibernate;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import common.base.dao.Dao;
import common.base.entity.Entity;
import common.component.permission.entity.Admin;
import common.component.permission.util.GlobeData;

public abstract class BaseHibernateDao extends HibernateDaoSupport implements Dao{
	
	protected Logger log = Logger.getLogger(this.getClass());

	protected abstract Class getPersistentClass();

	@Override
	public void create(Entity entity) {
		setAuditProperty(entity,0);
		getHibernateTemplate().save(entity);
	}
	
	@Override
	public Entity save(Entity entity) {
		if("-1".equalsIgnoreCase(entity.getId())){//新增的情况
			setAuditProperty(entity,0);
			getHibernateTemplate().save(entity);
		}else{
			setAuditProperty(entity,1);
			getHibernateTemplate().update(entity);
		}
		return entity;
	}

	@Override
	public void delete(Serializable id) {
		Entity entity=(Entity) getById(id);
		getHibernateTemplate().delete(entity);
	}

	@Override
	public void delete(Entity entity) {
		getHibernateTemplate().delete(entity);
	}

	@Override
	public void deleteTag(Serializable id) {
		Entity entity=(Entity) getById(id);
		entity.setModifyTime(new Date());
		entity.setDeleted(true);
		getHibernateTemplate().saveOrUpdate(entity);
	}
	
	@Override
	public void deleteTag(Entity entity) {
		entity.setModifyTime(new Date());
		entity.setDeleted(true);
		getHibernateTemplate().saveOrUpdate(entity);
	}

	@Override
	public Entity update(Entity entity) {
		setAuditProperty(entity,1);
		getHibernateTemplate().update(entity);
		return entity;
	}
	
	@Override
	public List gets(String condition) {
		return gets(null, condition);
	}
	@Override
	public List gets(Class clazz, String condition) {
		
		String queryStr;
		
		if(condition!=null&&
				(condition.trim().toLowerCase().startsWith("select ")
						||condition.trim().toLowerCase().startsWith("from "))){
			queryStr=condition;
		}else{
			String queryClassName;
			if (clazz == null)
				queryClassName = getPersistentClass().getName();
			else
				queryClassName = clazz.getName();
			
			//if(!StringUtil.isEmptyString(condition)&&!condition.trim().toLowerCase().startsWith("and"))
				//condition=" and "+condition;
			
			queryStr="From " + queryClassName + " where 1=1 " + condition;
		}
		
		List results = getHibernateTemplate().find(queryStr);
		return results;
	}

	private void setAuditProperty(Object entity,int flag) {
		if (entity instanceof Entity) {
			Admin user=GlobeData.getUser();

			if(flag==0){
				if(user!=null){
					((Entity) entity).setCreator(user.getId());
				}
				((Entity) entity).setCreateTime(new Date());
			}else{
				if(user!=null){
					((Entity) entity).setModifier(user.getId());
				}
				((Entity) entity).setModifyTime(new Date());
			}
		}
	}
	
	@Override
    public List getObjs(Class clazz) {
        return getHibernateTemplate().loadAll(clazz);
    }
	
	@Override
	public Object getById(Class clazz, Serializable id) {
        Object o = getHibernateTemplate().get(clazz, id);

        if (o == null) {
            throw new ObjectRetrievalFailureException(clazz, id);
        }

        return o;
	}

	@Override
	public void removeObj(Class clazz, Serializable id) {
		getHibernateTemplate().delete(getById(clazz, id));

	}

	@Override
	public void saveObj(Object o) {
		getHibernateTemplate().saveOrUpdate(o);

	}
	
	@Override
	public Object getById(Serializable id) {
		return getById(getPersistentClass(),id);
	}
	
}
