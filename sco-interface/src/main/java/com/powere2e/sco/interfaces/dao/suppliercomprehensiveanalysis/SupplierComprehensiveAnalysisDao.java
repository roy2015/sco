package com.powere2e.sco.interfaces.dao.suppliercomprehensiveanalysis;


import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.suppliercomprehensiveanalysis.SupplierComprehensiveAnalysis;
import com.powere2e.sco.model.suppliercomprehensiveanalysis.SupplierComprehensiveAnalysisForm;
/**
 * 总成本分析DAO接口
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月20日
 */
public interface SupplierComprehensiveAnalysisDao extends Dao {
	
	
	
	public List<SupplierComprehensiveAnalysis> listSupplierComprehensiveAnalysis(Map<String, Object> map, PageInfo pageInfo);
	public List<SupplierComprehensiveAnalysisForm> listSupplierComprehensiveAnalysisForm(Map<String, Object> map, PageInfo pageInfo);
}