package common.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 
 * @author hlf
 * 确保每个数据均包含在  ><之间
 * 如数据为：>thevalue<
 * 模板 为 ：>{key}<
 * 
 * 返回值为 key=thevalue
 */
public class TemplateDataUtil {
	// ~ Static fields/initializers
	// =============================================

	private final static Logger log = Logger.getLogger(TemplateDataUtil.class);
	
	private static String template;
	
	public static String getTemplate() {
		return template;
	}
	
	public static void setTemplate(String template) {
		TemplateDataUtil.template =new StringBuilder(">").append(template).append("<").toString();
	}
	
	public static void setFileTemplate(String filename){
		String filedata=FileUtil.readFile(filename);
		
		TemplateDataUtil.template =new StringBuilder(">").append(filedata).append("<").toString();
	}
	
	public static Map<String,String> getData(String ret,String template){
		setTemplate(template);
		
		return getData(ret);
	}
	
	public static Map<String,String> getData(String ret){
		
		if(StringUtil.isEmptyString(template)){
			return null;
		}
		
		Map<String,String> result=new HashMap<String,String>();
		
        if ((ret != null) && (ret.length() > 0)) {
        	
        	ret=new StringBuilder(">").append(ret).append("<").toString();
        	
        	log.debug(ret); 
        	
            char r;
            char t;
            int s=-1,e=-1;
            int tags=-1,tage=-1;
            String key=null,value=null;
            boolean getkey=false,getvalue=false;
            for (int ri=0,tj = 0; ri <= ret.length() - 1&&tj<=template.length() - 1; ) {  
            	//log.debug("ri="+ri+";tj="+tj+";");
            	r = ret.charAt(ri);
            	t=template.charAt(tj);
            	
            	if(r=='\n'||r=='\t'||r==' '||r=='\r'){
            		ri++;
            		continue;
            	}
            	
            	if(t=='\n'||t=='\t'||t==' '||t=='\r'){
            		tj++;
            		continue;
            	}
            	
            	if(t=='*'){
                    ri++;
            		tj++;
            		continue;
            	}
            	
            	if(r==t){//如果相同,则继续往前
            		
                    switch (r) {
                    case '>':
                    	tags=ri;
                        break;  
                    case '<':
                    	tage=ri;
                    	break;
                    }
                    
                    ri++;
            		tj++;
                    
            	}else{
            		
            		switch(t){
            		case '{':
            			s=tj;
            			break;
            		case '}':
            			e=tj;
            			key=template.substring(s+1,e);
            			e=-1;
            			getkey=true;
            			tj++;
            			break;
            		}
            		
        			if(!getkey){
        				tj++;
        			}
        			
    				if(!getvalue&&r!='<'){
    					ri++;
    				}
            	}
            	
            	
    			if(s!=-1&tags!=-1&&tage!=-1&&tage>tags){
    				value=ret.substring(tags+1,tage);
    				getvalue=true;
    				tags=-1;
    				tage=-1;
        			s=-1;
    			}
    			
    			log.debug(">tags="+tags+";<tage="+tage+";getkey="+getkey+";getvalue="+getvalue+";key="+key+";value="+value+";ri="+ri+";tj="+tj+";r="+r+";t="+t);
    			
            	if(getkey&&getvalue){
            		result.put(key, value);
            		getkey=false;
            		getvalue=false;
            	}
            	

            }
            
        }
		
		
		
		return result;
	}
	
}
