
var applicationReportAccessoryFn = {
		saveCommitteeReport : function(quotedCodes, applicationCodeNow) {
			 if(!$('#applicationReportAccessory_form').form('validate')){
					return;
				}
			if($('input:radio[name="cause"]:checked').val()==null){
				$.messager.alert('提示','<center>本案提报采购委员会原因必须选择！</center>');
			}else{
			
				var url = 'committeeApply_insertApplicationReportAccessoryAndSubscribeAccessory_2.html?quotedCodes=' + quotedCodes + '&applicationCodeNow=' + applicationCodeNow;

				utils.form("applicationReportAccessory_form").submit(url, null, function() {
				});
				
			}
		},
		
		// 是否审立项目
		doShowManager : function() {
			if($('#isslItem').combobox('getValues')=='是'){
				$('#manager').show();	
				$('#projectManager').validatebox({
					required : true
				});
				$('#projectBudget').validatebox({
					required : true
				});
			}else{
				$('#manager').hide();
				$('#projectManager').validatebox({
					required : false
				});
				$('#projectBudget').validatebox({
					required : false
				});
			}
		},
		// 单选框操作
		doShowAmount : function() {
			$('#ljamount').hide();
			$('#amount').show();
			$('#gzMoney').validatebox({
				required : true
			});
			$('#accumulativeYear').validatebox({
				required : false
			});
			$('#ljMoney').validatebox({
				required : false
			});
			$('#ljMoney').val("");
			$('#accumulativeYear').val("");
		},
		// 单选框操作
		doShowLjAmount : function() {
			$('#ljamount').show();
			$('#amount').hide();
			$('#gzMoney').validatebox({
				required : false
			});
			$('#gzMoney').val("");
			$('#accumulativeYear').validatebox({
				required : true
			});
			$('#ljMoney').validatebox({
				required : true
			});
		},
		// 单选框操作
		doHideAll : function() {
			$('#ljamount').hide();
			$('#amount').hide();
			$('#gzMoney').validatebox({
				required : false
			});
			$('#accumulativeYear').validatebox({
				required : false
			});
			$('#ljMoney').validatebox({
				required : false
			});
			$('#gzMoney').val("");
			$('#ljMoney').val("");
			$('#accumulativeYear').val("");
		}
};
