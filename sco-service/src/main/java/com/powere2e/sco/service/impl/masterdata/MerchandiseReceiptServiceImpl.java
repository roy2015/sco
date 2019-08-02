package com.powere2e.sco.service.impl.masterdata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.masterdata.MerchandiseReceiptDao;
import com.powere2e.sco.interfaces.service.masterdata.MerchandiseReceiptService;
import com.powere2e.sco.model.masterdata.MerchandiseReceipt;

/**
 * 商品收货单信息 业务类的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年5月13日
 */
public class MerchandiseReceiptServiceImpl extends ServiceImpl implements
		MerchandiseReceiptService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3481724776636836485L;
	private MerchandiseReceiptDao merchandiseReceiptDao;

	public static MerchandiseReceiptService getInstance() {
		return (MerchandiseReceiptService) ConfigFactory.getInstance().getBean(
				"merchandiseReceiptService");
	}

	// 获得DAO实例
	public MerchandiseReceiptDao getMerchandiseReceiptDao() {
		return merchandiseReceiptDao;
	}

	// 设置DAO实例
	public void setMerchandiseReceiptDao(
			MerchandiseReceiptDao merchandiseReceiptDao) {
		this.merchandiseReceiptDao = merchandiseReceiptDao;
	}

	// 查询
	@Override
	public List<MerchandiseReceipt> listMerchandiseReceipt(
			Map<String, Object> map, PageInfo pageInfo) {
		return this.getMerchandiseReceiptDao().listMerchandiseReceipt(map,
				pageInfo);
	}

	@Override
	public List<MerchandiseReceipt> listSumTodayMerchandiseReceipt(Map<String, Object> map) {
		return this.getMerchandiseReceiptDao().listSumTodayMerchandiseReceipt(map);
	}
	
	// 添加
	@Override
	public void insertMerchandiseReceipt(MerchandiseReceipt merchandiseReceipt) {
		this.getMerchandiseReceiptDao().insertMerchandiseReceipt(
				merchandiseReceipt.toMap());
	}

	// 删除
	@Override
	public void deleteMerchandiseReceipt(String merchandiseCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("merchandiseCode", merchandiseCode);
		this.getMerchandiseReceiptDao().deleteMerchandiseReceipt(map);
	}

}