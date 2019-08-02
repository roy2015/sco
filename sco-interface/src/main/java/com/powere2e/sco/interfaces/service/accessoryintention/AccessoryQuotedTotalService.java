package com.powere2e.sco.interfaces.service.accessoryintention;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedTotal;
/**
 * 辅料报价-总报价Service接口
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月8日
 */
public interface AccessoryQuotedTotalService extends Service {
	/**
	 * 辅料报价-总报价查询
	 
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回辅料报价-总报价列表
	 */
	public List<AccessoryQuotedTotal> listAccessoryQuotedTotal(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 根据ID号加载一个辅料报价-总报价
	 *
	 * @param map
	 *				
	 * @return
	 */
	public AccessoryQuotedTotal loadAccessoryQuotedTotal(String enquiryCode);
	/**
	 * 添加辅料报价-总报价
	 *
	 * @param map
	 *				
	 */
	public void insertAccessoryQuotedTotal(AccessoryQuotedTotal accessoryQuotedTotal);
	/**
	 * 删除辅料报价-总报价
	 *
	 * @param map 
	 *				必须参数id为要删除的辅料报价-总报价id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteAccessoryQuotedTotal(String enquiryCode);
	/**
	 * 删除辅料报价-总报价
	 *
	 * @param map 
	 *				必须参数id为要删除的辅料报价-总报价id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteAccessoryQuotedTotalFromQuotedCode(String quotedCode);
	/**
	 * 修改辅料报价-总报价
	 *
	 * @param map 
	 *				必须参数id为要修改辅料报价-总报价的id号，不能为数组
	 */
	public void updateAccessoryQuotedTotal(AccessoryQuotedTotal accessoryQuotedTotal);
}