package com.powere2e.sco.interfaces.dao.accessoryintentioncostanalysis.totalcostanalysis;


import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis.QuotedDetailForm;
import com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis.QuotedForm;
import com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis.Totalcostanalysis;
/**
 * 总成本分析DAO接口
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月20日
 */
public interface TotalcostanalysisDao extends Dao {
	
	/**
	 * 采购委员会竞价单OA申请意向品列表
	 */
	public List<Totalcostanalysis> listTotalcostanalysisIntentionApplication(Map<String, Object> map, PageInfo pageInfo);
	
	public List<QuotedForm> listQuotedForm(Map<String, Object> map, PageInfo pageInfo);
	
	public List<QuotedDetailForm> listQuotedDetailForm(Map<String, Object> map, PageInfo pageInfo);
	
	/**
	 * 根据报价单编号获得采购委员会竞价单OA申请意向品
	 */
	public Totalcostanalysis loadTotalcostanalysisIntentionApplication(Map<String, Object> map);
	/**
	 *更新关联表
	 */
	public void updateIntentionSupplierAccessory(Map<String, Object> map);
}