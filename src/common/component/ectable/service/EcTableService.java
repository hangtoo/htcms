package common.component.ectable.service;

import java.util.Map;

import org.extremecomponents.table.limit.FilterSet;
import org.extremecomponents.table.limit.Sort;

public interface EcTableService {
	public Map search(String sql, int page, int pageSize, Sort sort,
			FilterSet filterSet, boolean exported);
}
