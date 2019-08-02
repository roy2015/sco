package com.powere2e.sco.interfaces.service.suppliercomprehensiveanalysis;


import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.suppliercomprehensiveanalysis.SupplierComprehensiveAnalysis;
import com.powere2e.sco.model.suppliercomprehensiveanalysis.SupplierComprehensiveAnalysisForm;
import com.powere2e.sco.model.suppliercomprehensiveanalysis.SupplierComprehensiveAnalysisSet;
/**
 * 总成本分析Service接口
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月20日
 */
public interface SupplierComprehensiveAnalysisService extends Service {
	/**
	 * 采购委员会竞价单OA申请意向品列表
	 */
	public List<SupplierComprehensiveAnalysis> listSupplierComprehensiveAnalysis(Map<String, Object> map, PageInfo pageInfo);
	
	public List<SupplierComprehensiveAnalysisForm> listSupplierComprehensiveAnalysisForm(Map<String, Object> map, PageInfo pageInfo);
	/**
	 * 保存总成本类比结果
	 * 
	 * @param fileName
	 *            保存的文件名
	 * @param paraMap
	 *            查询条件
	 * @return 消息
	 */
	public String saveSearchDataForm(String fileName, Map<String, Object> paraMap);
	/**
	 * 导出excel
	 * 
	 * @param map
	 * 
	 */
	public void exportExcel(List<SupplierComprehensiveAnalysisForm> list,SupplierComprehensiveAnalysisSet supplierComprehensiveAnalysisSet, ServletOutputStream out);
}