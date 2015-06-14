<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="common.util.StringUtil,common.component.config.GlobeData,java.util.List"%>

<%
boolean leftshow=true;
List<String> left=GlobeData.getConfig("show.left");
if(left!=null){
	leftshow=!"0".equalsIgnoreCase(left.get(0));
}
if(leftshow){%>
	
	
<div class="left_menu">
	新闻
</div>
	
<%}%>