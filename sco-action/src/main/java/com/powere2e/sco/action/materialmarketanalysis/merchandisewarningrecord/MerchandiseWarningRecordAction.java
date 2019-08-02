package com.powere2e.sco.action.materialmarketanalysis.merchandisewarningrecord;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.interfaces.service.materialmarketanalysis.merchandisewarningrecord.MerchandiseWarningRecordService;
import com.powere2e.sco.model.materialmarketanalysis.merchandisewarningrecord.MerchandiseWarningRecord;
import com.powere2e.security.model.Option;

/**
 * 商品预警记录 WEB请求响应类
 * 
 * @author Gavillen.Zhou
 * @since 2015年8月11日
 * @version 1.0
 */
public class MerchandiseWarningRecordAction extends WorkAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3605316241608052001L;

	private MerchandiseWarningRecordService merchdiseWarningRecordService;

	@Override
	protected void beforeBuild() {
		merchdiseWarningRecordService = (MerchandiseWarningRecordService) ConfigFactory
				.getInstance().getBean("merchandiseWarningRecordService");
	}

	/**
	 * 显示商品预警记录主界面
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.materialmarketAnalysis.merchdiseWarningRecord")
	public void doShowMerchdiseWarningRecordMain() throws Exception {
		this.forwardPage("sco/materialmarketanalysis/merchdisewarningrecord/merchdiseWarningRecordGrid.ftl");
	}
	
	/**
	 * 查询原料地区
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListRegion() {
		List<Option> list = this.merchdiseWarningRecordService.listMaterialRegionOption();
		this.forwardData(true, list, null);
	}

	/**
	 * 查询商品预警记录
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.materialmarketAnalysis.merchdiseWarningRecord.listMerWarningRec")
	public void doListMerchdiseWarningRecord() throws Exception {
		List<MerchandiseWarningRecord> list = this.merchdiseWarningRecordService
				.listMerchandiseWarningRecrod(this.getMerchandiseWarnRec(),
						this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 获取页面参数
	 * 
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> getMerchandiseWarnRec() throws Exception {
		MerchandiseWarningRecord merWarnRec = new MerchandiseWarningRecord();
		this.asBean(merWarnRec);
		Map<String, Object> map = merWarnRec.toMap();
		String firstIn = this.asString("firstIn");
		Date[] dateArr = null;
		Date start = null;
		Date end = null;
		if("true".equalsIgnoreCase(firstIn)) {//第一次进入页面，最近三个月
			dateArr = DateUtils.getRecentYearAndMonthByCurrent(3);
			start = dateArr[0];
			end = dateArr[1];
		} else {
			String startStr = this.asString("start");
			String endStr = this.asString("end");
			if (StringUtils.isNotBlank(startStr) && StringUtils.isNotBlank(endStr)) {
				start = DateUtils.formatStrToDate(startStr, Constant.DATA_INTEFACE_DATEFORMATE_MONTH);
				end = DateUtils.formatStrToDate(endStr, Constant.DATA_INTEFACE_DATEFORMATE_MONTH);
				end = DateUtils.getLastMonthByDate(end, 1);
			}
		}
		map.put("start", start);
		map.put("end", end);
		map.put("warnType", BusinessConstants.Remind.SPJGYJ.toString());
		return map;
	}

}
