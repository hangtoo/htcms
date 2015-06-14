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


<%
	String id=request.getParameter("d_navigateid");
	if(!StringUtil.isEmptyString(id)){
		session.setAttribute("d_navigateid",id);
	}

%>
<div class="left_menu">
	<p><a href="javascript: d.openAll();">全部展开</a> | <a href="javascript: d.closeAll();">全部关闭</a>
		<script type="text/javascript">
		<!--
		try{

			d = new dTree('d','<%=request.getContextPath()%>/common/dtree/',false,'<%=session.getAttribute("d_navigateid")%>');
			
			//d.config.target = "iframetarget";//#session.tree
			<s:iterator id="treeele" value="@common.component.permission.util.GlobeData@getMenuTree()">
				<s:if test="#treeele.checked!=null">
					<s:set name="thepid" value="#treeele.navigate.parent.id"/>
					<s:set name="theurl" value="#treeele.navigate.url"/>
					<s:if test="#thepid!=null">
						<s:if test="#theurl!=\"#\"">
							d.add('<s:property value="navigate.id"/>','<s:property value="navigate.parent.id"/>','<s:property value="navigate.name"/>','<%=request.getContextPath()%>/${theurl}');
						</s:if>
						<s:else>
							d.add('<s:property value="navigate.id"/>','<s:property value="navigate.parent.id"/>','<s:property value="navigate.name"/>','${theurl}');
						</s:else>	
					</s:if>
					<s:else>
						d.add('<s:property value="navigate.id"/>','-1','<s:property value="navigate.name"/>','${theurl}');
					</s:else>
				</s:if>
			</s:iterator>
			
			document.write(d);

			d.openAll();
			//d.openTo(1,true);//1为id
			//alert(d.getSelected());
		}catch(e){
			document.write(e);
		}
		//-->
	</script>
	</p>
</div>
<%}%>

<style>
#contentfloat {
	overflow:hidden;
	float: right; padding: 0px;margin-left: <%if(leftshow){out.print("-169px");}else{out.print("0px");}%>; margin-right:1px;margin-top: -1px;
	width: 99%; height: 100%; 
	height: 100%;
}
#contenttitle{
	position: relative;
	margin-left: <%if(leftshow){out.print("169px");}else{out.print("0px");}%>;margin-top: 10px;padding:0;
	color:#196E89;
	background-color: inherit; 	color:#0F5B7F; font-size:105%; font-weight:bold;
	
}
#content {
	overflow:hidden;border-bottom:1px solid #F0F0F0;
	padding: 0px;margin-left: <%if(leftshow){out.print("169px");}else{out.print("0px");}%>;margin-top: 0px;
	height: 100%;
}
</style>