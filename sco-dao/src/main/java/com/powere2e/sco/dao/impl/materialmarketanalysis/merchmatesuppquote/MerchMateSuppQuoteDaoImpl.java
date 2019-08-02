package com.powere2e.sco.dao.impl.materialmarketanalysis.merchmatesuppquote;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.materialmarketanalysis.merchmatesuppquote.MerchMateSuppQuoteDao;
import com.powere2e.sco.model.datamaintenance.purchaserdata.relevancematerialandwebsite.RelevanceMaterialAndWebsite;
import com.powere2e.sco.model.materialmarketanalysis.merchmatesuppquote.MerchMateSuppQuote;

/**
 * 商品原料供应商报价 Dao接口的实现
 * 
 * @author Gavillen.Zhou
 * @since 2015年7月30日
 * @version 1.0
 */
public class MerchMateSuppQuoteDaoImpl extends DaoImpl implements
		MerchMateSuppQuoteDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7506261264364011150L;

	@Override
	public List<RelevanceMaterialAndWebsite> listMerchandise(
			Map<String, Object> map, PageInfo pageInfo) {
		return this.query(MerchMateSuppQuoteDao.class, "listMerchandise", map, pageInfo);
	}

	@Override
	public List<MerchMateSuppQuote> listMerMateHisPrice(Map<String, Object> map) {
		return this.query(MerchMateSuppQuoteDao.class, "listMerMateHisPrice", map, null);
	}

	@Override
	public MerchMateSuppQuote listAccAndIng(Map<String, Object> map) {
		MerchMateSuppQuote msq = (MerchMateSuppQuote) this.get(MerchMateSuppQuoteDao.class, "listAccAndIng", map);
		if (msq == null) msq = new MerchMateSuppQuote();
		return msq; 
	}

}
