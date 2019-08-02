var exportEnquiryFn = {
		//导出询价单
	exportEnquiry:function(){
			var intentionCode=$('#intentionCode').val();
			var enquiryDate=$("#enquiryDate").datebox("getValue");
			if (enquiryDate ==null || enquiryDate == "") {
				$.messager.alert('提示','<center>请选择最晚报价日期！</center>'); 
				return;
			}
			
			var panel2SupplierGrid = $('#panel2SupplierGrid').datagrid('getSelected');
			if(panel2SupplierGrid==null){
				$.messager.alert('提示','<center>请选择一条记录！</center>'); 
			}else{
				var  intentionSupplierCode=panel2SupplierGrid.intentionSupplierCode;//供应商编号
				url="merchandiseQuoted_exportEnquiryToExcel_3.html?intentionCode="+intentionCode+"&enquiryDate="+enquiryDate
				+"&intentionSupplierCode="+intentionSupplierCode;
				window.location=url;
			}
		
    }
};
