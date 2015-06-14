<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/WEB-INF/extremecomponents" prefix="ec"%>
<%@ page import="java.util.Map,common.component.cms.entity.Attribute"%>
<%@ page import="java.util.List,java.util.ArrayList"%>
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
<script type="text/javascript">
<!--
function gotoaddwindow(pid,pname){
	window.location="<%=request.getContextPath()%>/cms/catalogue_newOne.action?bean.parent.id="+pid+"&bean.parent.name="+pname;
}

function exporttable(){
	window.location.href="<%=request.getContextPath()%>/cms/cataloguedb_createTable.action?bean.id=${bean.id}";
}
//-->
</script>
</head>
<body>

<jsp:include flush="true" page="/common/top.jsp" />
<jsp:include flush="true" page="/common/left.jsp" />

<div id="contentfloat" >
<jsp:include flush="true" page="/common/contenttitle.jsp" />
<div id="content">
<!-- 放内容 -->
<jsp:include flush="true" page="cataloguetree.jsp"/>
<div class="operate_middle">
<%
List<Attribute> blobs=new ArrayList();
Map<String,String> clobblobs=(Map<String,String>)request.getAttribute("clobblobs");
%>
<s:form action="contentdb_update" theme="simple">
	<s:hidden key="bean.id"/>
	<s:hidden key="bean.catalogue.id"/>
	
	<s:bean name="common.component.cms.sql.OTypes" id="otypes"></s:bean>
	<%
	Map columns=(Map)request.getAttribute("columns");
	%>
	<s:iterator value="attributes" id="ele">
	<%
	Attribute attr=(Attribute)request.getAttribute("ele");
	%>
	
		
		<s:if test="#ele.type==#otypes.VARCHAR">${ele.name}:<input name="columns['${ele.column}']" value="<%=columns.get(attr.getColumn().toUpperCase())%>"/></s:if>
		
		<s:elseif test="#ele.type==#otypes.DATE"><!-- 日期控件 -->
			${ele.name}:
			<script type="text/javascript">
	    		dojo.require("dojo.widget.DatePicker");
			</script>
			<div dojoType="dropdowndatepicker"  name="dojo.columns['${ele.column}']"
				inputName="columns['${ele.column}']" displayFormat="yyyy-MM-dd" saveFormat="rfc" value="<%=columns.get(attr.getColumn().toUpperCase())%>">
			</div>
		</s:elseif>
		
		<s:elseif test="#ele.type==#otypes.CLOB"><!-- editor方式 -->
			${ele.name}:
			<textarea name="clobblobs['<%=columns.get(attr.getColumn().toUpperCase())%>']" rows="10" cols="50"><%=clobblobs.get(columns.get(attr.getColumn().toUpperCase()))%></textarea>
			<input name="columns['${ele.column}']" type="hidden" value="<%=columns.get(attr.getColumn().toUpperCase())%>"/>
		</s:elseif>
		
		<s:elseif test="#ele.type==#otypes.BLOB"><!-- 附件方式 -->
			<input name="columns['${ele.column}']" type="hidden" value="<%=columns.get(attr.getColumn().toUpperCase())%>"/>
			<%blobs.add(attr);%>
		</s:elseif>
		
		<s:else>
			${ele.name}:<input name="columns['${ele.column}']" value="<%=columns.get(attr.getColumn().toUpperCase())%>"/>
		</s:else>
		
		
		<br/>
	</s:iterator>
    
    
</s:form>

<!-- 引入上传模块 -->
	<%for (int i=0;i<blobs.size();i++){ %>
			
			<%=blobs.get(i).getName()%>:
			<jsp:include flush="true" page="/common/component/upload/uploadfile.jsp">
				<jsp:param name="thecolumn" value="<%=blobs.get(i).getColumn()%>"/>
			</jsp:include>
	<%} %>
<input type="button" onclick="contentdb_update.submit();" value="提交"/>
</div>
	
</div></div>

<jsp:include flush="true" page="/common/bottom.jsp" />
</body>
</html>