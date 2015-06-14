<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="operate_left">
	<p><s:a href="javascript: cataloguetree.openAll();">全部展开</s:a> | <s:a href="javascript: cataloguetree.closeAll();">全部关闭</s:a> | <s:a href="javascript:saveselect();">保存</s:a>
		<script type="text/javascript">
		<!--
		try{
			cataloguetree = new dTree('cataloguetree','<%=request.getContextPath()%>/common/dtree/','true');
			<s:set name="username" value="#session.user.username"/>
			//d.config.target = "iframetarget";
			<s:iterator id="treeele" value="@common.component.cms.util.GlobeData@getCatalogrightTree()">
				<s:if test="#username==@common.component.IConstants@USERNAME||#treeele.checked!=null">
					<s:set name="thepid" value="#treeele.catalogue.parent.id"/>
					<s:if test="#thepid!=null">
						cataloguetree.add('<s:property value="catalogue.id"/>','<s:property value="catalogue.parent.id"/>','<s:property value="catalogue.name"/>','<%=request.getContextPath()%>/cms/catalogue_edit.action?bean.id=<s:property value="catalogue.id"/>');
					</s:if>
					<s:else>
						cataloguetree.add('<s:property value="catalogue.id"/>','-1','<s:property value="catalogue.name"/>','#');
					</s:else>
				</s:if>
			</s:iterator>
			document.write(cataloguetree);
			//cataloguetree.openTo(1,true);//1为id
			
			var thecheckids=',';
			
			<s:iterator id="ele" value="catalogrights">
				<s:if test="#ele.checked!=null">
					thecheckids=thecheckids+'<s:property value="catalogue.id"/>,';
				</s:if>
			</s:iterator>
			
			cataloguetree.setchecked(thecheckids);
			
		}catch(e){
			document.write(e);
		}
		
		function saveselect(){
			try{
				var ids=cataloguetree.getallcheckids();
				
				document.all('catalogrightdb_save').all('checkrecords').value=ids;
				
				if(!confirm("确定保存吗?"))
					return;
				
				document.all('catalogrightdb_save').submit();
			}catch(e){
				alert('请选择操作的角色!');
			}
		}
		
		
		//-->
	</script>
	</p>
</div>
<s:form action="catalogrightdb_save" cssStyle="display:none;">
	<s:hidden key="bean.id"/>
	<s:hidden key="checkrecords"/>
</s:form>