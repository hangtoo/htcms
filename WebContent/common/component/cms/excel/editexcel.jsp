<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/WEB-INF/extremecomponents" prefix="ec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>信息发布</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/common/dtree/dtree.js"></script>
<link type="text/css" rel="stylesheet" media="all" href="<%=request.getContextPath()%>/common/dtree/dtree.css" />
<link type="text/css" rel="stylesheet" media="all" href="<%=request.getContextPath()%>/common/div/css/base.css" />
<link href="<%=request.getContextPath()%>/common/component/extreme/css/extremecomponents.css"  rel="stylesheet" type="text/css" />
<s:head theme="ajax" />

</head>
<body onunload="quit()">


<jsp:include flush="true" page="/common/top.jsp" />
<jsp:include flush="true" page="/common/left.jsp" />

<div id="contentfloat" >
<div id="contenttitle">您正在做的业务是：<script type="text/javascript">document.write(d.getSelectedName());</script></div>
<div id="content">
<!-- 放内容 -->
<jsp:include flush="true" page="cataloguetree.jsp"/>

<div id='msgdiv'><b>${returnmsg}</b></div>
<s:form action="exceldb_update" enctype="multipart/form-data" theme="simple">
	<s:hidden key="bean.catalogue.id"/>
	<s:hidden key="bean.id"/>
	<s:file key="documentFile"  cssStyle="height:0px;width:0px;COLOR: #FFFFFF; BACKGROUND-COLOR: #FFFFFF;"/>
    <s:submit key="submit" cssStyle="height:0px;width:0px;COLOR: #FFFFFF; BACKGROUND-COLOR: #FFFFFF;"/>
</s:form>

<input name="openbt" type="button" value="打开" onclick="openandoper()"/>
<input name="savebt" type="button" value="保存" onclick="saveandclose()"/>
</div></div>

<script language="vbscript"> 

Dim myDocApp
Dim myDoc

Set myDocApp = Nothing
Set myDoc = Nothing

'Set fso = CreateObject("Scripting.FileSystemObject")   
'Set MyFile = fso.GetFile("<%=request.getContextPath()%>/cms/excelfile_edit.action?bean.catalogue.id=11")
'MyFile.Copy ("c:\\temp_edit.XLS")

function openandoper()
	
	'On Error Resume Next 
	if myDocApp is Nothing Then
		Set myDocApp = CreateObject("Excel.Application")   
		'myDocApp.DisplayAlerts = False
	end if

	Err.Clear

	if myDoc is Nothing Then
		'Set myDoc = myDocApp.Workbooks.Open("<%=request.getContextPath()%>/cms/excelfile_edit.action?bean.catalogue.id=11",,False)
		'myDoc.Application.DisplayAlerts = False
		'myDoc.SaveAs "C:\\temp.xls",-4143 
		'myDoc.close()

		Set myDoc = myDocApp.Workbooks.Open("c:\\temp_edit.XLS")
	end if

    myDocApp.Visible = True   
    Set objSelection = myDocApp.Selection   

	showmsg("您必须安装Excel电子表格软件，同时浏览器须使用“ActiveX 控件”，您的浏览器须允许执行控件。 请点击【帮助】了解浏览器设置方法！")

end function 

function saveandclose()
	On Error Resume Next 
	'myDocApp.ActiveWorkbook.Save
	'myDoc.save()
    myDoc.close()   

	if Err.Number <>0 then 
		MsgBox "文件未打开或者Excel文件未保存！"
		Err.Clear
	else
	    myDocApp.quit()
		Set myDocApp = Nothing
		Set myDoc = Nothing
		submitdata()
		'deletetemp()
	end if
end function

function showmsg(str)
	'MsgBox Err.Number
	if Err.Number > 0 then 
		MsgBox str
		Err.Clear 
		'MsgBox Err.Number
	end if
end function 

function quit()
	myDocApp.quit()
	Set myDocApp = Nothing
	Set myDoc = Nothing
end function

function deletetemp()
	Set fso = CreateObject("Scripting.FileSystemObject")
    Set MyFile = fso.GetFile("c:\\temp_edit.XLS")   
    MyFile.delete()  
end function
</script>

<script language="jscript">
savefile("<%=request.getContextPath()%>/cms/excelfile_edit.action?bean.id=${bean.id}&bean.catalogue.id=${bean.catalogue.id}","c:\\temp_edit.XLS");
function submitdata(){
	document.all("exceldb_update").documentFile.focus();
	var WshShell=new ActiveXObject("WScript.Shell");
	WshShell.sendKeys("c:\\temp_edit.XLS");
	window.setTimeout("document.all('exceldb_update').all('submit').click();", 10);
}

function savefile(url,filename){
	if(url.indexOf('excelfile_edit.action')==-1)
		return;
	var sGet=new ActiveXObject("ADODB.Stream"); 
	var xGet = false; 
	try {
		xGet = new XMLHttpRequest(); 
	}catch (trymicrosoft){
		try {
			xGet = new ActiveXObject("Msxml2.XMLHTTP"); 
		}catch (othermicrosoft){
			try{
				xGet = new ActiveXObject("Microsoft.XMLHTTP"); 
			}
			catch (failed) {
				xGet = false; 
			}
		}
	}

	xGet.Open ("GET",url,false);

	xGet.Send(); 
	sGet.Mode=3; 
	sGet.Type=1; 
	sGet.Open(); 
	sGet.Write (xGet.ResponseBody); 
	sGet.SaveToFile (filename,2); 
}

</script>

<jsp:include flush="true" page="/common/bottom.jsp" />
</body>
</html>