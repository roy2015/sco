package com.powere2e.sco.action.parameterset.certificateoutofdateconfig;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.server.sequence.SequenceFactory;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.interfaces.service.parameterset.certificateoutofdateconfig.CertificateOutofdateConfigService;
import com.powere2e.sco.model.parameterset.certificateoutofdateconfig.CertificateOutofdateConfig;
import com.powere2e.security.model.User;
import com.powere2e.security.utils.PowerUtils;

/**
 * 证件过期提醒设置的WEB请求响应类
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月3日
 */
public class CertificateOutofdateConfigAction extends WorkAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5302186403068219067L;
	private CertificateOutofdateConfigService certificateOutofdateConfigService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		certificateOutofdateConfigService = (CertificateOutofdateConfigService) ConfigFactory.getInstance().getBean("certificateOutofdateConfigService");
	}

	/**
	 * 证件过期提醒设置列表grid
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.certificateoutofdateconfig")
	public void doCertificateOutofdateConfigGrid() throws Exception {
		this.forwardPage("sco/parameterSet/certificateoutofdateconfig/certificateOutofdateConfigGrid.ftl");
	}

	/**
	 * 证件过期提醒设置列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.certificateoutofdateconfig")
	public void doListCertificateOutofdateConfig() throws Exception {
		Map<String, Object> map = getCertificateOutofdateConfig().toMap();
		map.put("created", this.asString("created"));
		map.put("updated", this.asString("updated"));
		List<CertificateOutofdateConfig> list = certificateOutofdateConfigService.listCertificateOutofdateConfig(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 根据ID号获一个证件过期提醒设置
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.certificateoutofdateconfig")
	public void doLoadCertificateOutofdateConfig() throws Exception {
		CertificateOutofdateConfig certificateOutofdateConfig = this.certificateOutofdateConfigService.loadCertificateOutofdateConfig(asString("configCode"));
		this.forwardData(true, certificateOutofdateConfig, null);
	}

	/**
	 * 获取当前提醒天數是否存在
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	public boolean doListCertificateOutofdateConfigByOutofdate(Integer outofdate) throws Exception {
		boolean erification = false;
		CertificateOutofdateConfig certificateOutofdateConfig = this.certificateOutofdateConfigService.listCertificateOutofdateConfig(outofdate);
		if (certificateOutofdateConfig == null) {
			erification = true;
		}
		return erification;
	}

	/**
	 * 添加证件过期提醒设置界面
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.certificateoutofdateconfig.insert")
	public void doShowInsertCertificateOutofdateConfigForm() throws Exception {
		CertificateOutofdateConfig certificateOutofdateConfig = new CertificateOutofdateConfig();
		this.putObject("certificateOutofdateConfig", certificateOutofdateConfig);
		this.forwardPage("sco/parameterSet/certificateoutofdateconfig/certificateOutofdateConfigForm.ftl");
	}

	/**
	 * 修改证件过期提醒设置界面
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.certificateoutofdateconfig.edit")
	public void doShowUpdateCertificateOutofdateConfigForm() throws Exception {
		CertificateOutofdateConfig certificateOutofdateConfig = certificateOutofdateConfigService.loadCertificateOutofdateConfig(asString("configCode"));
		this.putObject("certificateOutofdateConfig", certificateOutofdateConfig);
		this.forwardPage("sco/parameterSet/certificateoutofdateconfig/certificateOutofdateConfigForm.ftl");
	}

	/**
	 * 添加证件过期提醒设置
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.certificateoutofdateconfig.insert")
	public void doInsertCertificateOutofdateConfig() throws Exception {
		boolean erification = doListCertificateOutofdateConfigByOutofdate(asInteger("outofdate"));
		String msg = "请勿重复填入相同提醒天数";
		if (erification == true) {
			msg = this.getText("public.success");
			CertificateOutofdateConfig certificateOutofdateConfig = getCertificateOutofdateConfig();
			certificateOutofdateConfig.setConfigCode(SequenceFactory.getInstance().nextID("certificate_outofdate_config"));
			User loginUser = PowerUtils.getCurrentUser();
			certificateOutofdateConfig.setCreateby(loginUser.getLoginName());
			certificateOutofdateConfigService.insertCertificateOutofdateConfig(certificateOutofdateConfig);
		}
		this.forwardData(erification, null, msg);
	}

	/**
	 * 修改证件过期提醒设置
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.certificateoutofdateconfig.edit")
	public void doUpdateCertificateOutofdateConfig() throws Exception {
		boolean erification = doListCertificateOutofdateConfigByOutofdate(asInteger("outofdate"));
		String msg = "提醒天数重复";
		if (erification == true) {
			msg = this.getText("public.success");
			certificateOutofdateConfigService.updateCertificateOutofdateConfig(getCertificateOutofdateConfig());
		}
		this.forwardData(erification, null, msg);
	}

	/**
	 * 删除证件过期提醒设置
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.certificateoutofdateconfig.delete")
	public void doDeleteCertificateOutofdateConfig() throws Exception {
		// String configCode=this.request.getParameter("configCode");
		String configCode = this.asString("configCode");
		certificateOutofdateConfigService.deleteCertificateOutofdateConfig(configCode);
		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private CertificateOutofdateConfig getCertificateOutofdateConfig() throws Exception {
		CertificateOutofdateConfig certificateOutofdateConfig = new CertificateOutofdateConfig();
		this.asBean(certificateOutofdateConfig);
		return certificateOutofdateConfig;
	}
}
