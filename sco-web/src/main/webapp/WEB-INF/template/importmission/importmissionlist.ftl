<#compress>
<!DOCTYPE html>  
<html>  
<head>  	
	<script>
		var fr="",t=2000;//这里2000表示每隔2秒自动刷新一次
		function Refresh(){
			$('#importmission_grid').datagrid('reload');
			fr=setTimeout("Refresh()",t);
		}
		function stopRefresh(){
			if(fr!=""){
				$("#timeButton").val("自动刷新");
				clearTimeout(fr);
				fr="";
			}else{
				$("#timeButton").val("暂  停");
				Refresh();
			}
		}
	</script>

    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
 	<script type="text/javascript" charset="utf-8">
		<#include "importmission/importmission.js" />
	</script>
</head>
<body> 

	<div id="importmission_toolbar">
		<input type="button" id="timeButton" value="自动刷新" onClick="stopRefresh()">
		<a onclick="importmission.clearSearch();"  plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">${action.getText("common.button.clearSearches")}</a>
	</div>
    <table id="importmission_grid"
    fit="true"
    iconCls= "icon-save"
	pagination = "true"
	pagePosition = 'bottom'
	pageSize = "10"
	pageList = "[ 30, 40, 50, 60 ]"
	toolbar="#importmission_toolbar"
	url="importmission!run.do?dotyp=2&ac=listImportMission"
    data-options="rownumbers:true">
        <thead>      
           <tr>
                <th data-options="field:'filename',width:180,sortable:true,
                formatter: function(value,row,index){
                	if(row.missionstatus=='Success'||row.missionstatus=='Failure'){
						return '<a href=\''+row.filepath+'\'>'+value+'</a>';
					}else{
						return value;
					}
				}">${action.getText("import.filename")}</th>
                <th data-options="field:'missionstatus',valueField:'missionstatus',width:100,sortable:true">${action.getText("import.missionstatus")}</th>
                <th data-options="field:'created',width:125,sortable:true">${action.getText("import.time")}</th>
                <th data-options="field:'finished',width:125,sortable:true">${action.getText("import.finished")}</th>
                <th data-options="field:'issuccess',width:100,sortable:true">${action.getText("import.issuccess")}</th>
                <th data-options="field:'createUserName',width:100,sortable:true">${action.getText("import.createUserName")}</th>	
                <th data-options="field:'errorfeedback',width:600,sortable:true">${action.getText("import.errorfeedback")}</th>
            </tr>              
            <tr>
            	<th><input style="width:160px;" filterName="filename"/></th>
	            <th>
                	<select style="width:85px;" filterName="missionstatus">
                		<option value="">${action.getText("import.all")}</option>
						<option value="Pending">${action.getText("import.Pending")}</option>
						<option value="Processing">${action.getText("import.Processing")}</option>
						<option value="Success">${action.getText("import.Success")}</option>
						<option value="Failure">${action.getText("import.Failure")}</option>
					</select>
                </th>
                <th><input style="width:100px;" filterName="created"/></th>
	            <th><input style="width:100px;" filterName="finished"/></th> 
	             <th>
                	<select style="width:80px;" filterName="issuccess">
                		<option value="">全部</option>
                		<option value="N">否</option>
						<option value="Y">是</option>
					</select>
                </th>
                <th><input style="width:80px;" filterName="createUserName"/></th> 
	            <th><input style="width:580px;" filterName="errorfeedback"/></th>             
            </tr>
        </thead>
    </table>
</body>
</html>
</#compress>