/**
 * 
 */
package common.component.cms.dao.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Session;

import common.base.dao.hibernate.BaseHibernateDao;
import common.component.cms.dao.BlobDao;
import common.component.cms.entity.BLOB;

public class BlobDaoImpl extends BaseHibernateDao implements BlobDao {

	@Override
	protected Class getPersistentClass() {
		return BLOB.class;
	}
	@Override
	public BLOB saveBlob(File documentFile,String documentFileContentType,String documentFileFileName) throws SQLException, IOException{
		
		FileInputStream fin = new FileInputStream(documentFile);

		int finlength=fin.available();
		
		if(finlength>65*1024)
			return null;
		
		byte[] fbyte = new byte[finlength];
		fin.read(fbyte);
		fin.close();
		
		BLOB blob = new BLOB();
		// 创建空Blob/Clob对象
		blob.setContent(Hibernate.createBlob(fbyte));
		blob.setName(documentFileFileName);
		blob.setType(documentFileContentType);

		Session session = this.getSession();//to do 确认session是否需要关闭
		//Transaction tx = session.beginTransaction();
		session.save(blob);

		session.flush();
		//tx.commit();
		return blob;
	}
	
/*	@Override
	public BLOB saveBlob(File src) throws SQLException, IOException{
		
		//String id=new UUIDHexGenerator().generate(null, null).toString();
		
		BLOB blob = new BLOB();
		// 创建空Blob/Clob对象
		blob.setContent(Hibernate.createBlob(new byte[1]));
		blob.setName(src.getName());
		//blob.setId(id);

		Session session = this.getSession();//to do 确认session是否需要关闭
		//Transaction tx = session.beginTransaction();
		session.save(blob);

		// 调用flush方法，强制Hibernate立即执行insert sql
		session.flush();

		// 通过refresh方法，强制Hibernate执行select for update
		session.refresh(blob, LockMode.UPGRADE);

		// 向Blob写入实际内容
		Blob content = blob.getContent();

		//OutputStream out=content.getBinaryOutputStream(0);// JDBC 2.0 get the
														// output stream from
														// the Blob to insert it
		FileInputStream fin = new FileInputStream(src);

		int length=fin.available();
		
		OutputStream out = content.setBinaryStream(1);// JDBC 3.0 get the
														// output stream from
														// the Blob to insert it

		
		byte[] buf = new byte[length];
		int len;
		while ((len = fin.read(buf)) != -1) {
			out.write(buf, 0, len);
		}
		fin.close();
		out.close();

		session.saveOrUpdate(blob);
		session.flush();
		//tx.commit();
		return blob;
	}*/

	@Override
	public BLOB getBlob(String id) throws SQLException, IOException {
		Session session = this.getSession();
		BLOB blob = (BLOB) session.get(BLOB.class, id);
		return blob;
	}
}
