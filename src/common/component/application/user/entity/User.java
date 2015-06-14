package common.component.application.user.entity;

import common.base.entity.Entity;

/**
 * @author Administrator
 * @hibernate.class table="t_user" dynamic-update="true" dynamic-insert="true"
 */
public class User extends Entity {
	
	private static final long serialVersionUID = 9L;
	
	private String name;//昵称
	
	private String username;//用户名,不允许修改
	
	private String password;
	
    private String sex;//性别
    
    private String email;//邮箱

    private String position;
    
    private String address;
    
    private String company;
    
    private String phonenumber;
    
    //无需持久化字段
	private String repassword;
	
	private String checkcode;

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
	 * @return the password
	 * @hibernate.property column = "p_password"
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
	 * @return the company
	 * @hibernate.property column = "p_company"
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return the phonenumber
	 * @hibernate.property column = "p_phonenumber"
	 */
	public String getPhonenumber() {
		return phonenumber;
	}

	/**
	 * @param phonenumber the phonenumber to set
	 */
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}


	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	/**
	 * @return the sex
	 * @hibernate.property column = "p_sex"
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the email
	 * @hibernate.property column = "p_email"
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
