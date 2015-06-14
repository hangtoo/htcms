package common.component.permission.service;

import java.io.Serializable;
import java.util.List;

import common.component.ectable.service.EcTableService;
import common.component.permission.entity.Navigate;
import common.component.permission.entity.Permission;
import common.component.permission.entity.Role;

public interface PermissionService extends EcTableService{

	List<Permission> getTree(String roleId);

	List<Permission> getTree();

	void save(Role bean,String checkrecords);
}
