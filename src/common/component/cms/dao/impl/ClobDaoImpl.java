/**
 * 
 */
package common.component.cms.dao.impl;

import java.io.IOException;
import java.io.Writer;
import java.sql.Clob;
import java.sql.SQLException;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Session;

import common.base.dao.hibernate.BaseHibernateDao;
import common.component.cms.dao.ClobDao;
import common.component.cms.entity.CLOB;

public class ClobDaoImpl extends BaseHibernateDao implements ClobDao {

	@Override
	protected Class getPersistentClass() {
		return CLOB.class;
	}
	
	@Override
	public String saveClob(String src) throws SQLException, IOException{
		//String id=new UUIDHexGenerator().generate(null, null).toString();
		CLOB clob = new CLOB();
		clob.setContent(Hibernate.createClob(src));
		//clob.setId(id);

		Session session = this.getSession();
		//Transaction tx = session.beginTransaction();
		session.saveOrUpdate(clob);
		session.flush();
		//tx.commit();
		return clob.getId();
	}

	@Override
	public String updateClob(String id, String src) throws SQLException, IOException{
		CLOB clob = (CLOB)this.getById(id);
		clob.setContent(Hibernate.createClob(src));

		Session session = this.getSession();
		//Transaction tx = session.beginTransaction();
		session.saveOrUpdate(clob);
		session.flush();
		//tx.commit();
		return clob.getId();
	}
	
/*	@Override
	public String saveClob(String src) throws SQLException, IOException{
		//String id=new UUIDHexGenerator().generate(null, null).toString();
		CLOB clob = new CLOB();
		// 创建空Clob/Clob对象
		clob.setContent(Hibernate.createClob(" "));
		//clob.setId(id);

		Session session = this.getSession();
		//Transaction tx = session.beginTransaction();
		session.save(clob);

		// 调用flush方法，强制Hibernate立即执行insert sql
		session.flush();

		// 通过refresh方法，强制Hibernate执行select for update
		session.refresh(clob, LockMode.UPGRADE);

		// 向Clob写入实际内容
		Clob content = clob.getContent();

		Writer writer = content.setCharacterStream(1);// JDBC 3.0 get the
														// output stream from
														// the Clob to insert it

		writer.write(src);
		
		writer.close();

		session.saveOrUpdate(clob);
		session.flush();
		//tx.commit();
		return clob.getId();
	}

	@Override
	public String updateClob(String id, String src) throws SQLException, IOException{
		//String id=new UUIDHexGenerator().generate(null, null).toString();
		CLOB clob = (CLOB)this.getById(id);
		// 创建空Clob/Clob对象
		clob.setContent(Hibernate.createClob(" "));
		//clob.setId(id);

		Session session = this.getSession();
		//Transaction tx = session.beginTransaction();
		session.save(clob);

		// 调用flush方法，强制Hibernate立即执行insert sql
		session.flush();

		// 通过refresh方法，强制Hibernate执行select for update
		session.refresh(clob, LockMode.UPGRADE);

		// 向Clob写入实际内容
		Clob content = clob.getContent();

		Writer writer = content.setCharacterStream(1);// JDBC 3.0 get the
														// output stream from
														// the Clob to insert it

		writer.write(src);
		
		writer.close();

		session.saveOrUpdate(clob);
		session.flush();
		//tx.commit();
		return clob.getId();
	}*/
}
