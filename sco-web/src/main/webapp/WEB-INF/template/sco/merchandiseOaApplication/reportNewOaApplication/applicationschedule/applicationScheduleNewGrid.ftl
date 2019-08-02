<#compress>
	<script type="text/javascript" >
		<#include "sco/merchandiseOaApplication/reportNewOaApplication/applicationschedule/applicationScheduleNew.js" />
    </script>
    
	<div id="appScheNews_toolbar">
		<a onclick="appScheNewFn.save();" plain="true" class="easyui-linkbutton" data-options="iconCls:'save'">
			保存
		</a>
		<a onclick="appScheNewFn.close();" plain="true" class="easyui-linkbutton" data-options="iconCls:'close'">
			关闭
		</a>
	</div>
	<div>
		<table id="appScheNewGrid" 
			pagination = "true"
			pagePosition = "bottom"
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#appScheNews_toolbar"
			style="height:500px"
			data-options="rownumbers:true
			<#-- ,url : 'applicationScheduleNew_listApplicationScheduleNew_2.html?applicationCode=${applicationCodeNow}&quotedCodes=${quotedCodes}' -->
		">  
			<thead>
				<tr>
					<th rowspan="2" data-options="field:'NULL',	checkbox:true"></th>
					<th rowspan="2" data-options="field:'intentionCode',halign:'left',width:150">
						意向品/商品编号
					</th>
					<th rowspan="2" data-options="field:'intentionName',halign:'left',width:180,required:true">
						意向品/商品名称
					</th>
					<th rowspan="2" data-options="field:'intentionSupplierCode',halign:'left',width:200">
						供应商/意向供应商编号
					</th>
					<th rowspan="2" data-options="field:'intentionSupplierName',halign:'left',width:200">
						供应商/意向供应商名称
					</th>
					<th rowspan="2" data-options="field:'quotedCode',halign:'left',width:180">
						报价单号
					</th>
					<th rowspan="2" data-options="field:'enquiryCount',halign:'left',width:180,align:'right'">
						询价数量
					</th>
					<th rowspan="2" data-options="field:'realPurCount',halign:'left',width:180,align:'right'">
						实际采购数量
					</th>
					<th rowspan="2" data-options="field:'contractPrice',halign:'left',width:180,align:'right'">
						合同进价
					</th>
					<th rowspan="2" data-options="field:'realPurPrice',halign:'left',width:180,align:'right'">
						实际采购金额
					</th>
					<th rowspan="2" data-options="field:'subscribeBillDate',halign:'left',width:180">
						收到申购单日期
					</th>
					<th data-options="field:'sjwgDate',halign:'left',width:150,sortable:true,required:true,
						editor:{
							type:'datebox',
							options:{
								editable:false
							}
						}">
	                	设计完稿日期
	                </th>
					<th rowspan="2" data-options="field:'requirementsDwDate',halign:'left',width:180">
						要求到货日期
					</th>
					<th rowspan="2" data-options="field:'jjdtjDate',halign:'left',width:180">
						竞价单提交日期
					</th>
					<th rowspan="2" data-options="field:'jjdwcDate',halign:'left',width:180">
						竞价单完成日期
					</th>
					<th rowspan="2" data-options="field:'jjdspwcts',halign:'left',width:200,align:'right'">
						竞价单审批完成天数
					</th>
					<th rowspan="2" data-options="field:'orderDate',halign:'left',width:180,
						editor:{
								type:'datebox',
								options:{
									editable:false
								}
						}">
						下达采购订单日期
					</th>
					<th rowspan="2" data-options="field:'aogDate',halign:'left',width:180,
						editor:{
							type:'datebox',
							options:{
								editable:false
							}
						}">
						实际到货日期
					</th>
					<th rowspan="2" data-options="field:'proofingCycle',halign:'left',width:180">
						样品打样周期
					</th>
					<th rowspan="2" data-options="field:'normalFlowDate',halign:'left',width:200,align:'right',
						editor:{
							type:'numberbox',
							options:{
								
							}
						}">
						正常作业流程时间（天）
					</th>
					<th rowspan="2" data-options="field:'realityFlowDate',halign:'left',width:200,align:'right'">
						实际作业流程时间（天）
					</th>
					<th rowspan="2" data-options="field:'differenceDate',halign:'left',width:180,align:'right'">
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
								appScheNewFn.closeErrDlg();
							}
						}
				]
		">
			<textArea id="errMsg" readonly="true" style="margin: 0px; width: 315px; height: 167px;border:0; resize: none;"></textArea>
		</div>
	</div>
</#compress>