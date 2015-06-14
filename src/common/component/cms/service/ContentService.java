package common.component.cms.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.extremecomponents.table.limit.FilterSet;
import org.extremecomponents.table.limit.Sort;

import common.component.cms.entity.Attribute;
import common.component.cms.entity.Content;
import common.component.ectable.service.EcTableService;
import common.exception.ExistException;
/**
 * @author huanglf
 *
 * �û�����ӿ�内容模块，获取内容树结构
 */
public interface ContentService  extends EcTableService{
	/**
	 * 内容：内容新增、修改
	 * 描述：
	 * @throws Exception 
	 */
	public Content save(Content catalog,List<Attribute> attributes,Map<String,String> columns) throws  ExistException, SQLException, IOException, Exception;
	
	/**
	 * 内容：内容删除
	 * 描述：
	 */
	public void delete(Content id);
	
	/**
	 * 内容：内容删除所有 id以,隔开
	 * 描述：
	 */
	public void deleteAll(Content content,String ids);

	/**
	 * 内容：内容修改
	 * 描述：
	 */
	public Content update(Content bean,List<Attribute> attributes,Map<String,String> columns,Map<String,String> clobblobs) throws SQLException, IOException,Exception;

	/**
	 * 内容：内容查询
	 * 描述：
	 */
	public Map getById(Content bean);
	
	/**
	 * 内容：内容列表查询
	 * 描述：
	 */
	public List<Map> getList(Content bean,String selectClause,String whereClause);
	
	/**
	 * 内容：内容列表查询
	 * 描述：
	 */
	public List<Map> getList(Content bean,String selectClause);
	
	/**
	 * 内容：内容列表查询
	 * 描述：
	 */
	public Map search(String hql, int page, int pageSize, Sort sort,
			FilterSet filterSet, boolean exported);
	
	/**
	 * 内容：表名获取
	 * 描述：
	 */
	public String getTableName(Content bean);
	
	/**
	 * 内容：获取clob内容,及blob文件名
	 * 描述：其中bean 代表栏目名 attributes代表栏目属性列表 columns代表属性及其数值对
	 */
	public Map<String,String> getClobBlobs(Content bean,List<Attribute> attributes,Map<String,String> columns) throws SQLException, IOException;
}
