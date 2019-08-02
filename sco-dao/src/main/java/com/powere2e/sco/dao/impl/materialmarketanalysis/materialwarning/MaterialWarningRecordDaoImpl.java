package com.powere2e.sco.dao.impl.materialmarketanalysis.materialwarning;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.materialmarketanalysis.materialwarning.MaterialWarningRecordDao;
import com.powere2e.sco.model.datamaintenance.purchaserdata.relevancematerialandwebsite.RelevanceMaterialAndWebsite;
import com.powere2e.sco.model.materialmarketanalysis.materialwarningrecord.MaterialWarningRecord;

/**
 * 原料预警记录 Dao接口的实现
 * 
 * @author Gavillen.Zhou
 * @since 2015年8月7日
 * @version 1.0
 */
public class MaterialWarningRecordDaoImpl extends DaoImpl implements
		MaterialWarningRecordDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1311959639676730159L;

	@Override
	public List<MaterialWarningRecord> listMatWarnRecord(
			Map<String, Object> map, PageInfo pageInfo) {
		return this.query(MaterialWarningRecordDao.class, "listMatWarnRecord", map,
				pageInfo);
	}

	@Override
	public BigDecimal calculateMomAvgMonthPrice(Map<String, Object> map) {
		return (BigDecimal) this.get(MaterialWarningRecordDao.class,
				"calculateMomAvgMonthPrice", map);
	}

	@Override
	public List<RelevanceMaterialAndWebsite> listMerchandise(
			Map<String, Object> map) {
		return this.query(MaterialWarningRecordDao.class, "listMerchandise", map, null);
	}

}
