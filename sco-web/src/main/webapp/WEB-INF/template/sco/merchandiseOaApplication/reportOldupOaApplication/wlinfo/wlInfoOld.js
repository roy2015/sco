
var wlInfoOldFn = {
		
	//加载数据
	loadData:function() {
		 $.post("wlInfoOld_showWlInfoOldFtl_1.html", {
			 applicationCode : '${applicationCode}',
			 intentionAndSupplierCodes : '${intentionAndSupplierCodes}' 
		}, function(data) {
			$("#oldDataForm").html(data);
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
		$("#oldWlInfoForm").form("submit", {
			url : "wlInfoOld_insertWlInfoOld_2.html",
			success : function(data) {
				var json = $.parseJSON(data);
				var msg = json.msg;
				if (json.success) {
					$.messager.show({
						title : '提示',
						msg : msg
					});
					wlInfoOldFn.loadData();
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