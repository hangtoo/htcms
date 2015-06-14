<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<link type="text/css" rel="stylesheet" media="all" href="<%=request.getContextPath()%>/common/div/css/fav.css" />
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
<div id="head">
	
	<ul id="info">				
			&nbsp;&nbsp;我喜欢的收藏，收藏喜欢的网页，和朋友们分享。<br/><br/>
	</ul>
	
	<ul id="sitemark">
		<span class="my">&nbsp;MY</span>
		<span class="f">F</span>
		<span class="a">A</span>
		<span class="v">V</span>
	</ul>
	
	<ul id="userinfo">	
	&nbsp;<b>注册/qn_lf</b> | 登陆/登出 | 设置 |帮助&nbsp;<br/>
	&nbsp;<input name="search" size="20" value="输入查询的内容" style="color:#c0c0c0;"/><b>&nbsp;查询&nbsp;</b> 	
	</ul>
	
		<ul id="navbar">
			<li class="current"><a href="/" title="QQ书签首页">首页</a></li>
			<li><a href="/popular/" title="查看热门收藏" id="NavID_Popular">热门收藏</a></li>
			<!--li><a href="/recent/" title="查看最新收藏" id="NavID_Recent">最新收藏</a></li-->
			<li><a href="/home/" title="查看我的收藏" id="NavID_Home">我的收藏</a></li>
			<li class="add"><a href="/post/" title="收藏一个新网址" id="NavID_Post">收藏新网址</a></li>
			<li><a href="http://service.qq.com/category/bookmark.html" title="腾讯书签服务" id="NavID_service">热门标签</a></li>
		</ul>
	
</div>
<%}%>