<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ page import="common.util.StringUtil,common.component.config.GlobeData,java.util.List"%>
<%
boolean leftshow=true;
List<String> list=GlobeData.getConfig("show.left");
if(list!=null){
	leftshow=!"0".equalsIgnoreCase(list.get(0));
}
if(leftshow){%>
<div id="contenttitle">您正在做的业务是：<script type="text/javascript">document.write(d.getSelectedName());</script></div>
<%}else{
	String title="struts2.0";
	List<String> titles=GlobeData.getConfig("show.title");
	if(titles!=null){
		title=titles.get(0);
	}
%>
<div id="contenttitle"><%=title%></div>
<%} %>