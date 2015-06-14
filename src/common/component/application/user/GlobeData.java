package common.component.application.user;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import common.component.IConstants;
import common.component.application.user.entity.User;

public class GlobeData {
	private static Map<String, List<User>> USER = new Hashtable<String, List<User>>();

	public static List<User> getUser(String key) {
		return USER.get(key);
	}

	public static void setUser(String key, List<User> value) {
		USER.put(key, value);
	}
	
	public static User getSessionUser(){
		Object obj=(User)ServletActionContext.getContext().getSession().get(IConstants.CLIENTKEY);
		if(obj!=null)
			return (User)obj;
		else
			return null;
	}
	
}
