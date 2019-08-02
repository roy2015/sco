package com.powere2e.sco.dao.impl.sharefunctionanalysis.supplieranalysis.supplierevaluatetable;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.sharefunctionanalysis.supplieranalysis.supplierevaluatetable.SupplierEvaluateTableDao;
import com.powere2e.sco.model.masterdata.Supplier;
import com.powere2e.sco.model.sharefunctionanalysis.supplieranalysis.supplierevaluatetable.SupplierEvaluateScore;

/**
 * 供应商考评表DAO接口的实现
 * 
 * @author caoliqiang
 * @version 1.0
 * @since 2015年5月12日
 */
public class SupplierEvaluateTableDaoImpl extends DaoImpl implements SupplierEvaluateTableDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3894636156577985180L;

	// 查询考评表创建人
	public List<String> findByUserId(Map<String, Object> id) {
		return this.query(SupplierEvaluateTableDao.class, "searchRealName", id, null);
	}

	// 查询考评模板
	@Override
	public List<SupplierEvaluateScore> listSupplierEvaluateTable(Map<String, Object> map, PageInfo pageInfo) {
		if (map.get("searchButton") != null) {
			List<SupplierEvaluateScore> list = this.query(SupplierEvaluateTableDao.class, "searchSupplierEvaluateTableDetails", map, pageInfo);
			return list;
		}
		return this.query(SupplierEvaluateTableDao.class, "searchSupplierEvaluateTable", map, pageInfo);
	}

	// 查询供应商
	@Override
	public List<Supplier> listSupplier(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(SupplierEvaluateTableDao.class, "searchSupplier", map, pageInfo);
	}

	// 发布供应商考评表
	@Override
	public void insertSupplierEvaluateTable(Map<String, Object> map) {
		this.insert(SupplierEvaluateTableDao.class, "saveSupplierEvaluateTable", map);
	}

	// 添加供应商考评
	@Override
	public void insertSupplierEvaluate(Map<String, Object> map) {
		this.insert(SupplierEvaluateTableDao.class, "saveSupplierEvaluate", map);
	}

	// 装载一个供应商考评表
	@Override
	public SupplierEvaluateScore loadSupplierEvaluateTable(Map<String, Object> map) {
		return (SupplierEvaluateScore) this.get(SupplierEvaluateTableDao.class, "searchSupplierEvaluateTable", map);
	}

	// 查询考评项编号
	@Override
	public List<String> listSupplierEvaluateItem(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(SupplierEvaluateTableDao.class, "selectEvaluateItemCode", map, pageInfo);
	}

	// 查询根据供应商编号查询供应商编号
	@Override
	public List<SupplierEvaluateScore> listSupplierCode(Map<String, Object> map) {
		return this.query(SupplierEvaluateTableDao.class, "selectSupplierCode", map, null);
	}

	// 根据考评表编号查询某个供应商考评表
	@Override
	public List<SupplierEvaluateScore> selectSupplierEvaluateScoreByEvaluateTableCode(Map<String, Object> map) {
		return this.query(SupplierEvaluateTableDao.class, "loadSupplierEvaluateTable", map, null);
	}

	// 查询考评权重总分
	@Override
	public SupplierEvaluateScore selectSumScore(Map<String, Object> map) {
		return (SupplierEvaluateScore) this.query(SupplierEvaluateTableDao.class, "searchSumScore", map, null).get(0);
	}

	// 给供应商考评表打分
	@Override
	public void updateSupplierEvaluateScoreByEvaluateItemCode(Map<String, Object> map) {
		this.update(SupplierEvaluateTableDao.class, "updateSupplierEvaluateScoreByEvaluateItemCode", map);
	}
}
