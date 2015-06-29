package common.component.application.share.entity;

import java.math.BigDecimal;
import java.util.Date;

import common.base.entity.Entity;

public class Stock extends Entity {
	
	private static final long serialVersionUID = 9L;
	
	private String name;//指数名称
	private BigDecimal data;    
	private Date date;    
	private BigDecimal add; 
	private float rate;    
	private BigDecimal highdata;
	private Date highdate;
    
    //无需持久化字段

	/**
	 * @return Returns the name.
	 * @hibernate.property column="p_name" length="64"
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
	 * @return Returns the name.
	 * @hibernate.property column="p_data" length="64"
	 */
	public BigDecimal getData() {
		return data;
	}

	public void setData(BigDecimal data) {
		this.data = data;
	}
	
	/**
	 * @return Returns the name.
	 * @hibernate.property column="p_date" length="64"
	 */
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * @return Returns the name.
	 * @hibernate.property column="p_add" length="64"
	 */
	public BigDecimal getAdd() {
		return add;
	}

	public void setAdd(BigDecimal add) {
		this.add = add;
	}
	
	/**
	 * @return Returns the name.
	 * @hibernate.property column="p_rate" length="64"
	 */
	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}
	
	/**
	 * @return Returns the name.
	 * @hibernate.property column="p_highdata" length="64"
	 */
	public BigDecimal getHighdata() {
		return highdata;
	}

	public void setHighdata(BigDecimal highdata) {
		this.highdata = highdata;
	}

	/**
	 * @return Returns the name.
	 * @hibernate.property column="p_highdate" length="64"
	 */
	public Date getHighdate() {
		return highdate;
	}

	public void setHighdate(Date highdate) {
		this.highdate = highdate;
	}
 
}
