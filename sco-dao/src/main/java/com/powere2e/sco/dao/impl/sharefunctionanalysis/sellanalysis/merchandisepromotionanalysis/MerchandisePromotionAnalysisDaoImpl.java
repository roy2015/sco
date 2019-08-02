package com.powere2e.sco.dao.impl.sharefunctionanalysis.sellanalysis.merchandisepromotionanalysis;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.sharefunctionanalysis.sellanalysis.merchandisepromotionanalysis.MerchandisePromotionAnalysisDao;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.merchandisepromotionanalysis.MerchandisePromotionAnalysis;

/**
 * 商品促销分析DAO接口的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月8日
 */
public class MerchandisePromotionAnalysisDaoImpl extends DaoImpl implements MerchandisePromotionAnalysisDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7211178178055872194L;

	/**
	 * 查询促销信息
	 */
	@Override
	public List<MerchandisePromotionAnalysis> listMerchandisePromotionAnalysis(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(MerchandisePromotionAnalysisDao.class, "searchMerchandisePromotionAnalysis", map, pageInfo);
	}

	/**
	 * 促销明细汇总
	 */
	@Override
	public List<MerchandisePromotionAnalysis> listMerchandisePromotionAnalysisDetail(Map<String, Object> map, PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return this.query(MerchandisePromotionAnalysisDao.class, "searchMerchandisePromotionAnalysisPool", map, pageInfo);
	}

	/**
	 * 促销明细列表
	 */
	@Override
	public List<MerchandisePromotionAnalysis> listMerchandisePromotionAnalysisDetailInfo(Map<String, Object> map, PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return this.query(MerchandisePromotionAnalysisDao.class, "searchMerchandisePromotionAnalysisPoolInfo", map, pageInfo);
	}

	/**
	 * 查看商品
	 */
	@Override
	public List<MerchandisePromotionAnalysis> listMerchandise(Map<String, Object> map, PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return this.query(MerchandisePromotionAnalysisDao.class, "searchMerchandise", map, pageInfo);
	}

	/**
	 * 查看商品详情
	 */
	@Override
	public List<MerchandisePromotionAnalysis> listMerchandiseInfo(Map<String, Object> map, PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return this.query(MerchandisePromotionAnalysisDao.class, "searchMerchandiseInfo", map, pageInfo);
	}
}