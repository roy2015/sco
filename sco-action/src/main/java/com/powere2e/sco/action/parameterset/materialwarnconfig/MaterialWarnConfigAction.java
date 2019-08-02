package com.powere2e.sco.action.parameterset.materialwarnconfig;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.server.sequence.SequenceFactory;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.interfaces.service.parameterset.materialwarnconfig.MaterialWarnConfigService;
import com.powere2e.sco.model.parameterset.materialwarnconfig.MaterialWarnConfig;
import com.powere2e.sco.model.parameterset.materialwarnconfig.MaterialWarnConfigJoint;
import com.powere2e.security.model.Option;
import com.powere2e.security.model.User;
import com.powere2e.security.utils.PowerUtils;

/**
 * 原料行情预警设置的WEB请求响应类
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月8日
 */
public class MaterialWarnConfigAction extends WorkAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4756385718379705613L;
	private MaterialWarnConfigService materialWarnConfigService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		materialWarnConfigService = (MaterialWarnConfigService) ConfigFactory.getInstance().getBean("materialWarnConfigService");
	}

	/**
	 * 原料行情预警设置列表grid
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.materialwarnconfig")
	public void doMaterialWarnConfigGrid() throws Exception {
		this.forwardPage("sco/parameterSet/materialwarnconfig/materialWarnConfigGrid.ftl");
	}

	/**
	 * 原料行情预警设置列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.materialwarnconfig")
	public void doListMaterialWarnConfig() throws Exception {
		Map<String, Object> map = getMaterialWarnConfigJoint().toMap();
		map.put("materialBigCode", this.asString("materialBigCode"));
		map.put("materialSmallCode", this.asString("materialSmallCode"));
		map.put("materialCode", this.asString("materialCode"));
		map.put("websiteCode", this.asString("websiteCode"));
		map.put("warnType", this.asString("warnType"));
		map.put("created", this.asString("created"));
		map.put("createds", this.asString("createds"));
		map.put("createby", "%" + this.asString("createby") + "%");
		map.put("sort", this.asString("sort"));
		map.put("order", this.asString("order"));
		List<MaterialWarnConfigJoint> list = materialWarnConfigService.listMaterialWarnConfig(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}
	/**
	 * 原料行情预警方式数据查询
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListWarnType() {
		List<Option> list = materialWarnConfigService.listWarnType();
		this.forwardData(true, list, null);
	}
	/**
	 * 根据ID号获一个原料行情预警设置
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.materialwarnconfig")
	public void doLoadMaterialWarnConfig() throws Exception {
		MaterialWarnConfig materialWarnConfig = this.materialWarnConfigService.loadMaterialWarnConfig(asString("configCode"));
		this.forwardData(true, materialWarnConfig, null);
	}

	/**
	 * 添加原料行情预警设置界面
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.materialwarnconfig.insert")
	public void doShowInsertMaterialWarnConfigForm() throws Exception {
		MaterialWarnConfig materialWarnConfig = new MaterialWarnConfig();
		this.putObject("materialWarnConfig", materialWarnConfig);
		this.forwardPage("sco/parameterSet/materialwarnconfig/materialWarnConfigForm.ftl");
	}

	/**
	 * 修改原料行情预警设置界面
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.materialwarnconfig.edit")
	public void doShowUpdateMaterialWarnConfigForm() throws Exception {
		MaterialWarnConfig materialWarnConfig = materialWarnConfigService.loadMaterialWarnConfig(asString("configCode"));
		this.putObject("materialWarnConfig", materialWarnConfig);
		this.forwardPage("sco/parameterSet/materialwarnconfig/materialWarnConfigForm.ftl");
	}

	/**
	 * 添加原料行情预警设置
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.materialwarnconfig.insert")
	public void doInsertMaterialWarnConfig() throws Exception {
		MaterialWarnConfig materialWarnConfig = getMaterialWarnConfig();
		boolean erification = doListMaterialWarnConfigByInfo(materialWarnConfig.getMaterialCode(), materialWarnConfig.getWarnType(), materialWarnConfig.getThresholdValue());
		String msg = "该公示网站原料已设置了该预警方式及该预警阀值，不可重复设置";
		if (erification == true) {
			msg = this.getText("public.success");
			materialWarnConfig.setConfigCode(SequenceFactory.getInstance().nextID("material_warn_config"));
			User loginUser = PowerUtils.getCurrentUser();
			materialWarnConfig.setCreateby(loginUser.getLoginName());
			materialWarnConfigService.insertMaterialWarnConfig(materialWarnConfig);
		}
		this.forwardData(erification, null, msg);
	}

	/**
	 * 修改原料行情预警设置
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.materialwarnconfig.edit")
	public void doUpdateMaterialWarnConfig() throws Exception {
		materialWarnConfigService.updateMaterialWarnConfig(getMaterialWarnConfig());
		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 删除原料行情预警设置
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.materialwarnconfig.delete")
	public void doDeleteMaterialWarnConfig() throws Exception {
		String[] configCode=asString("configCode").split(",");
		if(configCode.length!=0){
			materialWarnConfigService.deleteMaterialWarnConfig(asString("configCode"));	
		}
		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 查询原料行情预警设置
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private boolean doListMaterialWarnConfigByInfo(String materialCode, String warnType, BigDecimal thresholdValue) throws Exception {
		boolean erification = false;
		MaterialWarnConfig materialWarnConfig = this.materialWarnConfigService.listMaterialWarnConfig(materialCode, warnType, thresholdValue);
		if (materialWarnConfig == null) {
			erification = true;
		}
		return erification;
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private MaterialWarnConfigJoint getMaterialWarnConfigJoint() throws Exception {
		MaterialWarnConfigJoint materialWarnConfigJoint = new MaterialWarnConfigJoint();
		this.asBean(materialWarnConfigJoint);
		return materialWarnConfigJoint;
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private MaterialWarnConfig getMaterialWarnConfig() throws Exception {
		MaterialWarnConfig materialWarnConfig = new MaterialWarnConfig();
		this.asBean(materialWarnConfig);
		return materialWarnConfig;
	}
}
