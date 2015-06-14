package common.component.cms.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.dialect.DialectFactory;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.UUIDHexGenerator;
import org.hibernate.sql.Insert;
import org.hibernate.sql.Select;
import org.hibernate.sql.Update;

import common.component.cms.dao.BlobDao;
import common.component.cms.dao.CatalogueDao;
import common.component.cms.dao.ClobDao;
import common.component.cms.entity.Attribute;
import common.component.cms.entity.BLOB;
import common.component.cms.entity.CLOB;
import common.component.cms.entity.Catalogue;
import common.component.cms.entity.Content;
import common.component.cms.service.ContentService;
import common.component.cms.util.CmsUtil;
import common.component.ectable.dao.EcTableDAOJdbc;
import common.component.ectable.service.impl.EcTableServiceImpl;
import common.exception.ExistException;
import common.util.StringUtil;
/**
 * @author itfish
 *
 * �û�����ӿ�内容管理模块
 */
public class ContentServiceImpl extends EcTableServiceImpl implements ContentService{
	
	protected CatalogueDao catalogueDao;
	
	protected ClobDao clobDao;
	
	protected BlobDao blobDao;

	protected static IdentifierGenerator gen = new UUIDHexGenerator();
	
	public ContentServiceImpl(CatalogueDao catalogueDao,ClobDao clobDao,BlobDao blobDao,EcTableDAOJdbc ecTableDAO,String dialectName) {
		this.catalogueDao=catalogueDao;
		this.clobDao=clobDao;
		this.blobDao=blobDao;
		this.ecTableDAO=ecTableDAO;
		this.dialectName=dialectName;
		this.dialect=DialectFactory.buildDialect(dialectName);
	}
	
	@Override
	public Content save(Content bean,List<Attribute> attributes,Map<String,String> columns) throws ExistException, SQLException, IOException, Exception {
		
		Catalogue cata=(Catalogue)catalogueDao.getById(bean.getCatalogue().getId());
		bean.setCatalogue(cata);
		String tableName=CmsUtil.getTableName(bean);
		//String tableName=CmsUtil.getTableName(cata);
		 
		//在新增时，表名相同的记录//或者修改时候除本身外
/*		Select select=new Select(dialect);
		select.setFromClause(tableName, "alias");
		select.setWhereClause(" p_id ='"+bean.getId()+"'");
		
		List beans=ecTableDAO.getSimpleList(select.toStatementString());
		
		if(beans!=null&&beans.size()>=1){//新增存在多条记录的情况//或修改
			throw new ExistException("reduplicate name");
		}*/
		
		List<String> clobcolumns=getClobcolumns(attributes);
		
		Insert insert=new Insert(dialect);
		insert.setTableName(tableName);
		
		Iterator key=columns.keySet().iterator();
		Iterator value=columns.values().iterator();
		while(key.hasNext()){
			String column=""+key.next();
			
			if(!clobcolumns.contains(column))//非clob字段，其中blob字段已经优先处理，这边的id作为普通字段进行处理
				insert.addColumn(column,"'"+value.next()+"'");
			else{
				String clobs=""+value.next();
				
				String clobid=insertClob(clobs);//clob具体数据库操作
				insert.addColumn(column,"'"+clobid+"'");
			}
		}

		insert.addColumn("p_id","'"+gen.generate(null, null)+"'");
		
		ecTableDAO.execute(insert.toStatementString());
		return bean;
	}
	
