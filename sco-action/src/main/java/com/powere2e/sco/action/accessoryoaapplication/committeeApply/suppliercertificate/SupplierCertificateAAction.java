package com.powere2e.sco.action.accessoryoaapplication.committeeApply.suppliercertificate;

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
import com.powere2e.sco.interfaces.service.accessoryoaapplication.committeeApply.suppliercertificate.SupplierCertificateAService;
import com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.suppliercertificate.SupplierCertificateService;
import com.powere2e.sco.model.accessoryoaapplication.suppliercertificate.SupplierCertificateA;
import com.powere2e.sco.model.datamaintenance.purchaserdata.suppliercertificate.SupplierCertificate;
import com.powere2e.security.model.User;
import com.powere2e.security.utils.PowerUtils;

/**
 * 供应商证件(辅料OA)的WEB请求响应类
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月6日
 */
public class SupplierCertificateAAction extends UploadUtils {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1993926401937335621L;
	private SupplierCertificateAService supplierCertificateAService;
	private SupplierCertificateService supplierCertificateService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		supplierCertificateAService = (SupplierCertificateAService) ConfigFactory.getInstance().getBean("supplierCertificateAService");
		supplierCertificateService = (SupplierCertificateService) ConfigFactory.getInstance().getBean("supplierCertificateService");
	}

	/**
	 * 供应商证件(辅料OA)列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListSupplierCertificateA() throws Exception {
		Map<String, Object> map = getSupplierCertificateA().toMap();
		List<SupplierCertificateA> list = supplierCertificateAService.listSupplierCertificateA(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 根据ID号获一个供应商证件(辅料OA)
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doLoadSupplierCertificateA() throws Exception {
		SupplierCertificateA supplierCertificateA = this.supplierCertificateAService.loadSupplierCertificateA(asString("applicationCode"));
		this.forwardData(true, supplierCertificateA, null);
	}

	/**
	 * 显示添加供应商证件 界面
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doShowInsertSupplierCertificateAForm() throws Exception {
		SupplierCertificate supplierCertificate = new SupplierCertificate();
		supplierCertificate.setSupplierCode(this.asString("supplierCode"));
		this.putObject("supplierCertificate", supplierCertificate);
		this.forwardPage("sco/accessoryOaApplication/committeeApply/supplierCertificate/supplierCertificateForm.ftl");
	}

	/**
	 * 添加供应商证件(上传)
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doInsertSupplierCertificate() throws Exception {
		String msg = "";
		boolean bool = false;
		SupplierCertificate supplierCertificate = this.getSupplierCertificate();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", this.asString("type"));
		map.put("quotedCodes", this.asString("quotedCode"));
		map.put("applicationCode", this.asString("applicationCode"));
		supplierCertificateAService.validateOaStatus(map);
		String supCerFilePath = PathUtils.getSupplierCertificateFileUploadPath();
		List<File> fList = this.doUploadBySaveDir(supCerFilePath.concat("/"));
		if (!fList.isEmpty()) {
			supplierCertificate.setPath(supCerFilePath.replaceAll(ConfigPath.getUploadFilePath(), "").concat(fList.get(0).getName()));// 文件路径
			if (supplierCertificate.getPath().length() < 100) {
				supplierCertificateService.insertSupplierCertificate(supplierCertificate);
				User loginUser = PowerUtils.getCurrentUser();
				map.put("createby", loginUser.getUserId());
				supplierCertificate = supplierCertificateService.loadSupplierCertificate(map);
				SupplierCertificateA supplierCertificateA = new SupplierCertificateA();
				supplierCertificateA.setSupplierCode(supplierCertificate.getSupplierCode());
				supplierCertificateA.setApplicationCode(this.asString("applicationCode"));
				supplierCertificateA.setCertificateCode(supplierCertificate.getCertificateCode());
				if (supplierCertificateAService.listSupplierCertificateA(supplierCertificateA.toMap(), null).size() == 0) {
					List<SupplierCertificateA> list = new ArrayList<SupplierCertificateA>();
					list.add(supplierCertificateA);
					map.put("list", list);
					supplierCertificateAService.insertSupplierCertificateA(map);
				}
				msg = this.getText("public.success");
				bool = true;
			} else {
				msg = "文件长度过长";
				bool = false;
			}
		}
		this.forwardData(bool, null, msg);
	}

	/**
	 * 显示查看供应商证件 界面
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doShowSupplierCertificateAForm() throws Exception {
		SupplierCertificate supplierCertificate = new SupplierCertificate();
		supplierCertificate.setSupplierCode(this.asString("supplierCode"));
		this.putObject("supplierCertificate", supplierCertificate);
		this.forwardPage("sco/accessoryOaApplication/committeeApply/supplierCertificate/supplierCertificateGrid.ftl");
	}

	/**
	 * 添加供应商证件
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doInsertSupplierCertificateA() throws Exception {
		String msg = "";
		boolean bool = false;
		List<SupplierCertificateA> list = new ArrayList<SupplierCertificateA>();
		String[] supplierCode = this.asString("supplierCode").split(",");
		String[] applicationCode = this.asString("applicationCode").split(",");
		String[] certificateCode = this.asString("certificateCode").split(",");
		String type = this.asString("type");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("quotedCodes", this.asString("quotedCode"));
		map.put("applicationCode", applicationCode[0]);
		supplierCertificateAService.validateOaStatus(map);
		for (int i = 0; i < supplierCode.length; i++) {
			SupplierCertificateA SupplierCertificateA = new SupplierCertificateA();
			SupplierCertificateA.setSupplierCode(supplierCode[i]);
			SupplierCertificateA.setApplicationCode(applicationCode[i]);
			SupplierCertificateA.setCertificateCode(certificateCode[i]);
			if (supplierCertificateAService.listSupplierCertificateA(SupplierCertificateA.toMap(), null).size() == 0) {
				list.add(SupplierCertificateA);
			}
		}
		if (list.size() != 0) {
			map.put("list", list);
			supplierCertificateAService.insertSupplierCertificateA(map);
			msg = this.getText("public.success");
			bool = true;
		} else {
			msg = "数据重复";
		}
		this.forwardData(bool, null, msg);
	}

	/**
	 * 删除供应商证件(辅料OA)
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doDeleteSupplierCertificateA() throws Exception {
		String[] applicationCode = this.asString("applicationCode").split(",");
		String[] certificateCodes = this.asString("certificateCode").split(",");
		String[] supplierCodes = this.asString("supplierCode").split(",");
		String type = this.asString("type");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("quotedCodes", this.asString("quotedCode"));
		map.put("applicationCode", applicationCode[0]);
		supplierCertificateAService.validateOaStatus(map);
		List<SupplierCertificateA> list = new ArrayList<SupplierCertificateA>();
		for (int i = 0; i < supplierCodes.length; i++) {
			SupplierCertificateA SupplierCertificateA = new SupplierCertificateA();
			SupplierCertificateA.setSupplierCode(supplierCodes[i]);
			SupplierCertificateA.setApplicationCode(applicationCode[i]);
			SupplierCertificateA.setCertificateCode(certificateCodes[i]);
			list.add(SupplierCertificateA);
		}
		map.put("list", list);
		map.put("paths", this.asString("paths"));
		supplierCertificateAService.deleteSupplierCertificateA(map);
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
		map.put("certificateCode", this.asString("certificateCode"));
		List<SupplierCertificateA> list = supplierCertificateAService.listSupplierCertificateA(map, null);
		// 下载证件对象
		SupplierCertificateA row = list.get(0);
		String name = "";
		String scanPath = this.asString("path");
		if (row.getIntentionSupplierName() != "" && row.getIntentionSupplierName() != null) {
			name += row.getIntentionSupplierName() + "-";
		} else {
			name += row.getSupplierName() + "-";
		}
		name += row.getCertificateTypeName() + "." + scanPath.substring(scanPath.lastIndexOf(".") + 1, scanPath.length());
		this.forwardDownload("upload/"+scanPath, ExcelUtils.getEncodeFileName(name));
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private SupplierCertificateA getSupplierCertificateA() throws Exception {
		SupplierCertificateA supplierCertificateA = new SupplierCertificateA();
		this.asBean(supplierCertificateA);
		return supplierCertificateA;
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
