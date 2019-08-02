package com.powere2e.sco.service.impl.materialmarketanalysis.merchandisewarningrecord;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.materialmarketanalysis.merchandisewarningrecord.MerchandiseWarningRecordDao;
import com.powere2e.sco.interfaces.service.materialmarketanalysis.merchandisewarningrecord.MerchandiseWarningRecordService;
import com.powere2e.sco.model.materialmarketanalysis.merchandisewarningrecord.MerchandiseWarningRecord;
import com.powere2e.security.model.Option;

/**
 * 商品预警记录 Service接口实现
 * 
 * @author Gavillen.Zhou
 * @since 2015年8月11日
 * @version 1.0
 */
public class MerchandiseWarningRecordServiceImpl extends ServiceImpl implements
		MerchandiseWarningRecordService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2030399895587240189L;
	private MerchandiseWarningRecordDao merchandiseWarningRecordDao;

	public MerchandiseWarningRecordDao getMerchandiseWarningRecordDao() {
		return merchandiseWarningRecordDao;
	}

	public void setMerchandiseWarningRecordDao(
			MerchandiseWarningRecordDao merchandiseWarningRecordDao) {
		this.merchandiseWarningRecordDao = merchandiseWarningRecordDao;
	}

	@Override
	public List<Option> listMaterialRegionOption() {
		return this.merchandiseWarningRecordDao.listMaterialRegionOption();
	}
	
	@Override
	public List<MerchandiseWarningRecord> listMerchandiseWarningRecrod(
			Map<String, Object> map, PageInfo pageInfo) {
		return merchandiseWarningRecordDao.listMerchandiseWarningRecrod(map, pageInfo);
	}

}
