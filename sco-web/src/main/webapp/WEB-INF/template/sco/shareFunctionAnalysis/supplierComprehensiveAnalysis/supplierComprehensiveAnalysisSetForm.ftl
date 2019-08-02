<#compress>
<script type="text/javascript">
	<#include "sco/common/masterDataType.js" />
</script>

<div style="padding: 5px;overflow: hidden">
	<div id="haveSupplierComprehensiveAnalysis">
		<form id="supplierComprehensiveAnalysisSet_form" method="post" enctype="multipart/form-data">
			<table class="tableForm">
				<tr>
					<td><font color="red">*</font>数据时间范围:</td>
					<td>
					    <input class="easyui-datebox"  name="minDate" id="minDate" data-options="editable:false,onHidePanel:utils.dateFilter.setMaxDateValue,formatter:yearFormatter"  style="width: 124px;" /> 
				    </td>
				    <td>至:</td>
					<td>
					    <input class="easyui-datebox"  name="maxDate" id="maxDate" data-options="editable:false,onHidePanel:utils.dateFilter.setMinDateValue,formatter:yearFormatter" style="width: 124px;" />
				    </td>
				    <td><font color="red">*</font>查看同比:</td>
				    <td>
						<select id="years" name="years" panelHeight="auto" style="width:50px;" class="easyui-combobox" data-options="required:true" >
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
						</select>
						年数据
					</td>
				</tr>
				<tr>
					<td>小分类:</td>
					<td>     
						<input class="easyui-combobox filterSelect" id="smallTypeCode" filterName="smallTypeCode" style="width: 124px;"
								data-options="
								valueField:'id',
								textField:'text',
								editable:false,
								url:'masterDataType_listSmallType_5.html'">
						</input>			
					</td>
					<td >明细类:</td>
					<td cospan="3">
						<input class="easyui-combobox filterSelect" id="detailTypeCode" filterName="detailTypeCode" style="width: 124px;"
								data-options="
								valueField:'id',
								textField:'text',
								editable:false">
						</input>			
					</td>  
				</tr>
				<tr>
				    <td>销售表现:</td>
					<td >
						<select id="xsbx" name="xsbx"  style="width:124px;"  class="easyui-combobox" data-options="multiple:true" >
							<option value="销售量">销售量</option>
							<option value="销售金额">销售金额</option>
							<option value="毛利额">毛利额</option>
							<option value="销售量占比(占所有商品)">销售量占比(占所有商品)</option>
							<option value="销售金额占比(占所有商品)">销售金额占比(占所有商品)</option>
							<option value="毛利额占比(占所有商品)">毛利额占比(占所有商品)</option>
							<option value="销售量占比(占小分类)">销售量占比(占小分类)</option>
							<option value="销售金额占比(占小分类)">销售金额占比(占小分类)</option>
							<option value="毛利额占比(占小分类)">毛利额占比(占小分类)</option>
							<option value="销售量占比(占明细类)">销售量占比(占明细类)</option>
							<option value="销售金额占比(占明细类)">销售金额占比(占明细类)</option>
							<option value="毛利额占比(占明细类)">毛利额占比(占明细类)</option>
							<option value="PSD销量">PSD销量</option>
							<option value="PSD销售金额">PSD销售金额</option>
							<option value="PSD毛利">PSD毛利</option>
							<option value="权限数">权限数</option>
							<option value="权限店天">权限店天</option>
							<option value="销售店天">销售店天</option>
							<option value="活跃度">活跃度</option>
							<option value="ABCD门店数">ABCD门店数</option>
						</select>
					</td>
					<td>质量情况:</td>
					<td cospan="3">
						<select id="zlqk" name="zlqk"   style="width:124px;"   class="easyui-combobox" data-options="multiple:true" >
							<option value="质量星级">质量星级</option>
							<option value="召回次数">召回次数</option>
							<option value="召回SKU个数">召回SKU个数</option>
							<option value="抽检不合格次数">抽检不合格次数</option>
							<option value="抽检不合格SKU数">抽检不合格SKU数</option>
							<option value="供应商年度千万元客诉">供应商年度千万元客诉</option>
							<option value="供应商巡厂评分">供应商巡厂评分</option>
							<option value="供应商考评得分">供应商考评得分</option>
							<option value="供应商考评得分排行">供应商考评得分排行</option>
						</select>
					</td>
				</tr>
					
				<tr>
				    <td>送货情况:</td>
					<td >
						<select id="jhqk" name="jhqk"   style="width:124px;"   class="easyui-combobox" data-options="multiple:true" >
							<option value="进货量">进货量</option>
							<option value="进货额">进货额</option>
						</select>
					</td>
					<td>送货表现:</td>
					<td >
						<select id="shbx" name="shbx"   style="width:124px;"   class="easyui-combobox" data-options="multiple:true" >
							<option value="订单及时率">订单及时率</option>
							<option value="订单满足率">订单满足率</option>
							<option value="让步接收数量">让步接收数量</option>
							<option value="让步接收次数">让步接收次数</option>
							<option value="供应商实际库存">供应商实际库存</option>
							<option value="供应商安全库存">供应商安全库存</option>
						</select>
					</td>
					<td >新品配合情况:</td>
					<td >
						<select id="xpphqk" name="xpphqk"   style="width:124px;"   class="easyui-combobox" data-options="multiple:true" >
							<option value="供应商意向品数">供应商意向品数</option>
						</select>
					</td>
				</tr>
				  <!--  <tr>
				    <th align="right" style="width: 55px;"><span class="txtCenter">是否查看所有报价数量</span></th>
					<td>
						<select id="allbjsl" name="allbjsl" panelHeight="auto"  style="width:124px;"   class="easyui-combobox" data-options="required:true, onChange:totalcostanalysisFn.doShowBjsl " >
							<option value="是">是</option>
							<option value="否">否</option>
						</select>
					</td>
				   <td><span ></span></td>
				</tr>
				<tr id="txbjsl" style="display:none;">
					<th align="right" style="width: 55px;"><span class="txtCenter"><font color="red">*</font>只查看报价数量:</span></th>
					<td><input type="text" class="easyui-validatebox" data-options="required:true,validType:'length[0,10]'"  id="zckbjsl" name="zckbjsl" style="width: 120px;"  /></td>
				</tr>
			  	<tr>
			    	<th align="right" style="width: 55px;"><span class="txtCenter">查看哪些供应商资质信息:</span></th>
					<td>
						<select id="zzxx" name="zzxx" panelHeight="auto"  style="width:124px;"   class="easyui-combobox" data-options="required:true,multiple:true" >
							 <option value="all">全选</option>
							 <option value="supplierCode">供应商编号-供应商名称</option>
							 <option value="contacts">联系人</option>
							 <option value="phone">联系方式</option>
							 <option value="companySite">公司地址</option>
							 <option value="factorySite">工厂地址</option>
							 <option value="registerCapital">注册资金</option>
							 <option value="yearTurnover">年营业额</option>
							 <option value="factoryArea">工厂面积</option>
							 <option value="staffCount">工人数</option>
							 <option value="dailyCapacity">日产能</option>
							 <option value="hzgpp">合作过品牌</option>
							 <option value="invoiceType">发票类型(增/普票)</option>
							 <option value="sl">税率</option>
							 <option value="paymentType">付款方式</option>
						</select>
					</td>
				   	<td><span ></span></td>
				</tr>
				<tr>
				    <th align="right" style="width: 55px;"><span class="txtCenter">查看哪些样品信息:</span></th>
					<td>
						<select id="ypxx" name="ypxx" panelHeight="auto"  style="width:124px;"   class="easyui-combobox" data-options="required:true" >
								 <option value="全选">全选</option>
								 <option value="产前样">产前样</option>
								 <option value="非产前样">非产前样</option>
						</select>
					</td>
				   <td><span ></span></td>
				</tr>
			 	<tr>
				    <th align="right" style="width: 55px;"><span class="txtCenter">是否将增值发票报价转换为普通发票报价:</span></th>
					<td>
						<select id="zhfp" name="zhfp" panelHeight="auto"  style="width:124px;"   class="easyui-combobox" data-options="required:true" >
							 <option value="是">是</option>
							 <option value="否">否</option>
						</select>
					</td>
				   <td><span ></span></td>
				</tr>
			 	<tr>
				    <th align="right" style="width: 55px;"><span class="txtCenter">请填写以下询价单号对应的实际采购数量:</span></th>
					<td>
						<span ></span>
					</td>
				   	<td><span ></span></td>
				</tr>
				<#list enquiryCodeList as enquiryCode>
				  <tr>
				    <th align="right" style="width: 55px;"><span class="txtCenter">${enquiryCode}</span></th>
					<td>
						<input type="text" class="easyui-numberbox" data-options="min:0,max:9999999999"  id="${enquiryCode}" name="${enquiryCode}" style="width: 120px;"  />
					</td>
				   <td><span ></span></td>
				</tr>
				</#list> --!>
			  	<!-- <tr>
					<th align="right" style="width: 55px;"><span class="txtCenter">是否对意向品的最后一个询价单的所有报价数量分别进行总计:</span></th>
					<td>
					<select id="allxjd" name="allxjd" panelHeight="auto"  style="width:124px;"   class="easyui-combobox" data-options="required:true" >
					<option value="是">是</option>
					<option value="否">否</option>
					</select>
					</td>
					<td><span >(注:选“否”则只对所选意向品的所选询价单中的创建时间最晚的询价单的报价进行合计)</span></td>
				</tr> -->
			</table>
		</form>
	</div>
 </div>
</#compress>