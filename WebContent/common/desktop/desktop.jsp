<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="common.util.StringUtil,common.component.IConstants,common.component.permission.entity.Permission,common.component.permission.util.GlobeData,java.util.List"%>
<%@ page import="common.component.permission.util.Node,common.component.permission.entity.Navigate"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>struts2.0</title>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/common/extjs/resources/css/ext-all.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/common/desktop/css/desktop.css" />

    <!-- GC -->
 	<!-- LIBS -->
 	<script type="text/javascript" src="<%=request.getContextPath()%>/common/extjs/adapter/ext/ext-base.js"></script>
 	<!-- ENDLIBS -->

    <script type="text/javascript" src="<%=request.getContextPath()%>/common/extjs/ext-all-debug.js"></script>

    <!-- DESKTOP -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/common/desktop/js/StartMenu.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/common/desktop/js/TaskBar.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/common/desktop/js/Desktop.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/common/desktop/js/App.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/common/desktop/js/Module.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/common/desktop/js/CookieProvider-ex.js"></script>
    
    <script type="text/javascript" src="<%=request.getContextPath()%>/common/desktop/miframe.js"></script>
    
    
    <script type="text/javascript" src="<%=request.getContextPath()%>/common/extjs/build/locale/ext-lang-zh_CN-min.js"></script>
    <script type="text/javascript"><!--
    
	MyDesktop = new Ext.app.App({
		init :function(){
			Ext.QuickTips.init();
		},
	
		getModules : function(){
			return [
	            new MyDesktop.BogusMenuModule()
			];
		},
	
	    // config for the start menu
	    getStartConfig : function(){
	        return {
	            title: '${user.name}',
	            iconCls: 'user',
	            toolItems: [{
	                text:'设置',
	                iconCls:'settings',
	                scope:this
	            },'-',{
	                text:'退出登录',
	                iconCls:'logout',
	                handler : this.logout,
					//href:'<s:url value="/permission/login_logout.action"/>',
	                scope:this
	            }]
	        };
	    },
	    
	    logout: function(){
	    	window.location.href='<s:url value="/permission/login_logout.action"/>';
	    	window.close();
	    }
	});
	
	
    
    var windowIndex = 0;

	MyDesktop.BogusModule = Ext.extend(Ext.app.Module, {
		id:'grid-win',
	    init : function(){
	        this.launcher = {
	            text: 'Window '+(++windowIndex),
	            iconCls:'bogus',
	            handler : this.createWindow,
	            scope: this,
	            windowId:windowIndex
	        }
	    },

	    createWindow : function(src){
	    	var windowId;
	    	var text;
	    	var url;
	    	try{
	    		windowId=src.getAttribute('windowId');
	    		text=src.getAttribute('text');
	    		url=src.getAttribute('URL');
	    	}catch(e){
	    	    windowId=src.windowId;
	    		text=src.text;
	    		url=src.URL;
	    	}
	    
	        var desktop = this.app.getDesktop();
	        var win = desktop.getWindow('bogus'+windowId);
	        
	        var theurl;

	        if(url.substr(0,7)!="http://"){
	        	theurl='<%=request.getContextPath()%>/'+url;
	        }else
	        	theurl=url;

	        if(!win){
	            win = desktop.createWindow({
	                id: 'bogus'+windowId,
	                title:text,
	                width:640,
	                height:480,
					iconCls: 'icon-grid',
					shim:false,
					animCollapse:false,
					constrainHeader:true,
					layout: 'fit',
	                items:{
						xtype: 'box',
				        closable: true,
						border:false,
						frame:true,
				        autoEl: {
				        	tag: 'iframe',
				        	src: theurl, 
				        	cn: [{cls: 'loading-indicator', cn: '加载 ' + text + '...'}]
				        }
					}
					
	            });
	            
	        }
	        win.show();
	    }
	});
    
    
	MyDesktop.BogusMenuModule = Ext.extend(MyDesktop.BogusModule, {
	    init : function(){
	    	<s:set name="deskmenu" value="@common.component.permission.util.GlobeData@getDesktopMenu()"/>
	        this.launcher =${deskmenu}
	    }
	});
	
    --></script>
    <style>
/* shortcuts */
        <%
		for(Permission permission:GlobeData.getMenuTree()){
			if(!StringUtil.isEmptyString(permission.getChecked())){//对于有权限的节点
				Navigate nav=permission.getNavigate();
				if("#".equalsIgnoreCase(nav.getUrl()))
					continue;

		%>

			
			#grid-win-shortcut<%=nav.getId()%> img {
			    width:48px;
			    height:48px;
			    background-image: url(<%=request.getContextPath()%>/common/desktop/images/<%=nav.getIcon()%>);
			    filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='<%=request.getContextPath()%>/common/desktop/images/<%=nav.getIcon()%>', sizingMethod='scale');
			}
	    

		<%
		
			}
		}
        
        %>
    </style>
</head>
<body scroll="no">

<div id="x-desktop">

    <dl id="x-shortcuts">
        
        <%
        int i=0;
        
		for(Permission permission:GlobeData.getMenuTree()){
			if(!StringUtil.isEmptyString(permission.getChecked())){//对于有权限的节点
				Navigate nav=permission.getNavigate();
				if("#".equalsIgnoreCase(nav.getUrl()))
					continue;

		%>

        <dt <%out.print("style='");
        	if(++i%2==1){
        		out.print("margin: 10px 0px 0px 90px;'");
        	}else{
        		out.print("margin: 10px 0px 0px 10px; float: left;'");
        	}%>
         id="grid-win-shortcut<%=nav.getId()%>"  windowId="<%=nav.getId()%>" text="<%=nav.getName()%>" URL="<%=nav.getUrl()%>" iconCls="bogus">
            <a href="#" ><img src="<%=request.getContextPath()%>/common/desktop/images/s.gif" />
            <div><%=nav.getName()%></div></a>
        </dt>

		<%
		
			}
		}
        
        %>

    </dl>
</div>

<div id="ux-taskbar">
	<div id="ux-taskbar-start"></div>
	<div id="ux-taskbuttons-panel"></div>
	<div class="x-clear"></div>
</div>

</body>
</html>
