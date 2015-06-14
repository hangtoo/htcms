package common.component.cms.entity;

import common.base.entity.Entity;


/**
 * @author admin
 * @hibernate.class table="t_attribute" dynamic-update="true"
 *                  dynamic-insert="true"
 */
public class Attribute extends Entity {
	private static final long serialVersionUID = 1L;
	private Catalogue catalogue;//栏目
	private String name;//属性名
	
	private String column;//字段名
	private String type;//字段类型
	private String length;//字段长度
	
	/**
	 * @hibernate.property column="p_name"
	 * @return
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	/**
	 * @return the column
	 * @hibernate.property column = "p_column"
	 */
	public String getColumn() {
		return column;
	}

	/**
	 * @param column the column to set
	 */
	public void setColumn(String column) {
		this.column = column;
	}

	/**
	 * @return the type
	 * @hibernate.property column = "p_type"
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the length
	 * @hibernate.property column = "p_length"
	 */
	public String getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(String length) {
		this.length = length;
	}

}
