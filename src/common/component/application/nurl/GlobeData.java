package common.component.application.nurl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import common.component.application.nurl.entity.Nurl;
import common.component.application.user.entity.User;

public class GlobeData {
	protected static Logger log = Logger.getLogger(GlobeData.class);
	
	private static Map<String, List<Nurl>> NURL = new Hashtable<String, List<Nurl>>();

	public static List<Nurl> getNurl(String key) {
		return NURL.get(key);
	}
	
	public static void removeNurl(String nurlid){
		List<Nurl> nurl= getNurl();
		for(int i=0;i<nurl.size();i++){
			if(nurlid.endsWith(nurl.get(i).getId())){
				nurl.remove(i);
				break;
			}
		}
	}

	public static void setNurl(String key, List<Nurl> value) {
		
		if(log.isDebugEnabled()){
			log.debug(key+":");
			for(int i=0;i<value.size();i++){
				log.debug(value.get(i).getId());
			}
		}
		
		NURL.put(key, value);
	}
	
	public static List<Nurl> getNurl(){
		User user=common.component.application.user.GlobeData.getSessionUser();
		if(user!=null)	
			return getNurl(user.getId());
		else
			return null;
	}
}
