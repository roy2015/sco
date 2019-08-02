package com.powere2e.sco.service.impl.materialmarketanalysis.materialwarning;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.interfaces.dao.materialmarketanalysis.materialwarning.MaterialWarningRecordDao;
import com.powere2e.sco.interfaces.service.materialmarketanalysis.materialwarning.MaterialWarningRecordService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.relevancematerialandwebsite.RelevanceMaterialAndWebsite;
import com.powere2e.sco.model.materialmarketanalysis.materialwarningrecord.MaterialWarningRecord;

/**
 * 原料预警记录 业务类的实现
 * 
 * @author Gavillen.Zhou
 * @since 2015年8月7日
 * @version 1.0
 */
public class MaterialWarningRecordServiceImpl extends ServiceImpl implements
		MaterialWarningRecordService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4628719285444833466L;
	private MaterialWarningRecordDao materialWarningRecordDao;

	public MaterialWarningRecordDao getMaterialWarningRecordDao() {
		return materialWarningRecordDao;
	}

	public void setMaterialWarningRecordDao(
			MaterialWarningRecordDao materialWarningRecordDao) {
		this.materialWarningRecordDao = materialWarningRecordDao;
	}

	@Override
	public List<MaterialWarningRecord> listMatWarnRecord(
			Map<String, Object> map, PageInfo pageInfo) {
		List<MaterialWarningRecord> list = this.materialWarningRecordDao
				.listMatWarnRecord(map, pageInfo);
		/*Map<String, Object> paraMap = new HashMap<String, Object>();
		for (MaterialWarningRecord mwr : list) {
			this.setMomAvgMonthPrice(mwr, paraMap);//设置同环比月均价格
			this.setMomChangeChange(mwr);//设置同环比变化幅度
		}*/
		return list;
	}
	
	@Override
	public List<RelevanceMaterialAndWebsite> listMerchandise(Map<String, Object> map) {
		return this.materialWarningRecordDao.listMerchandise(map);
	}

	/**
	 * 设置同环比月均价格
	 * 
	 * @param mwr
	 *            当前实例
	 * @param paraMap
	 *            查询所需参数
	 */
	@SuppressWarnings("unused")
	private void setMomAvgMonthPrice(MaterialWarningRecord mwr, Map<String, Object> paraMap) {
		String warnType = mwr.getWarnType();// 预警类型
		Date created = mwr.getCreated();// 预警时间
		String materialCode = mwr.getMaterialCode();
		Date lastCreated = DateUtils.getLastMonthByDate(created, -12);// 去年今月
		Date start = null;
		Date end = null;
		if (BusinessConstants.Remind.YLJGHBYJ.toString().equalsIgnoreCase(warnType)) {
			start = DateUtils.getLastMonthByDate(created, -2);
			end = DateUtils.getLastMonthByDate(created, -1);
		} else if (BusinessConstants.Remind.YLJGTBYJ.toString().equalsIgnoreCase(warnType)) {
			start = DateUtils.getLastMonthByDate(lastCreated, -1);// 去年今月的上月
			end = lastCreated;
		}
		paraMap.put("materialCode", materialCode);
		paraMap.put("region", mwr.getRegion());
		paraMap.put("start", start);
		paraMap.put("end", end);

		BigDecimal momAvgMonthPrice = this.materialWarningRecordDao
				.calculateMomAvgMonthPrice(paraMap);
		mwr.setMomAvgMonthPrice(momAvgMonthPrice);
	}
	
	/**
	 * 设置同环比变化幅度
	 * 
	 * @param lasMonthAvgPrice
	 *            上月平均价格
	 * @param momAvgMonthPrice
	 *            同环比月均价格
	 */
	@SuppressWarnings("unused")
	private void setMomChangeChange(MaterialWarningRecord mwr) {
		BigDecimal lasMonthAvgPrice = mwr.getLasMonthAvgPrice();
		BigDecimal momAvgMonthPrice = mwr.getMomAvgMonthPrice();
		if (lasMonthAvgPrice == null || momAvgMonthPrice == null
				|| momAvgMonthPrice == BigDecimal.ZERO)
			return;
		mwr.setMomChangeChange((lasMonthAvgPrice.subtract(momAvgMonthPrice))
				.divide(momAvgMonthPrice, 4, BigDecimal.ROUND_HALF_UP));
	}

}
