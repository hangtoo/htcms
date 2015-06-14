package common.component.applet.httpclient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class HttpDataAnalyse {
	
	protected Logger log = Logger.getLogger(this.getClass());

	private String formatStr(String str){
		Pattern rpat = Pattern.compile("[\\t\\n\\r]");
		Matcher rmat = rpat.matcher(str);
		str=rmat.replaceAll("");
		
		return str;
	}
	
	//private Pattern mpattern=Pattern.compile("(.+?)\\=^//[(.+?)$//],");
	private Pattern mpattern=Pattern.compile("(.+?)\\=((\\[(.+?)\\])|([^\\[\\]]+?)),");
	
	//private Pattern lpattern=Pattern.compile("(.+?),");
	private Pattern lpattern=Pattern.compile("((\\{(.+?)\\})|([^\\{\\}]+?)),");

	
	//解析[1,2,3]
	private List<String> getListStr(String str){
		str=str.substring(1,str.length()-1)+",";
		List<String> result=new ArrayList<String>();
		Matcher lmatcher=lpattern.matcher(str);
		while (lmatcher.find()){
			result.add(lmatcher.group(1).trim());
		}
		return result;
	}
	
	//解析{a=1,b=2}
	public Map<String,Object> getModel(String ret){
		
		log.debug(ret);
		
		if(ret==null)
			return null;
		
		if(ret.startsWith("{")&&ret.endsWith("}"))
			ret=ret.substring(1,ret.length()-1)+",";
		else
			ret=ret+",";
		
		Map<String,Object> model=new HashMap<String,Object>();
		
		Matcher mmatcher=mpattern.matcher(ret);
		
		while (mmatcher.find()){
			String key=mmatcher.group(1).trim().toUpperCase();
			String value=mmatcher.group(2).trim();
			
			System.out.println(key+":"+value);
			
			if(value.startsWith("[")&&value.endsWith("]")){

				List<String> temp=this.getListStr(value);
				
				List<Map> list=new ArrayList<Map>();
				int i=0;
				
				for(;i<temp.size();i++){
					String tem=temp.get(i);
					if(tem.startsWith("{")&&tem.endsWith("}")){//解析a=[{b=1,c=2},{b=11,c=22}]
						
						Map te=this.getModel(tem);
						list.add(te);
						
					}else{//解析a=[11111,22222]
						break;
					}
				}
				
				if(i<temp.size())
					model.put(key,temp);
				else{
					model.put(key,list);
				}
				
			}else{//解析a=1
				model.put(key,value);
			}
		}
		
		return model;
	}
}
