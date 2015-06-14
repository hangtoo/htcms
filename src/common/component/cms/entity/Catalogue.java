package common.component.cms.entity;

import java.util.HashSet;
import java.util.Set;

import common.base.entity.Entity;

/**
 * @author admin
 * @hibernate.class table="t_catalogue" dynamic-update="true"
 *                  dynamic-insert="true"
 */
public class Catalogue extends Entity {
	private static final long serialVersionUID = 1L;
	private String name;
	private Catalogue parent;
	private Set children = new HashSet();
	private String order = "0";
	private String tablename;// 表名
	
	private String version;//表版本号,默认为空.通过组装表名及版本号,生成数据库中的表

	private Set attributes;// 属性

	/**
	 * @hibernate.set order-by="p_id" inverse="true" cascade="delete"
	 *                lazy="true"
	 * @hibernate.collection-key column="p_catalog"
	 * @hibernate.collection-one-to-many class="common.component.cms.entity.Attribute"
	 */
	public Set getAttributes() {
		return attributes;
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
	public Catalogue getParent() {
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
	 * @return Returns the children.
	 * @hibernate.set order-by="p_order" inverse="true" cascade="delete"
	 *                lazy="true"
	 * @hibernate.collection-key column="p_parent"
	 * @hibernate.collection-one-to-many class="common.component.cms.entity.Catalogue"
	 */
	public Set getChildren() {
		return children;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParent(Catalogue parent) {
		this.parent = parent;
	}

	public void setAttributes(Set attributes) {
		this.attributes = attributes;
	}

	public void addChilds(Catalogue nav) {
		if (nav != null) {
			nav.setParent(this);
			this.children.add(nav);
		}
	}

	public void setChildren(Set children) {
		this.children = children;
	}

	/**
	 * @return the tablename
	 * @hibernate.property column = "p_tablename"
	 */
	public String getTablename() {
		return tablename;
	}

	/**
	 * @param tablename
	 *            the tablename to set
	 */
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	/**
	 * @return the version
	 * @hibernate.property column = "p_version"
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
}
