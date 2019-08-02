package com.powere2e.sco.action.datamaintenance.purchaserdata.signedquantity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.BusinessUtils;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.signedquantity.SignedQuantityService;
import com.powere2e.sco.interfaces.service.masterdata.MerchandiseService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.signedquantity.SignedQuantity;
import com.powere2e.sco.model.masterdata.Merchandise;
import com.powere2e.sco.service.impl.common.MasterDataTypeServiceImpl;
import com.powere2e.sco.service.impl.masterdata.MerchandiseServiceImpl;
import com.powere2e.security.utils.PowerUtils;

/**
 * 签量数据维护WEB请求响应类
 * 
 * @author Gavillen.Zhou
 * @since 2015年3月18日
 * @version 1.0
 */
public class SignedQuantityAction extends WorkAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3989401046078389041L;
	private SignedQuantityService signedQuantityService;
	private MerchandiseService merchandiseService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		signedQuantityService = (SignedQuantityService) ConfigFactory
				.getInstance().getBean("signedQuantityService");
		merchandiseService = (MerchandiseService) ConfigFactory.getInstance()
				.getBean("merchandiseService");
	}

	/**
	 * 显示签量数据维护主页面
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.signedQuantity")
	public void doShowSignedQuantityMain() {
		this.putObject("qlStatusList", BusinessUtils.SINGED_QTY_LIST);
		this.forwardPage("sco/dataMaintenance/purchaserData/signedQuantity/signedQuantityGrid.ftl");
	}

	/**
	 * 签量数据维护主界面数据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.signedQuantity")
	public void doListSignedQuantity() throws Exception {
		Map<String, Object> map = this.getSignedQuantity().toMap();
		map.put("qlEndDate", this.asDate("qlEndDate"));
		List<SignedQuantity> list = this.signedQuantityService
				.listSignedQuantity(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 显示新增签量页面
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.signedQuantity.add")
	public void doShowInsSignedQtyForm() throws Exception {
		String merchandiseCodes = this.asString("merchandiseCodes");
		String supplierCodes = this.asString("supplierCodes");
		if(StringUtils.isBlank(merchandiseCodes)) return;
		
		this.putObject("merchandiseCodes", merchandiseCodes);//方便保存签量中该产品对应的信息
		this.putObject("supplierCodes", supplierCodes);// 存储改商品对应的供应商
		//查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("merchandiseCodes", merchandiseCodes);
		map.put("supplierCodes", supplierCodes);
		List<Merchandise> list = this.merchandiseService.listMerchandise(map, null);
		//添加页面所需参数
		this.putObject("sqty", new SignedQuantity());
		this.putObject("merList", list);
		this.forwardPage("sco/dataMaintenance/purchaserData/signedQuantity/signedQuantityForm.ftl");
	}
	
	/**
	 * 新增签量
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.signedQuantity.add")
	public void doInsertSignedQuantity() throws Exception {
		SignedQuantity pubData = this.getSignedQuantity();
		String qlCode = MasterDataTypeServiceImpl.getInstance().nextID("s_merchandise_ql");
		pubData.setQlCode(qlCode);
		pubData.setQlCreateby(PowerUtils.getCurrentUser().getUserId());//创建者
		String mCodes = this.asString("merchandiseCodes");//商品编号
		String sCodes = this.asString("supplierCodes");
		List<SignedQuantity> list = this.generateSignedQty(qlCode, mCodes);
		String msg = "";
		if (!list.isEmpty()) {
			msg = this.signedQuantityService.completeInsertSignedQuantity(
					this.getValidateMap(mCodes, sCodes, pubData), pubData, list);
		}
		if (StringUtils.isBlank(msg)) {
			this.forwardData(true, null, "新增成功！");
		} else {
			this.forwardData(false, null, msg);
		}
	}
	
	/**
	 * 显示改签页面
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.signedQuantity.gq")
	public void doShowGqSignedQtyForm() {
		this.searchSignedQtyData();
		this.forwardPage("sco/dataMaintenance/purchaserData/signedQuantity/signedQtyGqForm.ftl");
	}
	
	/**
	 * 显示修改签量数据界面
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.signedQuantity.edit")
	public void doShowUpdateSignedQtyForm() {
		this.searchSignedQtyData();
		this.forwardPage("sco/dataMaintenance/purchaserData/signedQuantity/signedQtyEditForm.ftl");
	}
	
	/**
	 * 显示商品添加界面
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.signedQuantity.edit")
	public void doShowAddMerchandiseForm() {
		this.forwardPage("sco/dataMaintenance/purchaserData/signedQuantity/addMerchandiseForm.ftl");
	}
	
	/**
	 * 查询勾选的商品
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.signedQuantity.edit")
	public void doAddMerchandise() {
		String merchandiseCodes = this.asString("merchandiseCodes");
		if(StringUtils.isBlank(merchandiseCodes)) {
			this.forwardData(true, "", null);
			return;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("merchandiseCodes", StrUtils.concatStr(merchandiseCodes.trim().split(",")));
		this.forwardData(true, MerchandiseServiceImpl.getInstance().listMerchandise(map, null), null);
	}
	
	/**
	 * 更新签量数据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.signedQuantity.edit")
	public void doUpdateSignedQty() throws Exception {
		SignedQuantity pubData = this.getSignedQuantity();
		String mCodes = this.asString("merchandiseCodes");//商品编号
		String sCodes = this.asString("supplierCodes");
		pubData.setMerchandiseCode(mCodes);
		List<SignedQuantity> list = this.generateSignedQty(pubData.getQlCode(), mCodes);
		if(!list.isEmpty()) {
			String msg = this.signedQuantityService.completeUpdateSignedQty(
					this.getValidateMap(mCodes, sCodes, pubData), pubData, list);
			if (StringUtils.isEmpty(msg)) {
				this.forwardData(true, null, "签量修改成功！");
				return ;
			} else {
				this.forwardData(false, null, msg);
				return ;
			}
		}
		this.forwardData(true, null, "签量修改成功！");
	}
	
	/***
	 * 改签签量单据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.signedQuantity.gq")
	public void doCompleteGqSignedQty() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String oldCode = this.asString("qlCode");
		map.put("qlCode", oldCode);//旧的签量编号
		map.put("qlStatus", StrUtils.concatStr(
				BusinessConstants.signedQuantityType.A.toString(),
				BusinessConstants.signedQuantityType.S.toString()
				));
		SignedQuantity pubData = this.signedQuantityService.searchSignedQtyMain(map);//签量主信息
		if(pubData == null) {
			this.forwardData(false, null, "该签量单状态已发生变动");
			return ;
		}
		
		List<SignedQuantity> list = this.signedQuantityService.listSignedQtyDetail(map);//明细信息
		String qlCode = MasterDataTypeServiceImpl.getInstance().nextID("s_merchandise_ql");
		pubData.setQlCode(qlCode);//新的签量编号
		
		for (SignedQuantity sqty : list) {
			String merchandiseCode = sqty.getMerchandiseCode();
			String remark = this.asString(merchandiseCode+"_bak");//设置备注
			sqty.setMerchandiseCode("'"+merchandiseCode+"'");
			sqty.setSupplierCode("'"+sqty.getSupplierCode()+"'");
			sqty.setQlCode(qlCode);
			sqty.setRemarks(remark);
		}
		pubData.setQlCount(new BigDecimal(this.asString("qlCount")));//设置签量总量
		pubData.setQlStatus(BusinessConstants.signedQuantityType.S.toString());
		try {
			this.signedQuantityService.completeGqSignedQty(oldCode, pubData, list);
		} catch (Exception e) {
			this.forwardData(false, null, "改签失败");
			return ;
		}
		this.forwardData(true, null, "改签成功！");
	}
	
	/***
	 * 删除签量单据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.signedQuantity.del")
	public void doDeleteSignedQuantity() {
		this.signedQuantityService.deleteSignedQuantity(this.asString("qlCode"));
		this.forwardData(true, null, "删除成功！");
	}
	
	/**
	 * 终止签量
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.signedQuantity.terQL")
	public void doTerSignedQuantity() {
		this.signedQuantityService.completeTerSignedQuantity(this.asString("qlCode"));
		this.forwardData(true, null, "签量终止成功！");
	}

	/**
	 * 撤销终止签量
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.signedQuantity.revokeTer")
	public void doRevokerTerSignedQuantity() {
		this.signedQuantityService.completeRevokerTerSignedQuantity(this.asString("qlCode"));
		this.forwardData(true, null, "撤销签量终止成功！");
	}
	
	/**
	 * 查询签量数据
	 */
	private void searchSignedQtyData() {
		String qlCode = this.asString("qlCode");
		if(StringUtils.isBlank(qlCode)) {
			throw new EscmException("不可选择状态为空、已改签或已终止的签量单");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qlCode", qlCode);
		map.put("qlStatus", StrUtils.concatStr(
				BusinessConstants.signedQuantityType.A.toString(),
				BusinessConstants.signedQuantityType.S.toString()
				));
		SignedQuantity sqty = this.signedQuantityService.searchSignedQtyMain(map);//签量主信息
 		List<SignedQuantity> list = this.signedQuantityService.listSignedQtyDetail(map);//明细信息
 		this.putObject("qlCode", qlCode);
 		this.putObject("merchandiseCodes", sqty.getMerchandiseCode());
 		this.putObject("supplierCodes", sqty.getSupplierCode());
 		this.putObject("sqty", sqty);
		this.putObject("merList", list);
	}
	
	/**
	 * 用于验证签量单是否有重复
	 * 
	 * @param mCodes
	 *            商品编码
	 * @param sCodes
	 *            供应商编码
	 * @param pubData
	 *            公共数据(签量日期、状态)
	 * @return 查询条件map
	 */
	private Map<String, Object> getValidateMap(String mCodes, String sCodes, SignedQuantity pubData) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("merchandiseCodes", mCodes.toUpperCase());
		map.put("supplierCodes", sCodes.toUpperCase());
		map.put("qlStartDate", pubData.getQlStartDate());
		map.put("qlEndDate", pubData.getQlStartDate());
		map.put("valStatus", StrUtils.concatStr(
				BusinessConstants.signedQuantityType.A.toString(),
				BusinessConstants.signedQuantityType.S.toString(),
				BusinessConstants.signedQuantityType.T.toString()
				));
		return map;
	}
	
	/**
	 * 导出签量数据Excel
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.signedQuantity.export")
	public void doExportSignedQtyExcel() throws Exception {
		ServletOutputStream out = response.getOutputStream();
		String fileName = "签量记录_".concat(DateUtils.formateDateTime()).concat(".xlsx");
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ new String(fileName.getBytes(Constant.DEFAULT_ENCODED_1), Constant.DEFAULT_ENCODED_2) + "\"");
		this.signedQuantityService.exportSignedQtyExcel(this
				.getSignedQuantity().toMap(), out);
	}
	
	/**
	 * 整理前台的数据
	 * 
	 * @param qlCode
	 * 			  签量编号  
	 * @param mCodes
	 *            商品编码
	 * @return
	 * @throws Exception
	 */
	private List<SignedQuantity> generateSignedQty(String qlCode, String mCodes) throws Exception {
		String[] mCode = mCodes.split(",");//新增签量的商品
		String[] sCode = this.asString("supplierCodes").split(",");//商品所对应供应商ID
		List<SignedQuantity> list = new ArrayList<SignedQuantity>();
		//设置商品对应信息
		for (int i = 0; i < mCode.length; i++) {
			double qlPrice = this.asDouble(mCode[i].concat("_price"));
			String bak = this.asString(mCode[i].concat("_bak"));
			list.add(new SignedQuantity(qlCode, mCode[i], sCode[i], new BigDecimal(qlPrice), bak));
		}
		return list;
	}
	
	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private SignedQuantity getSignedQuantity() throws Exception {
		SignedQuantity signedQuantity = new SignedQuantity();
		this.asBean(signedQuantity);
		return signedQuantity;
	}

}
