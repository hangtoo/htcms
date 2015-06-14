package common.component.cms.entity;

import java.sql.Clob;


/**
 * @author admin
 * @hibernate.class table="t_clob" dynamic-update="true"
 *                  dynamic-insert="true"
 */
public class CLOB {
	protected String id = new String("-1");

	protected Clob content;

	protected String name;
	/**
	 * @hibernate.id generator-class = "uuid.hex" column = "p_id" unsaved-value = "-1"
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the content
	 * @hibernate.property column = "p_content" type="java.sql.Clob"
	 */
	public Clob getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(Clob content) {
		this.content = content;
	}

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
}
