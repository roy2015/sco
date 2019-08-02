
var exportEnquiryFormFn = {
		// 导出询价单到Excel
		
		accessoryEnquiry2Excel:function(intentionCode){
		var supplier=undefined;
		var enquiry=undefined;
		supplier=$('#accessoryIntentionSupplier_dcxjd').datagrid("getSelected");
		enquiry=$('#accessoryEnquiry_dcxjd').datagrid("getSelected");
		var enquiryDate=$('#enquiryDate').datebox("getValue");
		var receiveDate=$('#receiveDate').datebox("getValue");
		if(enquiryDate==null || enquiryDate=='' ){
			$.messager.alert('提示', '<center>请输入最晚报价日期！</center>');
			return
		}
		if(supplier==undefined){
			$.messager.alert('提示', '<center>请选择一个供应商！</center>');
			return
		}
		if(enquiry==undefined){
			$.messager.alert('提示', '<center>请选择一个询价单！</center>');
			return
		}
		var selectSupplier=supplier.intentionSupplierCode;
		var selectEnquiry=enquiry.enquiryCode;
		url ="accessoryIntention_exportAccessoryEnquiryToExcel_6.html?enquiryCode="+selectEnquiry+"&supplierCode="+selectSupplier+"&enquiryDate="+enquiryDate+"&receiveDate="+receiveDate+"&intentionCode="+intentionCode;
		window.location =url;

		}
}