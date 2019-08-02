
var wlInfoNewFn = {
		
	//加载数据
	loadData:function() {
		 $.post("wlInfoNew_showWlInfoNewFtl_1.html", {
			 applicationCode : '${applicationCode}',
			 intentionAndSupplierCodes : '${intentionAndSupplierCodes}' 
		}, function(data) {
			$("#newDataForm").html(data);
			$(".easyui-numberbox").numberbox({  
			    min:0,  
			    precision:2
			}); 
		}, "html");
	},
		
	//保存
	save:function(){
		var flag = false;
		$("input[name=selectData]").each(function(i,obj){
			if($(obj).attr("checked")=="checked"){
				flag = true;
				return ;
			}
		});
		if(!flag) {
			$.messager.alert("提示", "请勾选需要保存的记录");
			return;
		}
		
		showLoading();
		$("#newWlInfoForm").form("submit", {
			url : "wlInfoNew_insertWlInfoNew_2.html",
			success : function(data) {
				var json = $.parseJSON(data);
				var msg = json.msg;
				if (json.success) {
					$.messager.show({
						title : '提示',
						msg : msg
					});
					wlInfoNewFn.loadData();
				}else {
					$.messager.alert('提示', '保存数据出错');
				}
				$('.msg_bg').remove();
			}
		});
	},	
		
	close:function() {
		parent.pubTab.closeCurrTab();
	}

};