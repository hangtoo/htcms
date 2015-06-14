<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="operate_left">
	<p><s:a href="javascript: navigatetree.openAll();">全部展开</s:a> | <s:a href="javascript: navigatetree.closeAll();">全部关闭</s:a> | <s:a href="javascript:saveselect();">保存</s:a>
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
					navigatetree.add('<s:property value="id"/>','-1','<s:property value="name"/>','#');
				</s:else>
			</s:iterator>
			document.write(navigatetree);
			//navigatetree.openTo(1,true);//1为id
			
			var thecheckids=',';
			
			<s:iterator id="ele" value="permissions">
				<s:if test="#ele.checked!=null">
					thecheckids=thecheckids+'<s:property value="navigate.id"/>,';
				</s:if>
			</s:iterator>
			
			navigatetree.setchecked(thecheckids);
			
		}catch(e){
			document.write(e);
		}
		
		function saveselect(){
			try{
				var ids=navigatetree.getallcheckids();
				
				document.all('permissiondb_save').all('checkrecords').value=ids;
				
				if(!confirm("确定保存吗?"))
					return;
				
				document.all('permissiondb_save').submit();
			}catch(e){
				alert('请选择操作的角色!');
			}
		}
		
		
		//-->
	</script>
	</p>
</div>
<s:form action="permissiondb_save" cssStyle="display:none;">
	<s:hidden key="bean.id"/>
	<s:hidden key="checkrecords"/>
</s:form>