package common.component.permission.action;

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
import common.component.permission.util.GlobeData;
import common.component.permission.entity.Admin;
import common.component.permission.entity.Permission;
import common.util.StringUtil;

public class PermissionFilter extends ActionSupport implements Filter{

	protected Logger log = Logger.getLogger(this.getClass());
	
	private String[] permissions=null;
	
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
        
        if(uri.indexOf(".action")==-1){
			hresponse.sendRedirect(hrequest.getContextPath());
			return;
        }
        
		for(int i=0;i<permissions.length;i++){
			if(uri.indexOf(permissions[i])!=-1){
				filter.doFilter(request, response);
				return;
			}
		}
		
		//Object obj=session.getAttribute(getText(IConstants.TREE));
		
		Object obj=session.getAttribute(IConstants.USERKEY);
		
		log.info(obj);
		
		if(obj==null||!(obj instanceof Admin)){
			hresponse.sendRedirect(hrequest.getContextPath());
			return;
		}
		
		List<Permission> list=GlobeData.getMenuTree(((Admin)obj).getRole().getId());
		
		if(list==null){
			hresponse.sendRedirect(hrequest.getContextPath());
			return;
		}
		
		try{
			for(int i=0;i<list.size();i++){//遍历导航模块
				Permission permission=list.get(i);
				String urlele=permission.getNavigate().getUrl();
				if(!StringUtil.isEmptyString(urlele)&&!"#".equalsIgnoreCase(urlele)){
					int position=uri.indexOf(urlele);//改成start with
					if(position!=-1){//进行权限判断 如果通过的话执行如下，否则进行跳转登录页面等操作
						if(permission.getChecked()!=null){
							filter.doFilter(request, response);
							return;
						}else{
							hresponse.sendRedirect(hrequest.getContextPath());
							return;
						}
					}
				}
			}
			//如果没有匹配模块进行放行处理
			filter.doFilter(request, response);
			return;
		}catch(Exception e){
			log.error(e);
		}
		
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String permission = filterConfig.getInitParameter("permission");
		if(permission!=null){
			permissions=permission.split(",");
		}
	}

}
