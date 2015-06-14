<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false"%>
<div class="upload">

<!-- 图片预览模块 -->
<img alt="" src="<%=request.getContextPath()%>/archives/${bean.image}" id="previewimage" name="previewimage" title="图片预览" 
 style="display: <s:if test="#bean.image==null||#bean.image==''}">none</s:if>;"/>

<!-- 图片上传模块 -->
<form method="post"
	action="<%=request.getContextPath()%>/upload/save.action" 
	target="imageframe"
	ENCTYPE="multipart/form-data">

	如有产品图片，请选择上传(jpg,gif；最大9999KB) 
	<input type="file" name="documentFile">
	&nbsp;
	<input type="submit" value="上传">
</form>

<!-- 图片上传结果模块 -->
<iframe name="imageframe" id="imageframe" style="display: none;"></iframe>
</div>