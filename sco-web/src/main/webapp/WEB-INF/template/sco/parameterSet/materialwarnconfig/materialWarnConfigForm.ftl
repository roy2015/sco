<#compress>
<script type="text/javascript">
	$(function() {
		$(".wt").hide();
		$("#warnTypeSelect").combobox({
			onSelect : function() {
				$(".wt").hide();
				$("." + $('#warnTypeSelect').combobox('getValue')).show();
			}
		});
		$("#thresholdValue").on('keyup', function (event) {
		    var $amountInput = $(this);
		    //响应鼠标事件，允许左右方向键移动 
		    event = window.event || event;
		    if (event.keyCode == 37 | event.keyCode == 39) {
		        return;
		    }
		    //先把非数字的都替换掉，除了数字和. 
		    $amountInput.val($amountInput.val().replace(/[^\d.]/g, "").
		        //只允许一个小数点              
		        replace(/^\./g, "").replace(/\.{2,}/g, ".").
		        //只能输入小数点后两位
		        replace(".", "$#$").replace(/\./g, "").replace("$#$", ".").replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3'));
		});
		$("#thresholdValue").on('blur', function () {
		    var $amountInput = $(this);
		    //最后一位是小数点的话，移除
		    $amountInput.val(($amountInput.val().replace(/\.$/g, "")));
		});
	});
</script>
<div style="padding: 5px; overflow: hidden;">
	<form id="materialWarnConfig_form" method="post">
		<span class="txtCenter">预警方式: </span> <input id="warnTypeSelect" class="easyui-combobox filterSelect"
			data-options="
					valueField: 'id',
					textField: 'text',
					url:'businessComBox_warnTypeList_5.html'"
			style="width: 124px;" /> <br /> <br />
		<div>
			<label class="wt YLJGHBYJ">当原料行情网站的原料价格环比变化幅度超过</label> <label class="wt YLJGTBYJ">当原料行情网站的原料价格同比变化幅度超过</label> <label id="" class="wt SPJGYJ">当受到原料行情网站的原料价格变化影响，商品价格超过</label> <label id=""
				class="wt FLYXPJGYJ">当受到原料行情网站的原料价格变化影响，辅料商品价格变化超过</label> <input type="text" class="wt YLJGHBYJ YLJGTBYJ SPJGYJ FLYXPJGYJ" id="thresholdValue" data-options="required:true,precision:3,min:0,max:999" name="thresholdValue"  style="width: 120px;" />
			<label  class="wt YLJGHBYJ">% 时通知所有采购用户</label> <label  class="wt YLJGTBYJ">% 时通知所有采购用户</label> <label class="wt SPJGYJ">% 时通知商品对应的小分类采购用户</label> <label class="wt FLYXPJGYJ">% 时通知辅料采购用户</label>
		</div>
	</form>
</div>
</#compress>
