package com.powere2e.sco.dao.impl.materialmarketanalysis.accessoryintentionrecord;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.materialmarketanalysis.accessoryintentionrecord.AccessoryIntentionRecordDao;
import com.powere2e.sco.model.materialmarketanalysis.accessoryintentionrecord.AccessoryIntentionRecord;

/**
 * 辅料商品预警记录 Dao接口的实现
 * 
 * @author Gavillen.Zhou
 * @since 2015年8月13日
 * @version 1.0
 */
public class AccessoryIntentionRecordDaoImpl extends DaoImpl implements
		AccessoryIntentionRecordDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2979428269920256626L;

	@Override
	public List<AccessoryIntentionRecord> listAccessoryIntentionRecord(
			Map<String, Object> map, PageInfo pageInfo) {
		return this.query(AccessoryIntentionRecordDao.class,
				"listAccessoryIntentionRecord", map, pageInfo);
	}

}
