Ext.namespace('system');
system.CorpSelectPanel = function(config) {
	Ext.applyIf(this, config);
	this.initUIComponents();
	system.CorpSelectPanel.superclass.constructor.call(this);
};
Ext.extend(system.CorpSelectPanel, Ext.Panel, {
	initUIComponents : function() {
		// BEGIN OF CODE GENERATION PARTS, DON'T DELETE CODE BELOW
		Ext.apply(this, {
			items : [{
				frame : "true",
				items : [{
					xtype : "datefield"
				}, {
					xtype : "textfield"
				}, {
					xtype : "button"
				}],
				layout : "form",
				xtype : "form"
			}, {
				frame : "true",
				xtype : "form",
				layout : "form"
			}, {
				frame : "true",
				xtype : "form",
				layout : "form"
			}]
		});
		// END OF CODE GENERATION PARTS, DON'T DELETE CODE ABOVE
	}
});
