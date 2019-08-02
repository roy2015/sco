package com.powere2e.sco.action.merchandiseoaapplication.reportnewoaapplication.supplierattachment;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.config.ConfigPath;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.sco.common.service.merchandiseoaapplication.MerchandiseOaApplicationUtil;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.common.utils.PathUtils;
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.sco.common.utils.UploadUtils;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportnewoaapplication.supplierattachment.SupplierAttachmentMService;
import com.powere2e.sco.model.masterdata.Merchandise;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationMerchandise;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.supplierattachment.SupplierAttachmentM;

/**
 * 供应商附件的WEB请求响应类
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月21日
 */
public class SupplierAttachmentMAction extends UploadUtils {
	/**
	 * 
	 */
	private static final long serialVersionUID = -14152213007729356L;
	private SupplierAttachmentMService supplierAttachmentMService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		supplierAttachmentMService = (SupplierAttachmentMService) ConfigFactory.getInstance().getBean("supplierAttachmentMService");
	}

	/**
	 * 供应商附件列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListSupplierAttachmentM() throws Exception {
		Map<String, Object> map = getSupplierAttachmentM().toMap();
		map.put("intentionCodes", this.asString("intentionCodes"));
		map.put("intentionSupplierCodes", this.asString("intentionSupplierCodes"));
		List<SupplierAttachmentM> list = supplierAttachmentMService.listSupplierAttachmentM(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 供应商附件列表(投料。核算)
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListSupplierAttachmentMInFrom() throws Exception {
		Map<String, Object> map = getSupplierAttachmentM().toMap();
		List<ApplicationMerchandise> applicationMerchandises = MerchandiseOaApplicationUtil.getIntentionAndSupplierCodeGroupList(this.asString("intentionAndSupplierCodes"));
		map.put("list", applicationMerchandises);
		List<SupplierAttachmentM> list = supplierAttachmentMService.listSupplierAttachmentMInFrom(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 意向品，供应商列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListIntentionSupplier() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ApplicationMerchandise> applicationMerchandises = MerchandiseOaApplicationUtil.getIntentionAndSupplierCodeGroupList(this.asString("intentionAndSupplierCodes"));
		map.put("list", applicationMerchandises);
		List<SupplierAttachmentM> list = supplierAttachmentMService.listIntentionSupplier(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 意向品，供应商列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListMerchandiseSupplier() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ApplicationMerchandise> applicationMerchandises = MerchandiseOaApplicationUtil.getIntentionAndSupplierCodeGroupList(this.asString("intentionAndSupplierCodes"));
		map.put("list", applicationMerchandises);
		List<SupplierAttachmentM> list = supplierAttachmentMService.listMerchandiseSupplier(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 导入供应商附件数据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doCompleteImportsupplierAttachmentM() throws Exception {
		String supAttFilePath = PathUtils.getSupplierAttachmentFileUploadPath();
		List<File> fList = this.doUploadBySaveDir(supAttFilePath.concat("/"));
		boolean result = true;
		String success = this.getText("public.success");
		if (!fList.isEmpty()) {
			SupplierAttachmentM supplierAttachmentM = this.getSupplierAttachmentM();
			supplierAttachmentM.setAttachmentName(StrUtils.getFileResourceName(fList.get(0).getName()));
			supplierAttachmentM.setPath(supAttFilePath.replaceAll(ConfigPath.getUploadFilePath(), "").concat(fList.get(0).getName()));// 文件路径
			Map<String, Object> map = supplierAttachmentM.toMap();
			map.put("intentionAndSupplierCodes", this.asString("intentionAndSupplierCodes"));
			map.put("applicationType", this.asString("applicationType"));
			supplierAttachmentMService.validateOaStatus(map);
			if (this.asString("typeShow") != null) {
				if (this.asString("typeShow").equals("2")) {
					map.put("supplierCode", this.asString("supplierCode").split(",")[0]);
					map.put("merchandiseCode", "");
					List<SupplierAttachmentM> list = supplierAttachmentMService.loadSupplierAttachmentM(supplierAttachmentM);
					String path = "";
					for (int i = 0; i < list.size(); i++) {
						path += list.get(i).getPath() + ",";
					}
					map.put("paths", path);
					supplierAttachmentMService.deleteSupplierAttachmentM(map);
					String[] merchandiseCodes = this.asString("merchandiseCode").split(",");
					String[] supplierCodes = this.asString("supplierCode").split(",");
					for (int i = 0; i < merchandiseCodes.length; i++) {
						map.put("supplierCode", supplierCodes[i]);
						map.put("merchandiseCode", merchandiseCodes[i]);
						supplierAttachmentMService.insertSupplierAttachmentM(map);
					}
				} else {
					List<SupplierAttachmentM> list = supplierAttachmentMService.loadSupplierAttachmentM(supplierAttachmentM);
					if (list.size() != 0) {
						map.put("paths", list.get(0).getPath());
						supplierAttachmentMService.deleteSupplierAttachmentM(map);
					}
					supplierAttachmentMService.insertSupplierAttachmentM(map);
				}
			} else {
				List<SupplierAttachmentM> list = supplierAttachmentMService.loadSupplierAttachmentM(supplierAttachmentM);
				if (list.size() != 0) {
					map.put("paths", list.get(0).getPath());
					supplierAttachmentMService.deleteSupplierAttachmentM(map);
				}
				supplierAttachmentMService.insertSupplierAttachmentM(map);
			}
		}
		this.forwardData(result, null, success);
	}

	/**
	 * 下载供应商附件上传文件
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doDownloadOriginalFile() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String address = this.asString("path").toString();
		map.put("path", address);
		List<SupplierAttachmentM> list = supplierAttachmentMService.listSupplierAttachmentM(map, null);
		SupplierAttachmentM s = list.get(0);
		String name = "";
		if (s.getIntentionName() != "" && s.getIntentionName() != null) {
			name += s.getIntentionName() + "-";
		} else {
			name += s.getMerchandiseName() + "-";
		}
		if (s.getIntentionSupplierName() != "" && s.getIntentionSupplierName() != null) {
			name += s.getIntentionSupplierName() + "-";
		} else {
			name += s.getSupplierName() + "-";
		}
		name += s.getElseAttachmentType() + "." + s.getAttachmentName().substring(s.getAttachmentName().lastIndexOf(".") + 1, s.getAttachmentName().length());
		this.forwardDownload("upload/" + this.asString("path"), ExcelUtils.getEncodeFileName(name));

	}

	/**
	 * 下载核算投料表上传文件
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doDownloadFile() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountingCode", this.asString("accountingCode"));
		map.put("typeCode", this.asString("type"));
		List<SupplierAttachmentM> list = supplierAttachmentMService.listSupplierAttachmentMInFrom(map, null);
		// 下载的投料核算表对象
		SupplierAttachmentM row = list.get(0);
		String name = "";
		if (row.getIntentionName() != "" && row.getIntentionName() != null) {
			name += row.getIntentionName() + "-";
		} else {
			name += row.getMerchandiseName() + "-";
		}
		if (row.getIntentionSupplierName() != "" && row.getIntentionSupplierName() != null) {
			name += row.getIntentionSupplierName() + "-";
		} else {
			name += row.getSupplierName() + "-";
		}
		if (this.asString("type").toString().endsWith("2")) {
			name += "核算表-";
		} else if (this.asString("type").toString().endsWith("1")) {
			name += "投料表-";
		}
		name += row.getCreateDate() + "." + row.getPath().substring(row.getPath().lastIndexOf(".") + 1, row.getPath().length());
		this.forwardDownload("upload/" + this.asString("path"), ExcelUtils.getEncodeFileName(name));
	}

	/**
	 * 删除供应商附件数据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doDeletesupplierAttachmentM() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String[] intentionCodes = this.asString("intentionCode").split(",");
		String[] intentionSupplierCodes = this.asString("intentionSupplierCode").split(",");
		String[] attachmentTypes = this.asString("attachmentType").split(",");
		String[] elseAttachmentTypes = this.asString("elseAttachmentTypes").split(",");
		List<Merchandise> merchandiseList = new ArrayList<Merchandise>();
		for (int i = 0; i < intentionCodes.length; i++) {
			Merchandise merchandise = new Merchandise();
			merchandise.setSupplierCode(intentionSupplierCodes[i]);
			merchandise.setMerchandiseCode(intentionCodes[i]);
			merchandise.setMerchandiseName(attachmentTypes[i]);
			merchandise.setSupplierName(elseAttachmentTypes[i]);
			merchandiseList.add(merchandise);
		}
		map.put("merchandise", merchandiseList);
		map.put("intentionAndSupplierCodes", this.asString("intentionAndSupplierCodes"));
		map.put("applicationType", this.asString("applicationType"));
		map.put("applicationCode", this.asString("applicationCode"));
		map.put("intentionCodes", this.asString("intentionCode").split(","));
		map.put("intentionSupplierCodes", this.asString("intentionSupplierCode").split(","));
		map.put("attachmentTypes", this.asString("attachmentType").split(","));
		map.put("paths", this.asString("paths"));
		supplierAttachmentMService.deleteSupplierAttachmentMs(map);
		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private SupplierAttachmentM getSupplierAttachmentM() throws Exception {
		SupplierAttachmentM supplierAttachmentM = new SupplierAttachmentM();
		this.asBean(supplierAttachmentM);
		return supplierAttachmentM;
	}
}
