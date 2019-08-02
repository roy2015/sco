<#compress>
<head>
	<meta charset=utf-8" />
	<link rel="stylesheet"  href="${copoxtPath}css/table.min.css" type="text/css" />
	<script type="text/javascript">
	<#include "sco/accessoryIntention/accessoryEnquiryForm.js" />
</script>
</head>
<body>
 <form id="createXJD_form" method="post" enctype="multipart/form-data">
	 	<div style="padding-top:20px">
		<table class="tableForm">
			<tr>
	 			<td colspan="6"></td>
	 		</tr>
					<tr>
					    <th align="right" ><span class="txtCenter"><font color="red">*</font>询价单编号: </span></th>
						
						<td><input filterName="enquiryCode" id="enquiryCode" name="enquiryCode" value="${accessoryEnquiry.enquiryCode}" readonly="readonly" disabled="true" class="easyui-validatebox"  style="width:120px;"/></td>
						
						<th align="right" ><span class="txtCenter"><font color="red">*</font>报价币种:</span></th>
						<td>
							<select id="quotedCurrency" name="quotedCurrency" panelHeight="auto" value="${accessoryEnquiry.quotedCurrency}" style="width:124px;"   class="easyui-combobox" data-options="required:true" >
									 <option <#if accessoryEnquiry.quotedCurrency=="CNY"> selected="selected"  </#if> value="CNY" >CNY</option>
									 <option <#if accessoryEnquiry.quotedCurrency=="USD"> selected="selected"  </#if> value="USD" >USD</option>
									 <option <#if accessoryEnquiry.quotedCurrency=="EUR"> selected="selected"  </#if> value="EUR" >EUR</option>
							</select>
						</td>
						<th align="right" ><span class="txtCenter"><font color="red">*</font>报价单位: ${intentionCode}</span></th>
						
						<td><input filterName="quotedUnits" id="quotedUnits" name="quotedUnits" value="${accessoryEnquiry.quotedUnits}" class="easyui-validatebox" data-options="required:true,validType:'length[1,10]'" style="width:120px;"/></td>
					</tr>
					<tr>
					<th align="right" ><span class="txtCenter"><font color="red">*</font>询价单名称: </span></th>
						
						<td><input filterName="enquiryName" id="enquiryName" name="enquiryName" value="${accessoryEnquiry.enquiryName}" data-options="required:true,validType:'length[0,33]'" class="easyui-validatebox"  style="width:120px;"/></td>
					    <th align="right"><span class="txtCenter">付款方式: </span></th>
						
						<td><input filterName="paymentType" id="paymentType"  name="paymentType" value="${accessoryEnquiry.paymentType}" data-options="validType:'length[0,33]'" class="easyui-validatebox"  style="width:120px;"/></td>
						
						 <th align="right" ><span class="txtCenter"><font color="red">*</font>交货方式: </span></th>
						
						<td ><input filterName="deliveryType" id="deliveryType" name="deliveryType" value="${accessoryEnquiry.deliveryType}" class="easyui-validatebox" data-options="required:true,validType:'length[0,33]'" style="width:120px;"/></td>
					</tr>
					<tr>
					    <th align="right" ><span class="txtCenter">备注: </span></th>
						
						<td colspan="5"><input filterName="remarks" id="remarks" name="remarks" value="${accessoryEnquiry.remarks}"  class="easyui-validatebox" data-options="validType:'length[0,333]'" style="width:120px;"/></td>
						
					</tr>
	
			   <tr>
				<td style="text-align:right;">选择文件:</td>
				<td >
					<input type="text" class="file" id="txt" style="width:0px;visibility: hidden" />
					<input class="input_file" size="30" type="file" onchange="txt.value=this.value" id="upload_xjd" name="upload" style="width:120px;" />
				</td>
				<td colspan="5">${accessoryEnquiry.attachment}</td>
			</tr>
		</table>
		</div>
	<div>
	<div style="padding-top:10px">
    <#include "sco/accessoryIntention/createXJD_BJSLForm.ftl" />
    </div>
	<div  style="padding-top:10px">
    <#include "sco/accessoryIntention/createXJD_YCLForm.ftl" />
    </div>
    <div  style="padding-top:10px">
    <#include "sco/accessoryIntention/createXJD_FLForm.ftl" />
    </div>
    <div  style="padding-top:10px">
    <#include "sco/accessoryIntention/createXJD_NWBZForm.ftl" />
    </div>
    <div  style="padding-top:10px">
    <#include "sco/accessoryIntention/createXJD_GYForm.ftl" />
    </div>
     <div  style="padding-top:10px">
    <#include "sco/accessoryIntention/createXJD_QTYQForm.ftl" />
    </div>
    </form>
 </body>
</html>  
</#compress>