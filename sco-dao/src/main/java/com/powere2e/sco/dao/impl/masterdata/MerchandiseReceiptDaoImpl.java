package com.powere2e.sco.dao.impl.masterdata;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.masterdata.MerchandiseReceiptDao;
import com.powere2e.sco.model.masterdata.MerchandiseReceipt;

/**
 * 商品收货单信息 DAO接口的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年5月13日
 */
public class MerchandiseReceiptDaoImpl extends DaoImpl implements
		MerchandiseReceiptDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1057212054763124681L;

	// 查询
	@Override
	public List<MerchandiseReceipt> listMerchandiseReceipt(
			Map<String, Object> map, PageInfo pageInfo) {
		return this.query(MerchandiseReceiptDao.class,
				"listMerchandiseReceipt", map, pageInfo);
	}

	@Override
	public List<MerchandiseReceipt> listSumTodayMerchandiseReceipt(
			Map<String, Object> map) {
		return this.query(MerchandiseReceiptDao.class,
				"listSumTodayMerchandiseReceipt", map, null);
	}
	
	// 添加
	@Override
	public void insertMerchandiseReceipt(Map<String, Object> map) {
		this.insert(MerchandiseReceiptDao.class, "insertMerchandiseReceipt", map);
	}

	// 删除
	@Override
	public void deleteMerchandiseReceipt(Map<String, Object> map) {
		this.delete(MerchandiseReceiptDao.class, "deleteMerchandiseReceipt",
				map);
	}

}