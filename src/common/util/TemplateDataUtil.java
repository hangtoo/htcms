package common.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;


public class TemplateDataUtil {
	// ~ Static fields/initializers
	// =============================================

	private final static Logger log = Logger.getLogger(TemplateDataUtil.class);
	
	public static Map<String,String> getData(String ret,String template){
		Map<String,String> result=new HashMap<String,String>();
		
        if ((ret != null) && (ret.length() > 0)) {  
        	ret=">"+ret+"<";
        	template=">"+template+"<";
        	System.out.println(ret); 
        	
            char r;
            char t;
            int s=-1,e=-1;
            int tags=-1,tage=-1;
            String key=null,value=null;
            boolean getkey=false,getvalue=false;
            for (int ri=0,tj = 0; ri <= ret.length() - 1; ) {  
            	System.out.println("ri="+ri+";tj="+tj+";");
            	r = ret.charAt(ri);
            	t=template.charAt(tj);
            	
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
    			
            	System.out.print(">tags="+tags+";<tage="+tage);
            	System.out.print(";getkey="+getkey+";getvalue="+getvalue);
            	System.out.print(";key="+key+";value="+value);
            	System.out.println(";ri="+ri+";tj="+tj+";r="+r+";t="+t);
    			
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
