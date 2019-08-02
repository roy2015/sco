package com.powere2e.sco.interfaces.dao;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.security.model.Option;

/**
 * 	查询供应商列表下拉框dao
 *  @author Joyce.li
 *  @since 2015年3月20日 上午10:13:32
 *  @version 1.0
 */
public interface SupplierDataDao extends Dao {
	
	/**
	 * 供应商下拉框数据查询
	 */
	public List<Option> listAllSupplier(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 供应商下拉框数据查询
	 */
	public List<Option> listAllSupplierFl(Map<String, Object> map,PageInfo pageInfo);

	/**
	 * 查询主数据(SAP)中的所有供应商 Gavillen --2015/04/12
	 * 
	 * @param pageInfo
	 *            分页参数
	 */
	public List<Option> listMasterSupplier(Map<String,Object> map, PageInfo pageInfo);
	
}