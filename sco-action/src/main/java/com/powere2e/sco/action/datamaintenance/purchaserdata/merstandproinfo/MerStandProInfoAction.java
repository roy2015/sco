package com.powere2e.sco.action.datamaintenance.purchaserdata.merstandproinfo;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.merstandproinfo.MerStandProInfoService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.merStandProInfo.MerStandProInfo;

/**
 * 商品引进标准进度信息维护 WEB请求响应类
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年09月28日
 */
public class MerStandProInfoAction extends WorkAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7219659844524575248L;
	private MerStandProInfoService merStandProInfoService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		merStandProInfoService = (MerStandProInfoService) ConfigFactory
				.getInstance().getBean("merStandProInfoService");
	}

	/**
	 * 显示标准进度信息页面
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.merStandProInfo")
	public void doShowMerStandProInfoMain() {
		this.forwardPage("sco/dataMaintenance/purchaserData/merStandProInfo/merStandProInfoGrid.ftl");
	}

	/**
	 * 显示标准进度信息数据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.merStandProInfo")
	public void doListMerStandProInfo() throws Exception {
		List<MerStandProInfo> list = this.merStandProInfoService
				.listMerMerStandProInfo(this.getMerStandProInfo().toMap(),
						this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 显示新增标准进度信息界面
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.merStandProInfo.add")
	public void doShowAddMerStandProInfoForm() {
		this.forwardPage("sco/dataMaintenance/purchaserData/merStandProInfo/merStandProInfoForm.ftl");
	}

	/**
	 * 新增标准进度信息
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.merStandProInfo.add")
	public void doInsertMerStandProInfo() throws Exception {
		this.merStandProInfoService.insertMerStandProInfo(this.getMerStandProInfo());
		this.forwardData(true, null, "新增成功");
	}

	/**
	 * 显示编辑标准进度信息界面
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.merStandProInfo.edit")
	public void doShowEditMerStandProInfoForm() throws Exception {
		List<MerStandProInfo> standProInfoList = this.merStandProInfoService
				.listMerMerStandProInfo(this.getMerStandProInfo().toMap(), null);
		if (standProInfoList.isEmpty()) {
			throw new EscmException("数据发生异常,请刷新页面后重试!");
		}
		this.putObject("standProInfo", standProInfoList.get(0));
		this.forwardPage("sco/dataMaintenance/purchaserData/merStandProInfo/merStandProInfoForm.ftl");
	}

	/**
	 * 更新标准进度信息
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.merStandProInfo.edit") 
	public void doUpdateMerStandProInfo() throws Exception {
		this.merStandProInfoService.updateMerStandProInfo(this.getMerStandProInfo());
		this.forwardData(true, null, "修改成功!");
	}
	
	/**
	 * 删除标准进度信息
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.merStandProInfo.del")
	public void doDeleteMerStandProInfo() throws Exception {
		String configCode = this.asString("configCode");
		if (StringUtils.isBlank(configCode)) {
			throw new EscmException("请勾选至少一条记录");
		}
		this.merStandProInfoService.deleteMerStandProInfo(this.getMerStandProInfo());
		this.forwardData(true, null, "删除成功");
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private MerStandProInfo getMerStandProInfo() throws Exception {
		MerStandProInfo merStandProInfo = new MerStandProInfo();
		this.asBean(merStandProInfo);
		return merStandProInfo;
	}
}
