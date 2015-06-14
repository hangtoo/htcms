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

<!-- 导航链接和页脚是静态内容,一年最多更改一次,对于这些文件,建议使用 include 伪指,例如template.jsp-->
<jsp:include flush="true" page="/common/top.jsp" />

<div id="contentfloat" >
<div id="contenttitle">您正在做的业务是：用户管理——用户列表</div>
<div id="content">
<!-- 放内容 -->
</div></div>

<jsp:include flush="true" page="/common/left.jsp" />
<jsp:include flush="true" page="/common/bottom.jsp" />

</body>
</html>