package com.powere2e.sco.dao.impl.accessoryintentioncostanalysis.historicalquotesupplier;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryintentioncostanalysis.historicalquotesupplier.HistoricalQuoteSupplierDao;
import com.powere2e.sco.model.accessoryintentioncostanalysis.historicalquotesupplier.HistoricalQuoteSupplier;
import com.powere2e.sco.model.accessoryintentioncostanalysis.historicalquotesupplier.HistoricalQuoteSupplierForm;
import com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis.QuotedDetailForm;
import com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis.QuotedForm;

/**
 * 采购委员会OA申请DAO接口的实现
 * 
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月20日
 */
public class HistoricalQuoteSupplierDaoImpl extends DaoImpl implements HistoricalQuoteSupplierDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2191963660799254704L;

	// 新品引进OA申请意向品列表
	@Override
	public List<HistoricalQuoteSupplier> listHistoricalQuoteSupplierIntentionApplication(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(HistoricalQuoteSupplierDao.class, "listHistoricalQuoteSupplierIntentionApplication", map, pageInfo);
	}

	// 根据报价单编号获得采购委员会竞价单OA申请意向品
	@Override
	public HistoricalQuoteSupplier loadHistoricalQuoteSupplierIntentionApplication(Map<String, Object> map) {
		return (HistoricalQuoteSupplier)this.get(HistoricalQuoteSupplierDao.class, "loadHistoricalQuoteSupplierIntentionApplication", map);
	}

	@Override
	public void updateIntentionSupplierAccessory(Map<String, Object> map) {
		this.update(HistoricalQuoteSupplierDao.class, "updateIntentionSupplierAccessory", map);
		
	}

	@Override
	public List<QuotedForm> listQuotedForm(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(HistoricalQuoteSupplierDao.class, "listQuotedForm", map, pageInfo);
	}

	@Override
	public List<QuotedDetailForm> listQuotedDetailForm(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(HistoricalQuoteSupplierDao.class, "listQuotedDetailForm", map, pageInfo);
	}

	@Override
	public List<HistoricalQuoteSupplierForm> listHistoricalQuoteSupplierForm(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(HistoricalQuoteSupplierDao.class, "listHistoricalQuoteSupplierForm", map, pageInfo);
	}
}