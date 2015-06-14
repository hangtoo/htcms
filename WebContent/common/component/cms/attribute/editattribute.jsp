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

</head>
<body>


<jsp:include flush="true" page="/common/top.jsp" />
<jsp:include flush="true" page="/common/left.jsp" />


<div id="contentfloat" >

<jsp:include flush="true" page="/common/contenttitle.jsp" />

<div id="content">
<!-- 放内容 -->
<jsp:include flush="true" page="cataloguetree.jsp" />

<div id='msgdiv' style="display: none;"><b>${returnmsg}</b></div>
<s:form action="attributedb_update" >
	<s:hidden key="bean.id"/>
	<s:hidden key="bean.catalogue.id"/>
	<s:textfield key="bean.name" required="true" requiredposition="right"/>
	<s:textfield key="bean.column" required="true"/>
	
	<s:bean name="common.component.cms.sql.OTypes" id="otypes"></s:bean>
	<s:select list="#otypes.getTypes()" listKey="key" listValue="value" key="bean.type" required="true">
	</s:select>
	
	<s:textfield key="bean.length" required="true"/>
	<s:textfield key="bean.remark"/>
    <s:submit key="submit"/>
</s:form>

</div></div>

<jsp:include flush="true" page="/common/bottom.jsp" />
</body>
</html>
