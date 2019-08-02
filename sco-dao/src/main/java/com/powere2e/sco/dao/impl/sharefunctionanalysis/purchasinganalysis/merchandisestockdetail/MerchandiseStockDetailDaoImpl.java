package com.powere2e.sco.dao.impl.sharefunctionanalysis.purchasinganalysis.merchandisestockdetail;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.sharefunctionanalysis.purchasinganalysis.merchandisestockdetail.MerchandiseStockDetailDao;
import com.powere2e.sco.model.sharefunctionanalysis.purchasinganalysis.merchandisestockdetail.MerchandiseStockDetail;
import com.powere2e.security.model.Option;

/**
 * 商品进货明细DAO接口的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月8日
 */
public class MerchandiseStockDetailDaoImpl extends DaoImpl implements MerchandiseStockDetailDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7211178178055872194L;


	/**
	 * 促销明细列表
	 */
	@Override
	public List<MerchandiseStockDetail> listMerchandiseStockDetailDetailInfo(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(MerchandiseStockDetailDao.class, "searchMerchandiseStockDetailInfo", map, pageInfo);
	}

	/**
	 * 查看商品
	 */
	@Override
	public List<MerchandiseStockDetail> listMerchandise(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(MerchandiseStockDetailDao.class, "searchMerchandise", map, pageInfo);
	}
	/**
	 * 查看库存地点名称
	 */
	@Override
	public List<Option> listRegion(Map<String, Object> map) {
		return this.query(MerchandiseStockDetailDao.class, "searchRegion", map, null);
	}
	/**
	 * 查看仓位
	 */
	@Override
	public List<Option> listWarehouse() {
		return this.query(MerchandiseStockDetailDao.class, "searchWarehouse", null, null);
	}
	/**
	 * 查看库存地点
	 */
	@Override
	public List<Option> listWarehouseSite() {
		return this.query(MerchandiseStockDetailDao.class, "searchWarehouseSite", null, null);
	}
}