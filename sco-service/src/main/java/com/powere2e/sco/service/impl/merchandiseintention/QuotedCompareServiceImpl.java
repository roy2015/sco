package com.powere2e.sco.service.impl.merchandiseintention;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.merchandiseintention.QuotedCompareDao;
import com.powere2e.sco.interfaces.service.merchandiseintention.MerchandiseIntentionService;
import com.powere2e.sco.interfaces.service.merchandiseintention.QuotedCompareService;
import com.powere2e.sco.model.merchandiseintention.MerchandiseIntention;
import com.powere2e.sco.model.merchandiseintention.MerchandiseQuoted;

/**
 * 商品意向品业务类的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年3月10日
 */
public class QuotedCompareServiceImpl extends ServiceImpl implements QuotedCompareService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1898963804653668435L;
	private QuotedCompareDao quotedCompareDao;

	public static MerchandiseIntentionService getInstance() {
		return (MerchandiseIntentionService) ConfigFactory.getInstance().getBean("merchandiseIntentionManager");
	}

	public static QuotedCompareService getQuotedCompareServiceInstance() {
		return (QuotedCompareService) ConfigFactory.getInstance().getBean("quotedCompareService");
	}
	
	/* ======================报价比价模块========================================= */
	@Override
	public List<MerchandiseQuoted> analysisQuoted(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return quotedCompareDao.analysisQuoted(map, pageInfo);
	}

	// 查看供应商历史报价
	@Override
	public List<MerchandiseQuoted> listHistoryQuoted(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return quotedCompareDao.listHistoryQuoted(map, pageInfo);
	}

	// 查看供应商针对某个意向品的最晚报价
	@Override
	public MerchandiseQuoted queryLastQuoted(Map<String, Object> map) throws Exception {
		return quotedCompareDao.queryLastQuoted(map);
	}

	// 查看对比结果
	@Override
	public List<MerchandiseQuoted> listCompareResult(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return quotedCompareDao.listCompareResult(map, pageInfo);
	}

	// 查看对比商品
	@Override
	public List<MerchandiseIntention> listCompareMerchandise(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return quotedCompareDao.listCompareMerchandise(map, pageInfo);
	}

	public QuotedCompareDao getQuotedCompareDao() {
		return quotedCompareDao;
	}

	public void setQuotedCompareDao(QuotedCompareDao quotedCompareDao) {
		this.quotedCompareDao = quotedCompareDao;
	}

}