package common.component.cms.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import common.component.IConstants;
import common.component.cms.entity.Catalogright;
import common.component.cms.util.GlobeData;
import common.component.permission.entity.Admin;

public class CatalogrightFilter extends ActionSupport implements Filter{

	protected Logger log = Logger.getLogger(this.getClass());
	
	private String[] catalogrights=null;
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filter) throws IOException, ServletException {
        HttpServletRequest hrequest = null;
        HttpServletResponse hresponse = null;
        HttpSession session=null;
        if(request instanceof HttpServletRequest){
            hrequest = (HttpServletRequest)request;
            session=hrequest.getSession();
        }
        if(response instanceof HttpServletResponse)
            hresponse = (HttpServletResponse)response;
        if(session==null||hrequest == null || hresponse == null)
            throw new RuntimeException();
        
        String uri = hrequest.getRequestURI();
        
        log.info(uri);
        
        String catalogueid=hrequest.getParameter("bean.catalogue.id");
        
        if(log.isDebugEnabled())
        	log.debug(catalogueid);
        
        if(catalogueid==null){
        	filter.doFilter(request, response);
        	return;
        }
        //to test session attribute is null
		Object obj=session.getAttribute(IConstants.USERKEY);
		
		log.info(obj);
		
		if(obj==null||!(obj instanceof Admin)){
			hresponse.sendRedirect(hrequest.getContextPath());
			return;
		}
		
		List<Catalogright> list=GlobeData.getCatalogrightTree(((Admin)obj).getRole().getId());
		
		if(list==null){//对于有catalogueid 且没有权限的访问直接跳转
			hresponse.sendRedirect(hrequest.getContextPath());
			return;
		}
		
		try{
			for(int i=0;i<list.size();i++){//遍历导航模块
				Catalogright catalogright=list.get(i);
				if(catalogright.getCatalogue().getId().equalsIgnoreCase(catalogueid)){
					if(IConstants.USERNAME.equalsIgnoreCase(((Admin)obj).getName())||"1".equalsIgnoreCase(catalogright.getChecked())){//说明有权限，进行放行处理
						filter.doFilter(request, response);
						return;
					}else
						break;
				}
			}
			
			//如果没有匹配模块，说明没有权限
			hresponse.sendRedirect(hrequest.getContextPath());
			return;
		}catch(Exception e){
			log.error(e);
			hresponse.sendRedirect(hrequest.getContextPath());
			//e.printStackTrace();
			return;
		}
		
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
