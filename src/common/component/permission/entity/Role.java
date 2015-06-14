package common.component.permission.entity;

import java.util.Collections;
import java.util.Set;

import common.base.entity.Entity;

/**
 * @author Administrator
 * @hibernate.class table="t_role" dynamic-update="true" dynamic-insert="true"
 */
public class Role extends Entity {
	private static final long serialVersionUID = 3L;

	private Set admins=Collections.EMPTY_SET;
	
	private Set permissions=Collections.EMPTY_SET;
	
	private String name;

	
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
	 * @return the admin
     * @hibernate.set lazy="true" cascade="none"
     * @hibernate.collection-key  column="p_role"
     * @hibernate.collection-one-to-many class="common.component.permission.entity.Admin"  
	 */
	public Set getAdmins() {
		return admins;
	}

	/**
	 * @param admins the admins to set
	 */
	public void setAdmins(Set admins) {
		this.admins = admins;
	}
	
	/**
	 * @hibernate.set inverse="true" cascade="delete" lazy="true"
	 * @hibernate.collection-key column="p_role"
	 * @hibernate.collection-one-to-many class="common.component.permission.entity.Permission"
	 */
	public Set getPermissions() {
		return permissions;
	}

	public void setPermissions(Set permissions) {
		this.permissions = permissions;
	}

}
