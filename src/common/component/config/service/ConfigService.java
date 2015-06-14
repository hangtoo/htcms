package common.component.config.service;

import java.util.List;
import java.util.Map;

import org.extremecomponents.table.limit.FilterSet;
import org.extremecomponents.table.limit.Sort;

import common.component.config.entity.Config;
import common.component.ectable.service.EcTableService;
import common.exception.ExistException;

/**
 * @author huanglf
 *
 * 配置模块，配置增删改查�û���½��֤���û���ɾ�Ĳ�ȹ���
 */
public interface ConfigService extends EcTableService{
	
	/**
	 * 功能：配置新增、修改
	 * 描述：
	 */
	public Config save(Config Config) throws ExistException;
	
	/**
	 * 功能：配置删除
	 * 描述：
	 */
	public void delete(String id);

	/**
	 * 功能：配置修改
	 * 描述：
	 */
	public Config update(Config Config);
	
	/**
	 * 功能：配置查询
	 * 描述：
	 */
	public Config getById(String id);

	/**
	 * 功能：配置列表查询
	 * 描述：
	 */
	public List<Config> getList(String condition);
	
	/**
	 * 功能：配置列表查询
	 * 描述：
	 */
	public List<Config> getList();

	/**
	 * 功能：配置列表查询
	 * 描述：
	 */
	public Map search(String hql, int page, int pageSize, Sort sort,
			FilterSet filterSet, boolean exported);

	
	public List<Config> getConfigParas(String configkey);
	
	public void initConfig();
	public void initConfig(String configkey,String configvalue);
	public void initConfig(String configkey);
}
