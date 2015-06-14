package common.base.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * @author Administrator䡢�޸�ʱ�䡢�����ˡ��޸��˵�
 */
public class Entity implements Serializable {
	
	private static final long serialVersionUID =806229974145833031L;
	
	protected String id = new String("-1");

	private Date createTime;

	private Date modifyTime;

	private String creator;
	
	private String modifier;
	
	private String remark;
	
	private Boolean deleted;

	/**
	 * @hibernate.property column = "p_createTime" type="java.sql.Timestamp" update="false"
	 * @return
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @hibernate.property column = "p_modifyTime" type="java.sql.Timestamp" insert="false"
	 * @return
	 */
	public Date getModifyTime() {
		return modifyTime;
	}

	/**
	 * @param modifyTime the modifyTime to set
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	/**
	 * @hibernate.property column = "p_creator" type="string" update="false"
	 * @return
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * @param creator the creator to set
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * @hibernate.property column = "p_modifier" type="string" insert="false"
	 * @return
	 */
	public String getModifier() {
		return modifier;
	}

	/**
	 * @param modifier the modifier to set
	 */
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	/**
	 * @hibernate.property column = "p_remark" type="string"
	 * @return
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

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
	 * @return the deleted
	 * @hibernate.property column = "p_deleted" type="java.lang.Boolean" insert="false"  unsaved-value = "false"
            
	 */
	public Boolean getDeleted() {
		return deleted;
	}

	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}
