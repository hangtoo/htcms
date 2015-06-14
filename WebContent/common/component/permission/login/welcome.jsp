<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/WEB-INF/extremecomponents" prefix="ec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${navigate_name}</title>

<script type="text/javascript" src="<s:url value="/common/dtree/dtree.js"/>"></script>
<link type="text/css" rel="stylesheet" media="all" href="<s:url value="/common/dtree/dtree.css"/>" />
<link type="text/css" rel="stylesheet" media="all" href="<s:url value="/common/div/css/base.css"/>" />
<link href="<s:url value="/common/component/extreme/css/extremecomponents.css"/>"  rel="stylesheet" type="text/css" />
<s:head theme="ajax" />

</head>
<body>


<jsp:include flush="true" page="/common/top.jsp" />

<div id="contentfloat" >
<div id="contenttitle">您正在做的业务是：${navigate_name}</div>
<div id="content">
<!-- 放内容 -->
</div></div>

<jsp:include flush="true" page="/common/left.jsp" />
<jsp:include flush="true" page="/common/bottom.jsp" />
</body>
</html>