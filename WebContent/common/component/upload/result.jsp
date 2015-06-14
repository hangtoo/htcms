<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<script type="text/javascript">
//上传文件名设置
window.parent.document.all("bean.image").value='${fileRealName}';

//上传图片预览
window.parent.document.all("previewimage").src='<%=request.getContextPath()%>/archives/${fileRealName}';
window.parent.document.all("previewimage").style.display="block";

//提示用户
if(window.parent.document.all("bean.image").value!=null&&window.parent.document.all("bean.image").value!='')
	alert("图片上传成功");
</script>