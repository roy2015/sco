package com.powere2e.sco.interfaces.dao.sharefunctionanalysis.supplieranalysis.suppliersalescontribution;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.sharefunctionanalysis.supplieranalysis.suppliersalescontribution.SupplierSalesContribution;

/**
 * 供应商销售贡献度排行分析Dao接口
 * 
 * @author Joyce.li
 * @since 2015年8月3日 下午3:00:41
 * @version 1.0
 */
public interface SupplierSalesContributionDao extends Dao {

	/**
	 * 查询供应商销售贡献度
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<SupplierSalesContribution> querySupplierSalesContribution(Map<String, Object> map, PageInfo pageInfo);

}