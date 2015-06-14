<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="common.component.application.nurl.entity.Nurl,common.component.application.nurl.GlobeData,java.util.List"%>

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

<%
List<Nurl> urlist=GlobeData.getNurl();

if(urlist!=null)
	for(int i=urlist.size()-1;i>=0;i--){
		Nurl nurl=urlist.get(i);
%>

<a href="<%=nurl.getNurl()%>"><%=nurl.getDesc()%></a> &nbsp; 1人收藏 &nbsp;<%=nurl.getCreateTime()%><br>
<%=nurl.getRemark()%><br>
<%=nurl.getTags()%>...<a href="<%=request.getContextPath()%>/nurl/nurl_edit.action?bean.id=<%=nurl.getId()%>">编辑</a>/<a href="<%=request.getContextPath()%>/nurl/nurldb_delete.action?bean.id=<%=nurl.getId()%>">删除</a><br>

<br>

<%	}
%>
</div></div>


<jsp:include flush="true" page="/common/component/application/common/bottom.jsp" />
</body>
</html>
