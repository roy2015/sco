var centerTabs;
var tabsMenu;

$(document).ready(function() {
	tabsMenu = $('#tabsMenu').menu({
		onClick : function(item) {
			var curTabTitle = $(this).data('tabTitle');
			var type = $(item.target).attr('type');

			if (type === 'refresh') {
				pubTab.refreshTab(curTabTitle);
				return;
			}

			if (type === 'close') {
				var t = centerTabs.tabs('getTab', curTabTitle);
				if (t.panel('options').closable) {
					centerTabs.tabs('close', curTabTitle);
				}
				return;
			}

			var allTabs = centerTabs.tabs('tabs');
			var closeTabsTitle = [];

			$.each(allTabs, function() {
				var opt = $(this).panel('options');
				if (opt.closable && opt.title != curTabTitle && type === 'closeOther') {
					closeTabsTitle.push(opt.title);
				} else if (opt.closable && type === 'closeAll') {
					closeTabsTitle.push(opt.title);
				}
			});

			for ( var i = 0; i < closeTabsTitle.length; i++) {
				centerTabs.tabs('close', closeTabsTitle[i]);
			}
		}
	});

	centerTabs = $('#centerTabs').tabs({
		fit : true,
		border : false,
		onContextMenu : function(e, title) {
			e.preventDefault();
			tabsMenu.menu('show', {
				left : e.pageX,
				top : e.pageY
			}).data('tabTitle', title);
		},
		/*选中某TAB时，功能导航菜单自动选中相应项*/
		onSelect : function(title,index) {
			var tab=$(this).tabs('getTab',index);
			var node=tab.data("menunode");//缓存的TAB对应的菜单节点
			if(node)$('#tree').tree("select",node.target);
		}
	});
});
	
	
var pubTab = {
	addTab : function(node, needRef) {
		if (centerTabs.tabs('exists', node.text)) {
			centerTabs.tabs('select', node.text);
			if (needRef)
			{
				var tab = centerTabs.tabs('getSelected');
				tab.panel('options').content = '<iframe id="iframe' + node.id + '" src="' + node.attributes.url + '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>';
				
				centerTabs.tabs('update', {
					tab : tab,
					options : tab.panel('options')
				});
			}
		} else {
			if (node.attributes.url && node.attributes.url.length > 0) {
				
				centerTabs.tabs('add', {
					title : node.text,
					closable : true,
					closed: true,
					fit : true,
					iconCls : node.iconCls,
					//href:node.attributes.url,
					content : '<iframe id="iframe' + node.id + '" src="' + node.attributes.url + '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
					tools : [ {
						iconCls : 'icon-mini-refresh',
						handler : function() {
							pubTab.refreshTab(node.text);
						}
					} ]
				});
			} else {
			}
		}
	},
	addTabByUrl : function(title,iconCls,url) {
		centerTabs.tabs('add', {
			title : title,
			closable : true,
			closed: true,
			fit : true,
			iconCls : iconCls,
			//href:node.attributes.url,
			content : '<iframe id="iframe" src="' + url + '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>'
			
//			,tools : [ {
//				iconCls : 'icon-mini-refresh',
//				handler : function() {
//					pubTab.refreshTab(title);
//				}
//			} ]
		});
	},
	activeTab : function(title)
	{
		if (centerTabs.tabs('exists', title))
			centerTabs.tabs('select', title);
	},
	
	refreshTabURL : function(url) {
		var tab = centerTabs.tabs('getSelected');
		tab.panel('options').content = '<iframe id="iframe' + node.id + '" src="' + url + '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>';
		centerTabs.tabs('update', {
			tab : tab,
			options : tab.panel('options')
		});
	},
	
	refreshTab : function(title) {
		var tab;
		if (title)
			tab = centerTabs.tabs('getTab', title);
		else
		 	tab = centerTabs.tabs('getSelected');
		if (!tab) return;
		
		centerTabs.tabs('update', {
			tab : tab,
			options : tab.panel('options')
		});
	},
	
	// 
	refreshTab2 : function(title) {
		var tab;
		if (title)
			tab = centerTabs.tabs('getTab', title);
		else
		 	tab = centerTabs.tabs('getSelected');
		if (!tab) return;

		tab.panel('refresh', tab.panel('options').href);
	},
	
	
	// evan.zhao 新增无需href刷新tab
	refreshCurrTab : function() {
		var tab = $("#centerTabs").tabs("getSelected");
		var options = tab.panel("options");
		tab.panel("refresh", options.href);
	},
	// evan.zhao新增
	showTab:function(title, iconCls, href){
		var exists = $('#centerTabs').tabs('exists', title);
		if (exists) {
			$('#centerTabs').tabs("close", title);
		}
		pubTab.addTabByUrl(title, iconCls, href);
	},
	// evan.zhao
	closeCurrTab:function(){
		var tab = $("#centerTabs").tabs("getSelected");
		var idx = $("#centerTabs").tabs("getTabIndex", tab);
		$("#centerTabs").tabs("close", idx);
	},
	//gavillen.zhou
	closeTabByTitle : function(){
		for(var t = 0; t < arguments.length; t++){
			var exists = $('#centerTabs').tabs('exists', arguments[t]);
			if(exists){
				$('#centerTabs').tabs('close', arguments[t]);
			}
		}
	},
	closeRestTab : function(){

		var tab = $("#centerTabs").tabs("getSelected");
		var options = tab.panel("options");
		var curTabTitle = options.title;

		var allTabs = centerTabs.tabs('tabs');

		$.each(allTabs, function() {
			var opt = $(this).panel('options');
			if (opt.closable && opt.title != curTabTitle) {
				$('#centerTabs').tabs('close', opt.title);
			}
		});
	},
	closeAllTab : function(){
		var allTabs = $('#centerTabs').tabs("tabs");
		// Evan.Zhao修改 ----》begin
		var size = allTabs.length;
		for (var i = size; i > 0; i--) {
			$('#centerTabs').tabs('close', $('#centerTabs').tabs('getTabIndex', allTabs[i-1]));
		}
		// Evan.Zhao修改 ----》end
//		$.each(allTabs, function() {
//			var opt = $(this).panel('options');
//			if (opt.closable) {
//				$('#centerTabs').tabs('close', opt.title);
//			}
//		});
	},
	closeTab : function(title) {
		centerTabs.tabs('close', title);
	}
};
