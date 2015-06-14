package common.component.cms.service.impl;

import java.io.Serializable;
import java.util.List;

import common.component.cms.dao.AttributeDao;
import common.component.cms.entity.Attribute;
import common.component.cms.entity.Catalogue;
import common.component.cms.service.AttributeService;
import common.component.ectable.dao.EcTableDAOJdbc;
import common.component.ectable.service.impl.EcTableServiceImpl;
import common.exception.ExistException;

public class AttributeServiceImpl extends EcTableServiceImpl implements AttributeService {
	private AttributeDao attributeDao;

	public AttributeServiceImpl(AttributeDao attributeDao,EcTableDAOJdbc ecTableDAO) {
		this.attributeDao = attributeDao;
		this.ecTableDAO=ecTableDAO;
	}
	
	@Override
	public Attribute save(Attribute attribute) throws ExistException {
		//在新增时，用户名相同的记录//或者修改时候除本身外
		String condition = " and deleted is null and column='" + attribute.getName()+"' and catalogue.id='"+attribute.getCatalogue().getId()+"'";// and ('"+attribute.getId()+"'='-1' or id!='"+attribute.getId()+"')
		
		List beans=attributeDao.gets(condition);
		
		if(beans!=null&&beans.size()>=1){//新增存在多条记录的情况//或修改
			throw new ExistException("reduplicate name");
		}
		
		attributeDao.save(attribute);
		return attribute;
	}
	
	@Override
	public void delete(String id) {
		attributeDao.deleteTag(id);
	}

	@Override
	public Attribute update(Attribute newAttribute) {
		
	    Attribute attribute = getById(newAttribute.getId());
	    attribute.setName(newAttribute.getName());
	    attribute.setColumn(newAttribute.getColumn());
	    attribute.setCatalogue(newAttribute.getCatalogue());
	    attribute.setType(newAttribute.getType());
	    attribute.setLength(newAttribute.getLength());
	    attribute.setRemark(newAttribute.getRemark());
		
		return (Attribute)attributeDao.update(attribute);
	}
	
	public Attribute getById(final Serializable id){
		
		Object obj=this.attributeDao.getById(id);
		
		if(obj instanceof Attribute)
			return (Attribute)obj;
		return null;
	}

	@Override
	public Attribute getById(String id) {
		Object obj=this.attributeDao.getById(id);
		
		if(obj instanceof Attribute)
			return (Attribute)obj;
		return null;
	}
	
	@Override
	public List<Attribute> getList() {
		String condition=" and deleted is null";
		List list = attributeDao.gets(condition);
		return list;
	}
	
	@Override
	public List<Attribute> getList(String condition) {
		List list = attributeDao.gets(condition);
		return list;
	}

	@Override
	public List<Attribute> getAttributeList(String cataid) {
		List list = attributeDao.gets(" and  catalogue='"+cataid+"'");
		return list;
	}
}
