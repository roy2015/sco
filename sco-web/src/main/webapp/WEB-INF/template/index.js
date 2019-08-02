$(document).ready(function() {
	  $('#publicMessageDlg').dialog({
			onOpen:function()
    					{
        					$(this).dialog('setTitle', '重要通知');
						}  
		});
		
		indexScript.refreshFramePanel();
		/*$('body').everyTime('1mins','Request_Notify',function(){
				indexScript.refreshFramePanel();
		},0,true);*/
		
		indexScript.showPublicMessageDlg();
});

var indexScript = {
		refreshFramePanel : function()
		{
			$.post("sysParam_systemMessage_2.html", {paramName:'SYSTEM_MESSAGE'}, 
					function (data, textStatus){
						if (data.rows != "")
						  $('#frame_mainPanel').panel('setTitle', 'SCO成本分析系统<div style="  position: absolute;top: 4px;right: 0;">系统消息：【<font color=red id=pubMsg>' + data.rows + '</font>】</div>');
					},
					'json'
			);
		},
		
		showPublicMessageDlg : function()
		{
			$.post("sysParam_systemMessage_2.html", {paramName:'SHOW_PUBLIC_HTML'}, 
					function (data, textStatus){
						if (data.rows != "")
						{
							$('#publicMessageDlg').dialog('open');
							$('#publicMessageDlg').dialog('refresh', data.rows);
						}
					},'json');
		}
};