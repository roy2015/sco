var totalcostanalysisSetFormFn = {
		
	// 是否察看所有报价数量
	doShowBjsl : function() {
		if($('#allbjsl').combobox('getValues')=='否'){
			$('#txbjsl').show();	
			$('#zckbjsl').validatebox({
				required : true
			});
		}else{
			$('#txbjsl').hide();
			$('#zckbjsl').validatebox({
				required : false
			});
		}
	}
	
};