package common.component.application.hotel.entity;

import common.base.entity.Entity;

/**
 * @author Administrator
 * @hibernate.class table="t_hotel" dynamic-update="true" dynamic-insert="true"
 */
public class Hotel extends Entity {
	
	private static final long serialVersionUID = 9L;
	
	private String area;
	private String name;
	private String address;
	
	private float mapx;
	private float mapy;
	
	private String prices;

	private String desc;


	/**
	 * @return the desc
	 * @hibernate.property column = "p_desc"
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the area
	 * @hibernate.property column = "p_area"
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * @return the name
	 * @hibernate.property column = "p_name"
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 * @hibernate.property column = "p_address"
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the mapx
	 * @hibernate.property column = "p_mapx" type="float"
	 */
	public float getMapx() {
		return mapx;
	}

	/**
	 * @param mapx the mapx to set
	 */
	public void setMapx(float mapx) {
		this.mapx = mapx;
	}

	/**
	 * @return the mapy
	 * @hibernate.property column = "p_mapy" type="float"
	 */
	public float getMapy() {
		return mapy;
	}

	/**
	 * @param mapy the mapy to set
	 */
	public void setMapy(float mapy) {
		this.mapy = mapy;
	}

	/**
	 * @return the prices
	 * @hibernate.property column = "p_prices"
	 */
	public String getPrices() {
		return prices;
	}

	/**
	 * @param prices the prices to set
	 */
	public void setPrices(String prices) {
		this.prices = prices;
	}
	
}
