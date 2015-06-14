package common.component.permission.entity;

import common.base.entity.Entity;

/**
 * @author Administrator
 * @hibernate.class table="t_admin" dynamic-update="true" dynamic-insert="true"
 */
public class Admin extends Entity {
	
	private static final long serialVersionUID = 2L;
	
	private String name;
	
	private String username;

	private String password;
	
    private Role role=new Role();
    
    private String position;
    
    private String group;
    
    //无需持久化字段
	private String repassword;
	
	private String checkcode;


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
	 * @return Returns
	 * @hibernate.property column = "p_password" type="string"
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return Returns
	 * @hibernate.many-to-one column="p_role"
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the username
	 * @hibernate.property column = "p_username"
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the position
	 * @hibernate.property column = "p_position"
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the group
	 * @hibernate.property column = "p_group"
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
	}
	
	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

}
