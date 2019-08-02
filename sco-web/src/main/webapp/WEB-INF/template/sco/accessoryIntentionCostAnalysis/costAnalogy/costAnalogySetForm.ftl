<#compress>
<!DOCTYPE html>  
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
<#include "sco/accessoryIntentionCostAnalysis/costAnalogy/costAnalogyModel.js" />
</script>
</head>
<body class="easyui-layout">

<div style="padding: 5px;overflow: hidden">
<div  id="haveCostAnalogySetForm">
	<form id="costAnalogySet_form" method="post" enctype="multipart/form-data">
	<input name="rows" id="rows" type="hidden"  value="${rows}"/>
		<table class="tableForm">
					  <tr>
					    <th align="right" style="width: 55px;"><span class="txtCenter">是否查看所有报价数量</span></th>
						<td>
							<select id="allbjsl" name="allbjsl"   style="width:124px;"   class="easyui-combobox" data-options="required:true,onChange:costAnalogyFn.doShowBjsl " >
									 <option value="是">是</option>
									 <option value="否">否</option>
							</select>
						</td>
					   <td><span ></span></td>
					</tr>
				<tr id="txbjsl" style="display:none;">
				<th align="right" style="width: 55px;"><span class="txtCenter"><font color="red">*</font>只查看报价数量:</span></th>
				<td><input type="text" class="easyui-validatebox" data-options="required:true,validType:'length[0,10]'"  id="zckbjsl" name="zckbjsl" style="width: 120px;"  /></td>
			   </tr>
					  <tr>
					    <th align="right" style="width: 55px;"><span class="txtCenter">查看哪些供应商资质信息:</span></th>
						<td>
							<select id="zzxx" name="zzxx"   style="width:124px;"   class="easyui-combobox" data-options="required:true,multiple:true" >
									 <option value="all">全选</option>
									 <option value="supplierCode">供应商编号-供应商名称</option>
									 <option value="contacts">联系人</option>
									 <option value="phone">联系方式</option>
									 <option value="companySite">公司地址</option>
									 <option value="factorySite">工厂地址</option>
									 <option value="registerCapital">注册资金</option>
									 <option value="yearTurnover">年营业额</option>
									 <option value="factoryArea">工厂面积</option>
									 <option value="staffCount">工人数</option>
									 <option value="dailyCapacity">日产能</option>
									 <option value="hzgpp">合作过品牌</option>
									 <option value="invoiceType">发票类型(增/普票)</option>
									 <option value="sl">税率</option>
									 <option value="paymentType">付款方式</option>
									 
							</select>
						</td>
					   <td><span ></span></td>
					</tr>
					  <tr>
					    <th align="right" style="width: 55px;"><span class="txtCenter">查看哪些样品信息:</span></th>
						<td>
							<select id="ypxx" name="ypxx"   style="width:124px;"   class="easyui-combobox" data-options="required:true" >
									 <option value="全选">全选</option>
									 <option value="产前样">产前样</option>
									 <option value="非产前样">非产前样</option>
							</select>
						</td>
					   <td><span ></span></td>
					</tr>
					  <tr>
					    <th align="right" style="width: 55px;"><span class="txtCenter">是否将增值发票报价转换为普通发票报价:</span></th>
						<td>
							<select id="zhfp" name="zhfp"   style="width:124px;"   class="easyui-combobox" data-options="required:true" >
									 <option value="否">否</option>
									 <option value="是">是</option>
							</select>
						</td>
					   <td><span ></span></td>
					</tr>
		</table>
		
		</form>
		</div>
</div>
</body>
</html>
</#compress>