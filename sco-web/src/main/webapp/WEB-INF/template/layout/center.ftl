<#compress>

<script type="text/javascript" charset="UTF-8">
	<#include "layout/center.js" />
</script>
<script type="text/javascript" charset="UTF-8">
	var centerTabs;
	var tabsMenu;
	$(function() {
		tabsMenu = $('#tabsMenu').menu({
			onClick : function(item) {
				var curTabTitle = $(this).data('tabTitle');
				var type = $(item.target).attr('type');

				if (type === 'refresh') {
					refreshTab(curTabTitle);
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

	});
	function addTab(node) {
		if (centerTabs.tabs('exists', node.text)) {
			centerTabs.tabs('select', node.text);
		} else {
			if (node.attributes.url && node.attributes.url.length > 0) {
				centerTabs.tabs('add', {
					title : node.text,
					closable : true,
					closed: true,
					iconCls : node.iconCls,
					//href:node.attributes.url,
					content : '<iframe src="' + node.attributes.url + '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>'
					/*,tools : [ {
						iconCls : 'icon-mini-refresh',
						handler : function() {
							refreshTab(node.text);
						}
					} ]*/
				});
				var newtab = centerTabs.tabs('getSelected');
				newtab.data("menunode",node);//缓存TAB对应的菜单节点
			} else {
				/*centerTabs.tabs('add', {
					title : node.text,
					closable : true,
					closed: true,
					iconCls : node.iconCls,
					content : '<iframe src="error/err.jsp" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
					tools : [ {
						iconCls : 'icon-mini-refresh',
						handler : function() {
							refreshTab(node.text);
						}
					} ]
				});*/
			}
		}
	}
	
	function addTabByUrl(title,iconCls,url) {
		centerTabs.tabs('add', {
			title : title,
			closable : true,
			closed: true,
			fit : true,
			iconCls : iconCls,
			//href:node.attributes.url,
			content : '<iframe id="iframe" src="' + url + '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>'
			/*tools : [ {
				handler : function() {
					pubTab.refreshTab(title);
				}
			} ]*/
		});
	}
	
	function refreshTab(title) {
		var tab = centerTabs.tabs('getTab', title);
		centerTabs.tabs('update', {
			tab : tab,
			options : tab.panel('options')
		});
	}
	function isExistsTab(title)
	{
		return centerTabs.tabs('exists', title);
	}
	function activeTab(title)
	{
		if (centerTabs.tabs('exists', title))
			centerTabs.tabs('select', title);
	}
	
</script>

<div id="centerTabs">
	<div title="首页" data-options="border:false,href:'home_showHome_1.html'"></div>
</div>
<div id="tabsMenu" style="width: 120px;display:none;">
	<div type="refresh">刷新</div>
	<div class="menu-sep"></div>
	<div type="close">关闭</div>
	<div type="closeOther">关闭其他</div>
	<div type="closeAll">关闭所有</div>
</div>

</#compress>