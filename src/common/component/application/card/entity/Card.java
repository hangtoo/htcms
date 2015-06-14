package common.component.application.card.entity;

import common.base.entity.Entity;
import common.component.application.hotel.entity.Hotel;
import common.component.application.user.entity.User;

/**
 * @author Administrator
 * @hibernate.class table="t_card" dynamic-update="true" dynamic-insert="true"
 */
public class Card extends Entity {
	
	private static final long serialVersionUID = 9L;
	
	private Hotel hotel;
	private String name;
	private String no;
	
	private User user;
	
	private float off;
	
	private String desc;
	
	/**
	 * @return Returns
	 * @hibernate.many-to-one column="p_hotel"
	 */
	public Hotel getHotel() {
		return hotel;
	}
	/**
	 * @param hotel the hotel to set
	 */
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
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
	 * @return the no
	 * @hibernate.property column = "p_no"
	 */
	public String getNo() {
		return no;
	}
	/**
	 * @param no the no to set
	 */
	public void setNo(String no) {
		this.no = no;
	}
	/**
	 * @return the off
	 * @hibernate.property column = "p_off"
	 */
	public float getOff() {
		return off;
	}
	/**
	 * @param off the off to set
	 */
	public void setOff(float off) {
		this.off = off;
	}
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
	 * @return the user
	 * @hibernate.many-to-one column = "p_user"
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

}
