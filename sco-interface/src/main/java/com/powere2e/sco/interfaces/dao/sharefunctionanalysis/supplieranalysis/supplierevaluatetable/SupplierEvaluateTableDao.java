package com.powere2e.sco.interfaces.dao.sharefunctionanalysis.supplieranalysis.supplierevaluatetable;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.masterdata.Supplier;
import com.powere2e.sco.model.sharefunctionanalysis.supplieranalysis.supplierevaluatetable.SupplierEvaluateScore;

/**
 * 供应商考评表DAO接口
 * 
 * @author caoliqiang
 * @version 1.0
 * @since 2015年5月12日
 */
public interface SupplierEvaluateTableDao extends Dao {
	/**
	 * 查询考评表创建人
	 * 
	 * @param id
	 * @return
	 */
	public List<String> findByUserId(Map<String, Object> id);

	/**
	 * 根据供应商编号查询供应商信息
	 */
	public List<SupplierEvaluateScore> listSupplierCode(Map<String, Object> map);

	/**
	 * 根据考评表编号查询考评项编号
	 */
	public List<String> listSupplierEvaluateItem(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 供应商考评表查询
	 *
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回供应商考评表列表
	 */
	public List<SupplierEvaluateScore> listSupplierEvaluateTable(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 查询供应商
	 * 
	 * @return
	 */
	public List<Supplier> listSupplier(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 根据ID号加载一个供应商考评表
	 *
	 * @param map
	 *
	 * @return
	 */
	public SupplierEvaluateScore loadSupplierEvaluateTable(Map<String, Object> map);

	/**
	 * 添加供应商考评
	 * 
	 * @param map
	 */
	public void insertSupplierEvaluate(Map<String, Object> map);

	/**
	 * 添加供应商考评表
	 *
	 * @param map
	 *
	 */
	public void insertSupplierEvaluateTable(Map<String, Object> map);

	/**
	 * 查询考评表详情
	 * 
	 * @param map
	 * @return
	 */
	public List<SupplierEvaluateScore> selectSupplierEvaluateScoreByEvaluateTableCode(Map<String, Object> map);

	/**
	 * 给供应商考评表打分
	 * 
	 * @param map
	 */
	public void updateSupplierEvaluateScoreByEvaluateItemCode(Map<String, Object> map);

	/**
	 * 查询考评权重总分
	 * 
	 * @param map
	 * @return
	 */
	public SupplierEvaluateScore selectSumScore(Map<String, Object> map);
}
