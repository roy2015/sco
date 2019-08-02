package com.powere2e.sco.service.impl.materialmarketanalysis.accessoryintentionrecord;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.materialmarketanalysis.accessoryintentionrecord.AccessoryIntentionRecordDao;
import com.powere2e.sco.interfaces.service.materialmarketanalysis.accessoryintentionrecord.AccessoryIntentionRecordService;
import com.powere2e.sco.model.materialmarketanalysis.accessoryintentionrecord.AccessoryIntentionRecord;

/**
 * 辅料商品预警记录
 * 
 * @author Gavillen.Zhou
 * @since 2015年8月13日
 * @version 1.0
 */
public class AccessoryIntentionRecordServiceImpl extends ServiceImpl implements
		AccessoryIntentionRecordService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5328761977260188072L;

	private AccessoryIntentionRecordDao accIntRecDao;

	public AccessoryIntentionRecordDao getAccIntRecDao() {
		return accIntRecDao;
	}

	public void setAccIntRecDao(AccessoryIntentionRecordDao accIntRecDao) {
		this.accIntRecDao = accIntRecDao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public List<AccessoryIntentionRecord> listAccessoryIntentionRecord(
			Map<String, Object> map, PageInfo pageInfo) {
		return accIntRecDao.listAccessoryIntentionRecord(map, pageInfo);
	}

}
