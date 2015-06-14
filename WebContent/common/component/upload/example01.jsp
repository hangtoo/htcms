<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>上传测试</title>
</head>

<body>
<s:form method="post" action="create.action" name="mainForm" theme="simple">
	<!-- 上传图片名称返回 -->
	<s:hidden name="bean.image" value="${bean.image}"></s:hidden>
</s:form>

<!-- 引入上传模块 -->
<jsp:include flush="true" page="/common/component/upload/upload.jsp" />
</body>
</html>