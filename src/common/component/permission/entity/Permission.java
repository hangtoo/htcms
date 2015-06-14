package common.component.permission.entity;

import common.base.entity.Entity;


/**
 * @author admin
 * @hibernate.class table="t_permission" dynamic-update="true"
 *                  dynamic-insert="true"
 */
public class Permission extends Entity {
	private static final long serialVersionUID = 1L;
	private Role role;
	private Navigate navigate;
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
	 * @hibernate.many-to-one column="p_navigate" outer-join="true"
	 */
	public Navigate getNavigate() {
		return navigate;
	}

	public void setNavigate(Navigate navigate) {
		this.navigate = navigate;
	}
}
