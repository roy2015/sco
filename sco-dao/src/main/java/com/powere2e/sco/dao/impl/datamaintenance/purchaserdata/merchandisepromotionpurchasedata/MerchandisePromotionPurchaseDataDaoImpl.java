package com.powere2e.sco.dao.impl.datamaintenance.purchaserdata.merchandisepromotionpurchasedata;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.merchandisepromotionpurchasedata.MerchandisePromotionPurchaseDataDao;
import com.powere2e.sco.model.datamaintenance.purchaserdata.merchandisepromotionpurchasedata.MerchandisePromotionPurchaseData;

/**
 * 商品促销进货价格维护DAO接口的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年12月9日
 */
public class MerchandisePromotionPurchaseDataDaoImpl extends DaoImpl implements MerchandisePromotionPurchaseDataDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -893518764119554112L;

	// 查询
	@Override
	public List<MerchandisePromotionPurchaseData> listMerchandisePromotionPurchaseData(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(MerchandisePromotionPurchaseDataDao.class, "searchMerchandisePromotionPurchaseData", map, pageInfo);
	}

	// 删除
	@Override
	public void deleteMerchandisePromotionPurchaseData(Map<String, Object> map) {
		this.delete(MerchandisePromotionPurchaseDataDao.class, "deleteMerchandisePromotionPurchaseData", map);
	}

	// 添加一批(100条)商品抽检不合格记录
	@Override
	public void insertMerchandisePromotionPurchaseData(Map<String, Object> map) {
		this.insert(MerchandisePromotionPurchaseDataDao.class, "saveMerchandisePromotionPurchaseData", map);
	}
}