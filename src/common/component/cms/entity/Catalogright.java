package common.component.cms.entity;

import common.base.entity.Entity;
import common.component.permission.entity.Role;


/**
 * @author admin
 * @hibernate.class table="t_catalogright" dynamic-update="true"
 *                  dynamic-insert="true"
 */
public class Catalogright extends Entity {
	private static final long serialVersionUID = 1L;
	private Role role;
	private Catalogue catalogue;
	private String checked;
	
	/**
	 * @hibernate.many-to-one column="p_role"
	 */
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @hibernate.property column="p_checked"
	 * @return
	 */
	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	/**
	 * @hibernate.many-to-one column="p_catalogue" outer-join="true"
	 */
	public Catalogue getCatalogue() {
		return catalogue;
	}

	public void setCatalogue(Catalogue catalogue) {
		this.catalogue = catalogue;
	}
}
