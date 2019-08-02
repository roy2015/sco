<#compress>
<script type="text/javascript" >
	<#include "sco/merchandiseCostAnalysis/accountingIngredient/accounting/accountingUpload.js" />
</script>

<link rel="stylesheet" href="css/table.min.css" type="text/css" />

<div style="padding: 5px;overflow: hidden;">
	<form id="uploadMDForm" method="post" enctype="multipart/form-data">
    	<font color="red">*</font>
    	<input type="text" id="viewfileSp" class="file" style="width: 0px;" name="viewfileOmr" />
        <input type="file" id="file" style="width:200px;height:19px;vertical-align:middle" multiple="true" class="input_file" id="uploadSp" name="upload" onchange="accountingUploadFn.upFile(this)" />
		<input class="input_file" type="button" value="导入" id="subBtn" onClick="accountingUploadFn.subForm()" style="margin-top:0px;*margin-left:0px;height:19px;vertical-align:middle"/>
	</form>
	<div>
		<p/>
		<span id="msg"></span>
	</div>
</div>
</#compress>