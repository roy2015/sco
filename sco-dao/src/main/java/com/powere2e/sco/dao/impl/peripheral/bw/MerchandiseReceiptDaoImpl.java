package com.powere2e.sco.dao.impl.peripheral.bw;

import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.peripheral.bw.MerchandiseReceiptDao;
/**
 * 商品收货单信息DAO接口的实现
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月19日
 */
public class MerchandiseReceiptDaoImpl extends DaoImpl implements MerchandiseReceiptDao {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7806682405323203822L;

	//添加
	@Override
	public void insertMerchandiseReceipt(Map<String, Object> map){
		this.insert(MerchandiseReceiptDao.class, "saveMerchandiseReceipt", map);
	}
	
}