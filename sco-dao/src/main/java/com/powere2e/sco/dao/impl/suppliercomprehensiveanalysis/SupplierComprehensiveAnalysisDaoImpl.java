package com.powere2e.sco.dao.impl.suppliercomprehensiveanalysis;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.suppliercomprehensiveanalysis.SupplierComprehensiveAnalysisDao;
import com.powere2e.sco.model.suppliercomprehensiveanalysis.SupplierComprehensiveAnalysis;
import com.powere2e.sco.model.suppliercomprehensiveanalysis.SupplierComprehensiveAnalysisForm;

/**
 * 采购委员会OA申请DAO接口的实现
 * 
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月20日
 */
public class SupplierComprehensiveAnalysisDaoImpl extends DaoImpl implements SupplierComprehensiveAnalysisDao {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1186509768848504553L;

	// 新品引进OA申请意向品列表
	@Override
	public List<SupplierComprehensiveAnalysis> listSupplierComprehensiveAnalysis(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(SupplierComprehensiveAnalysisDao.class, "listSupplierComprehensiveAnalysis", map, pageInfo);
	}

	@Override
	public List<SupplierComprehensiveAnalysisForm> listSupplierComprehensiveAnalysisForm(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(SupplierComprehensiveAnalysisDao.class, "listSupplierComprehensiveAnalysisForm", map, pageInfo);
	}
}