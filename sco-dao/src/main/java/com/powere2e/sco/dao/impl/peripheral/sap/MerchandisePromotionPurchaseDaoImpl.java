package com.powere2e.sco.dao.impl.peripheral.sap;

import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.peripheral.sap.MerchandisePromotionPurchaseDao;
/**
 * 商品促销进货价DAO接口的实现
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月21日
 */
public class MerchandisePromotionPurchaseDaoImpl extends DaoImpl implements MerchandisePromotionPurchaseDao {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8620114120605561660L;

	//添加
	@Override
	public void insertMerchandisePromotionPurchase(Map<String, Object> map){
		this.insert(MerchandisePromotionPurchaseDao.class, "saveMerchandisePromotionPurchase", map);
	}

	@Override
	public void deleteMerchandisePromotionPurchase() {
		this.delete(MerchandisePromotionPurchaseDao.class, "deleteMerchandisePromotionPurchase", null);
	}
	
}