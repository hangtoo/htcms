<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title>&nbsp;</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!--media=print 这个属性可以在打印时有效-->
<style media=print>
.Noprint {
	display: none;
}

.PageNext {
	page-break-after: always;
}
</style>

<style>
.tdp {
	border-bottom: 0 solid #000000;
	border-left: 0 solid #000000;
	border-right: 0 solid #ffffff;
	border-top: 0 solid #ffffff;
}

.tabp {
	border-color: #000000 #000000 #000000 #000000;
	border-style: solid;
	border-top-width: 0px;
	border-right-width: 0px;
	border-bottom-width: 1px;
	border-left-width: 0px;
}

.NOPRINT {
	font-family: "宋体";
	font-size: 9pt;
}
</style>

</head>

<body>
	<center class="Noprint">
	<p>
		<OBJECT id=WebBrowser
			classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0 width=0>
		</OBJECT> 
		<input type=button value=打印 onclick=document.all.WebBrowser.ExecWB(6,1)>
		<input type=button value=直接打印
			onclick=document.all.WebBrowser.ExecWB(6,6)> <input type=button
			value=页面设置 onclick=document.all.WebBrowser.ExecWB(8,1)>
	</p>
	<p>
		<input type=button value=打印预览
			onclick=document.all.WebBrowser.ExecWB(7,1)> <br/>
	</p>
	
	<hr align="center" width="90%" size="1" noshade>
	</center>


<jsp:include flush="true" page="/common/top.jsp" />
<hr align="center" width="90%" size="1" noshade class="NOPRINT">
<!--分页-->
<div class="PageNext"></div>
<jsp:include flush="true" page="/common/bottom.jsp" />
</body>
</html>
