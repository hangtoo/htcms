package common.component.cms.service;

import java.util.List;

import common.component.cms.entity.Catalogright;
import common.component.ectable.service.EcTableService;
import common.component.permission.entity.Role;

public interface CatalogrightService extends EcTableService{

	List<Catalogright> getTree(String roleId);

	List<Catalogright> getTree();

	void save(Role bean,String checkrecords);
	
	void save(String roleid);//同步该角色的所有栏目
	
	void save(String roleid,String catalogueid);//同步该角色的指定栏目
}
