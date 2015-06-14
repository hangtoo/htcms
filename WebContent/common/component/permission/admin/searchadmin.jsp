<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/WEB-INF/extremecomponents" prefix="ec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>用户管理</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/common/dtree/dtree.js"></script>
<link type="text/css" rel="stylesheet" media="all" href="<%=request.getContextPath()%>/common/dtree/dtree.css" />
<link type="text/css" rel="stylesheet" media="all" href="<%=request.getContextPath()%>/common/div/css/base.css" />
<link href="<%=request.getContextPath()%>/common/component/extreme/css/extremecomponents.css"  rel="stylesheet" type="text/css" />
<s:head theme="ajax" />

</head>
<body>


<jsp:include flush="true" page="/common/top.jsp" />
<jsp:include flush="true" page="/common/left.jsp" />

<div id="contentfloat" >
<jsp:include flush="true" page="/common/contenttitle.jsp" />
<div id="content">

<!-- 放内容 -->
<form action="<%=request.getContextPath()%>/permission/admin_search.action" method="POST" name="theForm" onSubmit="if(this.submitted){ return false;}this.submitted=true;return true;">
		<ec:table styleClass="tableClass" items="page" tableId="ec"
				action="${pageContext.request.contextPath}/permission/admin_search.action"
				imagePath="${pageContext.request.contextPath}/common/component/extreme/images/table/*.gif"
				retrieveRowsCallback="limit" filterRowsCallback="limit"
				sortRowsCallback="limit" rowsDisplayed="30"
				autoIncludeParameters="false" var="user" form="theForm"
				filterable="true" sortable="true">

				<ec:row highlightRow="true">
					<ec:column property="id" sortable="false" title="操作" viewsAllowed="html" filterable="false">
					<a href="<%=request.getContextPath()%>/permission/admin_edit.action?bean.id=${user.P_ID}">修改</a>&nbsp;&nbsp;
					<a onclick="return confirm('确定删除该记录吗?');" href="<%=request.getContextPath()%>/permission/admindb_delete.action?bean.id=${user.P_ID}">删除</a>&nbsp;&nbsp;
					</ec:column>
					<ec:column property="P_NAME" title="姓名" />
					<ec:column property="P_USERNAME" title="用户名" />
					<ec:column property="P_POSITION" title="职位" />
					<ec:column property="P_ROLENAME" title="角色" />
 					<ec:column property="P_CREATOR" title="录入人" />
 					<ec:column property="P_CREATETIME" title="录入时间" />
 					<ec:column property="P_MODIFYTIME" title="修改时间" />
				</ec:row>
			</ec:table> 
</form>


</div></div>

<jsp:include flush="true" page="/common/bottom.jsp" />
</body>
</html>
