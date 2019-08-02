package com.powere2e.sco.interfaces.dao.merchandisecostanalysis.totalcostanalogyanalysis;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
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
public interface TotalCostAnalogyAnalysisDao extends Dao {

	/**
	 * 查询总成本类比数据(同核算表与投料表数据一样)
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页信息
	 * @return 所需要的数据
	 */
	public List<AccountingBo> listTotalCostAnalogyAnalysis(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 查询所有已通过审批的商品
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页信息
	 * @return 所需要的数据
	 */
	public List<AccountingBo> listReferMerchandise(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 查询所有未通过审批的商品意向品
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页信息
	 * @return 所需要的数据
	 */
	public List<AccountingBo> listReferIntention(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 
	 *查询本次申请商品对应商品对应核算/投料表中的所有原料列表(主料和辅料)
	 * 
	 * @param map
	 *            查询参数
	 * @return 所需要的数据
	 */
	public List<IngredientItem> listMaterial(Map<String, Object> map);
	
	/**
	 * 
	 * 查询本次申请商品对应核算/投料表中的所有内包装材料名称
	 * 
	 * @param map
	 *            查询参数
	 * @return 所需要的数据
	 */
	public List<AccountingNPackag> listNpackag(Map<String, Object> map);
	
	/**
	 * 
	 * 查询本次申请商品对应核算/投料表中的所有外包装材料名称
	 * 
	 * @param map
	 *            查询参数
	 * @return 所需要的数据
	 */
	public List<AccountingWPackag> listWpackag(Map<String, Object> map);
	
	/**
	 * 
	 * 查询本次申请商品对应核算/投料表中的所有损耗类型名称 
	 * 
	 * @param map
	 *            查询参数
	 * @return 所需要的数据
	 */
	public List<AccountingWastage> listWastage(Map<String, Object> map);
	
	/**
	 * 查询虚假的核算表对象,供添加无核算编号的商品做成本分析
	 * @param map
	 * @return
	 */
	public Accounting loadFalseAccounting(Map<String, Object> map);
	
	/**
	 *  查询对应商品的合作价格地区
	 * @param map 查询参数
	 * @return 地区集合
	 */
	public List<AccountingRegion> listFalseAccountingRegion(Map<String, Object> map);
	
	/**
	 * 查询对应商品的合作地区的申请单号
	 * @param map
	 * @return OA申请单号
	 */
	public String searchOAApplicationCode(Map<String, Object> map);
	
	/**
	 * 
	 * 查询对应商品的地区合作价格
	 * 
	 * @param map
	 *            查询参数
	 * @return 所需要的数据
	 */
	public List<MerchandiseContractPrice> searchMerchandiseContractPrice(Map<String, Object> map);
}
