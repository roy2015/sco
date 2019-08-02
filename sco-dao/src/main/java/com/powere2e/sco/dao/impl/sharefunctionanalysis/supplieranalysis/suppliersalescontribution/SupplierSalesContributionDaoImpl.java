package com.powere2e.sco.dao.impl.sharefunctionanalysis.supplieranalysis.suppliersalescontribution;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.sharefunctionanalysis.supplieranalysis.suppliersalescontribution.SupplierSalesContributionDao;
import com.powere2e.sco.model.sharefunctionanalysis.supplieranalysis.suppliersalescontribution.SupplierSalesContribution;

/**
 * 供应商销售贡献度排行分析Dao实现
 * 
 * @author Joyce.li
 * @since 2015年8月3日 下午3:00:41
 * @version 1.0
 */
public class SupplierSalesContributionDaoImpl extends DaoImpl implements SupplierSalesContributionDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8563267107810000418L;

	@Override
	public List<SupplierSalesContribution> querySupplierSalesContribution(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(SupplierSalesContributionDao.class, "querySupplierSalesContribution", map, pageInfo);
	}

}