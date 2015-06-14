<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>struts2.0</title>

 	<script type="text/javascript" src="<%=request.getContextPath()%>/common/desktop/js/ModuleTree.js"></script>

 	<script type="text/javascript">
 	
 	m=new moduleTree('m','<%=request.getContextPath()%>/common/dtree/');
 	
			//d.config.target = "iframetarget";//#session.tree
			<s:iterator id="treeele" value="@common.component.permission.util.GlobeData@getMenuTree()">
				<s:if test="#treeele.checked!=null">
					<s:set name="thepid" value="#treeele.navigate.parent.id"/>
					<s:set name="theurl" value="#treeele.navigate.url"/>
					<s:if test="#thepid!=null">
						<s:if test="#theurl!=\"#\"">
							m.add('<s:property value="navigate.id"/>','<s:property value="navigate.parent.id"/>','<s:property value="navigate.name"/>','<%=request.getContextPath()%>/${theurl}');
						</s:if>
						<s:else>
							m.add('<s:property value="navigate.id"/>','<s:property value="navigate.parent.id"/>','<s:property value="navigate.name"/>','${theurl}');
						</s:else>	
					</s:if>
					<s:else>
						m.add('<s:property value="navigate.id"/>','-1','<s:property value="navigate.name"/>','${theurl}');
					</s:else>
				</s:if>
			</s:iterator>
 	
 	document.write(m);
 	
 	</script>



</body>
</html>
