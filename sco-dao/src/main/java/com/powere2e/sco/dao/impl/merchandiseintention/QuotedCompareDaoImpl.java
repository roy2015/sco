package com.powere2e.sco.dao.impl.merchandiseintention;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.merchandiseintention.QuotedCompareDao;
import com.powere2e.sco.model.merchandiseintention.MerchandiseIntention;
import com.powere2e.sco.model.merchandiseintention.MerchandiseQuoted;

/**
 * 商品意向品DAO接口的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年3月10日
 */
public class QuotedCompareDaoImpl extends DaoImpl implements QuotedCompareDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8474257320189804458L;

	/* ======================报价比价模块===================================== */
	// 分析已有报价单
	@Override
	public List<MerchandiseQuoted> analysisQuoted(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return this.query(QuotedCompareDao.class, "analysisQuoted", map, pageInfo);
	}

	// 查看供应商历史报价
	@Override
	public List<MerchandiseQuoted> listHistoryQuoted(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return this.query(QuotedCompareDao.class, "listHistoryQuoted", map, pageInfo);
	}

	// 查看供应商针对某个意向品的最晚报价
	@Override
	public MerchandiseQuoted queryLastQuoted(Map<String, Object> map) throws Exception {
		return (MerchandiseQuoted) this.get(QuotedCompareDao.class, "queryLastQuoted", map);
	}

	// 查看对比结果
	@Override
	public List<MerchandiseQuoted> listCompareResult(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return this.query(QuotedCompareDao.class, "listCompareResult", map, pageInfo);
	}

	// 查看对比商品
	@Override
	public List<MerchandiseIntention> listCompareMerchandise(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		String querySql = "";
		// 根据用户选择参数，分别查询不同数据
		String somePart = (String) map.get("somePart");
		if ("1".equals(somePart)) {// 查询已引进的商品
			querySql = "listFormalCompareMerchandise";
		} else if ("2".equals(somePart)) {// 查询意向品
			querySql = "listIntentionCompareMerchandise";
		} else {// 查询已引进的商品和意向品
			querySql = "listAllCompareMerchandise";
		}
		return this.query(QuotedCompareDao.class, querySql, map, pageInfo);
	}

}