	@Override
	public Map<String,String> getClobBlobs(Content bean,List<Attribute> attributes,Map<String,String> columns) throws SQLException, IOException {
		Map<String,String> clobblobs=new HashMap<String,String>();
		
		Map<String,List<String>> ret=getClobBlobColumns(attributes);
		List<String> clobcolumns=ret.get(""+Types.CLOB);
		List<String> blobcolumns=ret.get(""+Types.BLOB);
		
		StringBuffer content=new StringBuffer();
		for(int i=0;i<clobcolumns.size();i++){
			String thecolumn=clobcolumns.get(i);
			
			String id=columns.get(thecolumn.toUpperCase());

			if(StringUtil.isEmptyString(id)){
				continue;
			}
			
			CLOB clob=(CLOB)clobDao.getById(id);//导致多次查询 to modify
			
			Reader is = clob.getContent().getCharacterStream();
			BufferedReader br = new BufferedReader(is); 
			String s = br.readLine(); 
		    while (s != null)
		    { 
		    	content.append(s);  
		        s = br.readLine(); 
		    } 
		    clobblobs.put(id, content.toString());//clobblobs.put(thecolumn, content.toString());
		}
		
		for(int i=0;i<blobcolumns.size();i++){
			String thecolumn=blobcolumns.get(i);
			
			String ids=columns.get(thecolumn.toUpperCase());
			
			if(StringUtil.isEmptyString(ids)){
				continue;
			}
			
			String[] id=ids.split(",");
			for(int j=0;j<id.length;j++){
				if(StringUtil.isEmptyString(id[j])){
					continue;
				}
				
				BLOB blob=(BLOB)blobDao.getById(id[j]);//导致多次查询 to modify
				clobblobs.put(id[j], blob.getName());//clobblobs.put(thecolumn, blob.getName());
			}
		}
		
		return clobblobs;
	}
	
	private String insertClob(String src) throws SQLException, IOException{
		return clobDao.saveClob(src);

	}
	
	private String updateClob(String id,String src) throws SQLException, IOException{
		return clobDao.updateClob(id,src);

	}
	
	
	@Override
	public void deleteAll(Content bean,String ids) {
		Catalogue cata=(Catalogue)catalogueDao.getById(bean.getCatalogue().getId());
		bean.setCatalogue(cata);
		String tableName=CmsUtil.getTableName(bean);
		//String tableName=CmsUtil.getTableName(cata);
		Update update=new Update(dialect);
		
		update.setTableName(tableName);
		update.addColumn("p_deleted","1");
		update.setWhere(" p_id in ("+ids+")");
		
		ecTableDAO.execute(update.toStatementString());
	}
	
	@Override
	public void delete(Content bean) {
		Catalogue cata=(Catalogue)catalogueDao.getById(bean.getCatalogue().getId());
		bean.setCatalogue(cata);
		String tableName=CmsUtil.getTableName(bean);
		//String tableName=CmsUtil.getTableName(cata);
		
		Update update=new Update(dialect);
		
		update.setTableName(tableName);
		update.addColumn("p_deleted","1");
		update.setWhere(" p_id= '"+bean.getId()+"'");
		
		ecTableDAO.execute(update.toStatementString());
	}

	@Override
	public Content update(Content newContent,List<Attribute> attributes,Map<String,String> columns,Map<String,String> clobblobs) throws SQLException, IOException,Exception {
		Catalogue cata=(Catalogue)catalogueDao.getById(newContent.getCatalogue().getId());
		newContent.setCatalogue(cata);
		String tableName=CmsUtil.getTableName(newContent);
		//String tableName=CmsUtil.getTableName(cata);
		
		List<String> clobcolumns=getClobcolumns(attributes);
		
		Update update=new Update(dialect);
		
		update.setTableName(tableName);
		Iterator key=columns.keySet().iterator();
		Iterator value=columns.values().iterator();
		while(key.hasNext()){
			String column=""+key.next();
			
			if("p_id".equalsIgnoreCase(column))
				continue;
			
			if(!clobcolumns.contains(column))//非clob字段
				update.addColumn(column,"'"+value.next()+"'");
			
			else{
				String id=""+value.next();
				
				updateClob(id,clobblobs.get(id));//clob具体数据库操作
			}
			
		}
		update.setWhere(" p_id= '"+newContent.getId()+"'");
		
		ecTableDAO.execute(update.toStatementString());
		
		return newContent;
	}
	
