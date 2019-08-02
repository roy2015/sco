package com.powere2e.sco.interfaces.service.sharefunctionanalysis.supplieranalysis.supplierevaluatetable;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.masterdata.Supplier;
import com.powere2e.sco.model.sharefunctionanalysis.supplieranalysis.supplierevaluatetable.SupplierEvaluateScore;

/**
 * 供应商考评表Service接口
 * 
 * @author caoliqiang
 * @version 1.0
 * @since 2015年5月12日
 */
public interface SupplierEvaluateTableService extends Service {
	/**
	 * 根据供应商编号查询供应商编号
	 */
	public List<SupplierEvaluateScore> listSupplierCode(Map<String, Object> map);

	/**
	 * 查询供应商
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<Supplier> listSupplier(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 发布供应商考评表
	 * 
	 * @param map
	 */
	public void insertSupplierEvaluateTable(List<SupplierEvaluateScore> list);

	/**
	 * 查询考评表模板
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<SupplierEvaluateScore> listSupplierEvaluateTemplate(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 查询考评表详情
	 * 
	 * @param map
	 * @return
	 */
	public List<SupplierEvaluateScore> loadSupplierEvaluateTableItem(Map<String, Object> map);

	/**
	 * 给供应商考评表打分
	 * 
	 * @param map
	 */
	public void updateSupplierEvaluateScoreByEvaluateItemCode(Map<String, Object> map);

	/**
	 * 导出考评表
	 * 
	 * @param map
	 * @param out
	 */
	public void exportSupplierEvaluateTable(Map<String, Object> map, ServletOutputStream out);
}
