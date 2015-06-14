<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
		<META HTTP-EQUIV="Pragma" CONTENT="no-cache">   
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">   
		<META HTTP-EQUIV="Expires" CONTENT="0">
    <title>${navigate_name}</title>
    <link href="<s:url value="/common/component/permission/login/css/login.css"/>" rel="stylesheet" type="text/css"/>
    <s:head theme="ajax"/>
    <script>
    function submitInfo(){
    	if(login==undefined)
    		login=document.getElementById("login").getElementsByTagName("form");
    		
    	//if(login.all('bean.username').value=="")
    		//return;
    	login.submit();
    }
    </script>
    
</head>



<BODY style="BACKGROUND-COLOR: #fff; TEXT-ALIGN: center">
<DIV style="HEIGHT: 25%"></DIV>
<DIV style="BORDER-RIGHT: white 0px solid; BORDER-TOP: white 0px solid; MARGIN-LEFT: auto; BORDER-LEFT: white 0px solid; WIDTH: 60%; MARGIN-RIGHT: auto; BORDER-BOTTOM: white 0px solid">
<DIV style="BORDER-LEFT: white 400px solid; HEIGHT: 1px">
<DIV style="LEFT: 0px; MARGIN-LEFT: -400px; WIDTH: 474px; POSITION: relative; TOP: 0px; HEIGHT: 1px">
<SPAN class=btnSpan style="MARGIN: 5px; WIDTH: 100%">
	
	<!--上部圆角标题-->
	<STRONG class=btnSpanTop></STRONG><STRONG class=btnSpanCorner></STRONG>
	
	<SPAN class="btnSpanMiddle colorGray" style="CURSOR: default; HEIGHT: 25px; TEXT-ALIGN: left">
		<IMG height=24 alt="" hspace=5 src="<s:url value="/common/component/permission/login/img/user_headset_p5.gif"/>" width=24 align=middle>
		<B>登录 - struts2.0</B>
	</SPAN>
	
	<!--下部渐变灰色区域-->
	<SPAN class=colorGraySmooth style="BORDER-RIGHT: #b4b4b4 1px solid; PADDING-RIGHT: 10px; BORDER-TOP: #b4b4b4 1px solid; DISPLAY: block; PADDING-LEFT: 10px; PADDING-BOTTOM: 10px; OVERFLOW: hidden; BORDER-LEFT: #b4b4b4 1px solid; WIDTH: 100%; PADDING-TOP: 10px; BORDER-BOTTOM: #b4b4b4 1px solid; BACKGROUND-COLOR: #f3f3f3">
	<TABLE style="MARGIN-LEFT: auto; MARGIN-RIGHT: auto" cellPadding=0 border=0>
	<s:form action="login" theme="simple">
	<TBODY>
		<TR>
			<TD style="PADDING-RIGHT: 30px" vAlign=top align=middle rowSpan=4><IMG
				height=64 alt="" src="<s:url value="/common/component/permission/login/img/lockkey.gif"/>" width=64>
			</TD>
			<TD>用户名：</TD>
			<TD><s:textfield key="bean.username" required="true" value="admin"/></TD>
		</TR>
		<TR>
			<TD>密 码：</TD>
			<TD>
				<s:password key="bean.password" required="true"/>
				<INPUT type=hidden name=state>
			</TD>
		</TR>
		<TR>
			<TD>验证码：</TD>
			<TD>
				<s:textfield key="bean.checkcode" required="true" size="5"  />
				<IMG alt="" src="<s:url value="/vcode"/>">
			</TD>
		</TR>
		<TR>
			<TD style="PADDING-TOP: 9px; HEIGHT: 44px" align=right colSpan=2>
			<div id='msgdiv'><b>${returnmsg}</b></div>
			<!--登录按键开始-->
			<SPAN class=btnSpan style="MARGIN-RIGHT: 0px">
			<STRONG class=btnSpanTop></STRONG><STRONG class=btnSpanCorner></STRONG>
			<A class=btnSpanLink href="javascript:submitInfo();">登陆</A>
			<STRONG class=btnSpanCorner></STRONG><STRONG class=btnSpanBottom></STRONG>
			</SPAN>
			<!--登录按键结束-->
			
			</TD>
		</TR>
	</TBODY>
	</s:form>
	</TABLE>
	</SPAN>
	
</SPAN>

<!--Copyright区域-->
<SPAN class="colorGray font8" id=spanCopyright>
Copyright © 2009 HangZhou itfish Co., Ltd. &nbsp; All Rights Reserved. 
</SPAN>

</DIV>
</DIV>
</DIV>
</BODY>




</html>
