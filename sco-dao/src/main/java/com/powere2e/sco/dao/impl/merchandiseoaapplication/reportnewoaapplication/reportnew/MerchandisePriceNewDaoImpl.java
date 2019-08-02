package com.powere2e.sco.dao.impl.merchandiseoaapplication.reportnewoaapplication.reportnew;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportnewoaapplication.reportnew.MerchandisePriceNewDao;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.MerchandiseMaterial;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.reportnew.MerchandisePriceNew;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.reportnew.SameMerchandiseNew;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.reportnew.SellAnticipatedNew;

/**
 * 商品价格(新品引进)DAO接口的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年4月27日
 */
public class MerchandisePriceNewDaoImpl extends DaoImpl implements MerchandisePriceNewDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4876641452207097376L;

	// 查询
	@Override
	public List<MerchandisePriceNew> listMerchandisePriceNew(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return this.query(MerchandisePriceNewDao.class, "searchMerchandisePriceNew", map, pageInfo);
	}

	// 添加
	@Override
	public void insertMerchandisePriceNew(Map<String, Object> map) throws Exception {
		this.insert(MerchandisePriceNewDao.class, "saveMerchandisePriceNew", map);
	}

	// 删除
	@Override
	public void deleteMerchandisePriceNew(Map<String, Object> map) throws Exception {
		this.delete(MerchandisePriceNewDao.class, "deleteMerchandisePriceNew", map);
	}

	// 修改
	@Override
	public void updateMerchandisePriceNew(Map<String, Object> map) throws Exception {
		this.update(MerchandisePriceNewDao.class, "updateMerchandisePriceNew", map);
	}

	// 装载一个商品价格(新品引进)
	@Override
	public MerchandisePriceNew loadMerchandisePriceNew(Map<String, Object> map) throws Exception {
		return (MerchandisePriceNew) this.get(MerchandisePriceNewDao.class, "searchMerchandisePriceNew", map);
	}

	// 根据申请单号删除商品价格
	@Override
	public void deletePriceNewByApplicationCode(Map<String, Object> map) throws Exception {
		this.delete(MerchandisePriceNewDao.class, "deletePriceNewByApplicationCode", map);
	}

	// 相同商品模块
	// 查询
	@Override
	public List<SameMerchandiseNew> listSameMerchandiseNew(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return this.query(MerchandisePriceNewDao.class, "searchSameMerchandiseNew", map, pageInfo);
	}

	// 添加
	@Override
	public void insertSameMerchandiseNew(Map<String, Object> map) throws Exception {
		this.insert(MerchandisePriceNewDao.class, "saveSameMerchandiseNew", map);
	}

	// 删除
	@Override
	public void deleteSameMerchandiseNew(Map<String, Object> map) throws Exception {
		this.delete(MerchandisePriceNewDao.class, "deleteSameMerchandiseNew", map);
	}

	// 修改
	@Override
	public void updateSameMerchandiseNew(Map<String, Object> map) throws Exception {
		this.update(MerchandisePriceNewDao.class, "updateSameMerchandiseNew", map);
	}

	// 装载一个同类商品市场零售价(新品引进)
	@Override
	public SameMerchandiseNew loadSameMerchandiseNew(Map<String, Object> map) throws Exception {
		return (SameMerchandiseNew) this.get(MerchandisePriceNewDao.class, "searchSameMerchandiseNew", map);
	}

	// 根据申请单号删除同类商品
	@Override
	public void deleteSameNewByApplicationCode(Map<String, Object> map) throws Exception {
		this.delete(MerchandisePriceNewDao.class, "deleteSameNewByApplicationCode", map);
	}

	// 销售预计模块
	// 查询
	@Override
	public List<SellAnticipatedNew> listSellAnticipatedNew(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return this.query(MerchandisePriceNewDao.class, "searchSellAnticipatedNew", map, pageInfo);
	}

	// 添加
	@Override
	public void insertSellAnticipatedNew(Map<String, Object> map) throws Exception {
		this.insert(MerchandisePriceNewDao.class, "saveSellAnticipatedNew", map);
	}

	// 删除
	@Override
	public void deleteSellAnticipatedNew(Map<String, Object> map) throws Exception {
		this.delete(MerchandisePriceNewDao.class, "deleteSellAnticipatedNew", map);
	}

	// 修改
	@Override
	public void updateSellAnticipatedNew(Map<String, Object> map) throws Exception {
		this.update(MerchandisePriceNewDao.class, "updateSellAnticipatedNew", map);
	}

	// 装载一个商品销售预计(新品引进)
	@Override
	public SellAnticipatedNew loadSellAnticipatedNew(Map<String, Object> map) throws Exception {
		return (SellAnticipatedNew) this.get(MerchandisePriceNewDao.class, "searchSellAnticipatedNew", map);
	}

	// 根据申请单号删除销售预计
	@Override
	public void deleteSellNewByApplicationCode(Map<String, Object> map) throws Exception {
		this.delete(MerchandisePriceNewDao.class, "deleteSellNewByApplicationCode", map);
	}

	//根据申请单号删除关联分析报表(商品OA)(新品引进)
	@Override
	public void deleteAnalysisReportsMByApplicationCode(Map<String, Object> map) throws Exception {
		this.delete(MerchandisePriceNewDao.class, "deleteAnalysisReportsMByApplicationCode", map);
	}

	//删除报告检验标准(新品引进)
	@Override
	public void deleteCheckStandardNew(Map<String, Object> map) {
		this.delete(MerchandisePriceNewDao.class, "deleteCheckStandardNew", map);
	}
	
	//添加报告检验标准(新品引进)
	@Override
	public void insertCheckStandardNew(Map<String, Object> map) {
		this.insert(MerchandisePriceNewDao.class, "insertCheckStandardNew", map);
	}

	//商品原料(新品引进)查询
	@Override
	public List<MerchandiseMaterial> listMerchandiseMaterial(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(MerchandisePriceNewDao.class, "listMerchandiseMaterial", map, pageInfo);
	}

	//添加商品原料情况(新品引进)
	@Override
	public void insertMerchandiseMaterial(Map<String, Object> map) {
		this.insert(MerchandisePriceNewDao.class, "insertMerchandiseMaterial", map);
	}

	//删除商品原料情况(新品引进)
	@Override
	public void deleteMerchandiseMaterial(Map<String, Object> map) {
		this.delete(MerchandisePriceNewDao.class, "deleteMerchandiseMaterial", map);
	}

	//根据申请单号删除商品原料情况
	@Override
	public void deleteMerchandiseMaterialByCode(Map<String, Object> map) {
		this.delete(MerchandisePriceNewDao.class, "deleteMerchandiseMaterialByCode", map);
	}

}