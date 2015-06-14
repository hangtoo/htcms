<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>收藏管理</title>

<link type="text/css" rel="stylesheet" media="all" href="<%=request.getContextPath()%>/common/div/css/base-right.css" />
<s:head theme="ajax" />

</head>
<body>


<jsp:include flush="true" page="/common/component/application/common/top.jsp" />

<div id="contentfloat" >
<jsp:include flush="true" page="/common/component/application/common/contenttitle.jsp" />
<div id="content">
<!-- 放内容 -->

<div id='msgdiv'><b>${returnmsg}</b></div>
<s:form action="nurldb_save" >
	<s:hidden key="bean.id"/>

	<s:textfield key="bean.nurl" required="true" readonly="false"  requiredposition="right"  size="5"/>
    <s:textfield key="bean.desc" required="true" size="5"/>
    
	<s:textfield key="bean.remark" required="true" size="5"/>

	<s:textfield key="bean.tags" required="true" size="5"/>
	<s:submit key="public"/>
	<s:submit key="private"/>
</s:form>

</div></div>


<jsp:include flush="true" page="/common/component/application/common/bottom.jsp" />
</body>
</html>
