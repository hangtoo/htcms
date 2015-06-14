package common.component.application.nurl.service.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;

import common.component.IConstants;
import common.component.application.nurl.GlobeData;
import common.component.application.nurl.dao.NurlDao;
import common.component.application.nurl.entity.Nurl;
import common.component.application.nurl.service.NurlService;
import common.component.application.user.entity.User;
import common.component.ectable.dao.EcTableDAOJdbc;
import common.component.ectable.service.impl.EcTableServiceImpl;
import common.exception.ExistException;
/**
 * @author huanglf
 *
 * �û�����ӿ�收藏模块，收藏增删改查�û���½��֤���û���ɾ�Ĳ�ȹ���
 */
public class NurlServiceImpl extends EcTableServiceImpl implements NurlService{
	private NurlDao nurlDao;
	
	public NurlServiceImpl(NurlDao nurlDao,EcTableDAOJdbc ecTableDAO) {
		this.nurlDao = nurlDao;
		this.ecTableDAO=ecTableDAO;
	}
	
	@Override
	public Nurl save(Nurl nurl) throws ExistException {
		
		nurl.setUser((User)ServletActionContext.getContext().getSession().get(IConstants.CLIENTKEY));
		
		nurl=(Nurl)nurlDao.save(nurl);
		
		initNurl(nurl.getUser().getId(), nurl);//nurl.getUser().getId()
		
		return nurl;
	}
	

	@Override
	public void delete(String id) {
		nurlDao.deleteTag(id);
		GlobeData.removeNurl(id);
	}

	@Override
	public Nurl update(Nurl newnurl) {
		
	    Nurl nurl = getById(newnurl.getId());
	    
	    nurl.setUser((User)ServletActionContext.getContext().getSession().get(IConstants.CLIENTKEY));
	    nurl.setTags((newnurl.getTags()+"").replaceAll(",", " "));
	    nurl.setNurl(newnurl.getNurl());
	    nurl.setDesc(newnurl.getDesc());
	    nurl.setRemark(newnurl.getRemark());
	    
		nurl=(Nurl)nurlDao.update(nurl);
		
		initNurl(nurl.getUser().getId());
		return nurl;
	}
	
	public Nurl getById(final Serializable id){
		
		Object obj=this.nurlDao.getById(id);
		
		if(obj instanceof Nurl)
			return (Nurl)obj;
		return null;
	}

	@Override
	public Nurl getById(String id) {
		Object obj=this.nurlDao.getById(id);
		
		if(obj instanceof Nurl)
			return (Nurl)obj;
		return null;
	}

	@Override
	public List<Nurl> getList() {
		String condition=" and deleted is null order by id desc";
		List list = nurlDao.gets(condition);
		return list;
	}
	
	@Override
	public List<Nurl> getList(String condition) {
		List list = nurlDao.gets(condition);
		return list;
	}
	
	@Override
	public List<Nurl> getNurlParas(String nurlkey) {
		List list = nurlDao.gets(" and user='"+nurlkey+"' and deleted is null order by id desc");
		return list;
	}
	
	/**
	 * 显示参数
	 */
	public List<Nurl> getNurlParas() {
		List<Nurl> ret=getList();
		return ret;
	}
	
	//启动时，初始化
	public void initNurl(){
		List<Nurl> paras=this.getNurlParas();
		
		for(int i=0;i<paras.size();i++){
			
			List<Nurl> list=GlobeData.getNurl(paras.get(i).getUser().getId());
			
			if(list==null)
				list=new ArrayList<Nurl>();
			
			list.add(paras.get(i));//更新数值
			
			GlobeData.setNurl(paras.get(i).getUser().getId(), list);//加入缓存
			
			if(log.isDebugEnabled())
				log.debug(paras.get(i).getUser().getId()+":"+paras.get(i).getNurl());
			
		}
	}
	
	//适用于新增时候，增加键值对
	public void initNurl(String nurlkey,Nurl nurlvalue){

			List<Nurl> list=GlobeData.getNurl(nurlkey);
			
			if(list==null)
				list=new ArrayList<Nurl>();
			
			if(log.isDebugEnabled()){
				log.debug(nurlkey+":");
				for(int i=0;i<list.size();i++){
					log.debug(list.get(i).getId());
				}
			}
			
			try {
				list.add((Nurl)BeanUtils.cloneBean(nurlvalue));
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//新增数值
			
			if(log.isDebugEnabled()){
				log.debug(nurlkey+":");
				for(int i=0;i<list.size();i++){
					log.debug(list.get(i).getId());
				}
			}
			
			GlobeData.setNurl(nurlkey, list);//加入缓存
			
			if(log.isDebugEnabled())
				log.debug(nurlkey+":"+nurlvalue.getId());

	}
	
	//适用于修改时候，初始化指定key的值，对指定主键重新进行初始化
	public void initNurl(String nurlkey){

		List<Nurl> paras=this.getNurlParas(nurlkey);
		
		List<Nurl> list=new ArrayList<Nurl>();
		
		for(int i=0;i<paras.size();i++){
			
			list.add(paras.get(i));//更新数值
			
			if(log.isDebugEnabled())
				log.debug(nurlkey+":"+paras.get(i));
			
		}
		
		GlobeData.setNurl(nurlkey, list);//加入缓存
	}

}
