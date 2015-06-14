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


<!-- 放内容 -->
<div id='msgdiv'><b>${returnmsg}</b></div>
<s:form action="register_save">
用户名<input name="bean.username" type="text" id="username" maxlength="14" size="12" tabindex="1" />用户名由4～20个字母或者数字组成
<br/>  
昵称<input name="bean.name" type="text" id="nickname" maxlength="14" size="12" tabindex="2" /><br/>  
密码<input name="bean.password" type="password" id="password" maxlength="18" size="12" tabindex="3" /><br/>
确认<input name="bean.repassword" type="password" id="repassword" maxlength="18" size="12" tabindex="4" /><br/>
邮箱<input name="bean.email" type="text" id="email" maxlength="50" size="12" tabindex="5" /><br/>
<input name="bean.register" type="submit" id="submit" value="注册" tabindex="6" />
</s:form>

</div></div>


<jsp:include flush="true" page="/common/component/application/common/bottom.jsp" />
</body>
</html>