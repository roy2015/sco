<#compress>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>  
	<meta http-equiv="X-UA-Compatible" content="IE=7,chrome=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<#include "inc/easyui.ftl" />
	<script type="text/javascript" >
	<#include "sco/common/masterDataType.js" />
	<#include "sco/merchandiseCostAnalysis/totalCostAnalogyAnalysis/costContrast/costContrast.js" />
    </script>
    <style type="text/css">
    	body {
			font-size: 12px;
			font-family: "微软雅黑", "宋体"
		}
		.innerTable{
			width:100%;
			border-collapse: collapse;
			border-spacing: 0;
		}
		
		.innerTable tr td:first-child{
			white-space:pre-wrap;
			word-wrap:break-word;
			border-bottom:0px;
			border-left:0px;
		}
		
		.innerTable tr td:not(:first-child){
			white-space:pre-wrap;
			word-wrap:break-word;
			border-bottom:0px;
		}
		.materialtable tr:not(:first-child) td ,
		.npackagtable tr:not(:first-child) td ,
		.wpackagtable tr:not(:first-child) td ,
		.wastagetable tr:not(:first-child) td
		{
			border-top:1px solid #dddddd;
		}
		.table th {
			font-weight: normal;
			color:#333;
		}
		.table thead th {
			vertical-align: bottom;
		}
        .table-head{
        	text-align:left;
        	white-space:nowrap;
        	width:160px;
        }
        .text{
        	margin:0px 10px;
        }
    	#rowtitle td{
	    	font-size: 13px;
	    	font-weight:bold;
	    	background-color:rgb(0,146,240);
    	}
    	#deductptcostValue td,#nwpackagsubtotalValue td,#subTotal td{
    		color : red;
    	}   	
    	table {
			table-layout: fixed;
			background-color: transparent;
			border-collapse: collapse;
			border-spacing: 0;
		}
		#contrastResult{
			border-bottom:0px;
		}
		
		#contrastResult .price{
			text-align:right;
		}
		#contrastResult .remarks{
			white-space:pre-wrap;
			word-wrap:break-word;
			width:205px;
		}
		#contrastResult .units{
			white-space:pre-wrap;
			word-wrap:break-word;
			width:95px;
		}
		#contrastResult th, #contrastResult td{
			padding:0px;
			vertical-align: middle;	
			white-space:nowrap;
			line-height: 25px;
		}
		.table-bordered {
			border: 1px solid #dddddd;
		}
		.table-bordered th, .table-bordered td {
			border-left: 1px solid #dddddd;
			border-bottom: 1px solid #dddddd;
		}
    </style>
</head>
<body>
	<!-- 商品总成本类比结果表 --> 
	<div>
	<input id="data" type="hidden" value="${data}"/>
	<input id="inlandImport" type="hidden" value="${inlandImport}"/>
	<table>
	<tr>
		<td><a id="close" onclick="costContrastFn.close();" plain="true" class="easyui-linkbutton" data-options="iconCls:'close'">关闭 </a></td>
		<td><a id="refresh" onclick="costContrastFn.refresh();" plain="true" class="easyui-linkbutton" data-options="iconCls:'refresh'">刷新</a></td>
		<td><a id="save" onclick="costContrastFn.saveFile();" plain="true" class="easyui-linkbutton" data-options="iconCls:'save'">保存</a></td>
		<td colspan="4"><a id="import" onclick="costContrastFn.export2Excel();" plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'">导出Excel</a></td>
	</tr>
	<tr>
		<td><a id="addReferMerchandise" onclick="costContrastFn.addReferMerchandise();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'">添加参照品-商品</a></td>
		<td><a id="addReferIntention" onclick="costContrastFn.addReferIntention();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'">添加参照品-意向品 </a></td>
		<td><a id="removeRefer" onclick="costContrastFn.removeRefer();" plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'">移除参照品</a></td>
		<td><a id="editAccounting" onclick="costContrastFn.editAccounting();" plain="true" class="easyui-linkbutton" data-options="iconCls:'edit'">修改核算/投料表</a></td>
		<td><a id="right" onclick="costContrastFn.right();" plain="true" class="easyui-linkbutton">向右移动</a></td>
		<td colspan="2"><a id="left" onclick="costContrastFn.left();" plain="true" class="easyui-linkbutton">向左移动</a></td>
	</tr>
	<tr>
		<td><a id="setContrastUnits" onclick="costContrastFn.setContrastUnits();" plain="true" class="easyui-linkbutton" data-options="iconCls:'systemSet'">设置统一对比单位</a></td>
		<td><a id="cancelContrastUnits" onclick="costContrastFn.cancelContrastUnits();" plain="true" class="easyui-linkbutton" data-options="iconCls:'cancel'">取消设置统一对比单位</a></td>
		<td><a id="showAllRemarks" onclick="costContrastFn.showAllRemarks();" plain="true" class="easyui-linkbutton" data-options="iconCls:'find'">显示所有备注</a></td>
		<td><a id="onlyShowThisTimeRemarks" onclick="costContrastFn.onlyShowThisTimeRemarks();" plain="true" class="easyui-linkbutton" data-options="iconCls:'find'">只显示本次申请商品备注</a></td>
		<td><a id="hidenRemarks" onclick="costContrastFn.hidenRemarks();" plain="true" class="easyui-linkbutton" data-options="iconCls:'find'">隐藏备注</a></td>
		<td><a id="showHidenUnits" onclick="costContrastFn.showHidenUnits();" plain="true" class="easyui-linkbutton" data-options="iconCls:'find'">显示/隐藏报价计量单位</a></td>
		<td><input id="onlyShowsubtotal" type="checkbox" onclick="costContrastFn.onlyShowSmall(this);" value="onlyShowsubtotal"/>只显示小计</td>
	</tr>
	</table>
	</div>
	<div id="container" style="width:auto" style="margin-top:25px">
	<h5 id="unitsTip">以下成本对比分析表中包含多种报价计量单位,目前并未设置统一对比单位</h5>
	<table id="contrastResult" style="height:100px;margin-top:26px;" class="table table-bordered">
	</table>
	</div>
	
	<div class="easyui-dialog" id="saveFileDlg" style="width:320px;height:160px" 
		data-options="
			title:'请输入文件名称',
			closable:false,closed:true,modal:true,buttons:[
					{id:'save',text:'确定',iconCls:'save',handler:function(){costContrastFn.submitSaveFileDlg()}},
					{text:'关闭',iconCls:'close',handler:function(){costContrastFn.closeSaveFileDlg()}}]">
		<br/><br/>
		<div style="margin-left:25px">文件名称: <input id="fileName" class="easyui-validatebox" data-options="validType:'validateVarName'" style="width:200px;"/></div>		
	</div>
</body>
</html>
</#compress>