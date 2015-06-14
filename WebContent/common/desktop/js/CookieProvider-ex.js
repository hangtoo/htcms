/*
 * Ext JS Library 3.0 Pre-alpha
 * Copyright(c) 2006-2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */

	Ext.ux.CookieProvider = function(namespace,config){
		this.namespace=namespace;
	    Ext.ux.CookieProvider.superclass.constructor.call(this, config); 
	    
	    // private
	    this.readCookies = function(){
	        var cookies = {};
	        var c = document.cookie + ";";
	        var re = /\s?(.*?)=(.*?);/g;
	    	var matches;
	    	while((matches = re.exec(c)) != null){
	            var name = matches[1];
	            var value = matches[2];
	            if(name && name.substring(0,this.namespace.length) == this.namespace){
	                cookies[name.substr(this.namespace.length)] = this.decodeValue(value);
	            }
	        }
	        return cookies;
	    },
	
	    // private
	    this.getCookie = function(_name){
	        var c = document.cookie + ";";
	        var re = /\s?(.*?)=(.*?);/g;
	    	var matches;
	    	while((matches = re.exec(c)) != null){
	            var name = matches[1];
	            var value = matches[2];
	            if(name && name.substring(0,this.namespace.length) == this.namespace){
	            	if(_name==name.substr(this.namespace.length))
		            	return this.decodeValue(value);
	            }
	        }
	        return null;
	    },
	    
	    // private
	    this.setCookie = function(name, value){
	        document.cookie = this.namespace+ name + "=" + this.encodeValue(value) +
	           ((this.expires == null) ? "" : ("; expires=" + this.expires.toGMTString())) +
	           ((this.path == null) ? "" : ("; path=" + this.path)) +
	           ((this.domain == null) ? "" : ("; domain=" + this.domain)) +
	           ((this.secure == true) ? "; secure" : "");
	    },
	
	    // private
	    this.clearCookie = function(name){
	        document.cookie = this.namespace + name + "=null; expires=Thu, 01-Jan-70 00:00:01 GMT" +
	           ((this.path == null) ? "" : ("; path=" + this.path)) +
	           ((this.domain == null) ? "" : ("; domain=" + this.domain)) +
	           ((this.secure == true) ? "; secure" : "");
	    }	    
	    
	};
	
	Ext.extend(Ext.ux.CookieProvider,Ext.state.CookieProvider);
	
	Ext.state.Manager.setProvider(new Ext.ux.CookieProvider({ 
	     // use url pathname as namespace for get/set state in iframes 
	    namespace: function(){
	        return window.location.pathname.replace(/[^\w]/g, 'EXT')+'_'; 
	    }()   
	})); 
	
	

	
	
