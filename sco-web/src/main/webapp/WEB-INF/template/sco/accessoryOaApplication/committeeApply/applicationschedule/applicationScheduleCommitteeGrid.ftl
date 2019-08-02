<#compress>
	<script type="text/javascript" >
		<#include "sco/accessoryOaApplication/committeeApply/applicationschedule/applicationScheduleCommittee.js" />
    </script>
    
	<div id="appScheCommittee_toolbar">
		<a onclick="appScheCommitteeFn.save();" plain="true" class="easyui-linkbutton" data-options="iconCls:'save'">
			保存
		</a>
		<a onclick="appScheCommitteeFn.close();" plain="true" class="easyui-linkbutton" data-options="iconCls:'close'">
			关闭
		</a>
	</div>
	<div>
		<table id="appScheCommittGrid" 
			pagination = "true"
			pagePosition = "bottom"
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#appScheCommittee_toolbar"
			style="height:350px"
			data-options="rownumbers:true,
			url : 'applicationScheduleCommittee_listApplicationScheduleCommittee_2.html?applicationCode=${applicationCodeNow}&quotedCodes=${quotedCodes}'
		">  
			<thead>
				<tr>
					<th rowspan="2" data-options="field:'NULL',	checkbox:true"></th>
					<th rowspan="2" data-options="field:'intentionCode',width:150">
						<font color="red">*</font>意向品/商品编号
					</th>
					<th rowspan="2" data-options="field:'intentionName',width:180,required:true">
						<font color="red">*</font>意向品/商品名称
					</th>
					<th rowspan="2" data-options="field:'intentionSupplierCode',width:200">
						<font color="red">*</font>供应商/意向供应商编号
					</th>
					<th rowspan="2" data-options="field:'intentionSupplierName',width:200">
						<font color="red">*</font>供应商/意向供应商名称
					</th>
					<th rowspan="2" data-options="field:'quotedCode',width:180">
						<font color="red">*</font>报价单号
					</th>
					<th rowspan="2" data-options="field:'enquiryCount',width:180,align:'right'">
						<font color="red">*</font>询价数量
					</th>
					<th rowspan="2" data-options="field:'realPurCount',width:180,align:'right'">
						实际采购数量
					</th>
					<th rowspan="2" data-options="field:'contractPrice',width:180,align:'right'">
						合同进价
					</th>
					<th rowspan="2" data-options="field:'realPurPrice',width:180,align:'right'">
						实际采购金额
					</th>
					<th rowspan="2" data-options="field:'subscribeBillDate',width:180,
						formatter:function(value, rowData, rowIndex){
							var val = '';
							if (value != null) val = value;
							return '<span id=subscribeBillDate'+ rowIndex + '>' + val + '</span>';
						}
					">
						收到申购单日期
					</th>
					<th data-options="field:'sjwgDate',width:150,sortable:true,required:true,
						editor:{
							type:'datebox',
							options:{
								editable:false
							}
						}">
	                	设计完稿日期
	                </th>
					<th rowspan="2" data-options="field:'requirementsDwDate',width:180">
						要求到货日期
					</th>
					<th rowspan="2" data-options="field:'jjdtjDate',width:180">
						竞价单提交日期
					</th>
					<th rowspan="2" data-options="field:'jjdwcDate',width:180">
						竞价单完成日期
					</th>
					<th rowspan="2" data-options="field:'jjdspwcts',width:200,align:'right',
						formatter:function(value){
							if(value == null) return ;
							if (value < 0) return --value;
							return ++value;
						}
					">
						竞价单审批完成天数
					</th>
					<th rowspan="2" data-options="field:'orderDate',width:180,
						editor:{
								type:'datebox',
								options:{
									editable:false
								}
						}">
						下达采购订单日期
					</th>
					<th rowspan="2" data-options="field:'aogDate',width:180,
						formatter:function(value, rowData, rowIndex){
							var val = '';
							if (value != null) val = value;
							return '<input id=aogDate'+ rowIndex+' value='+ val +' >';
						}
					">
						实际到货日期
					</th>
					<th rowspan="2" data-options="field:'proofingCycle',width:180,align:'right',
						formatter:function(value){
 							if (value == null) return; 
							if (value < 0) return --value;
							return ++value;
						}
					">
						样品打样周期
					</th>
					<th rowspan="2" data-options="field:'normalFlowDate',width:200,align:'right',
						formatter:function(value, rowData, rowIndex){
							var val = '';
							if (value != null) val = value;
							return '<input id=normalFlowDate'+ rowIndex+' onblur=appScheCommitteeFn.calculDifferenceDate(\''+rowIndex+'\') value=' + val +' >';
						}
					">
						正常作业流程时间（天）
					</th>
					<th rowspan="2" data-options="field:'realityFlowDate',width:200,align:'right',
						formatter:function(value, rowData, rowIndex){
							if (value == null) {
								value = ''; 
							} else if (value < 0) {
								--value;
							} else {
								++value;
							}
							return '<span id=realityFlowDate'+ rowIndex + '>'+ value +'</span>';
						}
					">
						实际作业流程时间（天）
					</th>
					<th rowspan="2" data-options="field:'differenceDate',width:180,align:'right',
						formatter:function(value, rowData, rowIndex){
							var normalFlowDate = rowData.normalFlowDate;
							var realityFlowDate = rowData.realityFlowDate;

							if (normalFlowDate == null || realityFlowDate == null) {
								value = '';
							} else {
								if (realityFlowDate < 0) {
									--realityFlowDate;
								} else {
									++realityFlowDate;
								}
								value = normalFlowDate - realityFlowDate;
							}
							return '<span id=differenceDate'+ rowIndex + '>' + value + '</span>';
						}
					">
						差异天数
					</th>
				</tr>
			</thead>
		</table>
		<#-- 显示错误消息 -->
		<div class="easyui-dialog" id="msgDlg" style="width:330px;height:240px" 
			data-options="
				title:'提示',
				closable:false,closed:true,modal:true,buttons:[
						{
							text:'关闭',iconCls:'close',
							handler:function(){
								appScheCommitteeFn.closeErrDlg();
							}
						}
				]
		">
			<textArea id="errMsg" readonly="true" style="margin: 0px; width: 315px; height: 167px;border:0;resize: none;"></textArea>
		</div>
	</div>
</#compress>