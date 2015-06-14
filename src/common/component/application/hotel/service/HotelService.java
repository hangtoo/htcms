package common.component.application.hotel.service;

import java.util.List;
import java.util.Map;

import org.extremecomponents.table.limit.FilterSet;
import org.extremecomponents.table.limit.Sort;

import common.component.application.hotel.entity.Hotel;
import common.component.ectable.service.EcTableService;
import common.exception.ExistException;

/**
 * @author huanglf
 *
 * 酒店模块，酒店增删改查�û���½��֤���û���ɾ�Ĳ�ȹ���
 */
public interface HotelService extends EcTableService{
	
	/**
	 * 功能：酒店新增、修改
	 * 描述：
	 */
	public Hotel save(Hotel Hotel) throws ExistException;
	
	/**
	 * 功能：酒店删除
	 * 描述：
	 */
	public void delete(String id);

	/**
	 * 功能：酒店修改
	 * 描述：
	 */
	public Hotel update(Hotel Hotel);
	
	/**
	 * 功能：酒店查询
	 * 描述：
	 */
	public Hotel getById(String id);

	/**
	 * 功能：酒店列表查询
	 * 描述：
	 */
	public List<Hotel> getList(String condition);
	
	/**
	 * 功能：酒店列表查询
	 * 描述：
	 */
	public List<Hotel> getList();

	/**
	 * 功能：酒店列表查询
	 * 描述：
	 */
	public Map search(String hql, int page, int pageSize, Sort sort,
			FilterSet filterSet, boolean exported);

	
	public List<Hotel> getHotelParas(String hotelkey);
	
	public void initHotel();
	public void initHotel(String hotelkey,Hotel hotelvalue);
	public void initHotel(String hotelkey);
}
