<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<style type="text/css">
#dragMovediv {
    position:absolute;
    width:100px;//130px;
    height:60px;//85px;
    z-index:1;
    filter:alpha(Opacity=60,style=0);
    opacity:0.6;
    padding: 3px;
    border: 1px dashed #000000;
    z-index:5000;
    bottom:31px;
    font-size:9pt;
               
   
   
}
#dragMovediv ul {
    margin: 1px;
    padding: 0px;
    border-top-width: 1px;
    border-right-width: 1px;
    border-bottom-width: 1px;
    border-left-width: 1px;
    border-top-style: none;
    border-right-style: none;
    border-bottom-style: solid;
    border-left-style: none;
    border-top-color: #000000;
    border-right-color: #000000;
    border-bottom-color: #000000;
    border-left-color: #000000;
    color: #000000;
    background-color:#C0C0C0;//#0faead;
}
#dragMovediv li {
    height:24px;
    padding: 1px;
    line-height: 24px;
}
</style>
<script language="javascript" src="<%=request.getContextPath()%>/common/component/html/dragdiv/dragMoveDiv.js"></script>
 
<div id="dragMovediv" onmousedown="dragMoveDiv.Move('dragMovediv',event,1)" onmouseover="gapplet.focus();">
<span style="cursor:move;">操作区 点击拖动</span>
    <ul>
        <li>
		<applet name="gapplet" code=common.component.applet.GlobeApplet.class width="100px" height="25px">
			<PARAM NAME="archive" VALUE="<%=request.getContextPath()%>/common/component/applet/gapplet.jar">
			<PARAM NAME="PASSWORD" VALUE="">
			<PARAM NAME="PATH" VALUE="c:/">
			<PARAM NAME="BEAN.ID" VALUE="${bean.id}">
			<PARAM NAME="BEAN.CATALOGUE.ID" VALUE="${bean.catalogue.id}">
			<PARAM NAME="BEAN.CATALOGUE.NAME" VALUE="${bean.catalogue.name}">
			<PARAM NAME="EDITURL" VALUE="http://<%=request.getLocalAddr()+":"+request.getLocalPort()+request.getContextPath()%>/cms/http_edit.action">
			<PARAM NAME="SAVEURL" VALUE="http://<%=request.getLocalAddr()+":"+request.getLocalPort()+request.getContextPath()%>/cms/httpdb_save.action">
			<PARAM NAME="UPDATEURL" VALUE="http://<%=request.getLocalAddr()+":"+request.getLocalPort()+request.getContextPath()%>/cms/httpdb_update.action">
			<PARAM NAME="jsessionid" VALUE="<%=request.getSession().getId()%>">
		</applet>
        </li>
    </ul>
</div>
