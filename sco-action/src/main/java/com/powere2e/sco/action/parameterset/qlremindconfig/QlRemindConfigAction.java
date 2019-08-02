package com.powere2e.sco.action.parameterset.qlremindconfig;

import java.util.List;
import java.util.Map;
import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.interfaces.service.parameterset.qlremindconfig.QlRemindConfigService;
import com.powere2e.sco.model.parameterset.qlremindconfig.QlRemindConfig;
import com.powere2e.sco.service.impl.common.MasterDataTypeServiceImpl;

/**
 * 签量提醒设置的WEB请求响应类
 * 
 * @author pudge
 * @version 1.0
 * @since 2015年4月3日
 */
public class QlRemindConfigAction extends WorkAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1407276839406651160L;
	private QlRemindConfigService qlRemindConfigService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		qlRemindConfigService = (QlRemindConfigService) ConfigFactory.getInstance().getBean("qlRemindConfigService");
	}

	/**
	 * 签量提醒设置列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.qlremindconfig")
	public void doListQlRemindConfig() throws Exception {
		Map<String, Object> map = getQlRemindConfig().toMap();
		map.put("created", this.asString("created"));
		map.put("updated", this.asString("updated"));
		List<QlRemindConfig> list = qlRemindConfigService.listQlRemindConfig(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 显示签量提醒设置
	 */
	@Authority(privilege = "com.powere2e.sco.qlremindconfig")
	public void doShowQlRemindConfigGrid() throws Exception {
		this.forwardPage("sco/parameterSet/qlRemindConfig/qlRemindConfigGrid.ftl");
	}

	/**
	 * 根据ID号获一个签量提醒设置
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.qlremindconfig")
	public void doLoadQlRemindConfig() throws Exception {
		QlRemindConfig qlRemindConfig = this.qlRemindConfigService.loadQlRemindConfig(asString("configCode"));
		this.forwardData(true, qlRemindConfig, null);
	}

	/**
	 * 添加签量提醒设置界面
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.qlremindconfig.insert")
	public void doShowInsertQlRemindConfigForm() throws Exception {
		QlRemindConfig qlRemindConfig = new QlRemindConfig();
		this.putObject("qlRemindConfig", qlRemindConfig);
		this.forwardPage("sco/parameterSet/qlRemindConfig/qlRemindConfigForm");
	}

	/**
	 * 修改签量提醒设置界面
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.qlremindconfig.edit")
	public void doShowUpdateQlRemindConfigForm() throws Exception {
		QlRemindConfig qlRemindConfig = qlRemindConfigService.loadQlRemindConfig(asString("configCode"));
		this.putObject("qlRemindConfig", qlRemindConfig);
		this.forwardPage("sco/parameterSet/qlRemindConfig/qlRemindConfigForm");
	}

	/**
	 * 添加签量提醒设置
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.qlremindconfig.insert")
	public void doInsertQlRemindConfig() throws Exception {
		QlRemindConfig qlRemindConfig = getQlRemindConfig();
		this.asBean(qlRemindConfig);
		// 根据签量阀值查询是否已存在的签量阀值对象,来判断是否重复添加签量阀值
		QlRemindConfig temp = qlRemindConfigService.loadQlRemindConfigBythresholdValue(qlRemindConfig.getThresholdValue());
		if (temp != null) {
			this.forwardData(false, null, this.getText("请勿重复填入相同阀值数字"));
		} else {
			qlRemindConfig.setConfigCode(MasterDataTypeServiceImpl.getInstance().nextID("s_ql_remind_config"));
			qlRemindConfigService.insertQlRemindConfig(qlRemindConfig);
			this.forwardData(true, null, this.getText("public.success"));
		}
	}

	/**
	 * 修改签量提醒设置
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.qlremindconfig.edit")
	public void doUpdateQlRemindConfig() throws Exception {
		QlRemindConfig temp = qlRemindConfigService.loadQlRemindConfigBythresholdValue(getQlRemindConfig().getThresholdValue());
		if (temp != null) {
			this.forwardData(false, null, this.getText("请勿重复填入相同阀值数字"));
		} else {
			qlRemindConfigService.updateQlRemindConfig(getQlRemindConfig());
			this.forwardData(true, null, this.getText("public.success"));
		}
	}

	/**
	 * 删除签量提醒设置
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.qlremindconfig.delete")
	public void doDeleteQlRemindConfig() throws Exception {
		qlRemindConfigService.deleteQlRemindConfig(asString("configCode").replace("'", ""));
		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private QlRemindConfig getQlRemindConfig() throws Exception {
		QlRemindConfig qlRemindConfig = new QlRemindConfig();
		this.asBean(qlRemindConfig);
		return qlRemindConfig;
	}
}
