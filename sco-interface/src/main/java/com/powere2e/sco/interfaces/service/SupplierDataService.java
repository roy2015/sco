package com.powere2e.sco.interfaces.service;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.security.model.Option;

/**
 * 查询供应商列表下拉框service
 *  @author Joyce.li
 *  @since 2015年3月20日 上午10:13:32
 *  @version 1.0
 */
public interface SupplierDataService extends Service {
	
	/**
	 * 供应商下拉框数据查询
	 */
	public List<Option> listAllSupplier(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 供应商下拉框数据查询
	 */
	public List<Option> listAllSupplierFl(Map<String, Object> map,PageInfo pageInfo);

	/**
	 * 查询主数据(SAP)中的所有供应商
	 * Gavillen --2015/04/12
	 */
	public List<Option> listMasterSupplier(Map<String,Object>map, PageInfo pageInfo);

}