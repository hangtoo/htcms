package common.component.application.user.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import common.component.application.user.GlobeData;
import common.component.application.user.dao.UserDao;
import common.component.application.user.entity.User;
import common.component.application.user.service.UserService;
import common.component.ectable.dao.EcTableDAOJdbc;
import common.component.ectable.service.impl.EcTableServiceImpl;
import common.exception.ExistException;
import common.util.MD5;
/**
 * @author huanglf
 *
 * �û�����ӿ�用户模块，用户登陆验证、用户增删改查�û���½��֤���û���ɾ�Ĳ�ȹ���
 */
public class UserServiceImpl extends EcTableServiceImpl implements UserService{
	private UserDao userDao;
	
	public UserServiceImpl(UserDao userDao,EcTableDAOJdbc ecTableDAO) {
		this.userDao = userDao;
		this.ecTableDAO=ecTableDAO;
	}
	
	@Override
	public User save(User user) throws ExistException {
		
		List userlist=GlobeData.getUser(user.getUsername());
		
		if(userlist!=null&&userlist.size()>1)
			throw new ExistException("reduplicate user");
		
		//在新增时，用户名相同的记录//或者修改时候除本身外
		String condition = " and deleted is null and  email='"+user.getEmail()+"'";// and ('"+admin.getId()+"'='-1' or id!='"+admin.getId()+"')
		
		List beans=userDao.gets(condition);
		
		if(beans!=null&&beans.size()>=1){//新增存在多条记录的情况//或修改
			throw new ExistException("reduplicate email");
		}
		
		user.setPassword(new MD5().getMD5ofStr(""+user.getPassword()));
		
		userDao.save(user);
		
		initUser(user.getUsername(), user);
		
		return user;
	}
	

	@Override
	public void delete(String id) {
		userDao.deleteTag(id);
	}

	@Override
	public User update(User newuser) throws ExistException {
		
	    User user = getById(newuser.getId());
	    user.setName(newuser.getName());
	    user.setSex(newuser.getSex());
	    //user.setUsername(newuser.getUsername());
	    
		String condition = " and deleted is null and username!='"+user.getUsername()+"' and email='"+user.getEmail()+"'";// and ('"+admin.getId()+"'='-1' or id!='"+admin.getId()+"')
		
		List beans=userDao.gets(condition);
		
		if(beans!=null&&beans.size()>=1){//新增存在多条记录的情况//或修改
			throw new ExistException("reduplicate email");
		}
	    
	    user.setEmail(newuser.getEmail());
	    user.setAddress(newuser.getAddress());
	    user.setRemark(newuser.getRemark());
	    
		user=(User)userDao.update(user);
		
		initUser(newuser.getUsername());
		return user;
	}
	
	@Override
	public User valid(User user) {
		String condition = " and deleted is null and username='" + user.getUsername()+"'";
		List beans=userDao.gets(condition);

		if(beans!=null&&beans.size()==1){
			User bean=(User)beans.get(0);
			if(new MD5().getMD5ofStr(""+user.getPassword()).equalsIgnoreCase(bean.getPassword())){
				return bean;
			}
		}
		
		return null;
	}
	
	public User getById(final Serializable id){
		
		Object obj=this.userDao.getById(id);
		
		if(obj instanceof User)
			return (User)obj;
		return null;
	}

	@Override
	public User getById(String id) {
		Object obj=this.userDao.getById(id);
		
		if(obj instanceof User)
			return (User)obj;
		return null;
	}

	@Override
	public List<User> getList() {
		String condition=" and deleted is null order by id desc";
		List list = userDao.gets(condition);
		return list;
	}
	
	@Override
	public List<User> getList(String condition) {
		List list = userDao.gets(condition);
		return list;
	}
	
	@Override
	public List<User> getUserParas(String userkey) {
		List list = userDao.gets(" and username='"+userkey+"' order by id desc");
		return list;
	}
	
	/**
	 * 显示参数
	 */
	public List<User> getUserParas() {
		List<User> ret=getList();
		return ret;
	}
	
	//启动时，初始化
	public void initUser(){
		List<User> paras=this.getUserParas();
		
		for(int i=0;i<paras.size();i++){
			
			List<User> list=GlobeData.getUser(paras.get(i).getUsername());
			
			if(list==null)
				list=new ArrayList<User>();
			
			list.add(paras.get(i));//更新数值
			
			GlobeData.setUser(paras.get(i).getUsername(), list);//加入缓存
			
			if(log.isDebugEnabled())
				log.debug(paras.get(i).getUsername()+":"+paras.get(i).getName());
			
		}
	}
	
	//适用于新增时候，增加键值对
	public void initUser(String userkey,User uservalue){

			List<User> list=GlobeData.getUser(userkey);
			
			if(list==null)
				list=new ArrayList<User>();
			
			list.add(uservalue);//新增数值
			
			GlobeData.setUser(userkey, list);//加入缓存
			
			if(log.isDebugEnabled())
				log.debug(userkey+":"+uservalue);

	}
	
	//适用于修改时候，初始化指定key的值，对指定主键重新进行初始化
	public void initUser(String userkey){

		List<User> paras=this.getUserParas(userkey);
		
		for(int i=0;i<paras.size();i++){
			
			List<User> list=new ArrayList<User>();
			
			list.add(paras.get(i));//更新数值
			
			GlobeData.setUser(userkey, list);//加入缓存
			
			if(log.isDebugEnabled())
				log.debug(userkey+":"+paras.get(i));
			
		}
	}

}
