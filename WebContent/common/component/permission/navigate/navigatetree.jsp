<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="operate_left">
	<p><a href="javascript: navigatetree.openAll();">全部展开</a> | <a href="javascript: navigatetree.closeAll();">全部关闭</a> | <a href="javascript:deleteselect();">删除</a>
		<script type="text/javascript">
		<!--
		try{
			navigatetree = new dTree('navigatetree','<%=request.getContextPath()%>/common/dtree/','true');
			
			//d.config.target = "iframetarget";
			<s:iterator id="treeele" value="tree">
				<s:set name="thepid" value="#treeele.parent.id"/>
				<s:if test="#thepid!=null">
					navigatetree.add('<s:property value="id"/>','<s:property value="parent.id"/>','<s:property value="name"/>','<%=request.getContextPath()%>/permission/navigate_edit.action?bean.id=<s:property value="id"/>');
				</s:if>
				<s:else>
					navigatetree.add('<s:property value="id"/>','-1','<s:property value="name"/>','<%=request.getContextPath()%>/permission/navigate_edit.action?bean.id=<s:property value="id"/>');
				</s:else>
			</s:iterator>
			document.write(navigatetree);
			//navigatetree.openTo(1,true);//1为id
		}catch(e){
			document.write(e);
		}
		
		function deleteselect(){
			if(!confirm("确定删除选择的元素吗?"))
				return;
			var ids=navigatetree.getleafcheckids();	
			window.location.href="<%=request.getContextPath()%>/permission/navigatedb_delete.action?checkrecords="+ids;
		}
		
		//-->
	</script>
	</p>
</div>