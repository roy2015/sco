package com.powere2e.sco.dao.impl.masterdata;

import java.util.Map;
import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.masterdata.MerchandiseMarketDao;

/**
 * 商品上下市信息 DAO接口的实现
 * 
 * @author lipengjie
 * @version 1.0
 * @since 2016年3月30日
 */
public class MerchandiseMarketDaoImpl extends DaoImpl implements MerchandiseMarketDao {

	private static final long serialVersionUID = -6614017251230346528L;

	@Override
	public void insertMerchandiseMarket(Map<String, Object> map) {
		this.insert(MerchandiseMarketDao.class, "insertMerchandiseMarket" , map);
	}


}