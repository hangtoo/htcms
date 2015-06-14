package common.component.application.nurl.entity;

import common.base.entity.Entity;
import common.component.application.user.entity.User;

/**
 * @author Administrator
 * @hibernate.class table="t_nurl" dynamic-update="true" dynamic-insert="true"
 */
public class Nurl extends Entity {
	
	private static final long serialVersionUID = 9L;
	
	private String nurl;
	
	private String desc;
	
	private User user;

	private String tags;


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
	/**
	 * @return the nurl
	 * @hibernate.property column = "p_nurl"
	 */
	public String getNurl() {
		return nurl;
	}
	/**
	 * @param nurl the nurl to set
	 */
	public void setNurl(String nurl) {
		this.nurl = nurl;
	}
	/**
	 * @return the tags
	 * @hibernate.property column = "p_tags"
	 */
	public String getTags() {
		return tags;
	}
	/**
	 * @param tags the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}

}
