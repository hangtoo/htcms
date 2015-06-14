<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="/WEB-INF/extremecomponents" prefix="ec"%>

<div class="operate_right">

<form action="<%=request.getContextPath()%>/cms/attribute_search.action" method="POST" name="theForm" onSubmit="if(this.submitted){ return false;}this.submitted=true;return true;">
		<ec:table styleClass="tableClass" items="page" tableId="ec"
				action="${pageContext.request.contextPath}/cms/attribute_search.action?bean.catalogue.id=${bean.catalogue.id}"
				imagePath="${pageContext.request.contextPath}/common/component/extreme/images/table/*.gif"
				retrieveRowsCallback="limit" filterRowsCallback="limit"
				sortRowsCallback="limit" rowsDisplayed="30"
				autoIncludeParameters="false" var="user" form="theForm"
				filterable="true" sortable="true">

				<ec:row highlightRow="true">
					<ec:column property="id" sortable="false" title="操作" viewsAllowed="html" filterable="false">
					<a href="<%=request.getContextPath()%>/cms/attribute_edit.action?bean.id=${user.P_ID}">修改</a>&nbsp;&nbsp;
					<a onclick="return confirm('确定删除该记录吗?');" href="<%=request.getContextPath()%>/cms/attributedb_delete.action?bean.catalogue.id=${bean.catalogue.id}&bean.id=${user.P_ID}">删除</a>&nbsp;&nbsp;
					</ec:column>
					<ec:column property="P_NAME" title="名称" />
					<ec:column property="P_COLUMN" title="字段名" />
					<ec:column property="P_TYPE" title="字段类型">
						<s:bean name="common.component.cms.sql.OTypes" id="otypes">
							<s:param name="key">${user.P_TYPE}</s:param>
						</s:bean>
						<s:property value="#otypes.getValue()"/>
					</ec:column>
 					<ec:column property="P_CREATOR" title="录入人" />
 					<ec:column property="P_CREATETIME" title="录入时间" />
 					<ec:column property="P_MODIFYTIME" title="修改时间" />
				</ec:row>
			</ec:table> 
</form>

</div>