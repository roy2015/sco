package com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.merchandisepromotionpurchasedata;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.datamaintenance.purchaserdata.merchandisepromotionpurchasedata.MerchandisePromotionPurchaseData;

/**
 * 商品促销进货价格维护Service接口
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年12月9日
 */
public interface MerchandisePromotionPurchaseDataService extends Service {
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
	 * 删除商品促销进货价格维护
	 * 
	 * @param map
	 */
	public void deleteMerchandisePromotionPurchaseData(Map<String, Object> map);

	/**
	 * 导入execl
	 * 
	 * @param file
	 * @return
	 */
	public String completeImportMerchandisePromotionPurchaseData(File file);
}