package common.component.application.hotel.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import common.component.application.hotel.GlobeData;
import common.component.application.hotel.dao.HotelDao;
import common.component.application.hotel.entity.Hotel;
import common.component.application.hotel.service.HotelService;
import common.component.ectable.dao.EcTableDAOJdbc;
import common.component.ectable.service.impl.EcTableServiceImpl;
import common.exception.ExistException;
/**
 * @author huanglf
 *
 * �û�����ӿ�酒店模块，酒店登陆验证、酒店增删改查�û���½��֤���û���ɾ�Ĳ�ȹ���
 */
public class HotelServiceImpl extends EcTableServiceImpl implements HotelService{
	private HotelDao hotelDao;
	
	public HotelServiceImpl(HotelDao hotelDao,EcTableDAOJdbc ecTableDAO) {
		this.hotelDao = hotelDao;
		this.ecTableDAO=ecTableDAO;
	}
	
	@Override
	public Hotel save(Hotel hotel) throws ExistException {
		hotelDao.save(hotel);
		
		initHotel(hotel.getArea(), hotel);
		
		return hotel;
	}
	

	@Override
	public void delete(String id) {
		hotelDao.deleteTag(id);
	}

	@Override
	public Hotel update(Hotel newhotel) {
		
	    Hotel hotel = getById(newhotel.getId());
	    hotel.setName(newhotel.getName());
	    hotel.setAddress(newhotel.getAddress());
	    hotel.setDesc(newhotel.getDesc());
	    hotel.setMapx(newhotel.getMapx());
	    hotel.setMapy(newhotel.getMapy());
	    hotel.setPrices(newhotel.getPrices());
	    hotel.setArea(newhotel.getArea());
	    hotel.setRemark(newhotel.getRemark());
	    
		hotel=(Hotel)hotelDao.update(hotel);
		
		initHotel(newhotel.getArea());
		return hotel;
	}
	
	public Hotel getById(final Serializable id){
		
		Object obj=this.hotelDao.getById(id);
		
		if(obj instanceof Hotel)
			return (Hotel)obj;
		return null;
	}

	@Override
	public Hotel getById(String id) {
		Object obj=this.hotelDao.getById(id);
		
		if(obj instanceof Hotel)
			return (Hotel)obj;
		return null;
	}

	@Override
	public List<Hotel> getList() {
		String condition=" and deleted is null order by area desc,id desc";
		List list = hotelDao.gets(condition);
		return list;
	}
	
	@Override
	public List<Hotel> getList(String condition) {
		List list = hotelDao.gets(condition);
		return list;
	}
	
	@Override
	public List<Hotel> getHotelParas(String hotelkey) {
		List list = hotelDao.gets(" and area='"+hotelkey+"' order by area desc,id desc");
		return list;
	}
	
	/**
	 * 显示参数
	 */
	public List<Hotel> getHotelParas() {
		List<Hotel> ret=getList();
		return ret;
	}
	
	//启动时，初始化
	public void initHotel(){
		List<Hotel> paras=this.getHotelParas();
		
		for(int i=0;i<paras.size();i++){
			
			List<Hotel> list=GlobeData.getHotel(paras.get(i).getArea());
			
			if(list==null)
				list=new ArrayList<Hotel>();
			
			list.add(paras.get(i));//更新数值
			
			GlobeData.setHotel(paras.get(i).getArea(), list);//加入缓存
			
			if(log.isDebugEnabled())
				log.debug(paras.get(i).getArea()+":"+paras.get(i).getName());
			
		}
	}
	
	//适用于新增时候，增加键值对
	public void initHotel(String hotelkey,Hotel hotelvalue){

			List<Hotel> list=GlobeData.getHotel(hotelkey);
			
			if(list==null)
				list=new ArrayList<Hotel>();
			
			list.add(hotelvalue);//新增数值
			
			GlobeData.setHotel(hotelkey, list);//加入缓存
			
			if(log.isDebugEnabled())
				log.debug(hotelkey+":"+hotelvalue);

	}
	
	//适用于修改时候，初始化指定key的值，对指定主键重新进行初始化
	public void initHotel(String hotelkey){

		List<Hotel> paras=this.getHotelParas(hotelkey);
		
		for(int i=0;i<paras.size();i++){
			
			List<Hotel> list=new ArrayList<Hotel>();
			
			list.add(paras.get(i));//更新数值
			
			GlobeData.setHotel(hotelkey, list);//加入缓存
			
			if(log.isDebugEnabled())
				log.debug(hotelkey+":"+paras.get(i));
			
		}
	}

}
