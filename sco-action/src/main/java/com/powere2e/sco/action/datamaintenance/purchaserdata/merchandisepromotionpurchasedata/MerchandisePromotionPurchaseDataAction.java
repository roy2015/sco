package com.powere2e.sco.action.datamaintenance.purchaserdata.merchandisepromotionpurchasedata;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.common.utils.UploadUtils;
import com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.merchandisepromotionpurchasedata.MerchandisePromotionPurchaseDataService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.merchandisepromotionpurchasedata.MerchandisePromotionPurchaseData;

/**
 * 商品促销进货价格维护的WEB请求响应类
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年12月9日
 */
public class MerchandisePromotionPurchaseDataAction extends UploadUtils {
	/**
	 * 
	 */
	private static final long serialVersionUID = 212707905100112232L;
	private MerchandisePromotionPurchaseDataService merchandisePromotionPurchaseDataService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		merchandisePromotionPurchaseDataService = (MerchandisePromotionPurchaseDataService) ConfigFactory.getInstance().getBean("merchandisePromotionPurchaseDataService");
	}

	/**
	 * 商品促销进货价格维护列表grid
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.merchandisepromotionpurchasedata")
	public void doShowMerchandisePromotionPurchaseDataGrid() throws Exception {
		this.forwardPage("sco/dataMaintenance/purchaserData/merchandisePromotionPurchaseData/merchandisePromotionPurchaseDataGrid.ftl");
	}

	/**
	 * 商品促销进货价格维护列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.merchandisepromotionpurchasedata")
	public void doListMerchandisePromotionPurchaseData() throws Exception {
		Map<String, Object> map = getMerchandisePromotionPurchaseData().toMap();
		List<MerchandisePromotionPurchaseData> list = merchandisePromotionPurchaseDataService.listMerchandisePromotionPurchaseData(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 删除商品促销进货价格维护
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.merchandisepromotionpurchasedata.delete")
	public void doDeleteMerchandisePromotionPurchaseData() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String rows = this.asString("param");
		JSONArray jsonArr = JSONArray.fromObject(rows);
		MerchandisePromotionPurchaseData[] dataArray = new MerchandisePromotionPurchaseData[jsonArr.size()];
		for (int j = 0; j < jsonArr.size(); j++) {
			dataArray[j] = (MerchandisePromotionPurchaseData) JSONObject.toBean(jsonArr.getJSONObject(j), MerchandisePromotionPurchaseData.class);
		}
		map.put("list", dataArray);
		merchandisePromotionPurchaseDataService.deleteMerchandisePromotionPurchaseData(map);
		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 显示上传商品促销进货价格维护界面
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.gradecontroldata.merchandiseunqualified.upload")
	public void doShowMerchandisePromotionPurchaseDataForm() {
		this.forwardPage("sco/dataMaintenance/purchaserData/merchandisePromotionPurchaseData/merchandisePromotionPurchaseDataForm.ftl");
	}

	/**
	 * 下载导入模板
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doDownloadMerchandisePromotionPurchaseDataTemplate() throws Exception {
		this.forwardDownload("excel/sco/dataMaintenance/purchaserData/merchandisePromotionPurchaseData/MerchandisePromotionPurchaseData.xlsx", ExcelUtils.getEncodeFileName("商品促销进货价格") + ".xlsx");
	}

	/**
	 * 导入商品促销进货价格维护
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.gradecontroldata.merchandiseunqualified.upload")
	public void doCompleteImportMerchandisePromotionPurchaseData() {
		List<File> fileList = this.doUpload("XLSX");
		File file = null;
		try {
			file = fileList.get(0);
		} catch (Exception e) {
			this.forwardData(false, null, e.getMessage());
			return;
		}
		String msg = this.merchandisePromotionPurchaseDataService.completeImportMerchandisePromotionPurchaseData(file);
		if (StringUtils.isBlank(msg)) {
			this.forwardData(true, null, "文件导入成功！");
		} else {
			this.forwardData(false, null, msg);
		}
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private MerchandisePromotionPurchaseData getMerchandisePromotionPurchaseData() throws Exception {
		MerchandisePromotionPurchaseData merchandisePromotionPurchaseData = new MerchandisePromotionPurchaseData();
		this.asBean(merchandisePromotionPurchaseData);
		return merchandisePromotionPurchaseData;
	}
}
