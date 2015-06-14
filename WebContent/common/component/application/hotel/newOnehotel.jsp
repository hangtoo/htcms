<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/WEB-INF/extremecomponents" prefix="ec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>酒店管理</title>

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

<div id='msgdiv'><b>${returnmsg}</b></div>
<s:form action="hoteldb_save" >
	<s:hidden key="bean.id"/>
	<s:textfield key="bean.name" required="true" readonly="false"  requiredposition="right"  size="5"/>
    <s:textfield key="bean.area" required="true" size="5"/>
	<s:textfield key="bean.address" required="true" size="5"/>
	<s:textfield key="bean.prices" required="true" size="5"/>
	<s:textfield key="bean.mapx" required="true" size="5"/>
	<s:textfield key="bean.mapy" required="true" size="5"/>
	<s:textfield key="bean.remark" required="true"/><s:submit key="submit"/>
</s:form>

</div></div>


<jsp:include flush="true" page="/common/bottom.jsp" />
</body>
</html>
