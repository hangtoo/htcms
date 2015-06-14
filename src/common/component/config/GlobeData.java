package common.component.config;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GlobeData {
	private static Map<String, List<String>> CONFIG = new Hashtable<String, List<String>>();

	public static List<String> getConfig(String key) {
		return CONFIG.get(key);
	}

	public static void setConfig(String key, List<String> value) {
		CONFIG.put(key, value);
	}
	
	private static Boolean isTableMonth=null;
	
	private static Pattern lpattern=Pattern.compile("Unknown column '(.+?)' in 'field list'");
	
	public static String getError(String msg) {
		
		if(msg==null)
			return "";
		
		Matcher lmatcher=lpattern.matcher(msg);
		if (lmatcher.find()){
			String key=lmatcher.group(1).trim();
			
			List<String> ret=CONFIG.get(key);
			
			if(ret.size()>=1)
				return ret.get(0);
			
		}
		
		return null;
	}
	
	public static boolean isTableMonth(){
		if(isTableMonth!=null)
			return isTableMonth;
		
		List<String> istablemonth= getConfig("sys_tablemonth");
		
		if(istablemonth.size()>=1){
			if("1".equalsIgnoreCase(istablemonth.get(0))){
				isTableMonth=true;
				return isTableMonth;
			}
		}
		
		isTableMonth=false;
		
		return isTableMonth;
	}
}
