<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>我的收藏</title>

<link type="text/css" rel="stylesheet" media="all" href="<%=request.getContextPath()%>/common/div/css/base-right.css" />

<s:head theme="ajax" />

</head>
<body>

<jsp:include flush="true" page="/common/component/application/common/top.jsp" />

<div id="contentfloat" >
<jsp:include flush="true" page="/common/component/application/common/contenttitle.jsp" />
<div id="content">

<s:form action="login">
用户名<input name="bean.username" type="text" id="bean.username" maxlength="14" size="12" tabindex="1" /><br/>
密码<input name="bean.password" type="password" id="bean.password" maxlength="18" size="12" tabindex="2" /><br/>
<input name="login" type="submit" id="submit" value="登录" tabindex="3" /><a href="register.action">注册</a>
</s:form>


</div></div>


<jsp:include flush="true" page="/common/component/application/common/bottom.jsp" />
</body>
</html>
