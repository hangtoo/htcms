package common.component.cms.service.impl;

import java.io.Serializable;
import java.sql.Types;
import java.util.List;

import org.hibernate.dialect.DialectFactory;

import common.component.IConstants;
import common.component.cms.dao.AttributeDao;
import common.component.cms.dao.CatalogueDao;
import common.component.cms.entity.Attribute;
import common.component.cms.entity.Catalogue;
import common.component.cms.service.CatalogueService;
import common.component.cms.sql.OColumn;
import common.component.cms.sql.OTable;
import common.component.cms.util.CmsUtil;
import common.component.ectable.dao.EcTableDAOJdbc;
import common.component.ectable.service.impl.EcTableServiceImpl;
import common.exception.ExistException;
/**
 * @author itfish
 *
 * �û�����ӿ�功能模块，获取功能树结构
 */
public class CatalogueServiceImpl extends EcTableServiceImpl implements CatalogueService{
	private CatalogueDao catalogueDao;
	
	private AttributeDao attributeDao;
	
	public CatalogueServiceImpl(CatalogueDao catalogueDao,AttributeDao attributeDao,EcTableDAOJdbc ecTableDAO,String dialectName) {
		this.catalogueDao = catalogueDao;
		this.attributeDao=attributeDao;
		this.ecTableDAO=ecTableDAO;
		this.dialectName=dialectName;
		this.dialect=DialectFactory.buildDialect(dialectName);
	}
	
	@Override
	public Catalogue save(Catalogue catalog) throws ExistException {
		if(!IConstants.ASCII23.equalsIgnoreCase(catalog.getTablename())){
			//在新增时，表名相同的记录//或者修改时候除本身外
			String condition = " and deleted is null and tablename='" + catalog.getTablename()+"'";// and ('"+catalog.getId()+"'='-1' or id!='"+catalog.getId()+"')
			
			List beans=catalogueDao.gets(condition);
			
			if(beans!=null&&beans.size()>=1){//新增存在多条记录的情况//或修改
				throw new ExistException("reduplicate name");
			}
		}
		catalogueDao.save(catalog);
		return catalog;
	}
	
	@Override
	public void deleteAll(String ids) {
		ecTableDAO.execute("update t_catalogue set p_deleted=1 where p_id in("+ids+")");
	}
	
	@Override
	public void delete(String id) {
		catalogueDao.deleteTag(id);
	}

	@Override
	public Catalogue update(Catalogue newCatalogue) {
		
	    Catalogue catalog = getById(newCatalogue.getId());
	    catalog.setName(newCatalogue.getName());
	    catalog.setTablename(newCatalogue.getTablename());
	    catalog.setParent(newCatalogue.getParent());
	    catalog.setRemark(newCatalogue.getRemark());
		
		return (Catalogue)catalogueDao.update(catalog);
	}
	
	public Catalogue getById(final Serializable id){
		
		Object obj=this.catalogueDao.getById(id);
		
		if(obj instanceof Catalogue)
			return (Catalogue)obj;
		return null;
	}

	@Override
	public Catalogue getById(String id) {
		Object obj=this.catalogueDao.getById(id);
		
		if(obj instanceof Catalogue)
			return (Catalogue)obj;
		return null;
	}

	@Override
	public List<Catalogue> getTree() {
		List list = catalogueDao.gets(" and deleted is null order by id");
		return list;
	}
	
	@Override
	public List<Catalogue> getList() {
		String condition=" and deleted is null";
		List list = catalogueDao.gets(condition);
		return list;
	}
	
	@Override
	public List<Catalogue> getList(String condition) {
		List list = catalogueDao.gets(condition);
		return list;
	}
	
	@Override
	public void createTable(String catalogueId) throws Exception{
		Catalogue cata=getById(catalogueId);
		
		List<Attribute> attrs=attributeDao.gets(" and catalogue='"+catalogueId+"'");
		
		String catalog=null;
		String schema=null;
		
		OTable table=new OTable(CmsUtil.getTableName(cata));
		table.setSchema(schema);
		table.setCatalog(catalog);
		
		OColumn idcolumn=OTable.getColumn("p_id",Types.VARCHAR,255);
		//PrimaryKey primaryKey=OTable.getPrimaryKey("key_id",new OColumn[]{idcolumn});
		
		table.addColumn(idcolumn);
		//table.setPrimaryKey(primaryKey);
		
		OColumn deleted=OTable.getColumn("p_deleted",Types.BIT,0);
		OColumn createTime=OTable.getColumn("p_createTime",Types.TIMESTAMP,0);
		OColumn modifyTime=OTable.getColumn("p_modifyTime",Types.TIMESTAMP,0);
		OColumn creator=OTable.getColumn("p_creator",Types.VARCHAR,255);
		OColumn modifier=OTable.getColumn("p_modifier",Types.VARCHAR,255);
		OColumn remark=OTable.getColumn("p_remark",Types.VARCHAR,255);
		table.addColumn(deleted);
		table.addColumn(createTime);
		table.addColumn(modifyTime);
		table.addColumn(creator);
		table.addColumn(modifier);
		table.addColumn(remark);
		
		for(int i=0;i<attrs.size();i++){
			Attribute attr=attrs.get(i);
			OColumn namecolumn=OTable.getColumn(attr.getColumn(),attr.getType(),attr.getLength());
			table.addColumn(namecolumn);
		}

		String strSql=table.sqlCreateString(dialect, null, catalog, schema);
		
		ecTableDAO.execute(strSql);
	}
	

	
}
