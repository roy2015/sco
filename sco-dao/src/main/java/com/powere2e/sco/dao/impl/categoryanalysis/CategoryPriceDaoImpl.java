package com.powere2e.sco.dao.impl.categoryanalysis;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.categoryanalysis.CategoryPriceDao;
import com.powere2e.sco.model.categoryanalysis.CategoryPrice;

/**
 * 商品价格带 公共DAO接口的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年05月28日
 */
public class CategoryPriceDaoImpl extends DaoImpl implements CategoryPriceDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7577324068267964616L;

	@Override
	public List<CategoryPrice> listCollectData(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.query(CategoryPriceDao.class, "listCollectData", map, null);
	}

	@Override
	public List<CategoryPrice> listDetailData(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.query(CategoryPriceDao.class, "listDetailData", map, null);
	}

	@Override
	public List<CategoryPrice> listSoldQty(Map<String, Object> map) {
		return this.query(CategoryPriceDao.class, "listSoldQty", map, null);
	}
	
	@Override
	public List<CategoryPrice> listSoldPrice(Map<String, Object> map) {
		return this.query(CategoryPriceDao.class, "listSoldPrice", map, null);
	}

	@Override
	public List<CategoryPrice> listGrossFitQty(Map<String, Object> map) {
		return this.query(CategoryPriceDao.class, "listGrossFitQty", map, null);
	}
	
	@Override
	public String listPriceRegionDate(Map<String, Object> map) {
		return (String) this.get(CategoryPriceDao.class, "listPriceRegionDate", map);
	}

	@Override
	public List<String> listCalculateTotal(Map<String, Object> map) {
		return this.query(CategoryPriceDao.class, "listCalculateTotal", map, null);
	}

}