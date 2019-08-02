package com.powere2e.sco.dao.impl.peripheral.sap;

import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.peripheral.sap.MerchandiseDetailTypeDao;

/**
 * 商品明细类dao实现
 * 
 * @author Joyce.li
 * @since 2015年8月18日 上午10:54:24
 * @version 1.0
 */
public class MerchandiseDetailTypeDaoImpl extends DaoImpl implements MerchandiseDetailTypeDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6269131400191752884L;

	@Override
	public void insertMerchandiseDetailType(Map<String, Object> map) {
		this.insert(MerchandiseDetailTypeDao.class, "saveMerchandiseDetailType", map);
	}
	
	//删除商品明细类
	@Override
	public void deleteMerchandiseDetailType() {
		this.delete(MerchandiseDetailTypeDao.class, "deleteMerchandiseDetailType", null);		
	}

}
