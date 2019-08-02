<#compress>
<script type="text/javascript" charset="utf-8">
	<#include "sco/home/home.js" />
	<#include "sco/common/masterDataType.js" />
</script>
<style>
	*{ margin:0; padding:0}
	ul{
		list-style-type: none;
		font-size: 12px;
		font-family: "微软雅黑", "宋体"
	}
	.theme li{ float:left; width:200px; height:130px; margin:10px 18px; text-align:center; color:#FFF; cursor:pointer;}
	.theme li img{ margin:16px 0}
	.red{ background-color:#D95435}
	.blue{ background-color:#31AAE1}
	.green{ background-color:#32B67C}
	.yellow{ background-color:#FDB853}
	.purple{ background-color:#2557A0}
</style>
<div style="display:block;width:1191px;height:212px;margin-top:10px">
	<div id="gtasksRemindTool" style="height:25px;">
    	<strong style="line-height:25px;">待办事项提醒 ：共有<font color='red'></font>个申请单尚未关联公示网站原料、维护物料或大货信息！</strong>
    </div>
    <table id="gtasksRemind_grid"
		    singleSelect="true"
		    url="home_listGtasksRemind_2.html"
		    fitColumns="true"
		    pagination = "true"
		    pageSize = "5"
		    fit = "true"
		    toolbar="#gtasksRemindTool"
			pageList = [5,10,15,20]
		    pagePosition = 'bottom'
		    data-options="rownumbers:true">  
         <thead>
        	<tr>
        	    <th data-options="field:'applicationCode',width:110,sortable:true,align:'center',
					formatter : function(value, rowData, rowIndex) {
						if(value!=null && value!=''){
						return '<a href=# style=color:maroon;cursor:hand; onclick=homeFn.showEdit(\''+rowData.applicationCode+'\',\''+rowData.applicationType+'\') >'+value+'</a>'
						}
					}">SCO申请单号</th>
				<th data-options="field:'applicationTypeString',width:200,sortable:true,align:'center'">申请类型</th>
				<th data-options="field:'applicationPerson',width:120,sortable:true,align:'center'">申请人</th>
				<th data-options="field:'approvalDate',width:300,sortable:true,align:'center'">审批通过日期</th>
            </tr>
        </thead>
    </table>
</div>

<div style="display:block;width:1191px;height:212px;margin: 10px 0px;">
	<div id="elseRemindTool" style="height:25px;">
    	<strong style="line-height:25px;">其他提醒 ：</strong>
		<input id="elseRemindType" 
			style="width: 120px;" 
		    class="combobox";
		    data-options="
			valueField:'id',
			textField:'text',
			panelHeight:'auto',
			editable:false,
		    url:'businessComBox_remindTypeList_5.html'
		    ">
		</input>
		<a style="float:right" onclick="homeFn.notRemind()" plain="false" class="easyui-linkbutton" data-options="iconCls:'clear'">已阅清除 </a>
    </div>
	<table  	id="elseRemind_grid"
			    url="home_listElseRemind_2.html"
			    fitColumns="true"
			    pagination = "true"
			    pageSize = "5"
			    fit = "true"
			    pageList = [5,10,15,20]
			    pagePosition = 'bottom'
			    selectOnCheck="true"
			    checkOnSelect="true"
			    toolbar="#elseRemindTool"
			    data-options="rownumbers:true">  
	        <thead>
	        	<tr>
	        		<th align="center" data-options="field:'remindCode',width:40,checkbox:true"></th>
					<th data-options="field:'remindTypeString',width:130">提醒类别</th>
					<th data-options="field:'remindDate',width:150,sortable:true">提醒发布日期</th>
					<th data-options="field:'remindInfo',width:750,formatter:function(value,row){
																	if(row.remindType=='YLJGYJ'){
																	return value;
																	}else{
																	return '<a title=\''+value+'\'>'+value+'</a>';
																	}
																			  }">提醒信息</th>
	                <th data-options="field:'remindType',width:750,hidden:true"></th>
	                <th data-options="field:'qlCode',width:750,hidden:true"></th>
	                <th data-options="field:'configCode',width:750,hidden:true"></th>
	            </tr>
	        </thead>
	 </table>
</div>
<div style="display:block;width:1191px;height:200px;margin: 10px 0px;">
	 <strong>常用模块:</strong>
	 <ul class="theme">
		<li onclick="homeFn.myReport()" class="red"><img src="images/home/6.png"/>
		  <div>我的报表</div></li>
		<li onclick="homeFn.merchandiseIntention()" class="blue"><img src="images/home/8.png"/>
		  <div>商品意向品管理</div></li>
		<li onclick="homeFn.merchandiseCostAnalysis()" class="green"><img src="images/home/7.png"/>
		  <div>商品总成本类比分析</div></li>
		<li onclick="homeFn.sellDetail()" class="yellow"><img src="images/home/13.png"/>
		  <div>销售明细</div></li>
		<li onclick="homeFn.webSiteMaterial()" class="purple"><img src="images/home/14.png"/>
		  <div>公示网站原料历史行情</div></li>
	  </ul>
</div>
</#compress>