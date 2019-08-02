package com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.merchandisepromotionpurchasedata;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.datamaintenance.purchaserdata.merchandisepromotionpurchasedata.MerchandisePromotionPurchaseData;

/**
 * 商品促销进货价格维护DAO接口
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年12月9日
 */
public interface MerchandisePromotionPurchaseDataDao extends Dao {
	/**
	 * 商品促销进货价格维护查询
	 *
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回商品促销进货价格维护列表
	 */
	public List<MerchandisePromotionPurchaseData> listMerchandisePromotionPurchaseData(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 添加商品促销进货价格维护
	 *
	 * @param map
	 *
	 */
	public void insertMerchandisePromotionPurchaseData(Map<String, Object> map);

	/**
	 * 删除商品促销进货价格维护
	 *
	 * @param map
	 *            必须参数id为要删除的商品促销进货价格维护id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteMerchandisePromotionPurchaseData(Map<String, Object> map);
}