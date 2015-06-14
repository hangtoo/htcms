<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="common.component.config.GlobeData,java.util.List"%>
<%
boolean topshow=true;
List<String> top=GlobeData.getConfig("show.top");
if(top!=null){
	topshow=!"0".equalsIgnoreCase(top.get(0));
}
if(topshow){%>
<div class="top">
	<div class="top_logo">
		<ul>
			<li>Struts2.0</li>
		</ul>
	</div>
	
	<div class="top_stat">
		<ul>
			<li>${user.name}您好，欢迎光临！</li>
			<li>[<a href="<s:url value="/permission/login_logout.action"/>">退出登录</a>]</li>
			<li>[<a href="#">修改密码</a>]</li>
		</ul>
	</div>
</div>
<%}%>