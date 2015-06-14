package common.component.ectable.dao;

import java.util.List;

/**
 * 
 * @author itfish
 */
public interface EcTableDAOJdbc extends EcTableDAO{
	
	public List getSimpleList(String strSql);
	
	public void execute(String strSql);
	
	public void executeBatch(List<String> strs);

}
