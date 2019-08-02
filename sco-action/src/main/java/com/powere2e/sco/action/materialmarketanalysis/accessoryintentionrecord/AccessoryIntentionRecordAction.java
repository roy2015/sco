package com.powere2e.sco.action.materialmarketanalysis.accessoryintentionrecord;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.BusinessUtils;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.interfaces.service.materialmarketanalysis.accessoryintentionrecord.AccessoryIntentionRecordService;
import com.powere2e.sco.model.materialmarketanalysis.accessoryintentionrecord.AccessoryIntentionRecord;

/**
 * 辅料商品预警记录 WEB请求响应类
 * 
 * @author Gavillen.Zhou
 * @since 2015年8月13日
 * @version 1.0
 */
public class AccessoryIntentionRecordAction extends WorkAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2735453563022021637L;
	private AccessoryIntentionRecordService accIntenRecService;
	
	@Override
	protected void beforeBuild() {
		accIntenRecService = (AccessoryIntentionRecordService) ConfigFactory
				.getInstance().getBean("accIntenRecService");
	}

	/**
	 * 显示辅料商品预警记录主界面
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.materialmarketAnalysis.accessoryIntentionRecord")
	public void doShowAccessoryIntentionRecordMain() throws Exception {
		this.putObject("accDetTypeList", BusinessUtils.ACCESSORY_DETAIL_TYPE);
		Date[] dateArr = DateUtils.getRecentYearAndMonthByCurrent(3);//获取当前三个月其实日期
		this.putObject("startWarnDate", DateUtils.formatDateToStr(dateArr[0], Constant.DATA_INTEFACE_DATEFORMATE_MONTH));
		this.putObject("endWarnDate", DateUtils.formatDateToStr(dateArr[1], Constant.DATA_INTEFACE_DATEFORMATE_MONTH));
		this.forwardPage("sco/materialmarketanalysis/accessoryintentionrecord/accessoryIntentionRecordGrid.ftl");
	}

	/**
	 * 查询辅料商品预警记录
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.materialmarketAnalysis.accessoryIntentionRecord.listAccIntRec")
	public void doListAccessoryIntentionRecord() throws Exception {
		List<AccessoryIntentionRecord> list = this.accIntenRecService
				.listAccessoryIntentionRecord(this.getAccessoryIntention(),
						this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 获取页面参数
	 * 
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> getAccessoryIntention() throws Exception {
		AccessoryIntentionRecord accIntenRec = new AccessoryIntentionRecord();
		this.asBean(accIntenRec);
		Map<String, Object> map = accIntenRec.toMap();
		String firstIn = this.asString("firstIn");
		Date[] dateArr = null;
		Date start = null;
		Date end = null;
		if("true".equalsIgnoreCase(firstIn)) {//第一次进入页面，最近三个月
			dateArr = DateUtils.getRecentYearAndMonthByCurrent(3);
			start = dateArr[0];
			end = dateArr[1];
			map.put("recordType", "warn");
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
		map.put("warType", BusinessConstants.Remind.FLYXPJGYJ.toString());//预警类型(辅料意向品价格预警)
		return map;
	}

}
