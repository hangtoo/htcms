package common.component.config.entity;

import common.base.entity.Entity;

/**
 * @author Administrator
 * @hibernate.class table="t_config" dynamic-update="true" dynamic-insert="true"
 */
public class Config extends Entity {
	
	private static final long serialVersionUID = 9L;
	
	private String configkey;
	
	private String configvalue;

	private String desc;

	/**
	 * @return the configkey
	 * @hibernate.property column = "p_configkey"
	 */
	public String getConfigkey() {
		return configkey;
	}

	/**
	 * @param configkey the configkey to set
	 */
	public void setConfigkey(String configkey) {
		this.configkey = configkey;
	}

	/**
	 * @return the configvalue
	 * @hibernate.property column = "p_configvalue"
	 */
	public String getConfigvalue() {
		return configvalue;
	}

	/**
	 * @param configvalue the configvalue to set
	 */
	public void setConfigvalue(String configvalue) {
		this.configvalue = configvalue;
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
	
}
