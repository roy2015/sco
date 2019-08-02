package com.powere2e.sco.action.merchandiseoaapplication.reportnewoaapplication.suppliercertificate;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.config.ConfigPath;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.common.utils.PathUtils;
import com.powere2e.sco.common.utils.UploadUtils;
import com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.suppliercertificate.SupplierCertificateService;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportnewoaapplication.suppliercertificate.SupplierCertificateMService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.suppliercertificate.SupplierCertificate;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.suppliercertificate.SupplierCertificateM;
import com.powere2e.security.model.User;
import com.powere2e.security.utils.PowerUtils;

/**
 * 供应商证件的WEB请求响应类
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月23日
 */
public class SupplierCertificateMAction extends UploadUtils {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8569967318693731858L;
	private SupplierCertificateMService supplierCertificateMService;
	private SupplierCertificateService supplierCertificateService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		supplierCertificateMService = (SupplierCertificateMService) ConfigFactory.getInstance().getBean("supplierCertificateMService");
		supplierCertificateService = (SupplierCertificateService) ConfigFactory.getInstance().getBean("supplierCertificateService");
	}

	/**
	 * 供应商证件列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListSupplierCertificateM() throws Exception {
		Map<String, Object> map = getSupplierCertificateM().toMap();
		map.put("applicationCode", this.asString("applicationCode"));
		List<SupplierCertificateM> list = supplierCertificateMService.listSupplierCertificateM(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 显示添加供应商证件 界面
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doShowInsertSupplierCertificateMForm() throws Exception {
		SupplierCertificate supplierCertificate = new SupplierCertificate();
		supplierCertificate.setSupplierCode(this.asString("supplierCode"));
		this.putObject("supplierCertificate", supplierCertificate);
		this.forwardPage("sco/merchandiseOaApplication/reportNewOaApplication/supplierCertificate/supplierCertificateForm.ftl");
	}

	/**
	 * 显示查看供应商证件 界面
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doShowSupplierCertificateMForm() throws Exception {
		SupplierCertificate supplierCertificate = new SupplierCertificate();
		supplierCertificate.setSupplierCode(this.asString("supplierCode"));
		this.putObject("supplierCertificate", supplierCertificate);
		this.forwardPage("sco/merchandiseOaApplication/reportNewOaApplication/supplierCertificate/supplierCertificateGrid.ftl");
	}

	/**
	 * 根据ID号获一个供应商证件
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doLoadSupplierCertificateM() throws Exception {
		SupplierCertificateM supplierCertificateM = this.supplierCertificateMService.loadSupplierCertificateM(asString("applicationCode"));
		this.forwardData(true, supplierCertificateM, null);
	}

	/**
	 * 添加供应商证件(上传)
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doInsertSupplierCertificate() throws Exception {
		String supCerFilePath = PathUtils.getSupplierCertificateFileUploadPath();
		List<File> fList = this.doUploadBySaveDir(supCerFilePath.concat("/"));
		boolean result = true;
		String success = this.getText("public.success");
		if (!fList.isEmpty()) {
			SupplierCertificate supplierCertificate = this.getSupplierCertificate();
			supplierCertificate.setPath(supCerFilePath.replaceAll(ConfigPath.getUploadFilePath(), "").concat(fList.get(0).getName()));// 文件路径
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("intentionAndSupplierCodes", this.asString("intentionAndSupplierCodes"));
			map.put("applicationType", this.asString("applicationType"));
			map.put("applicationCode", this.asString("applicationCode"));
			supplierCertificateMService.validateOaStatus(map);
			supplierCertificateService.insertSupplierCertificate(supplierCertificate);
			User loginUser = PowerUtils.getCurrentUser();
			map.put("createby", loginUser.getUserId());
			supplierCertificate = supplierCertificateService.loadSupplierCertificate(map);
			SupplierCertificateM supplierCertificateM = new SupplierCertificateM();
			supplierCertificateM.setSupplierCode(supplierCertificate.getSupplierCode());
			supplierCertificateM.setApplicationCode(this.asString("applicationCode"));
			supplierCertificateM.setCertificateCode(supplierCertificate.getCertificateCode());
			if (supplierCertificateMService.listSupplierCertificateM(supplierCertificateM.toMap(), null).size() == 0) {
				List<SupplierCertificateM> list = new ArrayList<SupplierCertificateM>();
				list.add(supplierCertificateM);
				map.put("list", list);
				supplierCertificateMService.insertSupplierCertificateM(map);
			}
		}
		this.forwardData(result, null, success);
	}

	/**
	 * 添加供应商证件
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doInsertSupplierCertificateM() throws Exception {
		List<SupplierCertificateM> list = new ArrayList<SupplierCertificateM>();
		String[] supplierCode = this.asString("supplierCode").split(",");
		String[] applicationCode = this.asString("applicationCode").split(",");
		String[] certificateCode = this.asString("certificateCode").split(",");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("intentionAndSupplierCodes", this.asString("intentionAndSupplierCodes"));
		map.put("applicationType", this.asString("applicationType"));
		map.put("applicationCode", applicationCode[0]);
		supplierCertificateMService.validateOaStatus(map);
		for (int i = 0; i < supplierCode.length; i++) {
			SupplierCertificateM supplierCertificateM = new SupplierCertificateM();
			supplierCertificateM.setSupplierCode(supplierCode[i]);
			supplierCertificateM.setApplicationCode(applicationCode[i]);
			supplierCertificateM.setCertificateCode(certificateCode[i]);
			if (supplierCertificateMService.listSupplierCertificateM(supplierCertificateM.toMap(), null).size() == 0) {
				list.add(supplierCertificateM);
			}
		}
		if (list.size() != 0) {
			map.put("list", list);
			supplierCertificateMService.insertSupplierCertificateM(map);
			this.forwardData(true, null, this.getText("public.success"));
		} else {
			this.forwardData(false, null, "数据重复");
		}

	}

	/**
	 * 删除供应商证件数据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doDeleteSupplierCertificateM() throws Exception {
		Map<String, Object> map = getSupplierCertificateM().toMap();
		String applicationCode = this.asString("applicationCode");
		String certificateCodes = this.asString("certificateCode");
		String supplierCodes = this.asString("supplierCode");
		map.put("certificateCodes", certificateCodes.split(","));
		map.put("applicationCode", applicationCode);
		map.put("supplierCodes", supplierCodes.split(","));
		map.put("intentionAndSupplierCodes", this.asString("intentionAndSupplierCodes"));
		map.put("applicationType", this.asString("applicationType"));
		map.put("paths", this.asString("paths"));
		supplierCertificateMService.deleteSupplierCertificateM(map);
		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 下载上传文件
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doDownloadOriginalFile() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("path", this.asString("path"));
		List<SupplierCertificateM> list = supplierCertificateMService.listSupplierCertificateM(map, null);
		SupplierCertificateM s = list.get(0);
		String name = "";
		String scanPath = this.asString("path");
		if (s.getSupplierName() != null) {
			name += s.getSupplierName() + "-";
		} else {
			name += s.getIntentionSupplierName() + "-";
		}
		name += s.getCertificateTypeName() + "." + scanPath.substring(scanPath.lastIndexOf(".") + 1, scanPath.length());
		this.forwardDownload("upload/"+scanPath, ExcelUtils.getEncodeFileName(name));
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private SupplierCertificateM getSupplierCertificateM() throws Exception {
		SupplierCertificateM supplierCertificateM = new SupplierCertificateM();
		this.asBean(supplierCertificateM);
		return supplierCertificateM;
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private SupplierCertificate getSupplierCertificate() throws Exception {
		SupplierCertificate supplierCertificate = new SupplierCertificate();
		this.asBean(supplierCertificate);
		return supplierCertificate;
	}
}
