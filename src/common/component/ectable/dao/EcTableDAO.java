package common.component.ectable.dao;

import java.util.Map;

import org.extremecomponents.table.limit.FilterSet;
import org.extremecomponents.table.limit.Sort;
/**
 * 
 * @author itfish
 */
public interface EcTableDAO {
	
	public Map getCollectionData(String hql, int page, int pageSize, Sort sort,
			FilterSet filterSet, boolean exported);

}
