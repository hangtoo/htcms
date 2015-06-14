<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.Map,common.component.cms.entity.Attribute"%>
<%@ page import="java.util.List,java.util.Iterator,java.util.ArrayList,java.net.URLEncoder"%>
<%
List<Attribute> blobs=new ArrayList();
Map<String,String> clobblobs=(Map<String,String>)request.getAttribute("clobblobs");
%>
bean.catalogue.name=${bean.catalogue.name}.xls,bean.id=${bean.id},bean.catalogue.id=${bean.catalogue.id}
<s:bean name="common.component.cms.sql.OTypes" id="otypes"></s:bean>
<%
	Map<String,String> columns=(Map<String,String>)request.getAttribute("columns");

	Iterator<String> keys=columns.keySet().iterator();
	Iterator<String> values=columns.values().iterator();
	while(keys.hasNext()){
		String temp=values.next();
		if(temp==null)
			temp="";
		out.println(","+keys.next().toUpperCase()+"="+temp);
	}
%>

