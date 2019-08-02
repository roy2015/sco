<#compress>
	<script type="text/javascript" >
		<#include "sco/merchandiseOaApplication/reportOldupOaApplication/applicationschedule/applicationScheduleOld.js" />
    </script>
    
	<div id="appScheNews_toolbar">
		<a onclick="appScheOldFn.save();" plain="true" class="easyui-linkbutton" data-options="iconCls:'save'">
			保存
		</a>
		<a onclick="appScheOldFn.close();" plain="true" class="easyui-linkbutton" data-options="iconCls:'close'">
			关闭
		</a>
	</div>
	<div>
		<table id="appScheOldGrid" 
			pagination = "true"
			pagePosition = "bottom"
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#appScheNews_toolbar"
			style="height:350px"
			data-options="rownumbers:true,
			url : 'applicationScheduleOld_listApplicationScheduleOld_2.html?applicationCode=${applicationCode}&intentionAndSupplierCodes=${intentionAndSupplierCodes}'
		">  
			<thead>
				<tr>
					<th rowspan="2" data-options="field:'NULL',	checkbox:true,halign:'left'"></th>
					<th rowspan="2" data-options="field:'merchandiseCode',width:150,halign:'left'">
						意向品编号
					</th>
					<th rowspan="2" data-options="field:'merchandiseName',width:180,halign:'left'">
						意向品名称
					</th>
					<th rowspan="2" data-options="field:'supplierCode',width:200,halign:'left'">
						供应商编号
					</th>
					<th rowspan="2" data-options="field:'supplierName',width:200,halign:'left'">
						供应商名称
					</th>
					<th data-options="field:'xcsqDate',width:150,halign:'left',
						editor:{
							type:'datebox',
							options:{
								editable:false
							}
						}">
	                	巡厂申请日期
					<th data-options="field:'pkxcDate',width:150,halign:'left',
						editor:{
							type:'datebox',
							options:{
								editable:false
							}
						}">
	                	品控巡厂日期
					</th>
					<th data-options="field:'bzsjsqDate',width:150,halign:'left',
						editor:{
							type:'datebox',
							options:{
								editable:false
							}
						}">
	                	包装设计申请日期
					</th>
					<th data-options="field:'yjbgtjDate',width:150,halign:'left',
						editor:{
							type:'datebox',
							options:{
								editable:false
							}
						}">
	                	引进报告提交日期
					</th>
					<th data-options="field:'yjbgwcDate',width:150,halign:'left',
						editor:{
							type:'datebox',
							options:{
								editable:false
							}
						}">
	                	引进报告完成日期
					</th>
					<th data-options="field:'zsjsqDate',width:150,halign:'left',
						editor:{
							type:'datebox',
							options:{
								editable:false
							}
						}">
	                	主数据申请日期
					</th>
					<th data-options="field:'zsjsqwcDate',width:150,halign:'left',
						editor:{
							type:'datebox',
							options:{
								editable:false
							}
						}">
	                	主数据申请完成日期
					</th>
					<th data-options="field:'htqdDate',width:150,halign:'left',
						editor:{
							type:'datebox',
							options:{
								editable:false
							}
						}">
	                	合同签订日期
					</th>
					<th data-options="field:'bbtgDate',width:150,halign:'left',
						editor:{
							type:'datebox',
							options:{
								editable:false
							}
						}">
	                	八标提供日期
					</th>
					<th data-options="field:'qdgpDate',width:150,halign:'left',
						editor:{
							type:'datebox',
							options:{
								editable:false
							}
						}">
	                	取得光盘日期
					</th>
					<th data-options="field:'gyssdgpDate',width:150,halign:'left',
						editor:{
							type:'datebox',
							options:{
								editable:false
							}
						}">
	                	供应商收到光盘日期
					</th>
					<th data-options="field:'gzysqrDate',width:150,halign:'left',
						editor:{
							type:'datebox',
							options:{
								editable:false
							}
						}">
	                	包装印刷确认日期
					</th>
					<th data-options="field:'dyqrDate',width:150,halign:'left',
						editor:{
							type:'datebox',
							options:{
								editable:false
							}
						}">
	                	大样确认日期
					</th>
					<th data-options="field:'spdddcDate',width:150,halign:'left',
						editor:{
							type:'datebox',
							options:{
								editable:false
							}
						}">
	                	商品到达大仓日期
	                </th>
					<th data-options="field:'ssDate',width:150,halign:'left',
						editor:{
							type:'datebox',
							options:{
								editable:false
							}
						}">
	                	上市日期
	                </th>
	                <th rowspan="2" data-options="field:'remarks',width:200,halign:'left',
						editor:{
							type:'validatebox',
							options:{
								
							}
						}">
						备注
					</th>
				</tr>
			</thead>
		</table>
		<#-- 显示错误消息 -->
		<div class="easyui-dialog" id="msgOldDlg" style="width:330px;height:240px" 
			data-options="
				title:'提示',
				closable:false,closed:true,modal:true,buttons:[
						{
							text:'关闭',iconCls:'close',
							handler:function(){
								appScheOldFn.closeErrDlg();
							}
						}
				]
		">
			<textArea id="errOldMsg" readonly="true" style="margin: 0px; width: 315px; height: 167px;border:0;resize: none;"></textArea>
		</div>
	</div>
</#compress>