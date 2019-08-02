package com.powere2e.sco.action.materialmarketanalysis.materialwarningrecord;

import java.util.ArrayList;
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
import com.powere2e.sco.interfaces.service.materialmarketanalysis.materialwarning.MaterialWarningRecordService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.relevancematerialandwebsite.RelevanceMaterialAndWebsite;
import com.powere2e.sco.model.materialmarketanalysis.materialwarningrecord.MaterialWarningRecord;
import com.powere2e.security.model.Option;

/**
 * 商品原料预警记录 WEB请求响应类
 * 
 * @author Gavillen.Zhou
 * @since 2015年8月7日
 * @version 1.0
 */
public class MaterialWarningRecordAction extends WorkAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -763294702121622026L;
	private MaterialWarningRecordService materialWarningRecordService;

	@Override
	protected void beforeBuild() {
		materialWarningRecordService = (MaterialWarningRecordService) ConfigFactory
				.getInstance().getBean("materialWarningRecordService");
	}

	/**
	 * 显示商品原料预警主界面
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.materialmarketAnalysis.materialWarningRecord")
	public void doShowMaterialWarningRecordMain() throws Exception {
		this.forwardPage("sco/materialmarketanalysis/materialwarningrecord/materialwarningrecordGrid.ftl");
	}

	/**
	 * 预警类型
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListWarnTypeOption() throws Exception {
		List<Option> warnTypeList = new ArrayList<Option>();
		warnTypeList.add(new Option("", ""));
		warnTypeList.add(new Option(BusinessConstants.Remind.YLJGHBYJ.toString(), BusinessConstants.Remind.YLJGHBYJ.getRemindName()));
		warnTypeList.add(new Option(BusinessConstants.Remind.YLJGTBYJ.toString(), BusinessConstants.Remind.YLJGTBYJ.getRemindName()));
		this.forwardData(true, warnTypeList, null);
	}
	
	/**
	 * 查看预警记录
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.materialmarketAnalysis.materialWarningRecord.listMatWarnRec")
	public void doListMatWarnRec() throws Exception {
		List<MaterialWarningRecord> list = this.materialWarningRecordService
				.listMatWarnRecord(this.getMateWarnRec(), this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 显示关联的商品
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.materialmarketAnalysis.materialWarningRecord.listMatWarnRec")
	public void doListMerchandise() throws Exception {
		String materialCode = this.asString("materialCode");
		if (StringUtils.isBlank(materialCode)) return;
		List<RelevanceMaterialAndWebsite> list = this.materialWarningRecordService
				.listMerchandise(this.asMaterialWarningRecordBean().toMap());
		this.putObject("dataList", list);
		this.forwardPage("sco/materialmarketanalysis/materialwarningrecord/merchandiseForm.ftl");
	}
	
	/**
	 * 获取页面参数
	 * 
	 * @return 
	 * @throws Exception
	 */
	private Map<String, Object> getMateWarnRec() throws Exception {
		MaterialWarningRecord mateWarnRec = this.asMaterialWarningRecordBean();
		Map<String, Object> map = mateWarnRec.toMap();
		if (StringUtils.isBlank(mateWarnRec.getWarnType())) {
			map.put("warnTypeOption",
					"'".concat(BusinessConstants.Remind.YLJGHBYJ.toString())
							.concat("','")
							.concat(BusinessConstants.Remind.YLJGTBYJ
									.toString()).concat("'"));
		}
		Date[] dateArr = null;
		String firstIn = this.asString("firstIn");
		Date start = null;
		Date end = null;
		if ("true".equalsIgnoreCase(firstIn)) {// 第一次进入页面，最近三个月
			dateArr = DateUtils.getRecentYearAndMonthByCurrent(3);
			start = dateArr[0];
			end = dateArr[1];
		} else {// 页面是否传入参数
			String startStr = this.asString("startYear");
			String endStr = this.asString("endYear");
			if (startStr != null && endStr != null) {
				start = DateUtils.formatStrToDate(startStr, Constant.DATA_INTEFACE_DATEFORMATE_MONTH);
				end = DateUtils.formatStrToDate(endStr, Constant.DATA_INTEFACE_DATEFORMATE_MONTH);
				end = DateUtils.getLastMonthByDate(end, 1);
			}
		}
		map.put("start", start);
		map.put("end", end);
		return map;
	}

	/**
	 * 将页面参数映射到实体类中
	 * 
	 * @return
	 * @throws Exception
	 */
	private MaterialWarningRecord asMaterialWarningRecordBean()
			throws Exception {
		MaterialWarningRecord mateWarnRec = new MaterialWarningRecord();
		this.asBean(mateWarnRec);
		return mateWarnRec;
	}

}
