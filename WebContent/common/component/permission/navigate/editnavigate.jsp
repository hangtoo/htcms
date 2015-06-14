<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/WEB-INF/extremecomponents" prefix="ec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${navigate_name}</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/common/dtree/dtree.js"></script>
<link type="text/css" rel="stylesheet" media="all" href="<%=request.getContextPath()%>/common/dtree/dtree.css" />
<link type="text/css" rel="stylesheet" media="all" href="<%=request.getContextPath()%>/common/div/css/base.css" />
<link href="<%=request.getContextPath()%>/common/component/extreme/css/extremecomponents.css"  rel="stylesheet" type="text/css" />
<s:head theme="ajax" />

<script type="text/javascript">
<!--
function gotoaddwindow(pid,pname){
	window.location="<%=request.getContextPath()%>/permission/navigate_newOne.action?bean.parent.id="+pid+"&bean.parent.name="+pname;
}
//-->
</script>

</head>
<body>


<jsp:include flush="true" page="/common/top.jsp" />
<jsp:include flush="true" page="/common/left.jsp" />

<div id="contentfloat" >
<jsp:include flush="true" page="/common/contenttitle.jsp" />
<div id="content">
<!-- 放内容 -->

<jsp:include flush="true" page="navigatetree.jsp" />

<span class="operate_middle">
<div id='msgdiv'><b>${returnmsg}</b></div>
<s:form action="navigatedb_update" >
	<s:hidden key="bean.id"/>
	<s:hidden key="bean.parent.id"/>
	<s:textfield key="bean.parent.name" required="true" readonly="true"/>
	<s:textfield key="bean.name" required="true" requiredposition="right"/>
	<s:textfield key="bean.url" required="true"/>
	<s:textfield key="bean.remark"/>
	<s:submit key="submit"/>
</s:form>
<a href="javascript:gotoaddwindow('${bean.id}','${bean.name}');">新增</a>
</span>

</div></div>

<jsp:include flush="true" page="/common/bottom.jsp" />
</body>
</html>