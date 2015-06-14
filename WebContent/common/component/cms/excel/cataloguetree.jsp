<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="operate_left">
	<p><a href="javascript: catalogtree.openAll();">全部展开</a> | <a href="javascript: catalogtree.closeAll();">全部关闭</a> | <a href="javascript: newexcel();">新增</a>
		<script type="text/javascript">
		<!--
		try{
			catalogtree = new dTree('catalogtree','<%=request.getContextPath()%>/common/dtree/',false);
			<s:set name="username" value="#session.user.username"/>
			//d.config.target = "iframetarget";
			<s:iterator id="treeele" value="@common.component.cms.util.GlobeData@getCatalogrightTree()">
				<s:if test="#username==@common.component.IConstants@USERNAME||#treeele.checked!=null">
					<s:set name="thepid" value="#treeele.catalogue.parent.id"/>
					<s:if test="#thepid!=null">
						catalogtree.add('<s:property value="catalogue.id"/>','<s:property value="catalogue.parent.id"/>','<s:property value="catalogue.name"/>','<%=request.getContextPath()%>/cms/http_search.action?bean.catalogue.id=<s:property value="catalogue.id"/>');
					</s:if>
					<s:else>
						catalogtree.add('<s:property value="catalogue.id"/>','-1','<s:property value="catalogue.name"/>','<%=request.getContextPath()%>/cms/http_search.action?bean.catalogue.id=<s:property value="catalogue.id"/>');
					</s:else>
				</s:if>
			</s:iterator>
			document.write(catalogtree);
			//catalogtree.openTo(1,true);//1为id
		}catch(e){
			document.write(e);
		}
		
		function newexcel(){
			window.location.href="<%=request.getContextPath()%>/cms/excel_newOne.action?bean.catalogue.id=<s:property value="bean.catalogue.id"/>";
		}
		//-->
	</script>
	</p>
</div>