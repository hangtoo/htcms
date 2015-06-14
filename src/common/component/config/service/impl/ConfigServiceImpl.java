package common.component.config.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import common.component.config.GlobeData;
import common.component.config.dao.ConfigDao;
import common.component.config.entity.Config;
import common.component.config.service.ConfigService;
import common.component.ectable.dao.EcTableDAOJdbc;
import common.component.ectable.service.impl.EcTableServiceImpl;
import common.exception.ExistException;
/**
 * @author huanglf
 *
 * �û�����ӿ�用户模块，用户登陆验证、用户增删改查�û���½��֤���û���ɾ�Ĳ�ȹ���
 */
public class ConfigServiceImpl extends EcTableServiceImpl implements ConfigService{
	private ConfigDao configDao;
	
	public ConfigServiceImpl(ConfigDao configDao,EcTableDAOJdbc ecTableDAO) {
		this.configDao = configDao;
		this.ecTableDAO=ecTableDAO;
	}
	
	@Override
	public Config save(Config config) throws ExistException {
		configDao.save(config);
		
		initConfig(config.getConfigkey(), config.getConfigvalue());
		
		return config;
	}
	

	@Override
	public void delete(String id) {
		configDao.deleteTag(id);
	}

	@Override
	public Config update(Config newconfig) {
		
	    Config config = getById(newconfig.getId());
	    config.setConfigkey(newconfig.getConfigkey());
	    config.setConfigvalue(newconfig.getConfigvalue());
	    config.setRemark(newconfig.getRemark());
	    
		config=(Config)configDao.update(config);
		
		initConfig(newconfig.getConfigkey());
		return config;
	}
	
	public Config getById(final Serializable id){
		
		Object obj=this.configDao.getById(id);
		
		if(obj instanceof Config)
			return (Config)obj;
		return null;
	}

	@Override
	public Config getById(String id) {
		Object obj=this.configDao.getById(id);
		
		if(obj instanceof Config)
			return (Config)obj;
		return null;
	}

	@Override
	public List<Config> getList() {
		String condition=" and deleted is null order by configkey desc,id desc";
		List list = configDao.gets(condition);
		return list;
	}
	
	@Override
	public List<Config> getList(String condition) {
		List list = configDao.gets(condition);
		return list;
	}
	
	@Override
	public List<Config> getConfigParas(String configkey) {
		List list = configDao.gets(" and configkey='"+configkey+"' order by configkey desc,id desc");
		return list;
	}
	
	/**
	 * 显示参数
	 */
	public List<Config> getConfigParas() {
		List<Config> ret=getList();
		return ret;
	}
	
	//启动时，初始化
	public void initConfig(){
		List<Config> paras=this.getConfigParas();
		
		for(int i=0;i<paras.size();i++){
			
			List<String> list=GlobeData.getConfig(paras.get(i).getConfigkey());
			
			if(list==null)
				list=new ArrayList<String>();
			
			list.add(paras.get(i).getConfigvalue());//更新数值
			
			GlobeData.setConfig(paras.get(i).getConfigkey(), list);//加入缓存
			
			if(log.isDebugEnabled())
				log.debug(paras.get(i).getConfigkey()+":"+paras.get(i).getConfigvalue());
			
		}
	}
	
	//适用于新增时候，增加键值对
	public void initConfig(String configkey,String configvalue){

			List<String> list=GlobeData.getConfig(configkey);
			
			if(list==null)
				list=new ArrayList<String>();
			
			list.add(configvalue);//新增数值
			
			GlobeData.setConfig(configkey, list);//加入缓存
			
			if(log.isDebugEnabled())
				log.debug(configkey+":"+configvalue);

	}
	
	//适用于修改时候，初始化指定key的值，对指定主键重新进行初始化
	public void initConfig(String configkey){

		List<Config> paras=this.getConfigParas(configkey);
		
		for(int i=0;i<paras.size();i++){
			
			List<String> list=new ArrayList<String>();
			
			list.add(paras.get(i).getConfigvalue());//更新数值
			
			GlobeData.setConfig(configkey, list);//加入缓存
			
			if(log.isDebugEnabled())
				log.debug(configkey+":"+paras.get(i).getConfigvalue());
			
		}
	}

}
