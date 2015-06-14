package common.component.cms.entity;

import java.sql.Blob;


/**
 * @author admin
 * @hibernate.class table="t_blob" dynamic-update="true"
 *                  dynamic-insert="true"
 */
public class BLOB {
	protected String id = new String("-1");

	protected String name;
	
	protected String type;
	
	protected Blob content;

	
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
	 * @hibernate.property column = "p_content" type="java.sql.Blob"
	 */
	public Blob getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(Blob content) {
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
}
