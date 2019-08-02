package com.powere2e.sco.interfaces.service.sharefunctionanalysis.supplieranalysis.suppliersalescontribution;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.sharefunctionanalysis.supplieranalysis.suppliersalescontribution.SupplierSalesContribution;

/**
 * 供应商销售贡献度排行分析Service接口
 * 
 * @author Joyce.li
 * @since 2015年8月3日 下午3:00:41
 * @version 1.0
 */
public interface SupplierSalesContributionService extends Service {


	/**
	 * 查询供应商销售贡献度排行
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<SupplierSalesContribution> querySupplierSalesContribution(Map<String, Object> map, PageInfo pageInfo);

	
	/**
	 * 保存供应商销售贡献度排行为html页面
	 * 
	 * @param fileName
	 *            保存为该文件名
	 * @param paraMap
	 *            参数
	 * @return
	 * @throws Exception
	 */
	public String saveSalesContributionToHtml(String fileName, Map<String, Object> paraMap) throws Exception;

	/**
	 * 导出供应商贡献度排行
	 * 
	 * @param map
	 *            查询参数
	 * @param out
	 *            输出流
	 */
	public void exportSignedQtyExcel(Map<String, Object> map, ServletOutputStream out);
}