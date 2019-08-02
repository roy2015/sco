//count用于动态的为table的id赋值
var count=0;
$(document).ready(function(){
	$("input[id^='templateName']").attr("disabled", "disabled");
	$("input[id^='evaluateItemName']").attr("disabled", "disabled");
	$("input[id^='score']").attr("disabled", "disabled");
	$("input[id^='departments']").attr("disabled", "disabled");
	$("input[id^='according']").attr("disabled", "disabled");
	$("input[id^='standard']").attr("disabled", "disabled");
	$("input[id^='weight']").attr("disabled", "disabled");
	$("input[id^='evaluateFullScore']").attr("disabled", "disabled");
	$.post("supplierEvaluateTemplate_findSupplierEvaluateTemplateByTemplateCode_2.html",
		function(data){
		var temp = $.parseJSON(data).rows;
		if(temp.length>1){
			$("#table").attr("id","table0");
			$("#templateName").val(temp[0].templateName);
			$("#evaluateItemName").val(temp[0].evaluateItemName);
			$("#evaluateStartDate").datebox("setValue",temp[0].evaluateStartDate.substr(0,10));
			$("#evaluateEndDate").datebox("setValue",temp[0].evaluateEndDate.substr(0,10));
			$("#evaluateFullScore").val(temp[0].evaluateFullScore);
			$("#weight").val(moneyFormatter(temp[0].weight));
			$("#evaluateItemName").val(temp[0].evaluateItemName);
			$("#score").val(moneyFormatter(temp[0].score));
			$("#departments").val(temp[0].departments);
			$("#according").val(temp[0].according);
			$("#standard").val(temp[0].standard);
			for(var i=1;i<temp.length;i++){
				count++;
				//需要拷贝的table的部分
				var table='<table id="table'+count+'">'+
				'<tr>'+
				'<td>'+
					'<table id="table" style="width:800px;border:1px solid grey;background-color:#efefef">'+
						'<tr>'+
							'<td style="text-align:left">'+
								'<table id="tab'+count+'" style="margin-top:10px;margin-bottom:10px">'+
									'<tr>'+
										'<td style="text-align:right;width:70px">'+
											'评分项名称:'+
										'</td>'+
										'<td style="width:120px;">'+
											'<input id="evaluateItemName'+count+'" style="width:120px;" class="easyui-validatebox" required="true" />'+
										'</td>'+
										'<td style="text-align:right;width:50px">'+
											'权重(%):'+
										'</td>'+
										'<td style="width:120px;">'+
											'<input id="weight'+count+'" style="width:120px;" class="easyui-validatebox" required="true" />'+
										'</td>'+
										'<td style="text-align:right;width:50px">'+
											'分值:'+
										'</td>'+
										'<td style="width:120px;">'+
											'<input id="score'+count+'" style="width:120px;" class="easyui-validatebox" required="true" />'+
										'</td>'+
										'<td style="text-align:right;width:70px">'+
											'打分部门:'+
										'</td>'+
										'<td>'+
											'<input id="departments'+count+'"  style="width: 124px;" />'+
									    '</td>'+
									'</tr>'+
								'</table>'+
							'</td>'+
						'<tr>'+
						'<tr>'+
							'<td>'+
								'<table style="margin-top:10px;margin-bottom:10px">'+
									'<tr>'+
										'<td style="text-align:right;width:80px">'+
											'评分依据:'+
										'</td>'+
										'<td>'+
											'<textarea id="according'+count+'" name="according'+count+'" disabled="disabled" style="width:210px;height:100px;max-width:210px;max-height:100px" ></textarea>'+
										'</td>'+
										'<td></td>'+
										'<td style="text-align:right;width:80px">'+
											'评分标准:'+
										'</td>'+
										'<td>'+
											'<textarea id="standard'+count+'" name="standard'+count+'" disabled="disabled" style="width:210px;height:100px;max-width:210px;max-height:100px" ></textarea>'+
										'</td>'+
										'<td></td>'+
									'</tr>'+
								'</table>'+
							'</td>'+
						'</tr>'+
					'</table>'+
				'</td>'+
				'<td>'+
					'<div style="margin-top:-80px;text-align:left">'+
						
					'</div>'+
				'</td>'+
			'</tr>'+
		'</table>';
				//如果temp是多个的话，则动态添加表格
				$("#signedQty_search>table:last").after(table);
				//重新解析添加上去的表格
				$.parser.parse($("#table"+count));
				//把所有输入控件禁用
				$("input[id^='templateName']").attr("disabled", "disabled");
				$("input[id^='evaluateItemName']").attr("disabled", "disabled");
				$("input[id^='score']").attr("disabled", "disabled");
				$("input[id^='departments']").attr("disabled", "disabled");
				$("input[name^='according']").attr("readonly", "readonly");
				$("input[name^='standard']").attr("disabled", "disabled");
				$("input[id^='weight']").attr("disabled", "disabled");
				
				$("#evaluateItemName"+i).val(temp[i].evaluateItemName);
				$("#score"+i).val(moneyFormatter(temp[i].score));
				$("#weight"+i).val(moneyFormatter(temp[i].weight));
				//对返回的字符串进行判断，分别显示采购、品控和库存
				$("#departments"+i).val(temp[i].departments);
				$("#according"+i).val(temp[i].according);
				$("#standard"+i).val(temp[i].standard);
				$("#according"+i).val(temp[i].according);
				$("#standard"+i).val(temp[i].standard);
				for(var a=0;a<temp.length;a++){
					if(temp[0].templateType=='FL'){
						$("#tab"+count+" td:contains('打分部门')").next().remove();
						$("#tab"+count+" td:contains('打分部门')").remove();
					}
				}
			}
			//如果是辅料模板，则把打分部门文本和打分部门输入框删除
			if(temp[0].templateType=='FL'){
				$("#tab td:contains('打分部门')").next().remove();
				$("#tab td:contains('打分部门')").remove();
			}
		}else{
			$("#templateName").val(temp[0].templateName);
			$("#evaluateItemName").val(temp[0].evaluateItemName);
			$("#evaluateStartDate").datebox("setValue",temp[0].evaluateStartDate.substr(0,10));
			$("#evaluateEndDate").datebox("setValue",temp[0].evaluateEndDate.substr(0,10));
			$("#evaluateFullScore").val(temp[0].evaluateFullScore);
			$("#weight").val(moneyFormatter(temp[0].weight));
			$("#evaluateItemName").val(temp[0].evaluateItemName);
			$("#score").val(moneyFormatter(temp[0].score));
			$("#departments").val(temp[0].departments);
			$("#according").val(temp[0].according);
			$("#standard").val(temp[0].standard);
			if(temp[0].templateType=='FL'){
				$("#tab td:contains('打分部门')").next().remove();
				$("#tab td:contains('打分部门')").remove();
			}
		}
	});
	//把所有输入控件禁用
	$("input[id^='templateName']").attr("disabled", "disabled");
	$("input[id^='evaluateItemName']").attr("disabled", "disabled");
	$("input[id^='score']").attr("disabled", "disabled");
	$("input[id^='departments']").attr("disabled", "disabled");
	
});
var flag=true;