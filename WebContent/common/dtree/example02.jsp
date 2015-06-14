<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/dtree/dtree.js"></script>
<link type="text/css" rel="stylesheet" media="all" href="<%=request.getContextPath()%>/css/base.css" />	

<div id="leftmenu">			
	<p><a href="javascript: d.openAll();">open all</a> | <a href="javascript: d.closeAll();">close all</a>| <a href="javascript: d.openTo(1,true);">open to: Node 1 </a></p>
		<script type="text/javascript">
		<!--

		d = new dTree('d','<%=request.getContextPath()%>/common/dtree/');
		
		//d.config.target = "iframetarget";

		d.add(0,-1,'My example tree');
		d.add(1,0,'Node 1','example01.html');
		d.add(2,0,'Node 2','example01.html');
		d.add(3,1,'Node 1.1','example01.html');
		d.add(4,0,'Node 3','example01.html');
		d.add(5,3,'Node 1.1.1','example01.html');
		d.add(6,5,'Node 1.1.1.1','http://www.sina.com.cn');
		d.add(7,0,'Node 4','example01.html');
		d.add(8,1,'Node 1.2','example01.html');
		d.add(9,0,'My Pictures','example01.html','Pictures I\'ve taken over the years','','','<%=request.getContextPath()%>/common/dtree/img/imgfolder.gif');
		d.add(10,9,'The trip to Iceland','example01.html','Pictures of Gullfoss and Geysir');
		d.add(11,9,'Mom\'s birthday','example01.html');
		d.add(12,0,'Recycle Bin','example01.html','','','<%=request.getContextPath()%>/common/dtree/img/trash.gif');
		document.write(d);

		//-->
	</script>
</div>