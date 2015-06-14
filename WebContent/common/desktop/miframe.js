/*
 * @class Ext.ux.ManagedIFrame 
 * Version:  0.46
 * Author: Doug Hendricks 10/2007 doug[always-At]theactivegroup.com
 *
 *
 * <p> An Ext harness for iframe elements.  
  
  Adds Ext.UpdateManager(Updater) support and a compatible 'update' method for 
  writing content directly into an iFrames' document structure.
  
  * Usage:<br>
   * <pre><code>
   * // Harness it from an existing Iframe from markup
   * var i = new Ext.ux.ManagedIFrame("myIframe");
   * // Replace the iFrames document structure with the response from the requested URL.
   * i.load("http://myserver.com/index.php", "param1=1&amp;param2=2");
   * //Notes:  this is not the same as setting the Iframes src property !
   * // Content loaded in this fashion does not share the same document namespaces as it's parent --
   * // meaning, there (by default) will be no Ext namespace defined in it since the document is
   * // overwritten after each call to the update method, and no styleSheets.
  * </code></pre>
  * <br>
   * @cfg {Boolean/Object} autoCreate True to auto generate the IFRAME element, or a {@link Ext.DomHelper} config of the IFRAME to create
   * @cfg {String} html Any markup to be applied to the IFRAME's document content when rendered.
   * @cfg {Object} loadMask An {@link Ext.LoadMask} config or true to mask the iframe while using the update or setSrc methods (defaults to false).
   * @cfg {Object} src  The src attribute to be assigned to the Iframe after initialization (overrides the autoCreate config src attribute)
   * @constructor
   
    * @param {Mixed} el, Config object The iframe element or it's id to harness or a valid config object.
        
 */
 

 Ext.ux.ManagedIFrame = function(){
    		
    	var args=Array.prototype.slice.call(arguments, 0)
    	    ,el = Ext.get(args[0])
    	    ,config = args[0];
    	
    	if(el && el.dom && el.dom.tagName == 'IFRAME'){
    	    config = args[1] || {};
    	}else{
    	   config = args[0] || args[1] || {};
    	   el = config.autoCreate?
    	  	Ext.get(Ext.DomHelper.append(document.body, Ext.apply({tag:'iframe', src:(Ext.isIE&&Ext.isSecure)?Ext.SSL_SECURE_URL:''},config.autoCreate))):null;
    	} 
    	
    	 if(!el || el.dom.tagName != 'IFRAME') return el;
    	 
    	 !!el.dom.name.length || (el.dom.name = el.dom.id); //make sure there is a valid frame name
    	 
         this.addEvents({
    	 	        	 		  
    		   /**
    		     * @event domready
    		     * Fires when the iFrame's Document(DOM) has reach a state where the DOM may be manipulated
    		     * @param {Ext.ux.ManagedIFrame} this
    		     * Note: This event is only available when overwriting the iframe document using the update method.
    		     */
    		  
    		  "domready"       : true,
    		  
    		   /**
		     * @event documentloaded
		     * Fires when the iFrame has reached a loaded/complete state.
		     * @param {Ext.ux.ManagedIFrame} this
    		     */
    		  "documentloaded" : true
    
    	  });
             
         if(config.listeners){
             	this.listeners=config.listeners;
             	Ext.ux.ManagedIFrame.superclass.constructor.call(this);
          }
            
         Ext.apply(el,this);  // apply this class interface ( pseudo Decorator )
         
         el.addClass('x-managed-iframe');    

         if(config.loadMask){
		//el.setStyle('position','relative');
		el.loadMask = Ext.apply({msg:'Loading..',msgCls:'x-mask-loading',maskEl:null},config.loadMask);
		
         }
        
        
         //Hook the Iframes loaded state handler
         if(Ext.isIE){
	     el.dom.onreadystatechange = el.loadHandler.createDelegate(el);
	 } else {
	     el.dom.onload = el.loadHandler.createDelegate(el);
         }
         
                
         if(config.src){
            	el.setSrc(config.src);
         }else{
		el.src = el.dom.src||null;
		var content = config.html || config.content || false;

		if(content){
		 //el.update.defer(100,el,[content]);//allow the iframe to quiesce for Gecko
		 el.update(content);//allow the iframe to quiesce for Gecko
		}
         }    
         return el;
    	
    };	       
 
    Ext.extend(Ext.ux.ManagedIFrame , Ext.util.Observable,
      	{ 
      	  /**
	  * Sets the embedded Iframe src property.

	  * @param {String/Function} url (Optional) A string or reference to a Function that returns a URI string when called
	  * @param {Boolean} discardUrl (Optional) If not passed as <tt>false</tt> the URL of this action becomes the default SRC attribute for
	  * this iframe, and will be subsequently used in future setSrc calls (emulates autoRefresh by calling setSrc without params).
	  * Note:  invoke the function with no arguments to refresh the iframe based on the current defaultSrc value.
	 */
	setSrc : function(url, discardUrl){
	           
	      var src = url || this.src || (Ext.isIE&&Ext.isSecure?Ext.SSL_SECURE_URL:'');
	              
	      this.showMask(); 

	      (function(){
	           this._windowContext = null;
		   
		   this.dom.src = (typeof src == 'function'?src()||'':src);
		   
	      }).defer(100,this);
	               
	      if(discardUrl !== true){ this.src = src; }
	          
        },
      	/*
	  Write(replacing) string content into the IFrames document structure
	 * @param {String} content The new content
         * @param {Boolean} loadScripts (optional) true to also render and process embedded scripts
         * @param {Function} callback (optional) Callback when update is complete. 
    	*/
    	update : function(content,loadScripts,callback){
    	
    	       
    	       loadScripts = loadScripts || this.getUpdateManager().loadScripts || false;
        		
    		this._windowContext = null;
    		content = Ext.DomHelper.markup(content||'');
                             
    		var doc = this.getDocument();
    		if(doc){
    		  this._inUpdate = true;
    		  this.showMask();
    		  doc.open();
    		  
    		  doc.write(loadScripts===true?
    		       content:content.replace(/(?:<script.*?>)((\n|\r|.)*?)(?:<\/script>)/ig, ""));
    		  
    		  //create an 'eval'able context for the iframe and this.execScript
    		  doc.write ('<script type="text/javascript">(function(){'+
    		           "var MSIE/*@cc_on =1@*/;"+ // IE sniff
    		           "parent.Ext.get('"+this.dom.id +"')._windowContext=MSIE?this:{eval:function(s){return eval(s);}}"+
       			   "})();<\/script>" );
        			   
        		   
    		  doc.close();
    		
    		  if(!!content.length){
    		     this.checkDOM(false,callback); 
    		  } else if(callback){
    		  	callback();
    		  }
    		}
    
    		return this;
    	},
    	_windowContext : null,
    	/*
	  Return the Iframes document object
    	*/
    	getDocument:function(){
    		return this.getWindow().document;
    	},
    	
    	/*
	  Return the Iframes window object
    	*/
    	getWindow:function(){
    	        var dom= this.dom;
    		return dom?dom.contentWindow||window.frames[dom.name]:window;
    	},
    	
    	/*
	  Print the contents of the Iframes (if we own the document)
    	*/
    	print:function(){ 
    	    if(this._windowContext){
    	      try{
    		
    		var win = this.getWindow();
    		
    		if(Ext.isIE){ win.focus(); } 
    		win.print();
    	      } catch(ex){
    	    	 throw 'print: invalid document context';
    	      }
    	    }	    
    	},
    	//private
    	destroy:function(){
   	             
    	    this.removeAllListeners();
    	    
    	    if(this.dom){
		     //unHook the Iframes loaded state handler
		     this.dom[Ext.isIE?'onreadystatechange':'onload']=null;
		     //IE Iframe cleanup
		     if(this.dom.src){
			this.dom.src = 'javascript:false';
		     }
	    }    	     
    	    
    	    	    	  
    	}
    	/*
    	  Execute a javascript code block(string) within the context of the Iframes window object.
    	  * @param {String} block A valid ('eval'able) script source block.
    	  * <p> Note: execScript will only work after a successful update (document.write);
    	*/
    	,execScript: function(block){
    	    if(this._windowContext){
    	        return this._windowContext.eval( block );
    	    } else {
    	    	throw 'execScript:no script context';
    	    }
    	}
    	
    	// Private 
    	,showMask: function(){
    	
    	    if(this.loadMask){
    	       var lmask = this.loadMask;
    	       lmask.masker || (lmask.masker = Ext.get(lmask.maskEl||this.wrap({tag:'div',style:{position:'relative'}})));
	       lmask.masker.mask(lmask.msg,lmask.msgCls);
            }
    	
    	}
    	
    	// Private 
	,hideMask: function(){
	    	
	      if(this.loadMask  && this.loadMask.masker && (!!this.dom.src.length || this._inUpdate)){
		    this.loadMask.masker.unmask();
		}
	    	
    	}
    	
    	/* Private 
	  Evaluate the Iframes readyState/load event to determine its 'load' state,
	  and raise the 'documentloaded' event when applicable.
    	*/
    	,loadHandler : function(e){
    	     var rstatus = this.dom.readyState || e.type;
    	     switch(rstatus){
    	        
    	        case 'loading':
    	           this.showMask();
    	           break;
    	        case 'load':
    	        case 'complete':
    	           this.fireEvent("documentloaded",this);
    	           this.hideMask();
                   this._inUpdate = false;
    	           break;
    	        default:
    	      }
    	      //Ext.log(rstatus+':'+this.dom.src);
    	      //console.log(Ext.apply({},e));
    	   
    	}
    	/* Private 
	  Poll the Iframes document structure to determine DOM ready state,
	  and raise the 'domready' event when applicable.
    	*/
    	,checkDOM : function(win,callback){
    	        //initialise the counter
    		var n = 0 
    		   ,win = win||this.getWindow()
    		   ,manager = this;
    		
      		var t =function() //DOM polling
    			{
    				var domReady=false;
    				//if DOM methods are supported, and the body element exists
    				//(using a double-check including document.body, for the benefit of older moz builds [eg ns7.1] 
    				//in which getElementsByTagName('body')[0] is undefined, unless this script is in the body section)
    				
    				domReady  = (win.document && typeof win.document.getElementsByTagName != 'undefined' 
    				    && ( win.document.getElementsByTagName('body')[0] != null || win.document.body != null ));
    				
    				//if the timer has reached 70 (timeout after ~10.5 seconds)
    				//in practice, shouldn't take longer than 7 iterations [in kde 3 
    				//in second place was IE6, which takes 2 or 3 iterations roughly 5% of the time]
    				if(n++ < 70 && !domReady)
    				{
    					//try again
    					t.defer(10);
    					return;
    				}
    				if(callback)callback();
    				manager.fireEvent("domready",manager); //fallback
    		         };
    		t();
    	   }
 });
 
 /*
  * @class Ext.ux.ManagedIFramePanel 
  * Version:  0.15
  *     enhanced loadMask.maskEl support to support panel element names ie: 'body, bwrap' etc
  * Version:  0.13
  *     Added loadMask support and refactored domready/documentloaded events
  * Version:  0.11
  *     Made Panel state-aware.
  * Version:  0.1
  * Author: Doug Hendricks 12/2007 doug[always-At]theactivegroup.com
  *
  * 	
 */
 Ext.ux.ManagedIframePanel = Ext.extend(Ext.Panel, {
    /**
     * @cfg {String/Object/Function} bodyCfg
     Custom bodyCfg used to embed the ManagedIframe.     
    */
    bodyCfg:{tag:'div'
      ,cls:'x-panel-body'
      ,children:[{tag:'iframe',frameBorder:0,width:'100%',height:'100%',cls:'x-managed-iframe'}]
    },
    
    /**
         * Cached Iframe.src url to use for refreshes. Overwritten every time setSrc() is called unless "discardUrl" param is set to true.
         * @type String/Function (which will return a string URL when invoked)
     */
    defaultSrc:null,

    /**
         * @cfg {String/Object/Function} iframeStyle
         * Custom CSS styles to be applied to the body's ux.ManagedIframe element in the format expected by {@link Ext.Element#applyStyles}
         * (defaults to {overflow:'auto'}).
     */
    iframeStyle: {overflow:'auto',display:'block'},
    
    loadMask : false,
    
    animCollapse:false, 
    
    initComponent : function(){
        
         Ext.ux.ManagedIframePanel.superclass.initComponent.call(this); 
      
         this.addEvents({documentloaded:true, domready:true});
         
         if(this.defaultSrc){
 	      this.on('render', this.setSrc.createDelegate(this,[this.defaultSrc],0), this, {single:true});
 	      
 	      }
         },
         
      // private
     onDestroy : function(){
         if(this.iframe){
             delete this.iframe.ownerCt;
             Ext.destroy(this.iframe);
         }
                  
         Ext.ux.ManagedIframePanel.superclass.onDestroy.call(this);
     },   
    
     // private
      onRender : function(ct, position){
        
        Ext.ux.ManagedIframePanel.superclass.onRender.call(this, ct, position); 
        
        if(this.iframe = this.body.child('iframe.x-managed-iframe')){
            if(this.loadMask){
	       this.loadMask = Ext.apply({msg:'Loading..',msgCls:'x-mask-loading',maskEl:this.body},this.loadMask);
            }
            this.iframe = new Ext.ux.ManagedIFrame(this.iframe,
                              {loadMask:this.loadMask});
        
            this.iframe.ownerCt = this;  
            this.relayEvents(this.iframe, ["documentloaded","domready"]);
            
            if(this.iframeStyle){
	      this.iframe.applyStyles(this.iframeStyle);
            }
            
           
        }
      },  
        // private
     afterRender : function(){  
         if(this.html && this.iframe){
              this.iframe.update(typeof this.html == 'object' ?
                             Ext.DomHelper.markup(this.html) :
                             this.html);
              delete this.html;
          }
                              
          Ext.ux.ManagedIframePanel.superclass.afterRender.call(this); 
         
     },
       
     
         /**
          * Sets the embedded Iframe src property.
          
          * @param {String/Function} url (Optional) A string or reference to a Function that returns a URI string when called
          * @param {Boolean} discardUrl (Optional) If not passed as <tt>false</tt> the URL of this action becomes the default URL for
          * this panel, and will be subsequently used in future setSrc calls.
          * Note:  invoke the function with no arguments to refresh the iframe based on the current defaultSrc value.
         */
     setSrc : function(url, discardUrl){
           
         var src = url || this.defaultSrc || (Ext.isIE&&Ext.isSecure?Ext.SSL_SECURE_URL:'');
              
          if(this.rendered && this.iframe){ 
              
              this.iframe.setSrc(src,discardUrl);
              
           }
               
          if(discardUrl !== true){ this.defaultSrc = src; }
          this.saveState();
	             
          
     },
     
     //Make it state-aware
     getState: function(){

        return Ext.apply(Ext.ux.ManagedIframePanel.superclass.getState.call(this) || {},
             {defaultSrc  :(typeof this.defaultSrc == 'function')?this.defaultSrc():this.defaultSrc});
     }
 
}); 

Ext.reg('iframepanel', Ext.ux.ManagedIframePanel);

Ext.ux.ManagedIFramePortlet = Ext.extend(Ext.ux.ManagedIframePanel, {
     anchor: '100%',
     frame:true,
     collapsible:true,
     draggable:true,
     hideMode:'display',
     cls:'x-portlet'
 });
Ext.reg('iframeportlet', Ext.ux.ManagedIFramePortlet);