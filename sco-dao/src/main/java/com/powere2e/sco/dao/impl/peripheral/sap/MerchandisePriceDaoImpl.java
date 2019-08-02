package com.powere2e.sco.dao.impl.peripheral.sap;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.peripheral.sap.MerchandisePriceDao;
import com.powere2e.sco.model.peripheral.sap.MerchandisePrice;
/**
 * 商品销售价格信息DAO接口的实现
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月21日
 */
public class MerchandisePriceDaoImpl extends DaoImpl implements MerchandisePriceDao {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1931890557533166154L;

	//添加
	@Override
	public void insertMerchandisePrice(Map<String, Object> map){
		this.insert(MerchandisePriceDao.class, "saveMerchandisePrice", map);
	}

	@Override
	public void deleteMerchandisePrice() {
		this.delete(MerchandisePriceDao.class, "deleteMerchandisePrice", null);
	}

	//根据一条数据，查询是否有相同的数据
	@Override
	public List<MerchandisePrice> querySameMerchandisePrice(Map<String, Object> map) {
		return this.query(MerchandisePriceDao.class, "querySameMerchandisePrice", map, null);
	}

	//添加商品销售价格的重复数据
	@Override
	public void insertMerchandisePriceGroup(Map<String, Object> map) {
		this.insert(MerchandisePriceDao.class, "saveMerchandisePriceGroup", map);
	}
	
}