
var wlInfoFn = {
		saveWlInfo:function(){
			var rows = $("#wlInfo_grid").datagrid("getSelections");
			if(rows.length < 1) {
				window.parent.$.messager.alert("提示", "请勾选至少一条记录");
				return;
			}
			
			//验证
		//	var msg = "";
			$(rows).each(function(i, obj){
				var i = $('#wlInfo_grid').datagrid('getRowIndex', obj);//当前数据所在行
			//	$('#wlInfo_grid').datagrid('endEdit', editIndex);//结束在编辑的行
				// 获取编辑
			//	msg += appScheNonFoodFn.validateData(obj, i)+"\r\n";
				obj.applicationCode='${applicationCodeNow}';
				obj.purchaseCount=$('#purchaseCount'+i).val();
				obj.contractPrice=$('#contractPrice'+i).val();
				obj.purchaseMoney=$('#purchaseMoney'+i).val();
				obj.invoiceType=$('#invoiceType'+i).val();
				obj.accessorySapCode=$('#accessorySapCode'+i).val();
				obj.supplierSapCode=$('#supplierSapCode'+i).val();
				obj.sjyjSpecification=$('#sjyjSpecification'+i).val();
			});
			$('#wlInfo_grid').datagrid('acceptChanges');
			//是否通过验证
			/*if($.trim(msg).length > 0) {
				$("#errMsg").html(msg);
				$("#msgDlg").window('open');//打开窗口
				return;
			}*/
			
			var url='committeeApply_insertWlInfo_2.html';
			$.post(url,
					{
					rows : JSON.stringify(rows),
					applicationCodeNow:'${applicationCodeNow}',
					quotedCodes : '${quotedCodes}'
					}, function(data) {
						if (data.success) {
							$.messager.show({
								title:'提示',
								msg:'保存成功',
								timeout:4000,
								showType:'slide'
							});
							$('#wlInfo_grid').datagrid('load');
							$('#wlInfo_grid').datagrid('clearSelections');
						} else {
							$.messager.alert("提示", data.msg);
						}
			},"json");
		},
		 testaler:function(index,contractPrice){
			 var count=$('#purchaseCount'+index).val();
			 var price=$('#contractPrice'+index).val();
			 if(isNaN(count)){
				 $('#purchaseCount'+index).val('');
				 $('#purchaseMoney'+index).val(0);
				 $.messager.alert('提示', '<center>实际采购数量必须是数字</center>');
				 return;
			 }else if(isNaN(price)){
				 $('#contractPrice'+index).val('');
				 $('#purchaseMoney'+index).val(0);
				 $.messager.alert('提示', '<center>合同进价必须是数字</center>');
				 return; 
			 }else{
			 var amount='';
			if(count!=''&& count!=null && price!=''&& price!=null){
				amount=count*price;
				amount=amount.toFixed(2);
				$('#purchaseMoney'+index).val(amount);
			}else{
				$('#purchaseMoney'+index).val(0);
			}
				
			 }	
		 }
};
