package com.powere2e.sco.action.datamaintenance.purchaserdata.suppliercertificate;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.config.ConfigPath;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.utils.PathUtils;
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.sco.common.utils.UploadUtils;
import com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.suppliercertificate.SupplierCertificateService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.suppliercertificate.SupplierCertificate;
import com.powere2e.security.model.Option;

/**
 * 供应商证件 的WEB请求响应类
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月10日
 */
public class SupplierCertificateAction extends UploadUtils {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7219659844524575248L;
	private SupplierCertificateService supplierCertificateService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		supplierCertificateService = (SupplierCertificateService) ConfigFactory
				.getInstance().getBean("supplierCertificateService");
	}

	/**
	 * 显示供应商证件页面
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.supplierCertificate")
	public void doShowSupplierCertificateMain() {
		this.forwardPage("sco/dataMaintenance/purchaserData/supplierCertificate/supplierCertificateGrid.ftl");
	}
	
	/**
	 * 供应商证件 列表
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.supplierCertificate")
	public void doListSupplierCertificate() throws Exception {
		Map<String, Object> map = getSupplierCertificate().toMap();
		map.put("startDate", this.asDate("startDate"));
		map.put("endDate", this.asDate("endDate"));
		List<SupplierCertificate> list = supplierCertificateService
				.listSupplierCertificate(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}
	/**
	 * 所有供应商证件 列表
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListSupplierCertificateAll() throws Exception {
		Map<String, Object> map = getSupplierCertificate().toMap();
		map.put("startDate", this.asDate("startDate"));
		map.put("endDate", this.asDate("endDate"));
		map.put("supplierCode", this.asString("supplierCode"));
		List<SupplierCertificate> list = supplierCertificateService.listSupplierCertificateAll(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}
	/**
	 * 下载上传文件
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.supplierCertificate")
	public void doDownloadOriginalFile() {
		String path = this.asString("path");
		if(StringUtils.isBlank(path)) return;
		this.forwardDownload("upload/" + path, 
				StrUtils.getFileResourceName(new File(path).getName()));
	}
	
	/**
	 * 显示添加供应商证件 界面
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.supplierCertificate.add")
	public void doShowInsertSupplierCertificateForm() throws Exception {
		SupplierCertificate supplierCertificate = new SupplierCertificate();
		this.putObject("supplierCertificate", supplierCertificate);
		this.forwardPage("sco/dataMaintenance/purchaserData/supplierCertificate/supplierCertificateForm");
	}

	/**
	 * 添加供应商证件
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.supplierCertificate.add")
	public void doInsertSupplierCertificate() throws Exception {
		String supCerFilePath = PathUtils.getSupplierCertificateFileUploadPath();
		List<File> fList = this.doUploadBySaveDir(supCerFilePath.concat("/"));
		if (!fList.isEmpty()) {
			SupplierCertificate supplierCertificate = this.getSupplierCertificate();
			supplierCertificate.setPath(supCerFilePath
					.replaceAll(ConfigPath.getUploadFilePath(), "").concat(fList.get(0).getName()));// 文件路径
			supplierCertificateService.insertSupplierCertificate(supplierCertificate);
		}
		this.forwardData(true, null, "新增成功");
	}
	
	/**
	 * 删除供应商证件
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.supplierCertificate.del")
	public void doDeleteSupplierCertificate() {
		String certificateCodes = this.asString("certificateCodes");
		if(StringUtils.isBlank(certificateCodes)) {
			throw new EscmException("请勾选至少一条记录");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("certificateCodes", certificateCodes);
		this.supplierCertificateService.deleteSupplierCertificate(map);
		this.forwardData(true, null, "删除成功");
	}
	
	/**
	 * 查询供应商证件类型
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListSupplierCertificateType() {
		List<Option> list = this.supplierCertificateService.listSupplierCertificateType();
		this.forwardData(true, list, null);
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
