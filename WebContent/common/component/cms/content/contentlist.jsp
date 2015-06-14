<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="/WEB-INF/extremecomponents" prefix="ec"%>
<%@ page import="java.util.Map,common.component.cms.entity.Attribute"%>
<%@ page import="java.util.List,java.util.ArrayList"%>

<div class="operate_right">
<s:if test="#request.ec_totalRows!=''&&#request.ec_totalRows!=null">
<form action="<%=request.getContextPath()%>/cms/content_search.action" method="POST" name="theForm" onSubmit="if(this.submitted){ return false;}this.submitted=true;return true;">
		<ec:table styleClass="tableClass" items="page" tableId="ec"
				action="${pageContext.request.contextPath}/cms/content_search.action?bean.catalogue.id=${bean.catalogue.id}"
				imagePath="${pageContext.request.contextPath}/common/component/extreme/images/table/*.gif"
				retrieveRowsCallback="limit" filterRowsCallback="limit"
				sortRowsCallback="limit" rowsDisplayed="30"
				autoIncludeParameters="false" var="user" form="theForm"
				filterable="true" sortable="true">

				<ec:row highlightRow="true">
					<ec:column property="id" sortable="false" title="操作" viewsAllowed="html" filterable="false">
					<a href="<%=request.getContextPath()%>/cms/content_edit.action?bean.id=${user.P_ID}&bean.catalogue.id=${bean.catalogue.id}">修改</a>&nbsp;&nbsp;
					<a onclick="return confirm('确定删除该记录吗?');" href="<%=request.getContextPath()%>/cms/contentdb_delete.action?bean.id=${user.P_ID}&bean.catalogue.id=${bean.catalogue.id}">删除</a>&nbsp;&nbsp;
					</ec:column>
					<%
					List<Attribute> attr=(List<Attribute>)request.getAttribute("attributes");
					
					for(int i=0;i<attr.size();i++){
					
						Attribute ele=attr.get(i);
						
					%>
					<ec:column property="<%=ele.getColumn().toUpperCase()%>" title="<%=ele.getName()%>" />
					<%}%>
 					<ec:column property="P_CREATOR" title="录入人" />
 					<ec:column property="P_CREATETIME" title="录入时间" />
 					<ec:column property="P_MODIFYTIME" title="修改时间" />
				</ec:row>
			</ec:table> 
</form>
</s:if>
</div>