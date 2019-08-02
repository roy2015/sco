package com.powere2e.sco.dao.impl.peripheral.sap;

import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.peripheral.sap.MerchandisePromotionSellDao;
/**
 * 商品促销销售价DAO接口的实现
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月21日
 */
public class MerchandisePromotionSellDaoImpl extends DaoImpl implements MerchandisePromotionSellDao {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4483313144407624491L;

	//添加
	@Override
	public void insertMerchandisePromotionSell(Map<String, Object> map){
		this.insert(MerchandisePromotionSellDao.class, "saveMerchandisePromotionSell", map);
	}

	@Override
	public void deleteMerchandisePromotionSell() {
		this.delete(MerchandisePromotionSellDao.class, "deleteMerchandisePromotionSell", null);
	}
	
}