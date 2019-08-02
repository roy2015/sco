package com.powere2e.sco.dao.impl.materialmarketanalysis.merchandisewarningrecord;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.materialmarketanalysis.merchandisewarningrecord.MerchandiseWarningRecordDao;
import com.powere2e.sco.model.materialmarketanalysis.merchandisewarningrecord.MerchandiseWarningRecord;
import com.powere2e.security.model.Option;

/**
 * 商品预警记录 Dao接口的实现
 * 
 * @author Gavillen.Zhou
 * @since 2015年8月11日
 * @version 1.0
 */
public class MerchandiseWarningRecordDaoImpl extends DaoImpl implements
		MerchandiseWarningRecordDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -387806444694417413L;

	@Override
	public List<Option> listMaterialRegionOption() {
		return this.query(MerchandiseWarningRecordDao.class, "listMaterialRegionOption", null, null);
	}
	
	@Override
	public List<MerchandiseWarningRecord> listMerchandiseWarningRecrod(
			Map<String, Object> map, PageInfo pageInfo) {
		return this.query(MerchandiseWarningRecordDao.class,
				"listMerchandiseWarningRecrod", map, pageInfo);
	}

}
