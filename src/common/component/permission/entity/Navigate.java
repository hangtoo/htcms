package common.component.permission.entity;

import java.util.HashSet;
import java.util.Set;

import common.base.entity.Entity;


/**
 * @author admin
 * @hibernate.class table="t_navigate" dynamic-update="true" dynamic-insert="true"
 */
public class Navigate extends Entity {
	private static final long serialVersionUID = 1L;
	private String name;
	private Navigate parent;
	private Set children = new HashSet();
	private String order = "0";
	private String url;
	private String icon;

	private Set permissions;
	
	/**
	 * @hibernate.set order-by="p_id" inverse="true" cascade="delete"
	 *                lazy="true"
	 * @hibernate.collection-key column="p_navigate"
	 * @hibernate.collection-one-to-many class="common.component.permission.entity.Permission"
	 */
	public Set getPermissions() {
		return permissions;
	}

	/**
	 * @return Returns the name.
	 * @hibernate.property column="p_name" length="64"
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return Returns the parent.
	 * @hibernate.many-to-one column="p_parent" outer-join="true"
	 */
	public Navigate getParent() {
		return parent;
	}

	/**
	 * @hibernate.property column="p_order"
	 * @return
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * @return Returns the url.
	 * @hibernate.property column="p_url"
	 */
	public String getUrl() {
		return url;
	}
	

	/**
     * @return Returns the children.
	 * @hibernate.set order-by="p_order" inverse="true" cascade="delete" lazy="true"
	 * @hibernate.collection-key column="p_parent"
	 * @hibernate.collection-one-to-many class="common.component.permission.entity.Navigate"
	 */
	public Set getChildren() {
		return children;
	}
	
	/**
	 * @return the icon
	 * @hibernate.property column = "p_icon"
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParent(Navigate parent) {
		this.parent = parent;
	}

	public void setPermissions(Set permissions) {
		this.permissions = permissions;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void addChilds(Navigate nav) {
		if (nav != null) {
			nav.setParent(this);
			this.children.add(nav);
		}
	}

	public void setChildren(Set children) {
		this.children = children;
	}

}
