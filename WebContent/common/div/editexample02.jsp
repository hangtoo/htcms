<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" media="all" href="<%=request.getContextPath()%>/css/base.css" />	
</head>
<body>

<jsp:include flush="true" page="/common/top.jsp" />

<div id="contentfloat">
<div id="contenttitle">您正在做的业务是：</div>
<div id="content"><!-- 放内容 --> 

内容

</div>
</div>

<jsp:include flush="true" page="/common/dtree/example02.jsp" />
<jsp:include flush="true" page="/common/bottom.jsp" />

</body>
</html>
</body>
</html>