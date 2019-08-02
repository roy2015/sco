package com.powere2e.sco.action.accessoryoaapplication.committeeApply.supplierattachment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.committeeApply.supplierattachment.QuotedElectronicOfScanService;
import com.powere2e.sco.model.accessoryoaapplication.supplierattachment.QuotedElectronicOfScan;

/**
 * 关联辅料报价单-电子和扫描--辅料OA的WEB请求响应类
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月5日
 */
public class QuotedElectronicOfScanAction extends WorkAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2810008258650007519L;
	private QuotedElectronicOfScanService quotedElectronicOfScanService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		quotedElectronicOfScanService = (QuotedElectronicOfScanService) ConfigFactory.getInstance().getBean("quotedElectronicOfScanService");
	}

	/**
	 * 关联辅料报价单-电子和扫描--辅料OA列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListQuotedElectronicOfScan() throws Exception {
		Map<String, Object> map = getQuotedElectronicOfScan().toMap();
		List<QuotedElectronicOfScan> list = quotedElectronicOfScanService.listQuotedElectronicOfScan(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 下载上传文件
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doDownloadQuotedElectronicOfScanFile() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("path", java.net.URLDecoder.decode(this.asString("path"),"UTF-8"));
		List<QuotedElectronicOfScan> list = quotedElectronicOfScanService.listQuotedElectronicOfScan(map, null);
		String name = "";
		name += list.get(0).getIntentionName() + "-";
		if (list.get(0).getSupplierName() != "" && list.get(0).getSupplierName() != null) {
			name += list.get(0).getSupplierName() + "-";
		} else {
			name += list.get(0).getIntentionSupplierName() + "-";
		}
		name += "扫描版报价单."+list.get(0).getPath().substring(list.get(0).getPath().lastIndexOf(".") + 1, list.get(0).getPath().length());;;
		this.forwardDownload("upload/" + java.net.URLDecoder.decode(this.asString("path"),"UTF-8"), ExcelUtils.getEncodeFileName(name));
	}

	/**
	 * 根据ID号获一个关联辅料报价单-电子和扫描--辅料OA
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doLoadQuotedElectronicOfScan() throws Exception {
		QuotedElectronicOfScan quotedElectronicOfScan = this.quotedElectronicOfScanService.loadQuotedElectronicOfScan(asString("applicationCode"));
		this.forwardData(true, quotedElectronicOfScan, null);
	}

	/**
	 * 添加关联辅料报价单-电子和扫描--辅料OA界面
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doShowInsertQuotedElectronicOfScanForm() throws Exception {
		QuotedElectronicOfScan quotedElectronicOfScan = new QuotedElectronicOfScan();
		this.putObject("quotedElectronicOfScan", quotedElectronicOfScan);
		this.forwardPage("quotedElectronicOfScan/quotedElectronicOfScanForm.ftl");
	}

	/**
	 * 修改关联辅料报价单-电子和扫描--辅料OA界面
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doShowUpdateQuotedElectronicOfScanForm() throws Exception {
		QuotedElectronicOfScan quotedElectronicOfScan = quotedElectronicOfScanService.loadQuotedElectronicOfScan(asString("applicationCode"));
		this.putObject("quotedElectronicOfScan", quotedElectronicOfScan);
		this.forwardPage("quotedElectronicOfScan/quotedElectronicOfScanForm.ftl");
	}

	/**
	 * 添加关联辅料报价单-电子和扫描--辅料OA
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doInsertQuotedElectronicOfScan() throws Exception {
		String msg = "";
		boolean bool = false;
		List<QuotedElectronicOfScan> list = new ArrayList<QuotedElectronicOfScan>();
		String[] supplierCode = this.asString("supplierCode").split(",");
		String[] applicationCode = this.asString("applicationCode").split(",");
		String[] intentionCode = this.asString("intentionCode").split(",");
		String[] quotedCodeScan = this.asString("quotedCodeScan").split(",");
		String[] quotedCodeElectronic = this.asString("quotedElectronicCode").split(",");
		String type = this.asString("type");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("quotedCodes", this.asString("quotedCode"));
		map.put("applicationCode", applicationCode[0]);
		quotedElectronicOfScanService.validateOaStatus(map);
		for (int i = 0; i < quotedCodeScan.length; i++) {
			QuotedElectronicOfScan quotedElectronicOfScan = new QuotedElectronicOfScan();
			quotedElectronicOfScan.setApplicationCode(applicationCode.length == 0 ? "" : applicationCode[i]);
			quotedElectronicOfScan.setIntentionCode(intentionCode.length == 0 ? "" : intentionCode[i]);
			quotedElectronicOfScan.setQuotedCodeScan(quotedCodeScan.length == 0 ? "" : quotedCodeScan[i]);
			quotedElectronicOfScan.setQuotedCodeElectronic(quotedCodeElectronic.length == 0 ? "" : quotedCodeElectronic[i]);
			quotedElectronicOfScan.setSupplierCode(supplierCode.length == 0 ? "" : supplierCode[i]);
			if (quotedElectronicOfScanService.listQuotedElectronicOfScan(quotedElectronicOfScan.toMap(), null).size() == 0) {
				list.add(quotedElectronicOfScan);
			}
		}
		if (list.size() != 0) {
			map.put("list", list);
			quotedElectronicOfScanService.insertQuotedElectronicOfScan(map);
			msg = this.getText("public.success");
			bool = true;
		} else {
			msg = "数据重复";
		}
		this.forwardData(bool, null, msg);
	}

	/**
	 * 删除关联辅料报价单-电子和扫描--辅料OA
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doDeleteQuotedElectronicOfScan() throws Exception {
		String[] applicationCode = this.asString("applicationCode").split(",");
		String[] supplierCode = this.asString("supplierCode").split(",");
		String[] quotedCodeScan = this.asString("quotedCodeScan").split(",");
		String type = this.asString("type");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("quotedCodes", this.asString("quotedCode"));
		map.put("applicationCode", applicationCode[0]);
		quotedElectronicOfScanService.validateOaStatus(map);
		List<QuotedElectronicOfScan> list = new ArrayList<QuotedElectronicOfScan>();
		for (int i = 0; i < applicationCode.length; i++) {
			QuotedElectronicOfScan quotedElectronicOfScan = new QuotedElectronicOfScan();
			quotedElectronicOfScan.setQuotedCodeScan(quotedCodeScan[i]);
			quotedElectronicOfScan.setApplicationCode(applicationCode[i]);
			quotedElectronicOfScan.setSupplierCode(supplierCode[i]);
			list.add(quotedElectronicOfScan);
		}
		map.put("list", list);
		quotedElectronicOfScanService.deleteQuotedElectronicOfScan(map);
		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private QuotedElectronicOfScan getQuotedElectronicOfScan() throws Exception {
		QuotedElectronicOfScan quotedElectronicOfScan = new QuotedElectronicOfScan();
		this.asBean(quotedElectronicOfScan);
		return quotedElectronicOfScan;
	}
}
