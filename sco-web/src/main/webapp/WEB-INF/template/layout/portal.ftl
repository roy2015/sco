<#compress>
<!--
<script type="text/javascript" charset="utf-8">
	var portal;
	var panels;
	$(function() {
		
		panels = [ /*{
			id : 'p1',
			title : 'about',
			height : 200,
			collapsible : true,
			href:''
		}, {
			id : 'p2',
			title : 'link',
			height : 200,
			collapsible : true,
			href:''
		}, {
			id : 'p3',
			title : '修复数据库',
			height : 200,
			collapsible : true,
			href:''
		}, {
			id : 'p4',
			title : '说明',
			height : 200,
			collapsible : true,
			content : ''
		}, {
			id : 'p5',
			title : '说明2',
			height : 200,
			collapsible : true,
			href:''
		} , {
			id : 'p6',
			title : '其他信息',
			height : 200,
			collapsible : true,
			href:''
		}*/ ];

		portal = $('#portal').portal({
			border : false,
			fit : true,
			onStateChange : function() {
				$.cookie('portal-state', getPortalState(), {
					expires : 7
				});
			}
		});
		var state = $.cookie('portal-state');
		if (!state) {
			state = 'p1,p2,p3:p4,p5,p6';/*冒号代表列，逗号代表行*/
		}
		addPanels(state);
		portal.portal('resize');

	});

	function getPanelOptions(id) {
		for ( var i = 0; i < panels.length; i++) {
			if (panels[i].id == id) {
				return panels[i];
			}
		}
		return undefined;
	}
	function getPortalState() {
		var aa=[];
		for(var columnIndex=0;columnIndex<2;columnIndex++) {
			var cc=[];
			var panels=portal.portal('getPanels',columnIndex);
			for(var i=0;i<panels.length;i++) {
				cc.push(panels[i].attr('id'));
			}
			aa.push(cc.join(','));
		}
		return aa.join(':');
	}
	function addPanels(portalState) {
		var columns = portalState.split(':');
		for (var columnIndex = 0; columnIndex < columns.length; columnIndex++) {
			var cc = columns[columnIndex].split(',');
			for (var j = 0; j < cc.length; j++) {
				var options = getPanelOptions(cc[j]);
				if (options) {
					var p = $('<div/>').attr('id', options.id).appendTo('body');
					p.panel(options);
					portal.portal('add', {
						panel : p,
						columnIndex : columnIndex
					});
				}
			}
		}
	}
</script>

<div id="portal" style="position:relative">
	<div></div>
	<div></div>
</div>
-->


<html>
<body >
</body>
</html>
</#compress>