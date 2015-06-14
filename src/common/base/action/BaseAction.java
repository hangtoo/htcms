package common.base.action;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.context.HttpServletRequestContext;
import org.extremecomponents.table.limit.Limit;
import org.extremecomponents.table.limit.LimitFactory;
import org.extremecomponents.table.limit.TableLimit;
import org.extremecomponents.table.limit.TableLimitFactory;

import com.opensymphony.xwork2.ActionSupport;

import common.component.IConstants;
import common.component.ectable.service.EcTableService;

public class BaseAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	protected Logger log = Logger.getLogger(this.getClass());
	protected Map parameters=new Hashtable<String,Object>();
	public Map getParameters() {
		return parameters;
	}
	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}
	
	public String getReturnmsg(){
		Object returnmsg=parameters.get(IConstants.RETURNMSG);
		if(returnmsg==null)
			return null;
		return returnmsg+"";
	}
	
	
	protected void search(EcTableService service,String strsql){
		HttpServletRequest request = ServletActionContext.getRequest();

		Context context = new HttpServletRequestContext(request);
		LimitFactory limitFactory = new TableLimitFactory(context);// ec
		Limit limit = new TableLimit(limitFactory);
		String selectPageSize = request.getParameter("ec_rd");
		int pageSize = new Integer(selectPageSize != null ? selectPageSize
				: 30 + "").intValue();
		Map map = service
				.search(strsql,limit.getPage(), pageSize, limit.getSort(), limit
								.getFilterSet(), limit.isExported());

		Integer totalRows = (Integer) map.get("totalRows");

		request.setAttribute("ec_totalRows", totalRows);
		request.setAttribute("page", map.get("page"));
	}
}
