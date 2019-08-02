package com.powere2e.sco.dao.impl.merchandisecostanalysis.totalcostanalogyanalysis;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.merchandisecostanalysis.accountingingredient.accounting.AccountingDao;
import com.powere2e.sco.interfaces.dao.merchandisecostanalysis.totalcostanalogyanalysis.TotalCostAnalogyAnalysisDao;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.Accounting;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingBo;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingNPackag;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingRegion;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingWPackag;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingWastage;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.ingredient.IngredientItem;
import com.powere2e.sco.model.merchandisecostanalysis.totalcostanalogyanalysis.MerchandiseContractPrice;

/**
 * 商品总成本类比分析
 * 
 * @author lipengjie
 * @version 1.0
 * @since 2015年5月7日
 */
public class TotalCostAnalogyAnalysisDaoImpl extends DaoImpl implements TotalCostAnalogyAnalysisDao {

	private static final long serialVersionUID = -6947048687928932804L;

	@Override
	public List<AccountingBo> listTotalCostAnalogyAnalysis(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(AccountingDao.class, "searchAccountingList", map, pageInfo);
	}

	@Override
	public List<AccountingBo> listReferMerchandise(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(TotalCostAnalogyAnalysisDao.class, "searchReferMerchandiseList", map, pageInfo);
	}

	@Override
	public List<AccountingBo> listReferIntention(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(TotalCostAnalogyAnalysisDao.class, "searchReferIntentionList", map, pageInfo);
	}
	
	@Override
	public List<IngredientItem> listMaterial(Map<String, Object> map) {
		return this.query(TotalCostAnalogyAnalysisDao.class, "materialList", map, null);
	}
	
	@Override
	public List<AccountingNPackag> listNpackag(Map<String, Object> map) {
		return this.query(TotalCostAnalogyAnalysisDao.class, "npackagList", map, null);
	}
	
	@Override
	public List<AccountingWPackag> listWpackag(Map<String, Object> map) {
		return this.query(TotalCostAnalogyAnalysisDao.class, "wpackagList", map, null);
	}

	
	@Override
	public List<AccountingWastage> listWastage(Map<String, Object> map) {
		return this.query(TotalCostAnalogyAnalysisDao.class, "wastageList", map, null);
	}
	
	@Override
	public Accounting loadFalseAccounting(Map<String, Object> map) {
		return (Accounting) this.get(TotalCostAnalogyAnalysisDao.class, "loadFalseAccounting", map);
	}
	
	@Override
	public List<AccountingRegion> listFalseAccountingRegion(Map<String, Object> map) {
		return this.query(TotalCostAnalogyAnalysisDao.class, "listFalseAccountingRegion", map, null);
	}

	@Override
	public String searchOAApplicationCode(Map<String, Object> map) {
		List<String> list = this.query(TotalCostAnalogyAnalysisDao.class, "searchOAApplicationCode", map,null);
		if (list == null) {
			return "";
		}else{
			if (list.size()>0) {
				return list.get(0);
			} else {
				return "";
			}
		}
	}
	
	@Override
	public List<MerchandiseContractPrice> searchMerchandiseContractPrice(Map<String, Object> map) {
		return this.query(TotalCostAnalogyAnalysisDao.class, "searchMerchandiseContractPrice", map, null);
	}

}
