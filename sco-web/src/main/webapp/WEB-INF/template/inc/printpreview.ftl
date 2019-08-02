<script type="text/javascript" src="${contextPath}js/pdfobject.js?${App_Version}" charset="utf-8"></script>

<script type="text/javascript" >
    	$(document).ready(function() {
			$('#printPreviewDlg').dialog({
           		onOpen:function()
            					{
                					$(this).dialog('setTitle', '打印预览');
        						}  
     		});
		});
	
		var printerScript = {
			print : function(url)
			{
				window.location = url;
			},
			printView : function(url)
			{
				new PDFObject({ url: url,
				                pdfOpenParams: {
									navpanes: 1,
									view: "FitV",
									pagemode: "thumbs"
								}
                              }).embed("pdf_preview_div");
				$('#printPreviewDlg').dialog('open');
			}
		};
    </script>
<div id="printPreviewDlg" closed="true"  class="easyui-dialog" style="width:860px;height:460px;overflow: hidden;"
            data-options="
            	closable:true,
            	cache:false,
            	maximizable:true,
            	modal:true
        	">
        	<div id="pdf_preview_div" style="width:100%;height:100%;"></div>
</div>