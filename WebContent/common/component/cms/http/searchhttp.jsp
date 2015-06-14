<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/WEB-INF/extremecomponents" prefix="ec"%>
<%@ page import="java.util.Map,common.component.cms.entity.Attribute"%>
<%@ page import="java.util.List,java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>内容发布</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/common/dtree/dtree.js"></script>
<link type="text/css" rel="stylesheet" media="all" href="<%=request.getContextPath()%>/common/dtree/dtree.css" />
<link type="text/css" rel="stylesheet" media="all" href="<%=request.getContextPath()%>/common/div/css/base.css" />
<link href="<%=request.getContextPath()%>/common/component/extreme/css/extremecomponents.css"  rel="stylesheet" type="text/css" />
<script type="text/javascript">
function setModifyId(beanid,creator,createtime){
	//var jsbeanid=new java.lang.String(beanid);
	//alert(beanid);
	var jsbeanid=beanid;
	var jscreator=creator;
	var jscreatetime=createtime;
	gapplet.setBeanId(jsbeanid);
	gapplet.setCreator(jscreator);
	gapplet.setCreatetime(jscreatetime);
	
}
</script>

<s:head theme="ajax" />

</head>
<body>


<jsp:include flush="true" page="/common/top.jsp" />
<jsp:include flush="true" page="/common/left.jsp" />

<jsp:include flush="true" page="/common/component/html/dragdiv/gapplet.jsp" />

<div id="contentfloat" >
<jsp:include flush="true" page="/common/contenttitle.jsp" />
<div id="content">

<!-- 放内容 -->
<jsp:include flush="true" page="cataloguetree.jsp"/>

<div class="operate_right">
<s:if test="#request.ec_totalRows!=''&&#request.ec_totalRows!=null">
<form action="<%=request.getContextPath()%>/cms/http_search.action" method="POST" name="theForm" onSubmit="if(this.submitted){ return false;}this.submitted=true;return true;">
		<ec:table styleClass="tableClass" items="page" tableId="ec"
				action="${pageContext.request.contextPath}/cms/http_search.action?bean.catalogue.id=${bean.catalogue.id}"
				imagePath="${pageContext.request.contextPath}/common/component/extreme/images/table/*.gif"
				retrieveRowsCallback="limit" filterRowsCallback="limit"
				sortRowsCallback="limit" rowsDisplayed="30"
				autoIncludeParameters="false" var="user" form="theForm"
				filterable="true" sortable="true">

				<ec:row highlightRow="true">
					<ec:column property="id" sortable="false" title="操作" viewsAllowed="html" filterable="false">
						<input name="selectinput" type="radio" onclick="setModifyId('${user.P_ID}','${user.P_CREATOR}','${user.P_CREATETIME}')"/>
					</ec:column>
					
					<%
					List<Attribute> attr=(List<Attribute>)request.getAttribute("attributes");
					
					for(int i=0;i<attr.size()&&i<6;i++){
					
						Attribute ele=attr.get(i);
						
					%>
					<ec:column property="<%=ele.getColumn().toUpperCase()%>" title="<%=ele.getName()%>" />
					<%}%>
 					<ec:column property="P_CREATOR" title="录入人"/>
 					<ec:column property="P_CREATETIME" title="录入时间" />
 					<ec:column property="P_MODIFYTIME" title="修改时间" />
					
				</ec:row>
			</ec:table>
</form>
</s:if>
</div>

</div></div>

<jsp:include flush="true" page="/common/bottom.jsp" />
</body>
</html>
