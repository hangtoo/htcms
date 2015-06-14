<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="operate_left">
	<p><a href="javascript: roletree.openAll();">全部展开</a> | <a href="javascript: roletree.closeAll();">全部关闭</a> 
		<script type="text/javascript">
		<!--
		try{
			roletree = new dTree('roletree','<%=request.getContextPath()%>/common/dtree/',false);
			
			//d.config.target = "iframetarget";
			<s:iterator id="treeele" value="roles">
				<s:set name="thepid" value="#treeele.parent.id"/>
				<s:if test="#thepid!=null">
					roletree.add('<s:property value="id"/>','<s:property value="parent.id"/>','<s:property value="name"/>','<%=request.getContextPath()%>/cms/catalogright_edit.action?bean.id=<s:property value="id"/>');
				</s:if>
				<s:else>
					roletree.add('<s:property value="id"/>','-1','<s:property value="name"/>','<%=request.getContextPath()%>/cms/catalogright_edit.action?bean.id=<s:property value="id"/>');
				</s:else>
			</s:iterator>
			document.write(roletree);
			roletree.openAll();
		}catch(e){
			document.write(e);
		}
		//-->
	</script>
	</p>
</div>