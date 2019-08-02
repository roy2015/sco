package com.powere2e.sco.dao.impl.peripheral.sap;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.peripheral.sap.MerchandisePurchasePriceDao;
import com.powere2e.sco.model.peripheral.sap.MerchandisePurchasePrice;
/**
 * 商品进货价格信息DAO接口的实现
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月21日
 */
public class MerchandisePurchasePriceDaoImpl extends DaoImpl implements MerchandisePurchasePriceDao {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5118438095933250107L;

	//添加
	@Override
	public void insertMerchandisePurchasePrice(Map<String, Object> map){
		this.insert(MerchandisePurchasePriceDao.class, "saveMerchandisePurchasePrice", map);
	}

	@Override
	public void deleteMerchandisePurchasePrice() {
		this.delete(MerchandisePurchasePriceDao.class, "deleteMerchandisePurchasePrice", null);
	}

	@Override
	public List<MerchandisePurchasePrice> querySameMerchandisePurchasePrice(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.query(MerchandisePurchasePriceDao.class, "querySameMerchandisePurchasePrice", map, null);
	}

	@Override
	public void insertMerchandisePurchasePriceGroup(Map<String, Object> map) {
		// TODO Auto-generated method stub
		this.insert(MerchandisePurchasePriceDao.class, "saveMerchandisePurchasePriceGroup", map);
	}
	
}