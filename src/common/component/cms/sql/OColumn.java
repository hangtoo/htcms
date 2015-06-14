package common.component.cms.sql;

import org.hibernate.MappingException;
import org.hibernate.engine.Mapping;

public class OColumn extends org.hibernate.mapping.Column {
	public int getSqlTypeCode(Mapping mapping) throws MappingException {
		return getSqlTypeCode().intValue();
	}
}