	@Override
	public Map getById(Content bean){
		
		Catalogue cata=(Catalogue)catalogueDao.getById(bean.getCatalogue().getId());
		bean.setCatalogue(cata);
		String tableName=CmsUtil.getTableName(bean);
		//String tableName=CmsUtil.getTableName(cata);
		
		Select select=new Select(dialect);
		
		select.setSelectClause(" * ");
		select.setFromClause(tableName, "alias");
		select.setWhereClause(" p_id ='"+bean.getId()+"'");
		
		List ret=ecTableDAO.getSimpleList(select.toStatementString());
		
		if(ret.size()>=1){
			Object obj=ret.get(0);
			if(obj instanceof Map)
				return (Map)obj;
		}
		return null;
	}
	
	@Override
	public List<Map> getList(Content bean,String selectClause) {
		Select select=new Select(dialect);
		
		Catalogue cata=(Catalogue)catalogueDao.getById(bean.getCatalogue().getId());
		bean.setCatalogue(cata);
		String tableName=CmsUtil.getTableName(bean);
		//String tableName=CmsUtil.getTableName(cata);
		select.setSelectClause(selectClause);
		select.setFromClause(tableName, "alias");
		
		List ret=ecTableDAO.getSimpleList(select.toStatementString());

		return ret;
	}
	
	@Override
	public List<Map> getList(Content bean,String selectClause,String whereClause) {
		Select select=new Select(dialect);
		
		Catalogue cata=(Catalogue)catalogueDao.getById(bean.getCatalogue().getId());
		bean.setCatalogue(cata);
		String tableName=CmsUtil.getTableName(bean);
		//String tableName=CmsUtil.getTableName(cata);
		select.setSelectClause(selectClause);
		select.setFromClause(tableName, "alias");
		select.setWhereClause(whereClause);
		
		List ret=ecTableDAO.getSimpleList(select.toStatementString());

		return ret;
	}
	@Override
	public String getTableName(Content bean){
		Catalogue cata=(Catalogue)catalogueDao.getById(bean.getCatalogue().getId());
		bean.setCatalogue(cata);
		String tableName=CmsUtil.getTableName(bean);
		//String tableName=CmsUtil.getTableName(cata);
		return tableName;
	}
	
	//获取clob column列表
	private List<String> getClobcolumns(List<Attribute> attributes){//to modify 改写为通用的，不通过类型获取其他表的字段
		List<String> clobcolumns=new ArrayList<String>();//clob字段集合
		for(int i=0;i<attributes.size();i++){
			Attribute ele=attributes.get(i);
			if(ele.getType().equalsIgnoreCase(""+Types.CLOB))
				clobcolumns.add(ele.getColumn());
		}
		return clobcolumns;
	}
	
	//获取blob column列表
	private List<String> getBlobcolumns(List<Attribute> attributes){
		List<String> blobcolumns=new ArrayList<String>();//blob字段集合
		for(int i=0;i<attributes.size();i++){
			Attribute ele=attributes.get(i);
			if(ele.getType().equalsIgnoreCase(""+Types.BLOB))
				blobcolumns.add(ele.getColumn());
		}
		return blobcolumns;
	}
	
	//整合clob blob
	private Map<String,List<String>> getClobBlobColumns(List<Attribute> attributes){
		Map<String,List<String>> ret=new HashMap<String,List<String>>();
		
		List<String> clobcolumns=new ArrayList<String>();//clob字段集合
		List<String> blobcolumns=new ArrayList<String>();//blob字段集合
		for(int i=0;i<attributes.size();i++){
			Attribute ele=attributes.get(i);
			if(ele.getType().equalsIgnoreCase(""+Types.BLOB))
				blobcolumns.add(ele.getColumn());
			
			if(ele.getType().equalsIgnoreCase(""+Types.CLOB))
				clobcolumns.add(ele.getColumn());
		}
		
		ret.put(""+Types.BLOB, blobcolumns);
		ret.put(""+Types.CLOB, clobcolumns);
		
		return ret;
	}

}
