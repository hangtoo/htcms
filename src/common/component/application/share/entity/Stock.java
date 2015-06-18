package common.component.application.share.entity;

import java.math.BigDecimal;
import java.util.Date;

import common.base.entity.Entity;

/**
 * @author Administrator
 * @hibernate.class table="t_stock" dynamic-update="true" dynamic-insert="true"
 */
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
	 * @return the data
	 * @hibernate.property column = "p_data"
	 */
	public BigDecimal getData() {
		return data;
	}

	public void setData(BigDecimal data) {
		this.data = data;
	}
	
	/**
	 * @return the date
	 * @hibernate.property column = "p_date"
	 */
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * @return the add
	 * @hibernate.property column = "p_add"
	 */
	public BigDecimal getAdd() {
		return add;
	}

	public void setAdd(BigDecimal add) {
		this.add = add;
	}
	
	/**
	 * @return the rate
	 * @hibernate.property column = "p_rate"
	 */
	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}
	
	/**
	 * @return the highdata
	 * @hibernate.property column = "p_highdata"
	 */
	public BigDecimal getHighdata() {
		return highdata;
	}

	public void setHighdata(BigDecimal highdata) {
		this.highdata = highdata;
	}

	/**
	 * @return the highdate
	 * @hibernate.property column = "p_highdate"
	 */
	public Date getHighdate() {
		return highdate;
	}

	public void setHighdate(Date highdate) {
		this.highdate = highdate;
	}
 
}
