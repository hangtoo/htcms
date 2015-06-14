package common.base.dao;

import java.io.Serializable;
import java.util.List;

import common.base.entity.Entity;
/**
 * @author huanglf
 *
 * �û�����ӿ�完成简单的数据库对象操作
 * 主要有新增、有id的删除、有id的修改及无条件查询等功能
 */
public interface Dao {
	public List getObjs(Class clazz);
	
	public Object getById(Class clazz, Serializable id);
	
	public Object getById(Serializable id);
	
	public void saveObj(Object o);
	
	public void removeObj(Class clazz, Serializable id);
	
	public List gets(String condition);
	
	public List gets(Class clazz, String condition);
		
	public void create(Entity entity);
	
	public Entity save(Entity entity);

	public Entity update(Entity entity);
	
	public void delete(Entity entity);
	
	public void delete(Serializable id);
	
	public void deleteTag(Entity entity);
	
	public void deleteTag(Serializable id);
}
