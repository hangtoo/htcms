<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="Content-Language" content="en-us" />
	<meta http-equiv="imagetoolbar" content="no" />

<script type="text/javascript" src="<%=request.getContextPath()%>/common/dtree/dtree.js"></script>
<link type="text/css" rel="stylesheet" media="all" href="<%=request.getContextPath()%>/css/base.css" />
<link type="text/css" rel="stylesheet" media="all" href="<%=request.getContextPath()%>/common/dtree/dtree.css" />
<link type="text/css" rel="stylesheet" media="all" href="<%=request.getContextPath()%>/common/layout/lgblue/images/style.css" />

</head>

<body>
<div class="content">
	<div id="toph"></div>
	
	<div id="header">
		<div class="rside">
			<div class="citation">
				<h2>Lorem Ipsum</h2>
				Curabitur interdum molestie arcu. Cum sociis natoque penatibus et magnis dis.</div>
			</div>
		<div class="lside">
			<div class="title">[your logo here]<h2>YOUR COMPANY</h2>Your best Slogan Here</div>
		</div>
	</div>
	
	<div id="main">
		<div class="center">
			
			<h2><a href="#">LGBlue Free Css Template</a></h2>
			<h3>A SIMPLE CSS / XHTML TEMPLATE</h3>
			<div class="img"><img src="images/finance.jpg" alt="Finance" /></div>
			Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Cras sit amet nunc at arcu feugiat feugiat. Suspendisse potenti. Fusce mi velit, scelerisque vel, <a href="#">congue quis</a>, congue vitae, est. Vivamus et nisi nec purus consectetuer porta. Suspendisse potenti. Pellentesque egestas arcu. Aenean eleifend dignissim urna. Morbi dignissim. In faucibus porttitor mauris. Vestibulum eget lectus. Proin sapien. Sed dapibus dui a ante. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aliquam eu nunc. Vivamus sed enim. Aenean vulputate commodo purus. Ut tincidunt suscipit purus. Ut commodo nisi id velit. Nulla a ipsum id elit pharetra cursus. Integer lacinia pulvinar tortor.
				<p class="date">Posted by David  <img src="images/comment.gif" alt="" /> <a href="#">Comments (3)</a> <img src="images/timeicon.gif" alt="" /> 21.02.</p><br />
			<h2><a href="#">Lorem Ipsum</a></h2>
			<h3>USE IT FOR FREE WITH SNEWS OR NOT! ;)</h3>
			<div class="img"><img src="images/laptop1.jpg" alt="laptop" /></div>
			Nunc vitae odio feugiat erat sodales fermentum. Nulla semper. Donec sem mi, tempus vitae, ornare at, facilisis quis, nunc. Vestibulum bibendum dignissim lectus. Fusce convallis risus in elit. Proin iaculis, massa rhoncus malesuada rhoncus, est dui elementum nulla, a tristique magna leo vel sem. Aliquam ultricies,<a href="#"> diam at mattis imperdiet, mi mi blandit orci</a>, sit amet viverra tellus lorem in libero. Maecenas vitae sapien. Nulla porttitor elementum arcu. Vestibulum pharetra lacus rhoncus pede. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos hymenaeos. Duis mollis turpis vitae neque. Phasellus vel augue eu ipsum dictum viverra. Quisque ut est vitae augue scelerisque semper. Donec quis dui. Cras vestibulum mauris vel nunc.
			<p class="date">Posted by Jack <img src="images/more.gif" alt="" /> <a href="#">Read more</a> <img src="images/comment.gif" alt="" /> <a href="#">Comments (15)</a> <img src="images/timeicon.gif" alt="" /> 17.01.</p><br />
			<br />
			<div class="boxads">Curabitur interdum molestie arcu. Cum sociis natoque penatibus et <a href="#">magnis dis parturient</a> montes, nascetur ridiculus mus. Vivamus dignissim lobortis felis. Donec a urna. Fusce orci. Nullam id felis. Nulla posuere ante sed tellus. Ut pharetra massa vel nunc. Proin nulla. Nam ligula metus, fringilla eu, viverra non, sagittis nec, ante.</div>
		</div>
		
		<div class="leftmenu">
			<!-- nav -->
			<div class="nav">
			
	<p><a href="javascript: d.openAll();">1</a> | <a href="javascript: d.closeAll();">2</a></p>
		<script type="text/javascript">
		<!--
		try{
			d = new dTree('d','<%=request.getContextPath()%>/common/dtree/');
			
			//d.config.target = "iframetarget";
	
			<s:iterator id="treeele" value="tree">
				<s:set name="thepid" value="#treeele.parent.id"/>
				<s:if test="#thepid!=null">
					d.add(<s:property value="id"/>,<s:property value="parent.id"/>,'<s:property value="id"/>_<s:property value="name"/>','<s:property value="url"/>');
				</s:if>
				<s:else>
					d.add(<s:property value="id"/>,-1,'<s:property value="name"/>','<s:property value="url"/>');
				</s:else>
			</s:iterator>
			document.write(d);
			d.openTo(1,true);//
		}catch(e){
			document.write(e);
		}
		//-->
	</script>
			
				<ul>
					<li><a href="#">Home</a></li>
					<li><a href="#">Articles</a></li>
					<li><a href="#">Gallery</a></li>
					<li><a href="#">Affiliates</a></li>
					<li><a href="#">Archives</a></li>
					<li><a href="#">About</a></li>
					<li><a href="#">Contact</a></li>
				</ul>
			</div>
			<!-- padding -->
			<div class="padding">
				
				<!-- Search -->
				<h2>Search</h2>
				<form action="#">
					<div class="search">			
					<input type="text" value="" class="text" />
					<input type="submit" value="Go" class="searchbutton" /><br />
					</div>
				</form>
				<br />
			
				<!-- sNews -->
				<hr />
				<h2>sNews</h2>
				<h3>WHY SHOULD YOU USE IT?</h3>
				sNews is both template independant, and standards compliant, and having only one file means you can redesign your site whenever you want.
				
				<!-- link -->
				<hr /><br />
				<h2>Links</h2>
				<div class="links">
				<img src="images/arrow.gif" alt="" /> <a href="http://www.solucija.com">solucija.com</a> <br />
				<img src="images/arrow.gif" alt="" /> <a href="http://www.free-css-templates.com">free-css-templates.com</a> <br />
				</div>
			
			</div>
			
		</div>
		
	</div>
	<br />&nbsp;<br />
	<!-- footer -->
	<div id="footer">Copyright &copy; 2006 LGBlue | Design: <a href="http://www.free-css-templates.com">David Herreman </a> |
		<a href="#">Contact</a> | <a href="http://jigsaw.w3.org/css-validator/check/referer">CSS</a> and <a href="http://validator.w3.org/check?uri=referer">XHTML</a> | <a href="http://www.solucija.com">Solucija.com</a> | <a href="#">Login</a>
	</div>

</div>
	
	

</body>
</html>
