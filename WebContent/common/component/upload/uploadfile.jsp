<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.Map,common.component.cms.entity.Attribute,common.util.StringUtil"%>
<%@ page isELIgnored="false"%>

<%
String formname=request.getParameter("thecolumn");
Map<String,String> columns=(Map<String,String>)request.getAttribute("columns");
Map<String,String> clobblobs=(Map<String,String>)request.getAttribute("clobblobs");
%>

<script type="text/javascript">
<!--
function deletefile(obj,id){
	var thefiles=window.document.all("columns['<%=formname%>']");
	
	if(obj.type=='1'){
		obj.src="<%=request.getContextPath()%>/common/component/extjs/example/tasks/images/hd-check.gif";
		obj.type='0';

		thefiles.value=thefiles.value+','+id;
	}else{
		obj.src="<%=request.getContextPath()%>/common/component/extjs/example/tasks/images/delete.gif";
		obj.type='1';
		
		thefiles.value=thefiles.value.replace(','+id,'');
	}
}
//-->
</script>

<!-- 文件列表模块 -->
<div id="preview<%=formname%>">
<% 
//to do 编写一个下载的action根据id 获取,并提供删除功能
String thefiles=columns.get(formname.toUpperCase());
if(thefiles!=null){
	String[] filelist=thefiles.split(",");
	if(clobblobs!=null){
		for(int i=0;i<filelist.length;i++){
			//out.println(filelist[i]);
			if(StringUtil.isEmptyString(filelist[i]))
				continue;
			
			String filename=clobblobs.get(filelist[i]);
			out.print("<span style='white-space:nowrap;'><a href="+request.getContextPath()+"/upload/download.action?bean.id="+filelist[i]+">"+filename+"</a>"+
					"<img style='cursor:pointer;' type='0' onclick='deletefile(this,\""+filelist[i]+"\");' src='"+request.getContextPath()+"/common/component/extjs/example/tasks/images/hd-check.gif'/>"+
					"</span>");
		}
	}
}
%>
</div>

<!-- 文件上传模块 -->
<form method="post"
	action="<%=request.getContextPath()%>/upload/save.action" name="<%=formname%>form"
	target="<%=formname%>fileframe"
	ENCTYPE="multipart/form-data">
	<input type="hidden" name="column" value="<%=formname%>"/>
	<input type="file" name="documentFile"/>文件最大不超过65KB
	<input type="button" value="上传" onclick="document.all('<%=formname%>form').submit();"/>
</form>

<!-- 文件上传结果模块 -->
<iframe name="<%=formname%>fileframe" id="<%=formname%>fileframe" style="display:none;"></iframe>
