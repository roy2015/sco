package com.powere2e.sco.action.accessoryoaapplication.committeeApply.supplierattachment;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.config.ConfigPath;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.common.utils.PathUtils;
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.sco.common.utils.UploadUtils;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.committeeApply.supplierattachment.SupplierAttachmentAService;
import com.powere2e.sco.model.accessoryoaapplication.supplierattachment.SupplierAttachmentA;

/**
 * 供应商证件--辅料OA的WEB请求响应类
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月4日
 */
public class SupplierAttachmentAAction extends UploadUtils {
	/**
	 * 
	 */
	private static final long serialVersionUID = 413519397993425893L;
	private SupplierAttachmentAService supplierAttachmentAService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		supplierAttachmentAService = (SupplierAttachmentAService) ConfigFactory.getInstance().getBean("supplierAttachmentAService");
	}

	/**
	 * 报价记录列表--辅料OA列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListQuotedRecord() throws Exception {
		Map<String, Object> map = getSupplierAttachmentA().toMap();
		map.put("created", this.asString("created"));
		map.put("updated", this.asString("updated"));
		map.put("quotedCode", this.asString("quotedCode").split(","));
		List<SupplierAttachmentA> list = supplierAttachmentAService.listQuotedRecord(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 显示报价单
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doShowaccessoryQuotedScanGrid() throws Exception {
		//this.putObject("enquiryCode", this.asString("enquiryCode"));
		//this.putObject("supplierCode", this.asString("supplierCode"));
		String enquiryCodes="";
		String supplierCodes="";
		String rows = this.asString("rows");
		JSONArray jsonArr = JSONArray.fromObject(rows);
		SupplierAttachmentA[] dataArray = new SupplierAttachmentA[jsonArr.size()];
		for (int j = 0; j < jsonArr.size(); j++) {
			dataArray[j] = (SupplierAttachmentA) JSONObject.toBean(jsonArr.getJSONObject(j), SupplierAttachmentA.class);
		}
		for (int i = 0; i < dataArray.length; i++) {
			if (i == dataArray.length - 1) {
				enquiryCodes += dataArray[i].getEnquiryCode();
				supplierCodes+= "'"+dataArray[i].getIntentionSupplierCode()+"'";
			} else {
				enquiryCodes += dataArray[i].getEnquiryCode() + ",";
				supplierCodes += "'"+dataArray[i].getIntentionSupplierCode()+"'" + ",";
			}
		}
		this.putObject("enquiryCodes", enquiryCodes);
		this.putObject("supplierCodes", supplierCodes);
		this.forwardPage("sco/accessoryOaApplication/committeeApply/supplierAttachment/accessoryQuotedScanGrid.ftl");
	}

	/**
	 * 供应商证件--辅料OA列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListSupplierAttachmentA() throws Exception {
		Map<String, Object> map = getSupplierAttachmentA().toMap();
		map.put("created", this.asString("created"));
		map.put("updated", this.asString("updated"));
		List<SupplierAttachmentA> list = supplierAttachmentAService.listSupplierAttachmentA(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 根据ID号获一个供应商证件--辅料OA
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doLoadSupplierAttachmentA() throws Exception {
		Map<String, Object> map = getSupplierAttachmentA().toMap();
		SupplierAttachmentA supplierAttachmentA = this.supplierAttachmentAService.loadSupplierAttachmentA(map);
		this.forwardData(true, supplierAttachmentA, null);
	}

	/**
	 * 导入供应商附件数据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doCompleteImportsupplierAttachmentA() throws Exception {
		SupplierAttachmentA supplierAttachmentA = getSupplierAttachmentA();
		String type = this.asString("type");
		boolean result = true;
		String success = this.getText("public.success");
		Map<String, Object> map = supplierAttachmentA.toMap();
		map.put("type", type);
		map.put("quotedCodes", this.asString("quotedCodeScan"));
		map.put("applicationCode", this.asString("applicationCode"));
		supplierAttachmentAService.validateOaStatus(map);
		String supAttFilePath = PathUtils.getSupplierAttachmentFileUploadPath();
		List<File> fList = this.doUploadBySaveDir(supAttFilePath.concat("/"));
		if (!fList.isEmpty()) {
			supplierAttachmentA.setPath(supAttFilePath.replaceAll(ConfigPath.getUploadFilePath(), "").concat(fList.get(0).getName()));// 文件路径
			map.put("supplierCode", this.asString("supplierCode"));
			supplierAttachmentA.setFileName(StrUtils.getFileResourceName(fList.get(0).getName()));
			SupplierAttachmentA info=supplierAttachmentAService.loadSupplierAttachmentA(map);
			if (info != null) {
				List<SupplierAttachmentA> list = new ArrayList<SupplierAttachmentA>();
				list.add(supplierAttachmentA);
				map.put("list", list);
				map.put("paths", info.getPath());
				supplierAttachmentAService.deleteSupplierAttachmentA(map);
			}
			supplierAttachmentAService.insertSupplierAttachmentA(supplierAttachmentA);
		}
		this.forwardData(result, null, success);
	}

	/**
	 * 下载上传文件
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doDownloadFile() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("path", java.net.URLDecoder.decode(this.asString("path"),"UTF-8"));
		map.put("path", this.asString("path"));
		List<SupplierAttachmentA> list = supplierAttachmentAService.listSupplierAttachmentA(map, null);
		SupplierAttachmentA s = list.get(0);
		String name = "";
		if (s.getIntentionName() != "" && s.getIntentionName() != null) {
			name += s.getIntentionName() + "-";
		}
		if (s.getIntentionSupplierName() != "" && s.getIntentionSupplierName() != null) {
			name += s.getIntentionSupplierName() + "-";
		} else {
			name += s.getSupplierName() + "-";
		}
		name += s.getElseType()+ "." + s.getFileName().substring(s.getFileName().lastIndexOf(".") + 1, s.getFileName().length());;
		this.forwardDownload("upload/"+this.asString("path"), ExcelUtils.getEncodeFileName(name));
	}

	/**
	 * 删除供应商附件-辅料OA
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doDeleteSupplierAttachmentA() throws Exception {
		String type = this.asString("type");
		List<SupplierAttachmentA> list = new ArrayList<SupplierAttachmentA>();
		String[] applicationCode = this.asString("applicationCode").split(",");
		String[] intentionCodes = this.asString("intentionCodes").split(",");
		String[] supplierCodes = this.asString("supplierCodes").split(",");
		String[] fileTypes = this.asString("fileTypes").split(",");
		String[] elseTypes = this.asString("elseTypes").split(",");
		String paths = this.asString("paths");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("quotedCodes", this.asString("quotedCodeScan"));
		map.put("applicationCode", applicationCode[0]);
		supplierAttachmentAService.validateOaStatus(map);
		for (int i = 0; i < applicationCode.length; i++) {
			SupplierAttachmentA supplierAttachmentA = new SupplierAttachmentA();
			supplierAttachmentA.setApplicationCode(applicationCode[i]);
			supplierAttachmentA.setIntentionCode(intentionCodes[i]);
			supplierAttachmentA.setSupplierCode(supplierCodes[i]);
			supplierAttachmentA.setFileType(fileTypes[i]);
			supplierAttachmentA.setElseType(elseTypes[i]);
			list.add(supplierAttachmentA);
		}
		map.put("list", list);
		map.put("paths", paths);
		supplierAttachmentAService.deleteSupplierAttachmentA(map);
		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private SupplierAttachmentA getSupplierAttachmentA() throws Exception {
		SupplierAttachmentA supplierAttachmentA = new SupplierAttachmentA();
		this.asBean(supplierAttachmentA);
		return supplierAttachmentA;
	}
}
