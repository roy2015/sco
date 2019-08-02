<#compress>

<head>
<html>
<meta charset="UTF-8">
	<#include "inc/easyui.ftl" />
	<link rel="stylesheet"  href="${copoxtPath}css/table.min.css" type="text/css" />
	<script type="text/javascript">
		<#include "sco/shareFunctionAnalysis/supplierComprehensiveAnalysis/supplierComprehensiveAnalysisForm.js" />
	</script>
	<style>
	body {
			font-family : "微软雅黑";
			font-size : 12px;
		}
		table {
			border-collapse: collapse;
			border: none;
			width: 100%;
			font-family : "微软雅黑";
			font-size : 12px;
			white-space : nowrap
		}
		td, th {
			border: dotted #ddd 1px;
			padding:5px;
			white-space : nowrap;
		}
		th {
			background-color: #EEEEEE;
			white-space : nowrap;
		}
		th {
			font-weight:normal;
			border: solid #ddd 1px;
			background: #FDFDFD;  <!-- 一些不支持背景渐变的浏览器 -->
			background: -moz-linear-gradient(top,  #FDFDFD 0%, #F5F5F5 100%); <!-- 兼容Firefox浏览器 -->
			background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#FDFDFD), color-stop(100%,#F5F5F5)); <!-- 兼容chrome/Safari浏览器 -->
			background: -webkit-linear-gradient(top,  #FDFDFD 0%,#F5F5F5 100%);
			background: -o-linear-gradient(top,  #FDFDFD 0%,#ffffff 100%);
			background: -ms-linear-gradient(top,  #000000 0%,#ffffff 100%);
			background: linear-gradient(to bottom,  #FDFDFD 0%,#F5F5F5 100%);
			filter:progid:DXImageTransform.Microsoft.gradient( startColorstr='#FDFDFD', endColorstr='#F5F5F5',GradientType=0 );  <!-- 兼容IE浏览器 -->
		}
		:root {filter:none;}
	</style>
</head>
<body>
	<input name="json" id="json" type="hidden" value="${json}" /> 
	<form id="gyszhfx_form" method="post" enctype="multipart/form-data">
		<a id="close" onclick="supplierComprehensiveAnalysisFormFn.close();" plain="true" class="easyui-linkbutton" data-options="iconCls:'close'">关闭 </a>
		<a id="save" onclick="supplierComprehensiveAnalysisFormFn.saveFile();" plain="true" class="easyui-linkbutton" data-options="iconCls:'save'">保存</a>
		<a id="import" onclick="supplierComprehensiveAnalysisFormFn.export2Excel();" plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'">导出Excel</a>
	</form>
	<#setting number_format="#,##0.00">
		<table id="contrastResult" class="table table-bordered" >
    		<tr>
        	    <th>
                	<span >数据时间范围</span>
                </th>
                <th>
                	<span >供应商编号</span>
                </th>
                <th>
                	<span >供应商名称</span>
                </th>
                 <th>
                	<span >占地面积</span>
                </th>
                 <th>
                	<span >车间面积</span>
                </th>
                 <th>
                	<span >年总产值</span>
                </th>
                 <th>
                	<span >企业总人数</span>
                </th>
                <#if supplierComprehensiveAnalysisSet.xsl=='Y'>
				<th>
                	<span >销售量（公斤）</span>
                </th>
                </#if>
                 <#if supplierComprehensiveAnalysisSet.xsje=='Y'>
				<th>
                	<span >销售金额（元）</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.mle=='Y'>
                <th>
                	<span >毛利额（元）</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.xslzball=='Y'>
				<th>
                	<span >销售量占比（占所有商品）</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.xsjezball=='Y'>
                <th>
                	<span >销售金额占比（占所有商品）</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.mlezball=='Y'>
				<th >
                	<span >毛利额占比（占所有商品）</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.xslzbxfl=='Y'>
               <th>
                	<span >销售量占比（占小分类）</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.xsjezbxfl=='Y'>
                <th>
                	<span >销售金额占比（占小分类）</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.mlezbxfl=='Y'>
                <th>
                	<span >毛利额占比（占小分类）</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.xslzbmxl=='Y'>
                <th>
                	<span >销售量占比（占明细类）</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.xsjezbmxl=='Y'>
                <th>
                	<span >销售金额占比（占明细类）</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.mlezbmxl=='Y'>
                 <th>
                	<span >毛利额占比（占明细类）</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.psdxl=='Y'>
                 <th>
                	<span >PSD销量（公斤）</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.psdxsje=='Y'>
                 <th>
                	<span >PSD销售金额（元）</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.psdml=='Y'>
                <th>
                	<span >PSD毛利（元）</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.qxs=='Y'>
				<th>
                	<span >权限数</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.qxdt=='Y'>
                <th>
                	<span >权限店天</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.xsdt=='Y'>
				<th>
                	<span >销售店天</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.hyd=='Y'>
                <th>
                	<span >活跃度</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.abcdmds=='Y'>
				<th>
                	<span >A门店数</span>
                </th>
                 
               <th>
                	<span >B门店数</span>
                </th>
                
                <th>
                	<span >C门店数</span>
                </th>
                
                <th>
                	<span >D门店数</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.jhl=='Y'>
                <th>
                	<span >进货量（公斤）</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.jhe=='Y'>
                <th>
                	<span >进货额（元）</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.ddjsl=='Y'>
                 <th>
                	<span >订单及时率</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.ddmzl=='Y'>
                 <th>
                	<span >订单满足率</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.rbjssl=='Y'>
                 <th>
                	<span >让步接收数量（箱）</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.rbjscs=='Y'>
                 <th>
                	<span >让步接收次数</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.gyssjkc=='Y'>
                 <th>
                	<span >供应商实际库存（公斤）</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.gysaqkc=='Y'>
                 <th>
                	<span >供应商安全库存（公斤）</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.zlxj=='Y'>
                 <th>
                	<span >质量星级</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.zhcs=='Y'>
                 <th>
                	<span >召回次数</span>
                </th>
                 </#if>
                
                 <#if supplierComprehensiveAnalysisSet.zhskugs=='Y'>
                 <th>
                	<span >召回SKU个数</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.cjbhgcs=='Y'>
                 <th>
                	<span >抽检不合格次数</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.cjbhgskus=='Y'>
                 <th>
                	<span >抽检不合格SKU数</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.gysndqwyks=='Y'>
                <th>
                	<span >供应商年度千万元客诉</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.gysxcpf=='Y'>
                <th>
                	<span >供应商巡厂评分</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.gyskpdf=='Y'>
                <th>
                	<span >供应商考评得分</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.gyskpdfph=='Y'>
                <th>
                	<span >供应商考评得分排行</span>
                </th>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.gysyxps=='Y'>
                <th>
                	<span >供应商意向品数</span>
                </th>
                 </#if>
                
            </tr>
            <#list list as supplierComprehensiveAnalysisForm>
            <tr>
        	    <td>
                	<span >${supplierComprehensiveAnalysisForm.sjsjfw}&nbsp;</span>
                </td>
                <td>
                	<span >${supplierComprehensiveAnalysisForm.supplierCode}&nbsp;</span>
                </td>
                 <td>
                	<span >${supplierComprehensiveAnalysisForm.supplierName}&nbsp;</span>
                </td>
                 <td align="right">
                	<span >&nbsp;${(supplierComprehensiveAnalysisForm.zdmj)!}</span>
                </td>
                 <td align="right">
                	<span >&nbsp;${(supplierComprehensiveAnalysisForm.cjmj)!}</span>
                </td>
                 <td align="right">
                	<span >&nbsp;${(supplierComprehensiveAnalysisForm.nzcz)!}</span>
                </td>
                 <td align="right">
                	<span >&nbsp;${(supplierComprehensiveAnalysisForm.qyzrs?string)!?replace(".00","")}</span>
                </td>
                <#if supplierComprehensiveAnalysisSet.xsl=='Y'>
				 <td align="right">
                	<span >&nbsp;${(supplierComprehensiveAnalysisForm.xsl?string("#,##0.00"))!}</span>
                </td>
                </#if>
                 <#if supplierComprehensiveAnalysisSet.xsje=='Y'>
				 <td align="right">
                	<span >&nbsp;${supplierComprehensiveAnalysisForm.xsje}</span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.mle=='Y'>
                 <td align="right">
                	<span >&nbsp;${supplierComprehensiveAnalysisForm.mle}</span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.xslzball=='Y'>
				 <td align="right">
                	<span >&nbsp;<#if supplierComprehensiveAnalysisForm.xslzball?length gt 1 && supplierComprehensiveAnalysisForm.xslzball?index_of("%")!=-1>${((supplierComprehensiveAnalysisForm.xslzball)?replace("%","")?number)?string("#,##0.00")}%<#else>${supplierComprehensiveAnalysisForm.xslzball}</#if></span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.xsjezball=='Y'>
                <td align="right">
                	<span >&nbsp;<#if supplierComprehensiveAnalysisForm.xsjezball?length gt 1 && supplierComprehensiveAnalysisForm.xsjezball?index_of("%")!=-1>${((supplierComprehensiveAnalysisForm.xsjezball)?replace("%","")?number)?string("#,##0.00")}%<#else>${supplierComprehensiveAnalysisForm.xsjezball}</#if></span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.mlezball=='Y'>
				<td align="right">
                	<span >&nbsp;<#if supplierComprehensiveAnalysisForm.mlezball?length gt 1 &&  supplierComprehensiveAnalysisForm.mlezball?index_of("%")!=-1>${((supplierComprehensiveAnalysisForm.mlezball)?replace("%","")?number)?string("#,##0.00")}%<#else>${supplierComprehensiveAnalysisForm.mlezball}</#if></span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.xslzbxfl=='Y'>
                <td align="right">
                	<span >&nbsp;<#if supplierComprehensiveAnalysisForm.xslzbxfl?length gt 1 && supplierComprehensiveAnalysisForm.xslzbxfl?index_of("%")!=-1>${((supplierComprehensiveAnalysisForm.xslzbxfl)?replace("%","")?number)?string("#,##0.00")}%<#else>${supplierComprehensiveAnalysisForm.xslzbxfl}</#if></span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.xsjezbxfl=='Y'>
                 <td align="right">
                	<span >&nbsp;<#if supplierComprehensiveAnalysisForm.xsjezbxfl?length gt 1 && supplierComprehensiveAnalysisForm.xsjezbxfl?index_of("%")!=-1>${((supplierComprehensiveAnalysisForm.xsjezbxfl)?replace("%","")?number)?string("#,##0.00")}%<#else>${supplierComprehensiveAnalysisForm.xsjezbxfl}</#if></span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.mlezbxfl=='Y'>
                 <td align="right">
                	<span >&nbsp;<#if supplierComprehensiveAnalysisForm.mlezbxfl?length gt 1 && supplierComprehensiveAnalysisForm.mlezbxfl?index_of("%")!=-1>${((supplierComprehensiveAnalysisForm.mlezbxfl)?replace("%","")?number)?string("#,##0.00")}%<#else>${supplierComprehensiveAnalysisForm.mlezbxfl}</#if></span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.xslzbmxl=='Y'>
                 <td align="right">
                	<span >&nbsp;<#if supplierComprehensiveAnalysisForm.xslzbmxl?length gt 1 && supplierComprehensiveAnalysisForm.xslzbmxl?index_of("%")!=-1>${((supplierComprehensiveAnalysisForm.xslzbmxl)?replace("%","")?number)?string("#,##0.00")}%<#else>${supplierComprehensiveAnalysisForm.xslzbmxl}</#if></span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.xsjezbmxl=='Y'>
                 <td align="right">
                	<span >&nbsp;<#if supplierComprehensiveAnalysisForm.xsjezbmxl?length gt 1 && supplierComprehensiveAnalysisForm.xsjezbmxl?index_of("%")!=-1>${((supplierComprehensiveAnalysisForm.xsjezbmxl)?replace("%","")?number)?string("#,##0.00")}%<#else>${supplierComprehensiveAnalysisForm.xsjezbmxl}</#if></span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.mlezbmxl=='Y'>
                  <td align="right">
                	<span >&nbsp;<#if supplierComprehensiveAnalysisForm.mlezbmxl?length gt 1 && supplierComprehensiveAnalysisForm.mlezbmxl?index_of("%")!=-1>${((supplierComprehensiveAnalysisForm.mlezbmxl)?replace("%","")?number)?string("#,##0.00")}%<#else>${supplierComprehensiveAnalysisForm.mlezbmxl}</#if></span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.psdxl=='Y'>
                  <td align="right">
                	<span >&nbsp;${supplierComprehensiveAnalysisForm.psdxl}</span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.psdxsje=='Y'>
                  <td align="right">
                	<span >&nbsp;${supplierComprehensiveAnalysisForm.psdxsje}</span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.psdml=='Y'>
                 <td align="right">
                	<span >&nbsp;${supplierComprehensiveAnalysisForm.psdml}</span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.qxs=='Y'>
				 <td align="right">
                	<span >&nbsp;${(supplierComprehensiveAnalysisForm.qxs?string("#,##0"))!}</span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.qxdt=='Y'>
                 <td align="right">
                	<span >&nbsp;${(supplierComprehensiveAnalysisForm.qxdt?string("#,##0"))!}</span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.xsdt=='Y'>
				 <td align="right">
                	<span >&nbsp;${(supplierComprehensiveAnalysisForm.xsdt?string("#,##0"))!}</span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.hyd=='Y'>
                 <td align="right">
                	<span >&nbsp;<#if supplierComprehensiveAnalysisForm.hyd?index_of("%")!=-1 && supplierComprehensiveAnalysisForm.hyd?length gt 1>${((supplierComprehensiveAnalysisForm.hyd)?replace("%","")?number)?string("#,##0")}%<#else>${supplierComprehensiveAnalysisForm.hyd}</#if></span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.abcdmds=='Y'>
				 <td align="right">
                	<span>&nbsp;${(supplierComprehensiveAnalysisForm.amds?string("#,##0"))!}</span>
                </td>
                 
                <td align="right">
                	<span>&nbsp;${(supplierComprehensiveAnalysisForm.bmds?string("#,##0"))!}</span>
                </td>
                
                 <td align="right">
                	<span >&nbsp;${(supplierComprehensiveAnalysisForm.cmds?string("#,##0"))!}</span>
                </td>
                
                 <td align="right">
                	<span >&nbsp;${(supplierComprehensiveAnalysisForm.dmds?string("#,##0"))!}</span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.jhl=='Y'>
                 <td align="right">
                	<span >&nbsp;${(supplierComprehensiveAnalysisForm.jhl?string("#,##0"))!}</span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.jhe=='Y'>
                 <td align="right">
                	<span >&nbsp;${(supplierComprehensiveAnalysisForm.jhe?string("#,##0"))!}</span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.ddjsl=='Y'>
                  <td align="right">
                	<span >&nbsp;<#if supplierComprehensiveAnalysisForm.ddjsl?length gt 1>${((supplierComprehensiveAnalysisForm.ddjsl)?replace("%","")?number)?string("#,##0.00")}</#if>%</span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.ddmzl=='Y'>
                  <td align="right">
                	<span >&nbsp;<#if supplierComprehensiveAnalysisForm.ddmzl?length gt 1>${((supplierComprehensiveAnalysisForm.ddmzl)?replace("%","")?number)?string("#,##0.00")}</#if>%</span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.rbjssl=='Y'>
                  <td align="right">
                	<span >&nbsp;${supplierComprehensiveAnalysisForm.rbjssl}</span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.rbjscs=='Y'>
                  <td align="right">
                	<span >&nbsp;${(supplierComprehensiveAnalysisForm.rbjscs?string)!?replace(".00","")}</span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.gyssjkc=='Y'>
                  <td align="right">
                	<span >&nbsp;${supplierComprehensiveAnalysisForm.gyssjkc}</span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.gysaqkc=='Y'>
                  <td align="right">
                	<span >&nbsp;${supplierComprehensiveAnalysisForm.gysaqkc}</span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.zlxj=='Y'>
                  <td align="right">
                	<span >&nbsp;${supplierComprehensiveAnalysisForm.zlxj}</span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.zhcs=='Y'>
                  <td align="right">
                	<span >&nbsp;${(supplierComprehensiveAnalysisForm.zhcs?string)!?replace(".00","")}</span>
                </td>
                 </#if>
                
                 <#if supplierComprehensiveAnalysisSet.zhskugs=='Y'>
                  <td align="right">
                	<span >&nbsp;${(supplierComprehensiveAnalysisForm.zhskugs?string)!?replace(".00","")}</span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.cjbhgcs=='Y'>
                  <td align="right">
                	<span >&nbsp;${(supplierComprehensiveAnalysisForm.cjbhgcs?string)!?replace(".00","")}</span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.cjbhgskus=='Y'>
                  <td align="right">
                	<span >&nbsp;${(supplierComprehensiveAnalysisForm.cjbhgskus?string)!?replace(".00","")}</span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.gysndqwyks=='Y'>
                 <td align="right">
                	<span >&nbsp;${supplierComprehensiveAnalysisForm.gysndqwyks}</span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.gysxcpf=='Y'>
                 <td align="right">
                	<span >&nbsp;${supplierComprehensiveAnalysisForm.gysxcpf}</span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.gyskpdf=='Y'>
                 <td align="right">
                	<span >&nbsp;${supplierComprehensiveAnalysisForm.gyskpdf}</span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.gyskpdfph=='Y'>
                 <td align="right">
                	<span >&nbsp;${(supplierComprehensiveAnalysisForm.gyskpdfph?string)!?replace(".00","")}</span>
                </td>
                 </#if>
                 <#if supplierComprehensiveAnalysisSet.gysyxps=='Y'>
                 <td align="right">
                	<span >&nbsp;${(supplierComprehensiveAnalysisForm.gysyxps?string)!?replace(".00","")}</span>
                </td>
                 </#if>
                
            </tr>
            </#list>
        </thead>
    </table>
   
    <div class="easyui-dialog" id="saveFileDlg" style="width:270px;height:160px" 
		data-options="
			title:'请输入文件名称',
			closable:false,closed:true,modal:true,buttons:[
					{text:'确定',iconCls:'save',handler:function(){supplierComprehensiveAnalysisFormFn.submitSaveFileDlg()}},
					{text:'关闭',iconCls:'close',handler:function(){supplierComprehensiveAnalysisFormFn.closeSaveFileDlg()}}]">
		<br/><br/>
		<div style="margin-left:25px">文件名称: <input id="fileName" class="easyui-validatebox" data-options="validType:'validateVarName'" style="width:120px;"/></div>		
	</div>
	</body>
</html>
</#compress>