<#compress>
<script type="text/javascript" >
	<#include "sco/dataMaintenance/gradecontrolData/merchandiseUnqualified/merchandiseUnqualifiedUpload.js" />
</script>
<link rel="stylesheet" href="css/table.min.css" type="text/css" />
<div style="padding: 5px;overflow: hidden;">
	<form id="uploadMDForm" method="post" enctype="multipart/form-data">
		
		<label style="color:red;">*</label>
		<input type="text" id="viewfileSp" class="file" style="width: 0px;" name="viewfileOmr" />
        <input type="file" id="file" style="width:184px;vertical-align:middle" class="input_file" name="upload" onchange="document.getElementById('viewfileSp').value=this.value;" />
		&nbsp;
		
		<input class="input_file" type="button" value="导入" id="subBtn" onClick="merchandiseUnqualifiedUploadFn.subForm()" style="*margin-top:-5px;*margin-left:0px;height:19px;vertical-align:middle" />
		<a onclick="merchandiseUnqualifiedUploadFn.downloadExcelMold()" plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'">
			下载上传模板
		</a>
	</form>
	<div>
		<p/>
		<span id="msg"></span>
	</div>
</div>
</#compress>