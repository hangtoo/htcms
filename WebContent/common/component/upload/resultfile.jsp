<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false"%>

<script type="text/javascript">
try{

	if('${bean}'==''||'${bean}'=='null')
		alert('文件超过65K，上传失败');
	else{
		//上传文件名设置
		var thefiles=window.parent.document.all("columns['${column}']");
		thefiles.value=thefiles.value+","+'${bean.id}';
		
		
		var str="<span style='white-space:nowrap;'><a href='<%=request.getContextPath()%>/upload/download.action?bean.id=${bean.id}'>${bean.name}</a>"+
					"<img style='cursor:pointer;' type='0' onclick='deletefile(this,\"${bean.id}\");' src='<%=request.getContextPath()%>/common/component/extjs/example/tasks/images/hd-check.gif'/>"+
					"</span>";
		
		//上传文件列表 to do 文件下载列表
		window.parent.document.all("preview${column}").innerHTML=window.parent.document.all("preview${column}").innerHTML+str;
		window.parent.document.all("preview${column}").style.display="block";
		
		//提示用户
		if(thefiles.value!=null&&thefiles.value!='')
			alert("文件上传成功");
	}
}catch(e){
	alert("文件上传失败");
}
</script>
