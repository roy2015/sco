package com.powere2e.sco.action.datamaintenance.systemmanagerdata.suppliercertificatetype;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.interfaces.service.datamaintenance.systemmanagerdata.suppliercertificatetype.SupplierCertificateTypeService;
import com.powere2e.sco.model.datamaintenance.systemmanagerdata.suppliercertificatetype.SupplierCertificateType;
import com.powere2e.security.model.User;
import com.powere2e.security.utils.PowerUtils;

/**
 * 证件名称的WEB请求响应类
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月17日
 */
public class SupplierCertificateTypeAction extends WorkAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6771701114173255828L;
	private SupplierCertificateTypeService supplierCertificateTypeService;
	private User loginUser = PowerUtils.getCurrentUser();

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		supplierCertificateTypeService = (SupplierCertificateTypeService) ConfigFactory.getInstance().getBean("supplierCertificateTypeService");
	}

	/**
	 * 显示供应商证件页面
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.systemManagerMainternace.supplierCerticateTypeNameMainternace")
	public void doShowSupplierCertificateTypeGrid() {
		this.forwardPage("sco/dataMaintenance/systemManagerData/supplierCertificateType/supplierCertificateTypeGrid.ftl");
	}

	/**
	 * 证件名称列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.systemManagerMainternace.supplierCerticateTypeNameMainternace")
	public void doListSupplierCertificateType() throws Exception {
		Map<String, Object> map = getSupplierCertificateType().toMap();
		map.put("created", this.asString("created"));
		map.put("updated", this.asString("updated"));
		List<SupplierCertificateType> list = supplierCertificateTypeService.listSupplierCertificateType(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 根据ID号获一个证件名称
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.systemManagerMainternace.supplierCerticateTypeNameMainternace")
	public void doLoadSupplierCertificateType() throws Exception {
		SupplierCertificateType supplierCertificateType = this.supplierCertificateTypeService.loadSupplierCertificateType(asString("certificateTypeCode"));
		this.forwardData(true, supplierCertificateType, null);
	}

	/**
	 * 添加证件名称界面
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.systemManagerMainternace.supplierCerticateTypeNameMainternace.insert")
	public void doShowInsertSupplierCertificateTypeForm() throws Exception {
		SupplierCertificateType supplierCertificateType = new SupplierCertificateType();
		this.putObject("supplierCertificateType", supplierCertificateType);
		this.forwardPage("sco/dataMaintenance/systemManagerData/supplierCertificateType/supplierCertificateTypeForm.ftl");
	}

	/**
	 * 查询证件名称
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private boolean doListSupplierCertificateTypeByInfo(String certificateTypeName) throws Exception {
		boolean erification = false;
		SupplierCertificateType supplierCertificateType = this.supplierCertificateTypeService.loadSupplierCertificateType(certificateTypeName);
		if (supplierCertificateType == null) {
			erification = true;
		}
		return erification;
	}

	/**
	 * 添加证件名称
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.systemManagerMainternace.supplierCerticateTypeNameMainternace.insert")
	public void doInsertSupplierCertificateType() throws Exception {
		SupplierCertificateType supplierCertificateType = getSupplierCertificateType();
		boolean erification = this.doListSupplierCertificateTypeByInfo(supplierCertificateType.getCertificateTypeName());
		String msg = "该证件名称已存在，不可新增";
		if (erification == true) {
			supplierCertificateType.setCreateby(loginUser.getLoginName());
			msg = this.getText("public.success");
			supplierCertificateTypeService.insertSupplierCertificateType(supplierCertificateType);
		}
		this.forwardData(erification, null, msg);
	}

	/**
	 * 获取当前细分类ID是否存在于商品
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	public boolean doListSupplierCertificateType(String certificateTypeCode) throws Exception {
		boolean erification = false;
		String[] role = certificateTypeCode.split(",");
		for (int i = 0; i < role.length; i++) {
			Integer count = supplierCertificateTypeService.serachSupplierCertificate(role[i]);
			if (count <= 0) {
				erification = true;
			} else {
				erification = false;
				break;
			}
		}
		return erification;
	}

	/**
	 * 删除证件名称
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.systemManagerMainternace.supplierCerticateTypeNameMainternace.delete")
	public void doDeleteSupplierCertificateType() throws Exception {
		boolean erification = doListSupplierCertificateType(asString("certificateTypeCode"));
		String msg = "系统中目前有证件于该证件名称，不可将该证件名称从下拉列表中删除。";
		if (erification == true) {
			msg = this.getText("public.success");
			supplierCertificateTypeService.deleteSupplierCertificateType(asString("certificateTypeCode"));
		}
		this.forwardData(erification, null, msg);
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private SupplierCertificateType getSupplierCertificateType() throws Exception {
		SupplierCertificateType supplierCertificateType = new SupplierCertificateType();
		this.asBean(supplierCertificateType);
		return supplierCertificateType;
	}
}
